package stirling.software.SPDF.enums.general;

import java.io.Serializable;

import com.baomidou.mybatisplus.annotation.EnumValue;
import com.fasterxml.jackson.annotation.JsonValue;

import lombok.Getter;

/**
 * @author 29443
 * @date 2022/4/9
 */
@Getter
public enum MenuTypeEnum implements Serializable {
    CATALOGUE("0", "目录"),
    MENU("1", "菜单"),
    BUTTON("2", "按钮");

    @EnumValue @JsonValue private final String code;

    private final String value;

    MenuTypeEnum(String code, String value) {
        this.code = code;
        this.value = value;
    }
}
