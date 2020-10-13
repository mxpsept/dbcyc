package com.kcm.modules.examine.standard.service;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.kcm.modules.examine.standard.entity.BizExamineTemplateInfor;
import com.kcm.modules.examine.standard.vo.BizExamineTemplateInforVo;

import java.util.List;

/**
 * @author: lucky
 * @date: 2020/8/24
 * @description: 考核模板信息表(BIZ_EXAMINE_TEMPLATE_INFOR)服务接口
 **/
public interface IExaTemplateInforService {


    /***
     * 查询所有考核模板信息(分页插件查询)
     * @author lucky
     * @date 2020/8/24
     * @param current 当前页
     * @param pageSize 页面大小
     * @return 考核模板列表
     **/
    Page<BizExamineTemplateInforVo> queryPageAll(Integer current, Integer pageSize);

    /***
     * 新增考核模板信息
     * @author lucky
     * @date 2020/8/24
     * @param bizExamineTemplateInfor 模板对象
     * @return 数据库更新记录
     * @throws Exception e
     **/
    BizExamineTemplateInfor insert(BizExamineTemplateInfor bizExamineTemplateInfor) throws Exception;

    /***
     * 修改考核模板信息
     * @author lucky
     * @date 2020/8/24
     * @param bizExamineTemplateInfor 模板对象
     * @return 数据库操作记录
     * @throws Exception e
     **/
    BizExamineTemplateInfor update(BizExamineTemplateInfor bizExamineTemplateInfor) throws Exception;


    /***
     * 根据考核模板名称分页查询模板信息
     * @author lucky
     * @date 2020/8/26
     * @param page 分页参数
     * @param templateName 模板名称
     * @return 考核模板信息
     **/
    Page<BizExamineTemplateInforVo> searchByTemplateName(Page page, String templateName);

    /***
     * 根据主键删除考核模板信息以及其包含的指标信息
     * @author lucky
     * @date 2020/9/10
     * @param templateId 考核模板id
     * @throws Exception e
     **/
    void deleteByPrimaryKey(String templateId) throws Exception;

    /***
     * 查询全部考核模板信息
     * @author lucky
     * @date 2020/9/10
     * @return 考核模板列表
     **/
    List<BizExamineTemplateInfor> queryAll();
}
