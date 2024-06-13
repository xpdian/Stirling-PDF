package stirling.software.SPDF.domain.base;

import java.util.Date;

import lombok.Data;

/**
 * @author xp
 * @date 2022/11/29
 */
@Data
public abstract class AbsVo {

    private Integer id;

    private Date gmtCreate;

    private Date gmtUpdate;
}
