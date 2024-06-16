package stirling.software.SPDF.domain.vo;

import lombok.Data;

/**
 * 文件处理方法的统一返回结果
 *
 * @author：xp
 * @date：2024/6/16 20:40
 */
@Data
public class FileHandlerResultVO {

    /** 文件大小 */
    private Integer fileSize;

    /** 临时保存结果的文件路径 */
    private String tmpFilePath;

    /** 文件原始名 */
    private String fileName;
}
