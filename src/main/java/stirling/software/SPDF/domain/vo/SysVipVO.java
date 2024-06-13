package stirling.software.SPDF.domain.vo;

import java.math.BigDecimal;
import org.springframework.lang.NonNull;
import org.springframework.beans.BeanUtils;
import lombok.Data;
import stirling.software.SPDF.domain.base.AbsVo;
import stirling.software.SPDF.domain.entity.SysVip;

import java.util.Date;

/**
 * 会员展示
 *
 * @author xp
 * @since 2024-06-13
 */
@Data
public class SysVipVO extends AbsVo {

    /**  */
    private Integer userId;

    /** 到期时间 */
    private Date endTime;

    /** 会员状态 */
    private String status;

    /** 备注 */
    private String remark;

    /**  */
    private String vipType;

    /** 续费价格 */
    private BigDecimal renewPrice;

    public static SysVipVO getVoByEntity(@NonNull SysVip entity, SysVipVO vo) {
        if(vo == null) {
            vo = new SysVipVO();
        }
        BeanUtils.copyProperties(entity, vo);
        return vo;
    }

}
