package stirling.software.SPDF.domain.form;

import java.util.List;

import org.springframework.beans.BeanUtils;

import io.swagger.annotations.ApiModel;

import lombok.Data;
import lombok.EqualsAndHashCode;
import stirling.software.SPDF.domain.base.AbsForm;
import stirling.software.SPDF.domain.entity.SysRoleMenu;

/**
 * 表单
 *
 * @author 向培
 * @since 2022-05-29
 */
@Data
@ApiModel(value = "SysRoleMenu表单", description = "表单")
@EqualsAndHashCode(callSuper = true)
public class SysRoleMenuForm extends AbsForm {

    private String roleId;

    private List<String> menuIds;

    public static SysRoleMenu getSysRoleMenu(SysRoleMenu entity, SysRoleMenuForm form) {
        if (entity == null) {
            entity = new SysRoleMenu();
        }
        BeanUtils.copyProperties(form, entity);
        return entity;
    }
}
