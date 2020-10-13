package com.kcm.modules.examine.manage.service;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.kcm.modules.examine.manage.entity.BizExaminePlanInfor;
import com.kcm.modules.examine.manage.vo.BizExaminePlanInforVo;

/**
 * @author: lucky
 * @date: 2020/8/24
 * @description: 考核计划信息表(BIZ_EXAMINE_PLAN_INFOR)服务接口
 **/
public interface IExaPlanInforService {

    /***
     * 查询所有考核计划信息(分页插件查询)
     * @author lucky
     * @date 2020/8/25
     * @param current 当前页
     * @param pageSize 页面大小
     * @return 考核计划列表
     **/
    Page<BizExaminePlanInforVo> queryByPageAll(Integer current, Integer pageSize);

    /***
     * 新增考核计划信息
     * @author lucky
     * @date 2020/8/25
     * @param bizExaminePlanInforVo 考核计划对象
     * @return 数据库操作记录
     * @throws Exception e
     **/
    BizExaminePlanInforVo insert(BizExaminePlanInforVo bizExaminePlanInforVo) throws Exception;

    /***
     * 修改考核计划信息和考核结果信息
     * @author lucky
     * @date 2020/8/25
     * @param bizExaminePlanInfor 考核计划对象
     * @return 数据库操作记录
     * @throws Exception e
     **/
    BizExaminePlanInfor update(BizExaminePlanInfor bizExaminePlanInfor) throws Exception;

    /***
     * 根据考核计划id查询考核计划信息
     * @author lucky
     * @date 2020/8/26
     * @param examinePId
     * @return
     **/
    BizExaminePlanInfor selectByPrimaryKey(String examinePId);

    /***
     * 根据考核计划id查询考核计划信息和考核结果信息
     * @author lucky
     * @date 2020/8/26
     * @param examinePId 考核计划id
     * @return 考核计划信息和考核结果信息
     **/
    BizExaminePlanInforVo selectPlanAndResultByPId(String examinePId);

    /***
     * 根据主键删除考核计划信息以及其包含的考核结果信息
     * @author lucky
     * @date 2020/9/10
     * @param examinePlanId 考核模板id
     * @throws Exception e
     **/
    void deleteByPrimaryKey(String examinePlanId) throws Exception;

    /***
     * 根据计划名称查询考核计划信息
     * @author lucky
     * @date 2020/9/12
     * @param page 分页参数
     * @param planName 计划名称
     * @return 考核计划列表
     **/
    Page<BizExaminePlanInforVo> searchByPlanName(Page page, String planName);
}
