package stirling.software.SPDF.config;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;

import lombok.Data;

/**
 * @author：xp
 * @date：2024/6/16 15:42
 */
@Data
@Configuration
@ConfigurationProperties(prefix = "file")
public class FileConfig {

    /** 文件存储路径 */
    private String path;

    /** 是否临时缓存操作文件 */
    private Boolean tmpSave = Boolean.FALSE;
}
