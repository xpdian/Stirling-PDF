package stirling.software.SPDF.domain.base;

import javax.validation.constraints.NotNull;

import io.swagger.annotations.ApiModelProperty;

import lombok.Data;
import stirling.software.SPDF.group.Update;

/**
 * @author xp
 * @date 2022/11/29
 */
@Data
public abstract class AbsForm {

    @ApiModelProperty(value = "id,修改必传", required = false)
    @NotNull(
            message = "请选择数据",
            groups = {Update.class})
    private Integer id;
}
