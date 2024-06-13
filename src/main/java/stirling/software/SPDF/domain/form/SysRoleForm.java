package stirling.software.SPDF.domain.form;

import javax.validation.constraints.NotNull;

import org.springframework.beans.BeanUtils;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

import jakarta.validation.constraints.NotBlank;
import lombok.Data;
import lombok.EqualsAndHashCode;
import stirling.software.SPDF.domain.base.AbsForm;
import stirling.software.SPDF.domain.entity.SysRole;
import stirling.software.SPDF.enums.general.RoleStatusEnum;
import stirling.software.SPDF.group.Add;
import stirling.software.SPDF.group.Update;

/**
 * 系统角色表单
 *
 * @author 向培
 * @since 2022-05-22
 */
@Data
@ApiModel(value = "SysRole表单", description = "系统角色表单")
@EqualsAndHashCode(callSuper = true)
public class SysRoleForm extends AbsForm {

    @NotBlank(
            message = "角色名不能为空",
            groups = {Add.class, Update.class})
    @ApiModelProperty("角色名")
    private String roleName;

    @NotBlank(
            message = "角色key不能为空",
            groups = {Add.class, Update.class})
    @ApiModelProperty("角色key")
    private String roleKey;

    @NotNull(
            message = "状态不能为空",
            groups = {Add.class, Update.class})
    @ApiModelProperty("0正常，1禁用")
    private RoleStatusEnum status;

    @NotNull(
            message = "排序不能为空",
            groups = {Add.class, Update.class})
    @ApiModelProperty("排序")
    private Integer orderNum;

    @ApiModelProperty("备注")
    private String remark;

    public static SysRole getSysRole(SysRole entity, SysRoleForm form) {
        if (entity == null) {
            entity = new SysRole();
        }
        BeanUtils.copyProperties(form, entity);
        return entity;
    }
}
