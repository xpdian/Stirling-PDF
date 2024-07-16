package stirling.software.SPDF.exception;

import java.util.List;
import java.util.stream.Collectors;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.web.HttpRequestMethodNotSupportedException;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import com.auth0.jwt.exceptions.JWTVerificationException;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

import jakarta.servlet.http.HttpServletRequest;
import stirling.software.SPDF.domain.Result;

/**
 * @author 29443
 * @date 2022/4/9
 */
@RestControllerAdvice
public class GlobalExceptionHandler {

    public static final Logger log = LoggerFactory.getLogger(GlobalExceptionHandler.class);

    /** 公司项目的包结构，用于缩短错误日志的长度 */
    private static final String COMPANY_PACKAGE = "com.monkeylessey.";

    /**
     * 处理业务逻辑抛出的异常
     *
     * @param e
     * @param request
     * @return
     */
    @ExceptionHandler(BaseException.class)
    public Result serviceExceptionHandle(BaseException e, HttpServletRequest request) {
        this.printExceptionLocation(e, request, e.getMsg());
        Integer code = e.getCode();
        return code == null
                ? Result.error(HttpStatus.INTERNAL_SERVER_ERROR.value(), e.getMsg())
                : Result.error(e.getCode(), e.getMsg());
    }

    /**
     * token异常
     *
     * @param e
     * @param request
     * @return
     */
    @ExceptionHandler(JWTVerificationException.class)
    public Result JWTVerificationExceptionHandler(
            JWTVerificationException e, HttpServletRequest request) {
        this.printExceptionLocation(e, request, "身份凭证错误");
        return Result.error(HttpStatus.FORBIDDEN.value(), "身份凭证错误");
    }

    /**
     * 处理非手动抛出的运行时异常
     *
     * @param e
     * @param request
     * @return
     */
    @ExceptionHandler(Exception.class)
    public Result runtimeExceptionHandle(Exception e, HttpServletRequest request) {
        String requestURI = request.getRequestURI();
        log.error("请求地址'{}',发生未知异常：{}", requestURI, e);
        this.printExceptionLocation(e, request, e.getMessage());
        return Result.error(HttpStatus.INTERNAL_SERVER_ERROR.value(), e.getMessage());
    }

    /**
     * 请求方式不支持异常
     *
     * @param e
     * @param request
     * @return
     */
    @ExceptionHandler(HttpRequestMethodNotSupportedException.class)
    public Result handleHttpRequestMethodNotSupported(
            HttpRequestMethodNotSupportedException e, HttpServletRequest request) {
        String errMSg = String.format("不支持%s请求", e.getMethod());
        this.printExceptionLocation(e, request, errMSg);
        return Result.error(HttpStatus.INTERNAL_SERVER_ERROR.value(), e.getMessage());
    }

    /**
     * JSON传参数据校验异常
     *
     * @param e
     * @param request
     * @return
     * @throws JsonProcessingException
     */
    @ExceptionHandler(MethodArgumentNotValidException.class)
    public Result handleMethodArgumentNotValidException(
            MethodArgumentNotValidException e, HttpServletRequest request)
            throws JsonProcessingException {
        List<String> err =
                e.getBindingResult().getAllErrors().stream()
                        .map(error -> error.getDefaultMessage())
                        .collect(Collectors.toList());
        String s = new ObjectMapper().writeValueAsString(err);
        String errMsg = String.format("参数校验失败-%s", s);
        this.printExceptionLocation(e, request, errMsg);
        return Result.error(HttpStatus.INTERNAL_SERVER_ERROR.value(), s);
    }

    /**
     * Spring 的断言异常--参数异常
     *
     * @param e
     * @param request
     * @return
     */
    @ExceptionHandler(IllegalArgumentException.class)
    public Result handleIllegalArgumentException(
            IllegalArgumentException e, HttpServletRequest request) throws JsonProcessingException {
        String s = new ObjectMapper().writeValueAsString(e.getMessage());
        String errMsg = String.format("参数校验失败-%s", s);
        this.printExceptionLocation(e, request, errMsg);
        return Result.error(HttpStatus.INTERNAL_SERVER_ERROR.value(), s);
    }

    /**
     * 打印异常出现位置
     *
     * @param e
     */
    private void printExceptionLocation(Throwable e, HttpServletRequest request, String errMsg) {
        StackTraceElement stackTraceElement = e.getStackTrace()[0];
        String className =
                stackTraceElement.getClassName().contains(COMPANY_PACKAGE)
                        ? stackTraceElement.getClassName().split(COMPANY_PACKAGE)[1]
                        : stackTraceElement.getClassName();
        log.error(
                "接口：【{}】, 异常类：【{}】，方法：【{}】，所在行：【{}】, 错误信息：【{}】",
                request.getRequestURI(),
                className,
                stackTraceElement.getMethodName(),
                stackTraceElement.getLineNumber(),
                errMsg);
    }
}
