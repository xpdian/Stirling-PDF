package stirling.software.SPDF.aop;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.AfterReturning;
import org.aspectj.lang.annotation.Aspect;
import org.springframework.stereotype.Component;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import com.fasterxml.jackson.core.JsonProcessingException;

import jakarta.servlet.http.HttpServletRequest;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import stirling.software.SPDF.annotation.FileLog;
import stirling.software.SPDF.config.FileConfig;
import stirling.software.SPDF.domain.entity.SysFileLog;
import stirling.software.SPDF.domain.vo.FileHandlerResultVO;
import stirling.software.SPDF.service.SysFileLogService;
import stirling.software.SPDF.utils.SecurityUtil;

/**
 * 文件操作切面，保存文件操作
 *
 * @author：xp
 * @date：2024/6/16 20:33
 */
@Aspect
@Component
@Slf4j
@RequiredArgsConstructor
public class FileLogAOP {

    private final SysFileLogService fileLogService;
    private final FileConfig fileConfig;

    /**
     * @param joinPoint
     * @param log
     * @param result
     * @throws NoSuchMethodException
     * @annotation(log),处理作用于方法的注解
     */
    @AfterReturning(pointcut = "@annotation(log)", returning = "result")
    public void afterReturn(JoinPoint joinPoint, FileLog log, Object result)
            throws NoSuchMethodException, JsonProcessingException {
        handleLog(joinPoint, log, result);
    }

    /**
     * 保存文件操作日志
     *
     * @param joinPoint 切点（方法）
     * @param log 日志注解
     * @param result 文件处理统一结果
     * @throws JsonProcessingException
     */
    protected void handleLog(JoinPoint joinPoint, FileLog log, Object result)
            throws JsonProcessingException {
        FileHandlerResultVO fileResult = (FileHandlerResultVO) result;
        String filePath = fileResult.getTmpFilePath();

        SysFileLog opLog = new SysFileLog();
        // 文件大小
        opLog.setFileSize(fileResult.getFileSize());
        // 文件名
        opLog.setFileName(fileResult.getFileName());
        // 文件key
        int startIndex =
                filePath.indexOf(fileConfig.getPath()) == -1
                        ? 0
                        : filePath.indexOf(fileConfig.getPath());
        opLog.setFileKey(filePath.substring(startIndex, filePath.length() - 1));
        // 设置请求发起人
        opLog.setUserId(SecurityUtil.getCurrentUserId());
        // 获取操作
        opLog.setOpContent(log.opContent());
        // 获取api
        ServletRequestAttributes requset =
                (ServletRequestAttributes) RequestContextHolder.currentRequestAttributes();
        HttpServletRequest request = requset.getRequest();
        // 获取请求路径
        opLog.setApiUrl(request.getRequestURI());
        // 获取请求ip
        opLog.setRequestIp(request.getRemoteAddr());

        if (!fileLogService.save(opLog)) {
            throw new RuntimeException("系统繁忙，请稍后再试");
        }
    }
}
