package stirling.software.SPDF.service.impl;

import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.baomidou.mybatisplus.extension.conditions.query.LambdaQueryChainWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;

import lombok.RequiredArgsConstructor;
import stirling.software.SPDF.domain.Result;
import stirling.software.SPDF.domain.entity.SysMenu;
import stirling.software.SPDF.domain.vo.SysMenuVO;
import stirling.software.SPDF.mapper.SysMenuMapper;
import stirling.software.SPDF.service.SysMenuService;

/**
 * 服务实现类
 *
 * @author 向培
 * @since 2022-05-14
 */
@Service
@RequiredArgsConstructor
public class SysMenuServiceImpl extends ServiceImpl<SysMenuMapper, SysMenu>
        implements SysMenuService {

    private final SysMenuMapper menuMapper;

    @Override
    public Result deleteMenuById(String menuId) {
        try {
            baseMapper.deleteById(menuId);
            return Result.ok("删除成功");
        } catch (Exception e) {
            e.printStackTrace();
            return Result.error("删除失败");
        }
    }

    @Override
    public Result selectMenuById(String menuId) {
        SysMenuVO sysMenuVO = menuMapper.selectMenuById(menuId);
        if (0 == sysMenuVO.getParentId()) {
            sysMenuVO.setParentName("主目录");
        } else {
            SysMenu one =
                    new LambdaQueryChainWrapper<>(baseMapper)
                            .select(SysMenu::getMenuName)
                            .eq(
                                    Objects.nonNull(sysMenuVO.getParentId()),
                                    SysMenu::getId,
                                    sysMenuVO.getParentId())
                            .one();
            if (one != null) {
                sysMenuVO.setParentName(one.getMenuName());
            }
        }
        return Result.ok("").data(sysMenuVO);
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public Result deleteMenuByIds(List<String> ids) {
        return Result.ok("操作成功").data(baseMapper.deleteBatchIds(ids));
    }

    @Override
    public Result menuList() {
        List<SysMenu> sysMenus = baseMapper.selectList(null);
        List<SysMenuVO> vos =
                sysMenus.stream()
                        .map(menu -> SysMenuVO.getVoByEntity(menu, null))
                        .collect(Collectors.toList());
        return Result.ok().data(vos);
    }
}
