package stirling.software.SPDF.domain.dto;

import lombok.Data;

/**
 * 基础，包含身份信息
 *
 * @author：xp
 * @date：2024/7/13 23:24
 */
@Data
public class ClientInfoDTO {

    /** 客户端ID */
    private String clientId;

    /** 密钥 */
    private String secret;
}
