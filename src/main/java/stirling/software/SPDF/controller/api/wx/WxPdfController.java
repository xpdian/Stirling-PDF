package stirling.software.SPDF.controller.api.wx;

import io.swagger.annotations.ApiOperation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import stirling.software.SPDF.domain.Result;
import stirling.software.SPDF.domain.form.SysVipForm;
import stirling.software.SPDF.group.Add;

/**
 * @author：xp
 * @date：2024/6/22 17:32
 */
@RestController
@Tag(name = "微信", description = "微信小程序pdf操作接口")
@RequestMapping("/api/v1/wx/convert")
public class WxPdfController {



}
