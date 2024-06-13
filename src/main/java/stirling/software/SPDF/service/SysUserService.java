package stirling.software.SPDF.service;

import java.util.List;

import com.baomidou.mybatisplus.extension.service.IService;

import stirling.software.SPDF.domain.Result;
import stirling.software.SPDF.domain.entity.SysUser;
import stirling.software.SPDF.domain.vo.UserRolePermissionVO;

/**
 * 系统用户 服务类
 *
 * @author 向培
 * @since 2022-05-30
 */
public interface SysUserService extends IService<SysUser> {

    /**
     * 根据id查找系统用户
     *
     * @param id
     * @return
     */
    Result getSysUserById(String id);

    /**
     * 查询菜单权限
     *
     * @param userName
     * @return
     */
    List<String> getUserMenuPermissions(String userName);

    /**
     * 查询角色权限
     *
     * @param username
     * @return
     */
    List<String> getUserRolePermissions(String username);

    /**
     * 用户名查找菜单
     *
     * @param name
     * @return
     */
    UserRolePermissionVO getMenus(String name);

    /**
     * 重置用户密码
     *
     * @param userId
     * @return
     */
    Result resetUserPwd(Integer userId);
}
