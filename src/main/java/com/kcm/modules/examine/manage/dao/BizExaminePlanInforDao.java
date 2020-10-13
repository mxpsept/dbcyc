package com.kcm.modules.examine.manage.dao;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.kcm.modules.examine.manage.entity.BizExaminePlanInfor;
import com.kcm.modules.examine.manage.vo.BizExaminePlanInforVo;
import com.kcm.modules.examine.standard.vo.BizExamineTemplateInforVo;
import org.springframework.stereotype.Repository;

/**
 * 考核计划信息表(BIZ_EXAMINE_PLAN_INFOR)数据库访问层
 *
 * @author lucky
 * @date 2020/8/24
 **/
@Repository
public interface BizExaminePlanInforDao extends BaseMapper<BizExaminePlanInfor> {

    /***
     * 根据主键删除考核计划信息
     * @author lucky
     * @date 2020/9/12
     * @param examinePlanId
     * @return
     **/
    boolean deleteByPrimaryKey(String examinePlanId);

    /***
     * 根据考核计划id查询考核计划信息
     * @author lucky
     * @date 2020/8/26
     * @param examinePId 考核计划id
     * @return 考核计划信息
     **/
    BizExaminePlanInfor selectByPrimaryKey(String examinePId);

    int updateByPrimaryKeySelective(BizExaminePlanInfor record);

    int updateByPrimaryKey(BizExaminePlanInfor record);

    Page<BizExaminePlanInforVo> queryByPageAll(Page page);


    /***
     * 根据计划名称查询考核计划信息
     * @author lucky
     * @date 2020/9/11
     * @param page 分页参数
     * @param planName 计划名称
     * @return 计划列表
     **/
    Page<BizExaminePlanInforVo> selectByPlanNamePage(Page page, String planName);
}