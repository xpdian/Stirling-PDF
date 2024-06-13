package stirling.software.SPDF.domain.form;

import javax.validation.constraints.NotNull;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.BeanUtils;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

import jakarta.validation.constraints.NotBlank;
import lombok.Data;
import lombok.EqualsAndHashCode;
import stirling.software.SPDF.domain.base.AbsForm;
import stirling.software.SPDF.domain.entity.SysMenu;
import stirling.software.SPDF.enums.general.MenuTypeEnum;
import stirling.software.SPDF.enums.general.StatusEnum;

/**
 * @author 29443
 * @date 2022/5/14
 */
@Data
@ApiModel
@EqualsAndHashCode(callSuper = true)
public class SysMenuForm extends AbsForm {

    @ApiModelProperty("菜单名")
    @NotBlank(message = "菜单名不能为空")
    private String menuName;

    @NotNull(message = "菜单类型不能为空")
    @ApiModelProperty(value = "菜单分类,0目录，1菜单，2按钮")
    private MenuTypeEnum menuType;

    @NotNull(message = "菜单状态不能为空")
    @ApiModelProperty(value = "0可用，1停用")
    private StatusEnum status;

    @ApiModelProperty("菜单图标")
    private String menuIcon;

    @ApiModelProperty("菜单路径")
    private String menuPath;

    @ApiModelProperty("路由名")
    private String routerName;

    @ApiModelProperty("路由组件")
    private String routerComponent;

    @ApiModelProperty("权限")
    private String permission;

    @ApiModelProperty("排序")
    private Integer orderNum;

    @ApiModelProperty("父级")
    private Integer parentId;

    public static SysMenu getMenuEntity(SysMenu menuEntity, SysMenuForm menuForm) {
        if (menuEntity == null) {
            menuEntity = new SysMenu();
        }
        BeanUtils.copyProperties(menuForm, menuEntity);
        return menuEntity;
    }

    public void checkNotBlank() {
        // 非按钮不能为空
        if (!MenuTypeEnum.BUTTON.equals(this.menuType)) {
            if (StringUtils.isBlank(this.menuPath)) {
                throw new RuntimeException("菜单路径不能为空");
            }
            if (StringUtils.isBlank(this.routerName)) {
                throw new RuntimeException("路由名称不能为空");
            }
            if (StringUtils.isBlank(this.routerComponent)) {
                throw new RuntimeException("路由组件不能为空");
            }
        }
    }
}
