package stirling.software.SPDF.service;

import java.util.List;

import com.baomidou.mybatisplus.extension.service.IService;

import stirling.software.SPDF.domain.Result;
import stirling.software.SPDF.domain.entity.SysMenu;

/**
 * 服务类
 *
 * @author 向培
 * @since 2022-04-09
 */
public interface SysMenuService extends IService<SysMenu> {

    /**
     * 删除menu
     *
     * @param menuId
     * @return
     */
    Result deleteMenuById(String menuId);

    /**
     * id查找
     *
     * @param menuId
     * @return
     */
    Result selectMenuById(String menuId);

    /**
     * 批量删除菜单
     *
     * @param ids
     * @return
     */
    Result deleteMenuByIds(List<String> ids);

    /**
     * 菜单列表
     *
     * @return
     */
    Result menuList();
}
