package stirling.software.SPDF.config.feign;

import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.client.serviceregistry.ServiceRegistry;
import org.springframework.context.annotation.Configuration;

import lombok.RequiredArgsConstructor;

/**
 * openfeign 注册自己的服务实例，不使用注册中心
 *
 * @author：xp
 * @date：2024/7/13 15:48
 */
@Configuration
@RequiredArgsConstructor
@EnableDiscoveryClient(autoRegister = false)
public class ServiceRegisterConfig {

    private final ServiceRegistry registry;
    private final SecurityServiceRegister securityServiceRegister;

    // called through some external process, such as an event or a custom actuator endpoint
    public void register() {
        this.registry.register(securityServiceRegister);
    }
}
