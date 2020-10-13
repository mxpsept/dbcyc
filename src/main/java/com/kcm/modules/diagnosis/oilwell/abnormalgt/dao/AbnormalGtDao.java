package com.kcm.modules.diagnosis.oilwell.abnormalgt.dao;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.kcm.modules.diagnosis.oilwell.abnormalgt.entity.AbnormalGt;
import com.kcm.modules.diagnosis.oilwell.abnormalgt.vo.AbnormalGtVo;


import java.util.List;

/**
 *  功图数据异常持久层接口
 *
 * @author  zhaoqingwang
 * @date  2020/9/17 13:47
 * @version  1.0
 **/
public interface AbnormalGtDao extends BaseMapper<AbnormalGt> {

    int insert(AbnormalGt record);

    int insertSelective(AbnormalGt record);

    /**
     * 条件查询功图数据异常
     * @param page 分页参数
     * @param checkDate 查询日期
     * @param orgName 加油站名称
     * @return 查询结果
     */
    Page<AbnormalGtVo> selectAllPage(Page page, String checkDate,String orgName);

    /**
     * 通过井号（井名）和日期查询功图数据异常
     * @param wellId 井号（井名）
     * @param checkDate 日期
     * @return 查询结果
     */
    List<AbnormalGt> selectByIdAndDate(String wellId,String checkDate);
}