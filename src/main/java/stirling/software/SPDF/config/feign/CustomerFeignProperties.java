package stirling.software.SPDF.config.feign;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;

import lombok.Data;

/**
 * @author：xp
 * @date：2024/7/13 16:07
 */
@Data
@Configuration
@ConfigurationProperties(prefix = "customer.feign")
public class CustomerFeignProperties {

    private String serviceId;

    private String host;

    private Integer port;

    private Boolean secure;

    private String uri;
}
