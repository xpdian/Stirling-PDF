package stirling.software.SPDF.mapper;

import org.apache.ibatis.annotations.Mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;

import stirling.software.SPDF.domain.entity.SysVip;
import stirling.software.SPDF.domain.vo.SysVipVO;

/**
 * 会员 Mapper 接口
 *
 * @author xp
 * @since 2024-06-13
 */
@Mapper
public interface SysVipMapper extends BaseMapper<SysVip> {

    /**
     * id查找会员
     *
     * @param id
     * @return
     */
    SysVipVO getById(Integer id);
}
