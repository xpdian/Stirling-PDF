package stirling.software.SPDF.service.impl;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;

import com.baomidou.mybatisplus.extension.conditions.query.LambdaQueryChainWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;

import lombok.RequiredArgsConstructor;
import stirling.software.SPDF.domain.Result;
import stirling.software.SPDF.domain.entity.SysUserRole;
import stirling.software.SPDF.domain.vo.SysUserRoleVO;
import stirling.software.SPDF.mapper.SysUserRoleMapper;
import stirling.software.SPDF.service.SysUserRoleService;

/**
 * 服务实现类
 *
 * @author 向培
 * @since 2022-06-03
 */
@Service
@RequiredArgsConstructor
public class SysUserRoleServiceImpl extends ServiceImpl<SysUserRoleMapper, SysUserRole>
        implements SysUserRoleService {

    private final SysUserRoleMapper sysUserRoleMapper;

    @Override
    public Result getSysUserRoleById(String id) {
        SysUserRole entity = baseMapper.selectById(id);
        SysUserRoleVO vo = new SysUserRoleVO();
        if (entity != null) {
            BeanUtils.copyProperties(entity, vo);
        }
        return Result.ok().data(vo);
    }

    @Override
    public Result getSysUserRoleByUser(String userId) {
        SysUserRole one =
                new LambdaQueryChainWrapper<>(this.baseMapper)
                        .eq(StringUtils.isNotBlank(userId), SysUserRole::getUserId, userId)
                        .one();
        SysUserRoleVO vo = new SysUserRoleVO();
        if (one != null) {
            BeanUtils.copyProperties(one, vo);
            vo.setRoleIds(
                    Arrays.stream(one.getRoleId().split(","))
                            .map(item -> Integer.valueOf(item))
                            .collect(Collectors.toList()));
        }
        return Result.ok().data(vo);
    }

    @Override
    public Result getAllSysUserRole() {
        List<SysUserRole> sysUserRoles = new LambdaQueryChainWrapper<>(baseMapper).list();
        List<SysUserRoleVO> vos =
                sysUserRoles.stream()
                        .map(sysUserRole -> SysUserRoleVO.getVoByEntity(sysUserRole, null))
                        .collect(Collectors.toList());
        return Result.ok().data(vos);
    }
}
