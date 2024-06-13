package stirling.software.SPDF.domain.vo;

import org.springframework.beans.BeanUtils;
import org.springframework.lang.NonNull;

import lombok.Data;
import lombok.EqualsAndHashCode;
import stirling.software.SPDF.domain.base.AbsVo;
import stirling.software.SPDF.domain.entity.SysRoleMenu;

/**
 * 展示
 *
 * @author 向培
 * @since 2022-05-29
 */
@Data
@EqualsAndHashCode(callSuper = true)
public class SysRoleMenuVO extends AbsVo {

    private Long roleId;

    private String menuId;

    private String[] menuIds;

    /**
     * entity转vo
     *
     * @param entity
     * @param vo
     * @return
     */
    public static SysRoleMenuVO getVoByEntity(@NonNull SysRoleMenu entity, SysRoleMenuVO vo) {
        if (vo == null) {
            vo = new SysRoleMenuVO();
        }
        BeanUtils.copyProperties(entity, vo);
        return vo;
    }
}
