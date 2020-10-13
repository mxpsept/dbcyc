package com.kcm.modules.diagnosis.knowledge.liquidabnormalscreen.dao;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.kcm.modules.diagnosis.knowledge.liquidabnormalscreen.entity.LiqFilterCondition;



/**
 * 流量异常筛选表数据库持久层接口
 *
 * @author  zhaoqingwang
 * @date  2020/9/19 10:39
 * @version  1.0
 **/
public interface LiqFilterConditionDao extends BaseMapper<LiqFilterCondition> {

    /**
     * 新增液量异常筛选条件
     * @param liqFilterCondition 液量异常筛选条件实体
     * @return 数据库影响行数
     */
    int insert(LiqFilterCondition liqFilterCondition);

    int insertSelective(LiqFilterCondition record);

    /**
     * 查询所有液量异常筛选条件（分页）
     *
     * @param page 分页参数
     * @return 查询结果
     */
    Page<LiqFilterCondition> selectAll(Page page);

    /**
     * 条件查询（分页）
     *
     * @param prodDate 查询日期
     * @param orgName 加油站名称
     * @return 查询结果
     */
    Page<LiqFilterCondition> selectByTimeAndOrg(Page page,String prodDate,String orgName);

    /**
     * 删除液量异常筛选条件
     * @param  liqFilterCondition 液量异常筛选条件实体
     * @return 删除结果
     */
    Integer deleteByWellNameAndDate(LiqFilterCondition liqFilterCondition);

    /**
     * 修改液量异常筛选条件
     *
     * @param liqFilterCondition 液量异常筛选条件实体
     * @return 数据库影响行数
     */
    Integer updateByTerm(LiqFilterCondition liqFilterCondition);
}