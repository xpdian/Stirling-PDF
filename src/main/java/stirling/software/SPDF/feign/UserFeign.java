package stirling.software.SPDF.feign;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

/**
 * @author：xp
 * @date：2024/7/13 16:52
 */
@FeignClient(name = "${security.serviceName}", url = "${security.url}")
public interface UserFeign {

//    @PostMapping("/xpstart/login")
//    Object login(@RequestBody LoginForm loginForm);
}
