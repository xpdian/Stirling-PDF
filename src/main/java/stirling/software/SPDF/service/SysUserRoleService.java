package stirling.software.SPDF.service;

import com.baomidou.mybatisplus.extension.service.IService;

import stirling.software.SPDF.domain.Result;
import stirling.software.SPDF.domain.entity.SysUserRole;

/**
 * 服务类
 *
 * @author 向培
 * @since 2022-06-03
 */
public interface SysUserRoleService extends IService<SysUserRole> {

    /**
     * 根据id查找
     *
     * @param id
     * @return
     */
    Result getSysUserRoleById(String id);

    /**
     * 用户id查找
     *
     * @param userId
     * @return
     */
    Result getSysUserRoleByUser(String userId);

    /**
     * 查询所有
     *
     * @return
     */
    Result getAllSysUserRole();
}
