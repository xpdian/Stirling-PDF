package stirling.software.SPDF.domain.entity;

import java.io.Serializable;
import java.util.Date;

import com.baomidou.mybatisplus.annotation.*;

import lombok.Data;

/**
 * @author 向培
 * @since 2022-06-03
 */
@Data
@TableName("sys_user_role")
public class SysUserRole implements Serializable {

    private static final long serialVersionUID = 1L;

    @TableField("user_id")
    private Integer userId;

    @TableField("role_id")
    private String roleId;

    @TableId(value = "id", type = IdType.AUTO)
    private Integer id;

    @TableField(value = "gmt_create", fill = FieldFill.INSERT)
    private Date gmtCreate;

    @TableField("create_by")
    private String createBy;

    @TableField("deleted")
    private String deleted;
}
