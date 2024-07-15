package stirling.software.SPDF.config.feign;

import org.springframework.cloud.openfeign.FeignClientsConfiguration;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import feign.codec.ErrorDecoder;

/**
 * 通过配置类设置异常处理器
 *
 * @author：xp
 * @date：2024/7/14 11:33
 */
@Configuration
public class CustomerFeignConfiguration extends FeignClientsConfiguration {

    @Bean
    public ErrorDecoder errorDecoder() {
        return new FeignErrorDecoder();
    }
}
