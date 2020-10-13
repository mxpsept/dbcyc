package com.kcm.modules.diagnosis.oilwell.gtmjyc.service;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.IService;
import com.kcm.modules.diagnosis.oilwell.dymyc.entity.dto.DymParamDto;
import com.kcm.modules.diagnosis.oilwell.gtmjyc.entity.AbnormalGtmjEntity;
import com.kcm.modules.diagnosis.oilwell.gtmjyc.vo.AbnormalGtmjVo;


/**
 * <p>
 * 功图面积异常表 服务类
 * </p>
 *
 * @author xublu
 * @since 2020-09-18
 */
public interface AbnormalGtmjService extends IService<AbnormalGtmjEntity> {
    /**
     * 功图面积异常分页查询
     * @param pageNum 页码
     * @param size 每页记录数
     * @return 返回列表结果
     */
    Page<AbnormalGtmjVo> findAllByPage(Integer pageNum, Integer size);

    /**
     * 分页 + 条件查询
     * @param paramDto 实体类
     * @param pageNum 当前页
     * @param size 页面尺寸
     * @return 查询结果
     */
    Page<AbnormalGtmjVo> findAllByPageAndCon(DymParamDto paramDto, Integer pageNum, Integer size);
}
