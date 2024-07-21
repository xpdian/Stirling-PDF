package stirling.software.SPDF.feign;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;

import stirling.software.SPDF.config.feign.CustomerFeignConfiguration;
import stirling.software.SPDF.domain.Result;
import stirling.software.SPDF.domain.dto.ClientInfoDTO;
import stirling.software.SPDF.domain.dto.LatestDTO;

/**
 * @author：xp
 * @date：2024/7/13 16:52
 */
@FeignClient(
        name = "${security.serviceName}",
        url = "${security.url}",
        configuration = CustomerFeignConfiguration.class)
public interface UserFeign {

    //    @PostMapping("/xpstart/login")
    //    Object login(@RequestBody LoginForm loginForm);

    /**
     * 校验vip
     *
     * @param token
     * @param dto 客户端身份信息及其他信息
     * @return
     */
    @PostMapping("/vip/check")
    Result authVip(@RequestHeader("Authorization") String token, @RequestBody ClientInfoDTO dto);

    /**
     * 保存最近使用
     *
     * @param token
     * @param dto 客户端身份信息及其他信息
     * @return
     */
    @PostMapping("/latest-use/client")
    Result latest(@RequestHeader("Authorization") String token, @RequestBody LatestDTO dto);
}
