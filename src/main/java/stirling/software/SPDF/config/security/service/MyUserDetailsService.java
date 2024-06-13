package stirling.software.SPDF.config.security.service;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.util.ObjectUtils;

import com.baomidou.mybatisplus.extension.conditions.query.LambdaQueryChainWrapper;

import stirling.software.SPDF.domain.entity.SysUser;
import stirling.software.SPDF.domain.vo.LoginUser;
import stirling.software.SPDF.domain.vo.SysUserVO;
import stirling.software.SPDF.enums.general.UserStatusEnum;
import stirling.software.SPDF.service.SysUserService;

/**
 * @author 29443
 * @date 2022/4/5
 */
@Service
public class MyUserDetailsService implements UserDetailsService {

    private static final Logger log = LoggerFactory.getLogger(MyUserDetailsService.class);

    private static final String ROLE_PREFIX = "ROLE_";

    @Autowired private SysUserService sysUserService;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        SysUser user =
                new LambdaQueryChainWrapper<>(sysUserService.getBaseMapper())
                        .eq(SysUser::getUserName, username)
                        .eq(SysUser::getStatus, UserStatusEnum.ACTIVE.getCode())
                        .one();

        if (ObjectUtils.isEmpty(user)) {
            log.info("用戶：{}，不存在", username);
            throw new RuntimeException("用户不存在");
        }
        if (UserStatusEnum.INACTIVE.getCode().equals(user.getStatus())) {
            log.info("用戶：{}，被禁用", username);
            throw new RuntimeException("用户已被禁用");
        }

        LoginUser loginUser = new LoginUser(user.getId(), SysUserVO.getVoByEntity(user, null));
        loginUser.setAuthorities(this.getUserPermissions(username));
        return loginUser;
    }

    public Set<GrantedAuthority> getUserPermissions(String username) {
        Set<GrantedAuthority> authorities = new HashSet<>();
        // 查出所有的菜单权限
        List<String> userMenuPermissions = sysUserService.getUserMenuPermissions(username);
        for (String menuPermission : userMenuPermissions) {
            authorities.add(new SimpleGrantedAuthority(menuPermission));
        }
        // 查出所有的角色权限
        List<String> userRolePermissions = sysUserService.getUserRolePermissions(username);
        for (String rolePermission : userRolePermissions) {
            authorities.add(new SimpleGrantedAuthority(ROLE_PREFIX + rolePermission));
        }
        return authorities;
    }
}
