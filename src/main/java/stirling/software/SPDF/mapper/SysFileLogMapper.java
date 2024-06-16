package stirling.software.SPDF.mapper;

import org.apache.ibatis.annotations.Mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;

import stirling.software.SPDF.domain.entity.SysFileLog;

/**
 * 文件处理日志 Mapper 接口
 *
 * @author xp
 * @since 2024-06-13
 */
@Mapper
public interface SysFileLogMapper extends BaseMapper<SysFileLog> {}
