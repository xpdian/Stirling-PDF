package stirling.software.SPDF.domain.base;

import io.swagger.annotations.ApiModelProperty;

import lombok.Data;

/**
 * 基础分页查询，默认第一页，每页10条
 *
 * @author xp
 * @date 2022/11/29
 */
@Data
public class AbsQuery {

    @ApiModelProperty(value = "当前页", required = true)
    private Long currentPage = 1L;

    @ApiModelProperty(value = "每页条数", required = true)
    private Long pageSize = 10L;
}
