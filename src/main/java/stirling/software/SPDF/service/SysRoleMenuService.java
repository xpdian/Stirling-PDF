package stirling.software.SPDF.service;

import com.baomidou.mybatisplus.extension.service.IService;

import stirling.software.SPDF.domain.Result;
import stirling.software.SPDF.domain.entity.SysRoleMenu;

/**
 * 服务类
 *
 * @author 向培
 * @since 2022-05-29
 */
public interface SysRoleMenuService extends IService<SysRoleMenu> {

    /**
     * 根据id查找
     *
     * @param id
     * @return
     */
    Result getSysRoleMenuById(String id);

    /**
     * 通过角色查找
     *
     * @param roleId
     * @return
     */
    Result getSysRoleMenuByRole(String roleId);
}
