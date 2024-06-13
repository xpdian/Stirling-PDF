package stirling.software.SPDF.domain.vo;

import java.util.List;

import lombok.Data;

/**
 * @author：xp
 * @date：2023/11/27 18:06
 */
@Data
public class UserRolePermissionVO {

    /** 菜单权限 */
    private List<SysMenuVO> menuPermissions;

    /** 按钮权限 */
    private List<String> buttonPermissions;
}
