package stirling.software.SPDF.service;

import com.baomidou.mybatisplus.extension.service.IService;

import stirling.software.SPDF.domain.Result;
import stirling.software.SPDF.domain.entity.SysRole;

/**
 * 系统角色 服务类
 *
 * @author 向培
 * @since 2022-05-22
 */
public interface SysRoleService extends IService<SysRole> {

    /**
     * 根据id查找系统角色
     *
     * @param id
     * @return
     */
    Result getSysRoleById(String id);
}
