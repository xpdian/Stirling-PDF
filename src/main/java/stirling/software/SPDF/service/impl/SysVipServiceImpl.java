package stirling.software.SPDF.service.impl;

import org.springframework.stereotype.Service;
import org.springframework.util.Assert;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;

import lombok.RequiredArgsConstructor;
import stirling.software.SPDF.domain.Result;
import stirling.software.SPDF.domain.entity.SysVip;
import stirling.software.SPDF.domain.form.SysVipForm;
import stirling.software.SPDF.domain.vo.SysVipVO;
import stirling.software.SPDF.mapper.SysVipMapper;
import stirling.software.SPDF.service.SysVipService;

/**
 * 会员 服务实现类
 *
 * @author xp
 * @since 2024-06-13
 */
@Service
@RequiredArgsConstructor
public class SysVipServiceImpl extends ServiceImpl<SysVipMapper, SysVip> implements SysVipService {

    private final SysVipMapper sysVipMapper;

    /**
     * 添加
     *
     * @param form
     * @return
     */
    @Override
    public Result add(SysVipForm form) {
        SysVip entity = SysVipForm.getEntityByForm(form, null);
        baseMapper.insert(entity);
        return Result.ok("添加成功");
    }

    /**
     * 根据id查找
     *
     * @param id
     * @return
     */
    @Override
    public Result detail(Integer id) {
        SysVipVO vo = baseMapper.getById(id);
        Assert.notNull(vo, "记录不存在");
        return Result.ok().data(vo);
    }
}
