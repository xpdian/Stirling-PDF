package stirling.software.SPDF.config.feign;

import org.springframework.context.annotation.Configuration;

import feign.Response;
import feign.codec.ErrorDecoder;
import stirling.software.SPDF.exception.BaseException;

/**
 * feign的错误解码器
 *
 * @author：xp
 * @date：2024/7/14 11:30
 */
@Configuration
public class FeignErrorDecoder implements ErrorDecoder {

    @Override
    public Exception decode(String methodKey, Response response) {
        String msg = "";
        switch (response.status()) {
            case 401:
                msg = "未登录，无法操作";
                break;
            case 403:
                msg = "无权限执行操作";
                break;
            case 500:
                msg = "无权限执行操作";
                break;
            default:
                msg = "系统异常";
                break;
        }
        return new BaseException(msg, response.status());
    }
}
