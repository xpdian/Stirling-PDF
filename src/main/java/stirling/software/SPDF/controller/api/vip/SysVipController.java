package stirling.software.SPDF.controller.api.vip;

import jakarta.validation.constraints.NotEmpty;
import org.springframework.validation.annotation.Validated;
import org.springframework.security.access.prepost.PreAuthorize;
import lombok.RequiredArgsConstructor;
import java.util.List;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.web.bind.annotation.*;
import stirling.software.SPDF.domain.Result;
import stirling.software.SPDF.domain.form.SysVipForm;
import stirling.software.SPDF.group.Add;
import stirling.software.SPDF.group.Update;
import stirling.software.SPDF.service.SysVipService;

/**
 * 会员 前端控制器
 *
 * @author xp
 * @since 2024-06-13
 */
@Validated
@RequiredArgsConstructor
@Api(value = "会员", tags = "会员管理")
@RestController
@RequestMapping("/api/sys-vip")
public class SysVipController {

    private final SysVipService sysVipService;

    @PostMapping
    @ApiOperation(value = "添加", notes = "添加")
    @PreAuthorize("hasAuthority('sysVip:add')")
    public Result add(@RequestBody @Validated(Add.class) SysVipForm form) {
        return sysVipService.add(form);
    }

    @GetMapping("/{id}")
    @ApiOperation(value = "详情", notes = "详情")
    @PreAuthorize("hasAuthority('sysVip:detail')")
    public Result detail(@PathVariable("id") Integer id) {
        return sysVipService.detail(id);
    }

}
