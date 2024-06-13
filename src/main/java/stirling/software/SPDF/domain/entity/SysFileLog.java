package stirling.software.SPDF.domain.entity;

import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;
import stirling.software.SPDF.domain.base.AbsEntity;

/**
 * 文件处理日志
 *
 * @author xp
 * @since 2024-06-13
 */
@Data
@TableName("sys_file_log")
public class SysFileLog extends AbsEntity {

    private static final long serialVersionUID = 1L;

    @TableField("user_id")
    /**  */
    private Integer userId;

    @TableField("op_content")
    /** 操作内容 */
    private String opContent;

    @TableField("file_size")
    /** 文件大小 */
    private Long fileSize;

    @TableField("api_url")
    /** 接口地址 */
    private String apiUrl;


}
