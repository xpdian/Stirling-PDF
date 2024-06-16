package stirling.software.SPDF.service.impl;

import org.springframework.stereotype.Service;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;

import stirling.software.SPDF.domain.entity.SysFileLog;
import stirling.software.SPDF.mapper.SysFileLogMapper;
import stirling.software.SPDF.service.SysFileLogService;

/**
 * @author：xp
 * @date：2024/6/16 20:23
 */
@Service
public class SysFileLogServiceImpl extends ServiceImpl<SysFileLogMapper, SysFileLog>
        implements SysFileLogService {}
