package stirling.software.SPDF.domain.vo;

import org.springframework.beans.BeanUtils;
import org.springframework.lang.NonNull;

import lombok.Data;
import lombok.EqualsAndHashCode;
import stirling.software.SPDF.domain.base.AbsVo;
import stirling.software.SPDF.domain.entity.SysRole;
import stirling.software.SPDF.enums.general.RoleStatusEnum;

/**
 * 系统角色展示
 *
 * @author 向培
 * @since 2022-05-22
 */
@Data
@EqualsAndHashCode(callSuper = true)
public class SysRoleVO extends AbsVo {
    /** 角色名 */
    private String roleName;

    /** 角色key */
    private String roleKey;

    /** 0正常，1禁用 */
    private RoleStatusEnum status;

    /** 排序 */
    private Integer orderNum;

    /** 备注 */
    private String remark;

    /**
     * entity转vo
     *
     * @param entity
     * @param vo
     * @return
     */
    public static SysRoleVO getVoByEntity(@NonNull SysRole entity, SysRoleVO vo) {
        if (vo == null) {
            vo = new SysRoleVO();
        }
        BeanUtils.copyProperties(entity, vo);
        return vo;
    }
}
