package com.kcm.modules.diagnosis.knowledge.liquidabnormalscreen.service;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.IService;
import com.kcm.modules.diagnosis.knowledge.liquidabnormalscreen.entity.LiqFilterCondition;


/**
 *  流量异常筛选表服务层接口类
 *
 * @author  zhaoqingwang
 * @date  2020/9/19 10:39
 * @version  1.0
 **/

public interface LiqFilterConditionService extends IService<LiqFilterCondition> {

    /**
     * 查询所有液量异常筛选条件（分页）
     *
     * @param page 分页参数
     * @return 查询结果
     */
    Page<LiqFilterCondition> selectAll(Page page);

    /**
     * 条件查询(分页)
     *
     * @param prodDate 查询日期
     * @param orgName 加油站名称
     * @return 查询结果
     */
    Page<LiqFilterCondition> selectByTimeAndOrg(Page page,String prodDate,String orgName);

    /**
     * 新增液量异常筛选条件
     * @param liqFilterCondition 液量异常筛选条件实体
     * @return 数据库影响行数
     */
    Integer insert(LiqFilterCondition liqFilterCondition);

    /**
     * 修改液量异常筛选条件
     *
     * @param liqFilterCondition 液量异常筛选条件实体
     * @return 数据库影响行数
     */
    Integer updateByWellName(LiqFilterCondition liqFilterCondition);

    /**
     * 删除液量异常筛选条件
     * @param liqFilterCondition 液量异常筛选条件实体
     * @return 删除结果
     */
    Integer deleteByWellNameAndDate(LiqFilterCondition liqFilterCondition);
}
