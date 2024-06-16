package stirling.software.SPDF.domain.entity;

import java.util.Date;

import com.baomidou.mybatisplus.annotation.*;

import lombok.Data;

/**
 * 文件处理日志
 *
 * @author xp
 * @since 2024-06-13
 */
@Data
@TableName("sys_file_log")
public class SysFileLog {

    private static final long serialVersionUID = 1L;

    @TableId(value = "id", type = IdType.AUTO)
    private Integer id;

    /** 创建时间 */
    @TableField(value = "gmt_create", fill = FieldFill.INSERT)
    private Date gmtCreate;

    @TableField(value = "deleted", fill = FieldFill.INSERT)
    private Integer deleted;

    @TableField("user_id")
    /** */
    private Integer userId;

    @TableField("file_name")
    /** 文件原始名 */
    private String fileName;

    @TableField("file_key")
    /** 文件查询路径 */
    private String fileKey;

    @TableField("op_content")
    /** 操作内容 */
    private String opContent;

    @TableField("file_size")
    /** 文件大小：字节 */
    private Integer fileSize;

    @TableField("api_url")
    /** 接口地址 */
    private String apiUrl;

    @TableField("request_ip")
    /** 请求IP */
    private String requestIp;
}
