package stirling.software.SPDF.domain.entity;

import java.math.BigDecimal;
import java.time.LocalDateTime;

import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableName;

import lombok.Data;
import stirling.software.SPDF.domain.base.AbsEntity;

/**
 * 会员
 *
 * @author xp
 * @since 2024-06-13
 */
@Data
@TableName("sys_vip")
public class SysVip extends AbsEntity {

    private static final long serialVersionUID = 1L;

    @TableField("user_id")
    /** */
    private Integer userId;

    @TableField("end_time")
    /** 到期时间 */
    private LocalDateTime endTime;

    @TableField("status")
    /** 会员状态 */
    private String status;

    @TableField("remark")
    /** 备注 */
    private String remark;

    @TableField("vip_type")
    /** */
    private String vipType;

    @TableField("renew_price")
    /** 续费价格 */
    private BigDecimal renewPrice;
}
