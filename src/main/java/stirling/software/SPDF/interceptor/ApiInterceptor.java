package stirling.software.SPDF.interceptor;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Lazy;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerInterceptor;

import com.fasterxml.jackson.databind.ObjectMapper;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import stirling.software.SPDF.config.client.ClientProperties;
import stirling.software.SPDF.constants.HttpStatusConstants;
import stirling.software.SPDF.domain.Result;
import stirling.software.SPDF.domain.dto.ClientInfoDTO;
import stirling.software.SPDF.feign.UserFeign;

/**
 * @author：xp
 * @date：2024/7/13 21:03
 */
@Component
public class ApiInterceptor implements HandlerInterceptor {

    @Autowired @Lazy private UserFeign userFeign;

    @Autowired private ClientProperties clientProperties;

    @Override
    public boolean preHandle(
            HttpServletRequest request, HttpServletResponse response, Object handler)
            throws Exception {
        // 校验权限
        ClientInfoDTO clientInfo = new ClientInfoDTO();
        clientInfo.setClientId(clientProperties.getClientId());
        clientInfo.setSecret(clientProperties.getSecret());
        Boolean can = userFeign.authVip(request.getHeader("Authentication"), clientInfo);
        if (can) {
            return HandlerInterceptor.super.preHandle(request, response, handler);
        } else {
            Result error = Result.error(HttpStatusConstants.NOT_VIP, "有相应会员才能执行此操作");
            String s = new ObjectMapper().writeValueAsString(error);
            response.setCharacterEncoding("utf-8");
            response.getWriter().print(s);
            response.setStatus(org.springframework.http.HttpStatus.UNAUTHORIZED.value());
            response.setContentType(MediaType.APPLICATION_OCTET_STREAM.getType());
            return false;
        }
    }
}
