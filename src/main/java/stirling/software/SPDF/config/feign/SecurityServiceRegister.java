package stirling.software.SPDF.config.feign;

import java.net.URI;
import java.util.Map;

import org.springframework.cloud.client.serviceregistry.Registration;
import org.springframework.stereotype.Component;

import lombok.RequiredArgsConstructor;

/**
 * @author：xp
 * @date：2024/7/13 15:52
 */
@Component
@RequiredArgsConstructor
public class SecurityServiceRegister implements Registration {

    private final CustomerFeignProperties feignProperties;

    /**
     * 服务ID，唯一即可
     *
     * @return
     */
    @Override
    public String getServiceId() {
        return feignProperties.getServiceId();
    }

    /**
     * 主机地址
     *
     * @return
     */
    @Override
    public String getHost() {
        return feignProperties.getHost();
    }

    /**
     * 服务端口
     *
     * @return
     */
    @Override
    public int getPort() {
        return feignProperties.getPort();
    }

    /**
     * 是否使用的https
     *
     * @return
     */
    @Override
    public boolean isSecure() {
        return feignProperties.getSecure();
    }

    /**
     * uri
     *
     * @return
     */
    @Override
    public URI getUri() {
        return URI.create(feignProperties.getUri());
    }

    @Override
    public Map<String, String> getMetadata() {
        return null;
    }
}
