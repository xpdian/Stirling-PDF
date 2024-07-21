package stirling.software.SPDF.domain.dto;

import lombok.Data;

/**
 * @author：xp
 * @date：2024/7/21 11:51
 */
@Data
public class LatestDTO extends ClientInfoDTO {

    /** 操作名称 */
    private String opName;

    /** 路由 */
    private String opRouter;
}
