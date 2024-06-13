package stirling.software.SPDF.service.impl;

import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;

import lombok.RequiredArgsConstructor;
import stirling.software.SPDF.domain.Result;
import stirling.software.SPDF.domain.entity.SysRole;
import stirling.software.SPDF.domain.vo.SysRoleVO;
import stirling.software.SPDF.mapper.SysRoleMapper;
import stirling.software.SPDF.service.SysRoleService;

/**
 * 系统角色 服务实现类
 *
 * @author 向培
 * @since 2022-05-22
 */
@Service
@RequiredArgsConstructor
public class SysRoleServiceImpl extends ServiceImpl<SysRoleMapper, SysRole>
        implements SysRoleService {

    private final SysRoleMapper sysRoleMapper;

    @Override
    public Result getSysRoleById(String id) {
        SysRole entity = baseMapper.selectById(id);
        SysRoleVO vo = new SysRoleVO();
        BeanUtils.copyProperties(entity, vo);
        return Result.ok().data(vo);
    }
}
