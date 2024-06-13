package stirling.software.SPDF.config.security.handler.exception;

import java.io.IOException;

import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.security.web.access.AccessDeniedHandler;
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
public class MyAccessDeniedHandler implements AccessDeniedHandler {
    @Override
    public void handle(
            HttpServletRequest request,
            HttpServletResponse response,
            AccessDeniedException accessDeniedException)
            throws IOException, ServletException {
        Result result = Result.error(HttpStatusConstants.UNAUTHORIZED);
        result.put("msg", "权限不足");
        String s = new ObjectMapper().writeValueAsString(result);
        response.setCharacterEncoding("utf-8");
        response.getWriter().print(s);
        response.setStatus(HttpStatus.FORBIDDEN.value());
        response.setContentType(MediaType.APPLICATION_JSON_VALUE);
    }
}
