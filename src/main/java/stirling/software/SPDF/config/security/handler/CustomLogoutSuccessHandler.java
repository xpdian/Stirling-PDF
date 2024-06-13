package stirling.software.SPDF.config.security.handler;

import java.io.IOException;

import org.springframework.context.annotation.Configuration;
import org.springframework.http.MediaType;
import org.springframework.security.core.Authentication;
import org.springframework.security.web.authentication.logout.LogoutSuccessHandler;

import com.fasterxml.jackson.databind.ObjectMapper;

import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import stirling.software.SPDF.constants.RedisKeyPrefixConstants;
import stirling.software.SPDF.domain.Result;
import stirling.software.SPDF.domain.vo.SysUserVO;
import stirling.software.SPDF.utils.RedisUtil;
import stirling.software.SPDF.utils.TokenUtil;

/**
 * @author xp
 * @data 2023/4/6
 */
@Configuration
public class CustomLogoutSuccessHandler implements LogoutSuccessHandler {

    private final RedisUtil redisUtil;
    private final TokenUtil tokenUtil;

    public CustomLogoutSuccessHandler(RedisUtil redisUtil, TokenUtil tokenUtil) {
        this.redisUtil = redisUtil;
        this.tokenUtil = tokenUtil;
    }

    @Override
    public void onLogoutSuccess(
            HttpServletRequest request, HttpServletResponse response, Authentication authentication)
            throws IOException, ServletException {
        SysUserVO user = tokenUtil.getCurrentUserInfo(request);
        // 清除redis中的token
        redisUtil.deleteKey(RedisKeyPrefixConstants.TOKEN_PREFIX + user.getId());
        ObjectMapper objectMapper = new ObjectMapper();
        Result result = Result.ok("操作成功");
        response.setCharacterEncoding("utf-8");
        response.getWriter().println(objectMapper.writeValueAsString(result));
        response.setContentType(MediaType.APPLICATION_JSON_VALUE);
    }
}
