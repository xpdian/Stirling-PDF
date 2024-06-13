package stirling.software.SPDF.domain.form;

import javax.validation.constraints.NotNull;

import org.springframework.beans.BeanUtils;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

import jakarta.validation.constraints.NotBlank;
import lombok.Data;
import lombok.EqualsAndHashCode;
import stirling.software.SPDF.domain.base.AbsForm;
import stirling.software.SPDF.domain.entity.SysUser;
import stirling.software.SPDF.enums.general.SexEnum;
import stirling.software.SPDF.group.Add;
import stirling.software.SPDF.group.Update;

/**
 * 系统用户表单
 *
 * @author 向培
 * @since 2022-05-30
 */
@Data
@ApiModel(value = "SysUser表单", description = "系统用户表单")
@EqualsAndHashCode(callSuper = true)
public class SysUserForm extends AbsForm {

    @NotBlank(
            message = "用户名不能为空",
            groups = {Add.class, Update.class})
    @ApiModelProperty("用户名")
    private String userName;

    @NotBlank(
            message = "昵称不能为空",
            groups = {Add.class, Update.class})
    @ApiModelProperty("昵称")
    private String nickName;

    @NotBlank(
            message = "所属组织不能为空",
            groups = {Add.class, Update.class})
    @ApiModelProperty("所属组织")
    private String belongOrg;

    @NotBlank(
            message = "邮箱不能为空",
            groups = {Add.class, Update.class})
    @ApiModelProperty("邮箱")
    private String email;

    @NotBlank(
            message = "电话不能为空",
            groups = {Add.class, Update.class})
    @ApiModelProperty("电话")
    private String phoneNumber;

    @NotNull(
            message = "0男，1女不能为空",
            groups = {Add.class, Update.class})
    @ApiModelProperty("0男，1女")
    private SexEnum sex;

    @NotBlank(
            message = "密码不能为空",
            groups = {Add.class})
    @ApiModelProperty("密码")
    private String password;

    @ApiModelProperty("头像路径")
    private String avatar;

    public static SysUser getSysUser(SysUser entity, SysUserForm form) {
        if (entity == null) {
            entity = new SysUser();
        }
        BeanUtils.copyProperties(form, entity);
        return entity;
    }
}
