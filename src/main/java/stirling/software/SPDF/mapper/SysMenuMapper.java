package stirling.software.SPDF.mapper;

import org.apache.ibatis.annotations.Mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;

import stirling.software.SPDF.domain.entity.SysMenu;
import stirling.software.SPDF.domain.vo.SysMenuVO;

/**
 * Mapper 接口
 *
 * @author 向培
 * @since 2022-05-14
 */
@Mapper
public interface SysMenuMapper extends BaseMapper<SysMenu> {

    /**
     * id查找
     *
     * @param menuId
     * @return
     */
    SysMenuVO selectMenuById(String menuId);
}
