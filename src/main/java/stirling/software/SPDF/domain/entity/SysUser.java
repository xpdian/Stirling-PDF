package stirling.software.SPDF.domain.entity;

import java.util.Date;

import com.baomidou.mybatisplus.annotation.FieldFill;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableName;
import com.fasterxml.jackson.annotation.JsonFormat;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;
import stirling.software.SPDF.domain.base.AbsEntity;
import stirling.software.SPDF.enums.general.SexEnum;
import stirling.software.SPDF.enums.general.UserStatusEnum;

/**
 * @author 向培
 * @since 2022-04-05
 */
@Getter
@Setter
@TableName("sys_user")
@EqualsAndHashCode(callSuper = true)
public class SysUser extends AbsEntity {

    //    private static final long serialVersionUID = 1L;

    //    @TableId(value = "id", type = IdType.AUTO)
    //    private String id;

    @TableField("user_name")
    private String userName;

    @TableField("nick_name")
    private String nickName;

    @TableField("belong_org")
    private String belongOrg;

    @TableField("email")
    private String email;

    @TableField("phone_number")
    private String phoneNumber;

    @TableField("sex")
    private SexEnum sex;

    @TableField("password")
    private String password;

    @TableField("avatar")
    private String avatar;

    @TableField("status")
    private UserStatusEnum status;

    @TableField(value = "login_date", fill = FieldFill.UPDATE)
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private Date loginDate;

    //    @TableField(value = "gmt_create", fill = FieldFill.INSERT)
    //    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    //    private Date gmtCreate;
    //
    //    @TableField("deleted")
    //    private Integer deleted;
    //
    //    @TableField(value = "gmt_update", fill = FieldFill.UPDATE)
    //    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    //    private Date gmtUpdate;
    //
    //    @TableField("create_by")
    //    private String createBy;
    //
    //    @TableField("update_by")
    //    private String updateBy;

    @TableField("tenant_id")
    private String tenantId;
}
