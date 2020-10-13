package com.kcm.modules.diagnosis.oilwell.liquidvolume.service;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.IService;
import com.kcm.common.exception.BizException;
import com.kcm.modules.diagnosis.oilwell.liquidvolume.entity.LiquidVolumeAbnormal;
import com.kcm.modules.diagnosis.oilwell.liquidvolume.vo.LiquidVolumeAbnormalVO;

import java.util.Date;
import java.util.List;
import java.util.Map;

/**
 * 液量异常汇总服务
 *
 * @author beiguoge
 * @version 1.0
 * @date 2020/9/17 14:40
 */
public interface LiquidVolumeAbnormalService extends IService<LiquidVolumeAbnormal> {

    /**
     * 分页查询液量异常汇总信息
     *
     * @param current 当前页
     * @param pageSize 页面大小
     * @return 查询结果
     */
    Page<LiquidVolumeAbnormalVO> queryByPage(Integer current, Integer pageSize);

    /**
     * 根据采油站、日期等条件查询液量异常汇总信息
     *
     * @param current 当前页
     * @param pageSize 页面大小
     * @param orgName 采油站
     * @param date 日期
     * @return 查询结果
     */
    Page<LiquidVolumeAbnormalVO> queryByOrgName(Integer current, Integer pageSize, String orgName, Date date);

    /**
     * 查询所有采油站名
     *
     * @return 查询结果
     */
    List<String> queryOrgNames();

    /**
     * 根据主键删除异常信息
     *
     * @param primaryId 主键ID
     * @throws BizException BizException
     */
    void deleteByPrimaryId(String primaryId) throws BizException;

    /**
     * 根据主键ID、日期条件查询液量异常汇总区间信息
     *
     * @param primaryId 主键ID
     * @param startDate 开始日期
     * @param endDate 结束日期
     * @return 查询结果
     */
    Map<String, List<List<Object>>> queryByPrimaryId(String primaryId, Date startDate, Date endDate);

}
