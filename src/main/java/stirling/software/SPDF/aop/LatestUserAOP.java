package stirling.software.SPDF.aop;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.AfterReturning;
import org.aspectj.lang.annotation.Aspect;
import org.springframework.stereotype.Component;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import com.fasterxml.jackson.core.JsonProcessingException;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import stirling.software.SPDF.annotation.Latest;
import stirling.software.SPDF.config.client.ClientProperties;
import stirling.software.SPDF.domain.dto.LatestDTO;
import stirling.software.SPDF.feign.UserFeign;

/**
 * @author：xp
 * @date：2024/7/21 10:34
 */
@Aspect
@Component
@Slf4j
@RequiredArgsConstructor
public class LatestUserAOP {

    private final UserFeign userFeign;
    private final ClientProperties clientProperties;

    @AfterReturning(pointcut = "@annotation(log)", returning = "result")
    public void afterReturn(JoinPoint joinPoint, Latest log, Object result)
            throws NoSuchMethodException, JsonProcessingException {
        handleLog(joinPoint, log, result);
    }

    /**
     * 保存最近操作
     *
     * @param joinPoint 切点（方法）
     * @param log 注解
     * @param result 文件处理统一结果
     * @throws JsonProcessingException
     */
    protected void handleLog(JoinPoint joinPoint, Latest log, Object result)
            throws JsonProcessingException {
        ServletRequestAttributes requset =
                (ServletRequestAttributes) RequestContextHolder.getRequestAttributes();
        String token = requset.getRequest().getHeader("Authentication");
        System.out.println(token);
        LatestDTO dto = new LatestDTO();
        dto.setClientId(clientProperties.getClientId());
        dto.setSecret(clientProperties.getSecret());
        dto.setOpName(log.opName());
        dto.setOpRouter(log.router());
        userFeign.latest(token, dto);
    }
}
