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
public enum UserStatusEnum implements Serializable {
    ACTIVE("0", "正常"),
    INACTIVE("1", "禁用");

    @EnumValue @JsonValue private final String code;

    private final String value;

    UserStatusEnum(String code, String value) {
        this.code = code;
        this.value = value;
    }
}
