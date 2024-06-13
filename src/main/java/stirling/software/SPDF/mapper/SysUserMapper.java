package stirling.software.SPDF.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;

import stirling.software.SPDF.domain.entity.SysUser;

/**
 * Mapper 接口
 *
 * @author 向培
 * @since 2022-04-05
 */
@Mapper
public interface SysUserMapper extends BaseMapper<SysUser> {
    List<SysUser> xp();

    /**
     * 用户名获取菜单权限String
     *
     * @return
     */
    String getMenuIdString(String username);

    /**
     * 通过用户名查找用户角色
     *
     * @param username
     * @return
     */
    String getRoleString(String username);
}
