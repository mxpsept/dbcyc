package com.kcm.modules.examine.standard.dao;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.kcm.modules.examine.standard.entity.BizExamineTemplateInfor;
import com.kcm.modules.examine.standard.vo.BizExamineTemplateInforVo;
import org.springframework.stereotype.Repository;

/**
 * 考核模板信息表(BIZ_EXAMINE_TEMPLATE_INFOR)数据库访问层
 *
 * @author lucky
 * @date 2020/8/24
 **/
@Repository
public interface BizExamineTemplateInforDao extends BaseMapper<BizExamineTemplateInfor> {

    /***
     * 查询所有考核模板信息(分页插件查询)
     * @author lucky
     * @date 2020/8/24 
     * @param page
     * @return
     **/
    Page<BizExamineTemplateInforVo> queryByPageAll(Page page);

    /***
     * 根据主键查询考核模板信息
     * @author lucky
     * @date 2020/8/24
     * @param templateId 主键
     * @return 绩效考核模板信息
     **/
    BizExamineTemplateInfor selectByPrimaryKey(String templateId);

    /***
     * 根据主键删除考核模板信息
     * @author lucky
     * @date 2020/9/10
     * @param templateId 考核模板id
     * @throws Exception e
     **/
    void deleteByPrimaryKey(String templateId) throws Exception;

    /***
     * 根据模板名称查询模板信息
     * @author lucky
     * @date 2020/9/11
     * @param page 分页参数
     * @param templateName 模板名称
     * @return 模板列表
     **/
    Page<BizExamineTemplateInforVo> selectByTemplateNamePage(Page page, String templateName);
}