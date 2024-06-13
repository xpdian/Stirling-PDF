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
public enum SexEnum implements Serializable {
    MALE("0", "男"),
    FEMALE("1", "女");

    private final String value;

    // 该注解表示将code字段存入数据库
    @EnumValue
    // 该注解表示序列化时使用这个值
    @JsonValue
    private final String code;

    SexEnum(String code, String value) {
        this.value = value;
        this.code = code;
    }
}
