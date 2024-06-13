package stirling.software.SPDF.domain.form;

import jakarta.validation.constraints.NotBlank;
import org.springframework.beans.BeanUtils;
import javax.validation.constraints.NotNull;
import org.springframework.lang.NonNull;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import stirling.software.SPDF.domain.base.AbsForm;
import stirling.software.SPDF.domain.entity.SysVip;
import stirling.software.SPDF.group.Add;
import stirling.software.SPDF.group.Update;

import java.math.BigDecimal;
import java.util.Date;

/**
 * 会员表单
 *
 * @author xp
 * @since 2024-06-13
 */
@Data
@ApiModel(value = "SysVip表单", description = "会员表单")
public class SysVipForm extends AbsForm {

    @NotNull(message = "不能为空", groups = {Add.class, Update.class})
    @ApiModelProperty("")
    private Integer userId;

    @NotNull(message = "到期时间不能为空", groups = {Add.class, Update.class})
    @ApiModelProperty("到期时间")
    private Date endTime;

    @NotBlank(message = "会员状态不能为空", groups = {Add.class, Update.class})
    @ApiModelProperty("会员状态")
    private String status;

    @NotBlank(message = "备注不能为空", groups = {Add.class, Update.class})
    @ApiModelProperty("备注")
    private String remark;

    @NotBlank(message = "不能为空", groups = {Add.class, Update.class})
    @ApiModelProperty("")
    private String vipType;

    @NotNull(message = "续费价格不能为空", groups = {Add.class, Update.class})
    @ApiModelProperty("续费价格")
    private BigDecimal renewPrice;

    public static SysVip getEntityByForm(@NonNull SysVipForm form, SysVip entity) {
        if(entity == null) {
          entity = new SysVip();
        }
        BeanUtils.copyProperties(form, entity);
        return entity;
    }

}
