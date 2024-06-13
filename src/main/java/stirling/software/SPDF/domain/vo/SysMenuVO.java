package stirling.software.SPDF.domain.vo;

import java.util.Date;
import java.util.List;

import org.springframework.beans.BeanUtils;
import org.springframework.lang.NonNull;

import com.fasterxml.jackson.annotation.JsonFormat;

import lombok.Data;
import stirling.software.SPDF.domain.entity.SysMenu;
import stirling.software.SPDF.enums.general.MenuTypeEnum;
import stirling.software.SPDF.enums.general.StatusEnum;

/**
 * @author 29443
 * @date 2022/5/14
 */
@Data
public class SysMenuVO {

    private Integer id;

    private String menuName;

    private MenuTypeEnum menuType;

    private StatusEnum status;

    private String menuIcon;

    private String menuPath;

    private String routerName;

    private String routerComponent;

    private String permission;

    private Integer orderNum;

    private Integer parentId;

    private String parentName;

    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private Date gmtCreate;

    private String createBy;

    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private Date gmtUpdate;

    private String updateBy;

    private List<SysMenuVO> children;

    public static SysMenuVO getVoByEntity(@NonNull SysMenu entity, SysMenuVO vo) {
        if (vo == null) {
            vo = new SysMenuVO();
        }
        BeanUtils.copyProperties(entity, vo);
        return vo;
    }
}
