package stirling.software.SPDF.domain.vo;

import java.util.Collection;
import java.util.Set;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import lombok.Data;

/**
 * @author 29443
 * @date 2022/4/5
 */
@Data
public class LoginUser implements UserDetails {

    /** 用户ID */
    private Integer userId;

    /** 部门ID */
    private Long deptId;

    /** 用户唯一标识 */
    private String token;

    /** 登录时间 */
    private Long loginTime;

    /** 过期时间 */
    private Long expireTime;

    /** 登录IP地址 */
    private String ipaddr;

    /** 登录地点 */
    private String loginLocation;

    /** 浏览器类型 */
    private String browser;

    /** 操作系统 */
    private String os;

    /** 权限列表 */
    private Set<GrantedAuthority> authorities;

    /** 用户信息 */
    private SysUserVO user;

    public LoginUser(Integer userId, SysUserVO user) {
        this.userId = userId;
        this.user = user;
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return this.authorities;
    }

    @Override
    public String getPassword() {
        return user.getPassword();
    }

    @Override
    public String getUsername() {
        return user.getUserName();
    }

    /** 返回用戶没过期 */
    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    /** 用户没有过期 */
    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    /** 密码没有过期 */
    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    /** 用户可用 */
    @Override
    public boolean isEnabled() {
        return true;
    }
}
