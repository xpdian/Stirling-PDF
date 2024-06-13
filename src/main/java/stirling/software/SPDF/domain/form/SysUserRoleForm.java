package stirling.software.SPDF.domain.form;

import java.util.List;

import org.springframework.beans.BeanUtils;

import io.swagger.annotations.ApiModel;

import lombok.Data;
import lombok.EqualsAndHashCode;
import stirling.software.SPDF.domain.base.AbsForm;
import stirling.software.SPDF.domain.entity.SysUserRole;

/**
 * 表单
 *
 * @author 向培
 * @since 2022-06-03
 */
@Data
@ApiModel(value = "SysUserRole表单", description = "表单")
@EqualsAndHashCode(callSuper = true)
public class SysUserRoleForm extends AbsForm {

    private String userId;

    private List<String> roleIds;

    public static SysUserRole getSysUserRole(SysUserRole entity, SysUserRoleForm form) {
        if (entity == null) {
            entity = new SysUserRole();
        }
        BeanUtils.copyProperties(form, entity);
        return entity;
    }
}
