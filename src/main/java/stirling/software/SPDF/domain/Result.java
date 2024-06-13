package stirling.software.SPDF.domain;

import java.util.HashMap;

import org.springframework.lang.NonNull;

/**
 * 新的响应类
 *
 * @author xp
 * @date 2022/12/2
 */
public class Result extends HashMap<String, Object> {

    private static final long serialVersionUID = 1L;

    private static final String TOTAL = "total";

    public static final String DATA = "data";

    private static final String MSG = "msg";

    private static final String CODE = "code";

    public Result() {
        put(MSG, "success");
        put(CODE, 200);
    }

    /**
     * 成功，啥也不做
     *
     * @return
     */
    public static Result ok() {
        return new Result();
    }

    /**
     * 成功，自定义提示信息
     *
     * @param msg
     * @return
     */
    public static Result ok(String msg) {
        Result result = new Result();
        result.put(MSG, msg);
        return result;
    }

    /**
     * 失败，啥也不做
     *
     * @return
     */
    public static Result error() {
        Result result = new Result();
        result.put(CODE, 500);
        result.put(MSG, "操作失败");
        return result;
    }

    /**
     * 失败，自定义错误提示
     *
     * @param msg
     * @return
     */
    public static Result error(String msg) {
        Result result = new Result();
        result.put(CODE, 500);
        result.put(MSG, msg);
        return result;
    }

    /**
     * 失败，自定义错误码
     *
     * @param code
     * @return
     */
    public static Result error(@NonNull Integer code) {
        Result result = new Result();
        result.put(CODE, code);
        result.put(MSG, "操作失败");
        return result;
    }

    /**
     * 失败，自定义错误码和错误提示
     *
     * @param code
     * @param msg
     * @return
     */
    public static Result error(@NonNull Integer code, String msg) {
        Result result = new Result();
        result.put(CODE, code);
        result.put(MSG, msg);
        return result;
    }

    /**
     * 添加数据
     *
     * @param data
     * @return
     */
    public Result data(Object data) {
        this.put(DATA, data);
        return this;
    }

    /**
     * 添加总条数
     *
     * @param total
     * @return
     */
    public Result total(long total) {
        this.put(TOTAL, total);
        return this;
    }

    @Override
    public Result put(String key, Object value) {
        super.put(key, value);
        return this;
    }
}
