package com.kcm.modules.diagnosis.oilwell.liquidvolume.dao;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.kcm.modules.diagnosis.oilwell.liquidvolume.entity.LiquidVolumeAbnormal;
import com.kcm.modules.diagnosis.oilwell.liquidvolume.vo.LiquidVolumeAbnormalVO;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.Date;
import java.util.List;

/**
 * 液量异常汇总
 *
 * @author beiguoge
 * @version 1.0
 * @date 2020/9/17 14:36
 */
@Mapper
public interface LiquidVolumeAbnormalDao extends BaseMapper<LiquidVolumeAbnormal> {

    /**
     * 分页查询液量异常汇总信息
     *
     * @param page 液量异常VO实体
     * @return 查询结果
     */
    Page<LiquidVolumeAbnormalVO> queryByPage(Page<LiquidVolumeAbnormalVO> page);

    /**
     * 根据采油站、日期等条件查询液量异常汇总信息
     *
     * @param page 液量异常VO实体
     * @param orgName 采油站
     * @param date 日期
     * @return 查询结果
     */
    Page<LiquidVolumeAbnormalVO> queryByOrgName(Page<LiquidVolumeAbnormalVO> page, @Param("orgName") String orgName, @Param("date") Date date);

    /**
     * 查询所有采油站名
     *
     * @return 查询结果
     */
    List<String> queryOrgNames();

}
