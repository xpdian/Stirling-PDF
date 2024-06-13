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
public enum StatusEnum implements Serializable {

    // 当code为数值时，通用枚举在获取json值的时候（比如code为1），它会找第一个枚举对象（从0开始计数），这应该是bug
    ACTIVE("0", "激活"),
    HIDDEN("1", "不显示"),
    INACTIVE("2", "禁用");

    @EnumValue @JsonValue private final String code;

    private final String value;

    StatusEnum(String code, String value) {
        this.code = code;
        this.value = value;
    }
}
