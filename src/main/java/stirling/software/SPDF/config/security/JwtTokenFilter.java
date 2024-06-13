package stirling.software.SPDF.config.security;

import java.io.IOException;
import java.util.Date;
import java.util.concurrent.TimeUnit;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.filter.OncePerRequestFilter;

import com.auth0.jwt.exceptions.JWTVerificationException;
import com.fasterxml.jackson.databind.ObjectMapper;

import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import stirling.software.SPDF.config.security.service.MyUserDetailsService;
import stirling.software.SPDF.constants.HttpStatusConstants;
import stirling.software.SPDF.constants.RedisKeyPrefixConstants;
import stirling.software.SPDF.domain.Result;
import stirling.software.SPDF.domain.vo.SysUserVO;
import stirling.software.SPDF.utils.RedisUtil;
import stirling.software.SPDF.utils.TokenUtil;

/**
 * @author 29443
 * @date 2022/4/4
 */
public class JwtTokenFilter extends OncePerRequestFilter {

    @Autowired private TokenUtil tokenUtil;

    @Autowired private RedisUtil redisUtil;

    @Autowired private MyUserDetailsService userDetailsService;

    @Override
    protected void doFilterInternal(
            HttpServletRequest request, HttpServletResponse response, FilterChain chain)
            throws IOException, ServletException {

        // 获取请求头的token
        String token = request.getHeader(tokenUtil.getHeader());
        // token为空，有可能是访问放行或可匿名的资源，让它继续走
        if (StringUtils.isEmpty(token)) {
            chain.doFilter(request, response);
            return;
        }
        try {
            // 验证token合法性,并取出值
            tokenUtil.verifyToken(token);
            SysUserVO user = tokenUtil.getCurrentUserInfo(token);

            String key = RedisKeyPrefixConstants.TOKEN_PREFIX + user.getId();
            if (!redisUtil.hasKey(key)) {
                createResponse(response);
                return;
            } else if (token.equals(redisUtil.getValue(key, String.class))) {
                // 这里需要往SecurityContextHolder放入凭证信息，代表已经认证的用户
                UsernamePasswordAuthenticationToken authenticationToken =
                        new UsernamePasswordAuthenticationToken(
                                tokenUtil.getCurrentUserInfo(token),
                                null,
                                userDetailsService.getUserPermissions(user.getUserName()));
                SecurityContextHolder.getContext().setAuthentication(authenticationToken);
                chain.doFilter(request, response);
                // 检查是否需要刷新redis token过期时间（距离过期不到一小时）
                Date date = new Date();
                if ((redisUtil.getExpire(key) - date.getTime()) / 3600 < 1) {
                    redisUtil.saveForValueWithExpire(
                            key, token, (long) tokenUtil.getExpire(), TimeUnit.MINUTES);
                }
            } else {
                createResponse(response);
            }
            // 只捕获token验证异常
        } catch (JWTVerificationException e) {
            // token 无效
            Result error = Result.error(HttpStatusConstants.UNAUTHORIZED);
            error.put("msg", "Invalid Token");
            String s = new ObjectMapper().writeValueAsString(error);
            response.setCharacterEncoding("utf-8");
            response.getWriter().print(s);
            response.setStatus(org.springframework.http.HttpStatus.UNAUTHORIZED.value());
            response.setContentType(MediaType.APPLICATION_JSON_VALUE);
        }
    }

    /**
     * 创建token过期的响应
     *
     * @param response
     * @throws IOException
     */
    private void createResponse(HttpServletResponse response) throws IOException {
        // token 失效，请重新登录
        Result error = Result.error(HttpStatusConstants.UNAUTHORIZED);
        error.put("msg", "身份过期，请重新登录");
        String s = new ObjectMapper().writeValueAsString(error);
        response.setCharacterEncoding("utf-8");
        response.getWriter().print(s);
        response.setStatus(org.springframework.http.HttpStatus.UNAUTHORIZED.value());
        response.setContentType(MediaType.APPLICATION_JSON_VALUE);
    }
}
