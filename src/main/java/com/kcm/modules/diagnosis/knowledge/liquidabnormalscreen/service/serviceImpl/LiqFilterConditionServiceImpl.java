package com.kcm.modules.diagnosis.knowledge.liquidabnormalscreen.service.serviceImpl;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.kcm.modules.diagnosis.knowledge.liquidabnormalscreen.dao.LiqFilterConditionDao;
import com.kcm.modules.diagnosis.knowledge.liquidabnormalscreen.entity.LiqFilterCondition;
import com.kcm.modules.diagnosis.knowledge.liquidabnormalscreen.service.LiqFilterConditionService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;


/**
 * 流量异常筛选表服务层实现类
 *
 * @author zhaoqingwang
 * @date  2020/9/19 10:39
 * @version  1.0
 **/
@Service
@RequiredArgsConstructor
public class LiqFilterConditionServiceImpl extends ServiceImpl<LiqFilterConditionDao, LiqFilterCondition> implements LiqFilterConditionService {

    private final LiqFilterConditionDao liqFilterConditionDao;

    /**
     * 查询所有液量异常筛选条件（分页）
     *
     * @param page 分页参数
     * @return 查询结果
     */
    @Override
    public Page<LiqFilterCondition> selectAll(Page page) {
        return liqFilterConditionDao.selectAll(page);
    }

    /**
     * 条件查询(分页)
     *
     * @param prodDate 查询日期
     * @param orgName 加油站名称
     * @return 查询结果
     */
    @Override
    public Page<LiqFilterCondition> selectByTimeAndOrg(Page page,String prodDate, String orgName) {

        return liqFilterConditionDao.selectByTimeAndOrg(page,prodDate,orgName);
    }

    /**
     * 新增液量异常筛选条件
     * @param liqFilterCondition 液量异常筛选条件实体
     * @return 数据库影响行数
     */
    @Override
    public Integer insert(LiqFilterCondition liqFilterCondition) {
        if (!"任意天".equals(liqFilterCondition.getFilter())){liqFilterCondition.setAppointDate(null);}
        return liqFilterConditionDao.insert(liqFilterCondition);
    }

    /**
     * 修改液量异常筛选条件
     *
     * @param liqFilterCondition 液量异常筛选条件实体
     * @return 数据库影响行数
     */
    @Override
    public Integer updateByWellName(LiqFilterCondition liqFilterCondition) {
        if (!"任意天".equals(liqFilterCondition.getFilter())){liqFilterCondition.setAppointDate(null);}
        return liqFilterConditionDao.updateByTerm(liqFilterCondition);
    }

    /**
     * 删除液量异常筛选条件
     * @param liqFilterCondition 液量异常筛选条件实体
     * @return 删除结果
     */
    @Override
    public Integer deleteByWellNameAndDate(LiqFilterCondition liqFilterCondition) {

        return liqFilterConditionDao.deleteByWellNameAndDate(liqFilterCondition);
    }
}
