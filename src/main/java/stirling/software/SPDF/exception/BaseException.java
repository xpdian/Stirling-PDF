package stirling.software.SPDF.exception;

/**
 * 基础非检查异常
 *
 * @author 29443
 * @version 1.0
 * @date 2022/4/25
 */
public class BaseException extends RuntimeException {

    private String msg;

    private Integer code;

    public BaseException(String msg, Throwable cause) {
        super(msg, cause);
    }

    public BaseException(String msg) {
        super(msg);
    }

    public BaseException(String msg, Integer code) {
        this.msg = msg;
        this.code = code;
    }

    public String getMsg() {
        return msg;
    }

    public Integer getCode() {
        return code;
    }
}
