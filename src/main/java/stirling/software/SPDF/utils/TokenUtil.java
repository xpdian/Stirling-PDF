package stirling.software.SPDF.utils;

import java.util.Calendar;
import java.util.Date;
import java.util.concurrent.TimeUnit;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import com.auth0.jwt.JWT;
import com.auth0.jwt.JWTCreator;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.exceptions.JWTDecodeException;
import com.auth0.jwt.interfaces.DecodedJWT;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

import jakarta.servlet.http.HttpServletRequest;
import stirling.software.SPDF.constants.RedisKeyPrefixConstants;
import stirling.software.SPDF.domain.vo.LoginUser;
import stirling.software.SPDF.domain.vo.SysUserVO;

/**
 * @author 29443
 * @date 2022/4/16
 */
@Component
public class TokenUtil {

    @Value("${token.header}")
    private String header;

    @Value("${token.secret}")
    private String secret;

    @Value("${token.expire}")
    private int expire = 24 * 60;

    /** 当 */
    private static final long EFFECTIVE_TIME_TO_REFRESH = 30 * 60 * 1000L;

    private final RedisUtil redisUtil;

    public TokenUtil(RedisUtil redisUtil) {
        this.redisUtil = redisUtil;
    }

    /**
     * 创建token
     *
     * @param loginUser
     * @return
     */
    public String createToken(LoginUser loginUser) throws JsonProcessingException {
        SysUserVO user = loginUser.getUser();
        ObjectMapper objectMapper = new ObjectMapper();

        // 添加荷载，即要保存到token的用户信息等
        JWTCreator.Builder builder = JWT.create();

        // 设置过期时间
        Calendar instance = Calendar.getInstance();
        instance.setTime(new Date());
        instance.add(Calendar.MINUTE, expire);

        // 返回token(将用户信息转为json字符串保存到token)
        String token =
                builder.withSubject(user.getUserName())
                        .withClaim("userInfo", objectMapper.writeValueAsString(user))
                        .sign(Algorithm.HMAC256(secret));
        // token 存入redis管理
        redisUtil.saveForValueWithExpire(
                RedisKeyPrefixConstants.TOKEN_PREFIX + user.getId(),
                token,
                (long) expire,
                TimeUnit.MINUTES);
        return token;
    }

    /**
     * 验证token,如果token非法，会抛出异常
     *
     * @param token
     */
    public void verifyToken(String token) {
        JWT.require(Algorithm.HMAC256(secret)).build().verify(token);
    }

    /**
     * 获取token中保存的信息（如果你保存了的话），token非法会抛出异常
     *
     * @param token
     * @return
     */
    public SysUserVO getCurrentUserInfo(String token) throws JsonProcessingException {
        ObjectMapper objectMapper = new ObjectMapper();
        DecodedJWT verify = JWT.require(Algorithm.HMAC256(secret)).build().verify(token);
        String userInfoString = verify.getClaim("userInfo").asString();
        SysUserVO loginUser = objectMapper.readValue(userInfoString, SysUserVO.class);
        return loginUser;
    }

    /**
     * 获取token中保存的信息（如果你保存了的话），token非法会抛出异常
     *
     * @param token
     * @return
     */
    public static SysUserVO getCurrentUserInfoStatic(String token, String secret)
            throws JsonProcessingException {
        ObjectMapper objectMapper = new ObjectMapper();
        DecodedJWT verify = JWT.require(Algorithm.HMAC256(secret)).build().verify(token);
        String userInfoString = verify.getClaim("userInfo").asString();
        SysUserVO loginUser = objectMapper.readValue(userInfoString, SysUserVO.class);
        return loginUser;
    }

    /**
     * 通过请求获取头部token
     *
     * @param request
     * @return
     */
    public SysUserVO getCurrentUserInfo(HttpServletRequest request) throws JsonProcessingException {
        String token = request.getHeader(this.header);
        ObjectMapper objectMapper = new ObjectMapper();
        DecodedJWT verify = JWT.require(Algorithm.HMAC256(secret)).build().verify(token);
        String userInfoString = verify.getClaim("userInfo").asString();
        SysUserVO loginUser = objectMapper.readValue(userInfoString, SysUserVO.class);
        return loginUser;
    }

    /**
     * 获取当前用户名
     *
     * @param token
     * @return
     * @throws JsonProcessingException
     */
    public String getCurrentUsername(String token) throws JsonProcessingException {
        return getCurrentUserInfo(token).getUserName();
    }

    /**
     * 获取token过期时间
     *
     * @param token
     * @return 过期时间
     */
    public Date getExpireDate(String token) {
        return JWT.decode(token).getExpiresAt();
    }

    /**
     * 验证token是否过期
     *
     * @param token
     * @return true表示过期，false没过期
     */
    public boolean isExpire(String token) {
        try {
            Date expiresAt = JWT.decode(token).getExpiresAt();
            Date now = new Date();
            if (expiresAt.after(now)) {
                return false;
            }
        } catch (JWTDecodeException e) {
            return true;
        }
        return true;
    }

    /**
     * 检查是否需要重建token，过期时间还剩不到1小时
     *
     * @param expireDate
     * @param now
     * @return true代表重新创建
     */
    public boolean enableRefreshToken(Date expireDate, Date now) {
        return (expireDate.getTime() - now.getTime()) / (60 * 60 * 1000) < 1;
    }

    /**
     * 获取主体，即用户名
     *
     * @param token
     * @return
     */
    public String getUserName(String token) {
        return JWT.decode(token).getSubject();
    }

    public String getHeader() {
        return header;
    }

    public int getExpire() {
        return expire;
    }
}
