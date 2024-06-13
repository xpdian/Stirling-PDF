package stirling.software.SPDF.constants;

import java.util.concurrent.TimeUnit;

/**
 * @author 29443
 * @version 1.0
 * @date 2022/4/21
 */
public class RedisKeyExpireConstants {

    /** 验证码有效时长 */
    public static final Long CAPTCHA_EXPIRE_TIME = 2L;

    /** 验证码有效时长单位 */
    public static final TimeUnit CAPTCHA_TIME_UNIT = TimeUnit.MINUTES;
}
