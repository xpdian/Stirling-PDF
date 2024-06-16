package stirling.software.SPDF.config.wx;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;

import lombok.Data;

/**
 * @author：xp
 * @date：2024/6/14 17:23
 */
@Data
@Configuration
@ConfigurationProperties(prefix = "wx")
public class WxLoginConfig {

    private String appId;

    private String appSecret;
}
