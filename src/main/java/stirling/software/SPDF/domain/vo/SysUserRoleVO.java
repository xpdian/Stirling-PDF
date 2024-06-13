package stirling.software.SPDF.domain.vo;

import java.util.List;

import org.springframework.beans.BeanUtils;
import org.springframework.lang.NonNull;

import lombok.Data;
import lombok.EqualsAndHashCode;
import stirling.software.SPDF.domain.base.AbsVo;
import stirling.software.SPDF.domain.entity.SysUserRole;

/**
 * 展示
 *
 * @author 向培
 * @since 2022-06-03
 */
@Data
@EqualsAndHashCode(callSuper = true)
public class SysUserRoleVO extends AbsVo {

    private Integer userId;

    private String roleId;

    private List<Integer> roleIds;

    /**
     * entity转vo
     *
     * @param entity
     * @param vo
     * @return
     */
    public static SysUserRoleVO getVoByEntity(@NonNull SysUserRole entity, SysUserRoleVO vo) {
        if (vo == null) {
            vo = new SysUserRoleVO();
        }
        BeanUtils.copyProperties(entity, vo);
        return vo;
    }
}
