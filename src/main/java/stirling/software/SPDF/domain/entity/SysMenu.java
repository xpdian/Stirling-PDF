package stirling.software.SPDF.domain.entity;

import java.io.Serializable;

import com.baomidou.mybatisplus.annotation.FieldStrategy;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableName;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

import lombok.Data;
import lombok.EqualsAndHashCode;
import stirling.software.SPDF.domain.base.AbsEntity;
import stirling.software.SPDF.enums.general.MenuTypeEnum;
import stirling.software.SPDF.enums.general.StatusEnum;

/**
 * @author 向培
 * @since 2022-04-07
 */
@Data
@TableName("sys_menu")
@ApiModel(value = "SysMenu对象", description = "SysMenu对象")
@EqualsAndHashCode(callSuper = true)
public class SysMenu extends AbsEntity implements Serializable {

    @ApiModelProperty("菜单名")
    @TableField("menu_name")
    private String menuName;

    @ApiModelProperty("菜单分类,0目录，1菜单，2按钮")
    @TableField("menu_type")
    private MenuTypeEnum menuType;

    @ApiModelProperty("0可用，1停用")
    @TableField("status")
    private StatusEnum status;

    @ApiModelProperty("菜单图标")
    @TableField("menu_icon")
    private String menuIcon;

    @ApiModelProperty("菜单路径")
    @TableField(value = "menu_path", updateStrategy = FieldStrategy.IGNORED)
    private String menuPath;

    @ApiModelProperty("路由名")
    @TableField("router_name")
    private String routerName;

    @ApiModelProperty("路由组件")
    @TableField("router_component")
    private String routerComponent;

    @ApiModelProperty("权限")
    @TableField("permission")
    private String permission;

    @ApiModelProperty("排序")
    @TableField("order_num")
    private Integer orderNum;

    @TableField("parent_id")
    private Integer parentId;

    //    @TableField(value = "gmt_create", fill = FieldFill.INSERT)
    //    private Date gmtCreate;
    //
    //    @TableField("create_by")
    //    private String createBy;
    //
    //    @TableField(value = "gmt_update", fill = FieldFill.UPDATE)
    //    private Date gmtUpdate;
    //
    //    @TableField("update_by")
    //    private String updateBy;
    //
    //    @TableField("deleted")
    //    private Integer deleted;

}
