package stirling.software.SPDF.domain.entity;

import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableName;

import lombok.Data;
import lombok.EqualsAndHashCode;
import stirling.software.SPDF.domain.base.AbsEntity;
import stirling.software.SPDF.enums.general.RoleStatusEnum;

/**
 * 系统角色
 *
 * @author 向培
 * @since 2022-05-22
 */
@Data
@TableName("sys_role")
@EqualsAndHashCode(callSuper = true)
public class SysRole extends AbsEntity {

    @TableField("role_name")
    private String roleName;

    @TableField("role_key")
    private String roleKey;

    @TableField("status")
    private RoleStatusEnum status;

    @TableField("order_num")
    private Integer orderNum;

    @TableField("remark")
    private String remark;
}
