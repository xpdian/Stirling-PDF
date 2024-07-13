package stirling.software.SPDF.domain.vo;

import lombok.Data;

/**
 * @author：xp
 * @date：2024/7/13 16:25
 */
@Data
public class FileHandlerResultVO {

    private String fileName;

    private Integer fileSize;

    private String tmpFilePath;
}
