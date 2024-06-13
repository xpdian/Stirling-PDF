package stirling.software.SPDF.config.security.handler.exception;

import java.io.IOException;

import org.springframework.http.MediaType;
import org.springframework.security.web.AuthenticationEntryPoint;
import org.springframework.stereotype.Component;

import com.fasterxml.jackson.databind.ObjectMapper;

import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import stirling.software.SPDF.constants.HttpStatusConstants;
import stirling.software.SPDF.domain.Result;

/**
 * @author 29443
 * @date 2022/4/16
 */
@Component
public class AuthenticationException implements AuthenticationEntryPoint {
    @Override
    public void commence(
            HttpServletRequest request,
            HttpServletResponse response,
            org.springframework.security.core.AuthenticationException authException)
            throws IOException, ServletException {

        Result result = Result.error(HttpStatusConstants.UNAUTHORIZED);
        result.put("msg", "认证失败");
        String s = new ObjectMapper().writeValueAsString(result);
        response.setCharacterEncoding("utf-8");
        response.getWriter().print(s);
        response.setStatus(HttpStatusConstants.UNAUTHORIZED);
        response.setContentType(MediaType.APPLICATION_JSON_VALUE);
    }
}
