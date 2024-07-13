package stirling.software.SPDF.config.client;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;

import lombok.Data;

/**
 * @author：xp
 * @date：2024/7/13 23:27
 */
@Data
@Configuration
@ConfigurationProperties(prefix = "client")
public class ClientProperties {

    /** 客户端ID */
    private String clientId;

    /** 密钥 */
    private String secret;
}
