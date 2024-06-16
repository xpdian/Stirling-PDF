package stirling.software.SPDF.service;

import com.baomidou.mybatisplus.extension.service.IService;

import stirling.software.SPDF.domain.Result;
import stirling.software.SPDF.domain.entity.SysVip;
import stirling.software.SPDF.domain.form.SysVipForm;

/**
 * 会员 服务类
 *
 * @author xp
 * @since 2024-06-13
 */
public interface SysVipService extends IService<SysVip> {

    /**
     * 添加
     *
     * @param form
     * @return
     */
    Result add(SysVipForm form);

    /**
     * 根据id查找
     *
     * @param id
     * @return
     */
    Result detail(Integer id);
}
