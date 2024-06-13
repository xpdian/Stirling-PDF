package stirling.software.SPDF.domain.base;

import java.util.Date;

import com.baomidou.mybatisplus.annotation.FieldFill;
import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;

import lombok.Data;

/**
 * @author xp
 * @date 2022/11/29
 */
@Data
public abstract class AbsEntity {

    private static final long serialVersionUID = 1L;

    @TableId(value = "id", type = IdType.AUTO)
    private Integer id;

    /** 创建时间 */
    @TableField(value = "gmt_create", fill = FieldFill.INSERT)
    private Date gmtCreate;

    /** 修改时间 */
    @TableField(value = "gmt_update", fill = FieldFill.INSERT_UPDATE)
    private Date gmtUpdate;

    @TableField(value = "deleted", fill = FieldFill.INSERT)
    private String deleted;
}
