package stirling.software.SPDF.domain.vo;

import java.util.Date;

import org.springframework.beans.BeanUtils;
import org.springframework.lang.NonNull;

import lombok.Data;
import lombok.EqualsAndHashCode;
import stirling.software.SPDF.domain.base.AbsVo;
import stirling.software.SPDF.domain.entity.SysUser;
import stirling.software.SPDF.enums.general.SexEnum;
import stirling.software.SPDF.enums.general.UserStatusEnum;

/**
 * 系统用户展示
 *
 * @author 向培
 * @since 2022-05-30
 */
@Data
@EqualsAndHashCode(callSuper = true)
public class SysUserVO extends AbsVo {
    /** 用户名 */
    private String userName;

    /** 昵称 */
    private String nickName;

    /** 所属组织 */
    private String belongOrg;

    /** 所属组织 */
    private String belongOrgName;

    /** 邮箱 */
    private String email;

    /** 电话 */
    private String phoneNumber;

    /** 0男，1女 */
    private SexEnum sex;

    private String password;

    /** 头像路径 */
    private String avatar;

    /** 状态0正常，1禁用 */
    private UserStatusEnum status;

    /** 上一次登录日期 */
    private Date loginDate;

    /** 租户id */
    private Integer tenantId;

    /**
     * entity转vo
     *
     * @param entity
     * @param vo
     * @return
     */
    public static SysUserVO getVoByEntity(@NonNull SysUser entity, SysUserVO vo) {
        if (vo == null) {
            vo = new SysUserVO();
        }
        BeanUtils.copyProperties(entity, vo);
        return vo;
    }
}
