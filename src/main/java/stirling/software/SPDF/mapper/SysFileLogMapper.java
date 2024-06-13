package stirling.software.SPDF.mapper;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import java.util.List;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import stirling.software.SPDF.domain.entity.SysFileLog;

/**
 * 文件处理日志 Mapper 接口
 *
 * @author xp
 * @since 2024-06-13
 */
@Mapper
public interface SysFileLogMapper extends BaseMapper<SysFileLog> {


}
