package stirling.software.SPDF.utils;

import org.springframework.security.core.context.SecurityContextHolder;

import stirling.software.SPDF.domain.vo.SysUserVO;

/**
 * 安全服务工具类
 *
 * @author xp
 */
public class SecurityUtil {
    /**
     * 获取当前请求的用户的信息
     *
     * @return
     */
    public static SysUserVO getCurrentUser() {
        SysUserVO loginUser =
                (SysUserVO) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        loginUser.setPassword("");
        return loginUser;
    }

    /**
     * 获取当前请求的用户名
     *
     * @return
     */
    public static String getCurrentUserName() {
        SysUserVO loginUser =
                (SysUserVO) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        return loginUser.getUserName();
    }

    /**
     * 获取当前请求用户的id
     *
     * @return
     */
    public static Integer getCurrentUserId() {
        //        return getCurrentUser().getId();
        return 1;
    }
}
