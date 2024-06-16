package stirling.software.SPDF.enums;

import lombok.Getter;

/**
 * 文件类型
 *
 * @author：xp
 * @date：2024/6/16 16:20
 */
@Getter
public enum FileTypeEnum {
    PDF("pdf"),
    DOC("doc"),
    DOCX("docx"),
    PPT("ppt"),
    ;

    private final String value;

    FileTypeEnum(String value) {
        this.value = value;
    }
}
