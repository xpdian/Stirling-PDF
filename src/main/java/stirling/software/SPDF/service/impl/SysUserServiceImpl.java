package stirling.software.SPDF.service.impl;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.BeanUtils;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.baomidou.mybatisplus.extension.conditions.query.LambdaQueryChainWrapper;
import com.baomidou.mybatisplus.extension.conditions.update.LambdaUpdateChainWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;

import lombok.RequiredArgsConstructor;
import stirling.software.SPDF.domain.Result;
import stirling.software.SPDF.domain.entity.SysMenu;
import stirling.software.SPDF.domain.entity.SysRole;
import stirling.software.SPDF.domain.entity.SysRoleMenu;
import stirling.software.SPDF.domain.entity.SysUser;
import stirling.software.SPDF.domain.vo.SysMenuVO;
import stirling.software.SPDF.domain.vo.SysUserVO;
import stirling.software.SPDF.domain.vo.UserRolePermissionVO;
import stirling.software.SPDF.enums.general.MenuTypeEnum;
import stirling.software.SPDF.enums.general.StatusEnum;
import stirling.software.SPDF.mapper.SysMenuMapper;
import stirling.software.SPDF.mapper.SysRoleMapper;
import stirling.software.SPDF.mapper.SysRoleMenuMapper;
import stirling.software.SPDF.mapper.SysUserMapper;
import stirling.software.SPDF.service.SysUserService;
import stirling.software.SPDF.utils.RedisUtil;

/**
 * 系统用户 服务实现类
 *
 * @author 向培
 * @since 2022-05-30
 */
@Service
@RequiredArgsConstructor
public class SysUserServiceImpl extends ServiceImpl<SysUserMapper, SysUser>
        implements SysUserService {

    private final SysUserMapper sysUserMapper;
    private final SysMenuMapper sysMenuMapper;
    private final SysRoleMenuMapper roleMenuMapper;
    private final SysRoleMapper roleMapper;
    private final RedisUtil redisUtil;
    private final PasswordEncoder passwordEncoder;

    @Override
    public Result getSysUserById(String id) {
        SysUser entity = baseMapper.selectById(id);
        SysUserVO vo = new SysUserVO();
        BeanUtils.copyProperties(entity, vo);
        return Result.ok().data(vo);
    }

    @Override
    public List<String> getUserMenuPermissions(String username) {
        List<String> permissions = new ArrayList();
        // 先查出用户所有角色
        String roleString = sysUserMapper.getRoleString(username);
        if (StringUtils.isNotBlank(roleString)) {
            // 通过角色查找拥有的菜单id
            String[] split = roleString.split(",");
            List<SysRoleMenu> list =
                    new LambdaQueryChainWrapper<>(roleMenuMapper)
                            .select(SysRoleMenu::getMenuId)
                            .in(SysRoleMenu::getRoleId, split)
                            .list();
            Set<String> set = new HashSet();
            for (SysRoleMenu sysRoleMenu : list) {
                if (StringUtils.isNotBlank(sysRoleMenu.getMenuId())) {
                    for (String s : sysRoleMenu.getMenuId().split(",")) {
                        set.add(s);
                    }
                }
            }
            // 根据id查出对应菜单
            List<SysMenu> sysMenus =
                    new LambdaQueryChainWrapper<>(sysMenuMapper)
                            .ne(SysMenu::getStatus, StatusEnum.INACTIVE.getCode())
                            .in(SysMenu::getId, set)
                            .list();
            for (SysMenu sysMenu : sysMenus) {
                if (StringUtils.isNotBlank(sysMenu.getPermission())) {
                    permissions.add(sysMenu.getPermission());
                }
            }
        }
        return permissions;
    }

    @Override
    public List<String> getUserRolePermissions(String username) {
        List<String> permissions = new ArrayList();
        // 先查出用户所有角色
        String roleString = sysUserMapper.getRoleString(username);
        if (StringUtils.isNotBlank(roleString)) {
            String[] roleIds = roleString.split(",");
            List<SysRole> roleList =
                    new LambdaQueryChainWrapper<>(roleMapper)
                            .select(SysRole::getRoleKey)
                            .in(SysRole::getId, roleIds)
                            .list();
            roleList.stream().map(SysRole::getRoleKey).forEach(roleKey -> permissions.add(roleKey));
        }
        return permissions;
    }

    @Override
    public UserRolePermissionVO getMenus(String username) {
        UserRolePermissionVO userRolePermissionVO = new UserRolePermissionVO();
        Set<String> set = new HashSet();
        // 先查出用户所有角色
        String roleString = sysUserMapper.getRoleString(username);
        if (StringUtils.isNotBlank(roleString)) {
            // 通过角色查找拥有的菜单id
            String[] split = roleString.split(",");
            List<SysRoleMenu> list =
                    new LambdaQueryChainWrapper<>(roleMenuMapper)
                            .select(SysRoleMenu::getMenuId)
                            .in(SysRoleMenu::getRoleId, split)
                            .list();
            for (SysRoleMenu sysRoleMenu : list) {
                if (StringUtils.isNotBlank(sysRoleMenu.getMenuId())) {
                    for (String s : sysRoleMenu.getMenuId().split(",")) {
                        set.add(s);
                    }
                }
            }
        }
        // 根据id查出对应菜单(正常和隐藏的菜单)
        List<SysMenuVO> sysMenuVOS = new ArrayList<>();
        // 菜单为空直接返回
        if (set.isEmpty()) {
            return userRolePermissionVO;
        }
        List<SysMenu> sysMenus =
                new LambdaQueryChainWrapper<>(sysMenuMapper)
                        .ne(SysMenu::getStatus, StatusEnum.INACTIVE.getCode())
                        .in(SysMenu::getId, set)
                        .orderByAsc(SysMenu::getOrderNum)
                        .list();

        // 转化为VO
        for (SysMenu sysMenu : sysMenus) {
            SysMenuVO sysMenuVO = new SysMenuVO();
            BeanUtils.copyProperties(sysMenu, sysMenuVO);
            sysMenuVOS.add(sysMenuVO);
        }
        // 获取到按钮权限
        List<String> buttonPermissions =
                sysMenuVOS.stream()
                        .filter(menu -> MenuTypeEnum.BUTTON.equals(menu.getMenuType()))
                        .map(SysMenuVO::getPermission)
                        .collect(Collectors.toList());
        userRolePermissionVO.setButtonPermissions(buttonPermissions);
        List<SysMenuVO> hasAddToChildren = new ArrayList<>();
        // 两层循环，设置父子关系
        for (SysMenuVO parent : sysMenuVOS) {
            if (parent.getParentId() != 0) {
                hasAddToChildren.add(parent);
            }
            List<SysMenuVO> childrens = new ArrayList<>();
            for (SysMenuVO children : sysMenuVOS) {
                if (parent == children) {
                    continue;
                }
                if (children.getParentId() == parent.getId()
                        && !MenuTypeEnum.BUTTON.equals(children.getMenuType())) {
                    childrens.add(children);
                }
            }
            parent.setChildren(childrens);
        }
        // 如果菜单成为了其它菜单的子菜单，那么就从结果中排除
        sysMenuVOS.removeAll(hasAddToChildren);
        userRolePermissionVO.setMenuPermissions(sysMenuVOS);
        return userRolePermissionVO;
    }

    @Override
    public Result resetUserPwd(Integer userId) {
        new LambdaUpdateChainWrapper<>(sysUserMapper)
                .eq(SysUser::getId, userId)
                .set(SysUser::getPassword, passwordEncoder.encode("123456"))
                .update();
        return Result.ok("操作成功");
    }
}
