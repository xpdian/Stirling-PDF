package stirling.software.SPDF.constants;

/**
 * @author 29443
 * @date 2022/4/5 redis中的key或者key前缀
 */
public class RedisKeyPrefixConstants {

    /** 登录用户标识 */
    public static final String LOGIN_USER_INFO_PREFIX = "loginUser_";

    /** 验证码id标识 */
    public static final String CAPTCHA = "captcha_";

    /** oss临时访问令牌 */
    public static final String OSSTOKEN = "ossToken";

    /** oss RAM角色临时的access key id */
    public static final String OSSACCESSKEYID = "ossAccessKeyId";

    /** oss RAM角色临时的access key secret */
    public static final String OSSACCESSKEYSECRET = "ossAccessKeySecret";

    /** token存入redis的key前缀 */
    public static final String TOKEN_PREFIX = "user_token_";
}
