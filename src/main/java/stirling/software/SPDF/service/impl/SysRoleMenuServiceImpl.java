package stirling.software.SPDF.service.impl;

import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;

import com.baomidou.mybatisplus.extension.conditions.query.LambdaQueryChainWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;

import lombok.RequiredArgsConstructor;
import stirling.software.SPDF.domain.Result;
import stirling.software.SPDF.domain.entity.SysRoleMenu;
import stirling.software.SPDF.domain.vo.SysRoleMenuVO;
import stirling.software.SPDF.mapper.SysRoleMenuMapper;
import stirling.software.SPDF.service.SysRoleMenuService;

/**
 * 服务实现类
 *
 * @author 向培
 * @since 2022-05-29
 */
@Service
@RequiredArgsConstructor
public class SysRoleMenuServiceImpl extends ServiceImpl<SysRoleMenuMapper, SysRoleMenu>
        implements SysRoleMenuService {

    private final SysRoleMenuMapper sysRoleMenuMapper;

    @Override
    public Result getSysRoleMenuById(String id) {
        SysRoleMenu entity = baseMapper.selectById(id);
        SysRoleMenuVO vo = new SysRoleMenuVO();
        BeanUtils.copyProperties(entity, vo);
        return Result.ok().data(vo);
    }

    @Override
    public Result getSysRoleMenuByRole(String roleId) {
        SysRoleMenu one =
                new LambdaQueryChainWrapper<>(baseMapper).eq(SysRoleMenu::getRoleId, roleId).one();
        SysRoleMenuVO sysRoleMenuVO = new SysRoleMenuVO();
        if (one != null) {
            BeanUtils.copyProperties(one, sysRoleMenuVO);
            sysRoleMenuVO.setMenuIds(one.getMenuId().split(","));
        }
        return Result.ok().data(sysRoleMenuVO);
    }
}
