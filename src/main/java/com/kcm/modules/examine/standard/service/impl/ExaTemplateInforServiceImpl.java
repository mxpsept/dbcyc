package com.kcm.modules.examine.standard.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.kcm.modules.examine.standard.dao.BizExamineTemplateInforDao;
import com.kcm.modules.examine.standard.entity.BizExamineIndexInfor;
import com.kcm.modules.examine.standard.entity.BizExamineTemplateInfor;
import com.kcm.modules.examine.standard.service.BizIndexInforService;
import com.kcm.modules.examine.standard.service.IExaTemplateInforService;
import com.kcm.modules.examine.standard.vo.BizExamineTemplateInforVo;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

/**
 * @author: lucky
 * @date: 2020/8/24
 * @description: 考核模板信息表(BIZ_EXAMINE_TEMPLATE_INFOR)服务实现类
 **/
@Service
public class ExaTemplateInforServiceImpl extends ServiceImpl<BizExamineTemplateInforDao, BizExamineTemplateInfor> implements IExaTemplateInforService {

    private BizExamineTemplateInforDao bizExamineTemplateInforDao;
    private BizIndexInforService bizIndexInforService;

    public ExaTemplateInforServiceImpl(BizExamineTemplateInforDao bizExamineTemplateInforDao, BizIndexInforService bizIndexInforService) {
        this.bizExamineTemplateInforDao = bizExamineTemplateInforDao;
        this.bizIndexInforService = bizIndexInforService;
    }

    /***
     * 查询所有考核模板信息(分页插件查询)
     * @author lucky
     * @date 2020/8/24
     * @param current 当前页
     * @param pageSize 页面大小
     * @return 考核模板信息列表
     **/
    @Override
    public Page<BizExamineTemplateInforVo> queryPageAll(Integer current, Integer pageSize) {
        Page<BizExamineTemplateInforVo> bizExamineTemplateInforVoPage = bizExamineTemplateInforDao.queryByPageAll(new Page<>(current, pageSize));
        //遍历组装考核模板及考核指标信息
        for (BizExamineTemplateInforVo bizExamineTemplateInforVo : bizExamineTemplateInforVoPage.getRecords()) {
            bizExamineTemplateInforVo.setBizExamineIndexInfors(bizIndexInforService.queryByTemplateId(bizExamineTemplateInforVo.getExamineTId()));
        }
        return bizExamineTemplateInforVoPage;
    }

    /***
     * 新增考核模板信息
     * @author lucky
     * @date 2020/8/24 
     * @param bizExamineTemplateInfor 模板对象
     * @return 数据库影响记录
     * @throws Exception e
     **/
    @Override
    public BizExamineTemplateInfor insert(BizExamineTemplateInfor bizExamineTemplateInfor) throws Exception {
        boolean result = save(bizExamineTemplateInfor);
        if (result) {
            return bizExamineTemplateInforDao.selectByPrimaryKey(bizExamineTemplateInfor.getExamineTId());
        } else {
            throw new Exception("添加绩效考核模板信息失败！");
        }
    }

    /***
     * 修改绩效考核模板信息
     * @author lucky
     * @date 2020/8/24
     * @param bizExamineTemplateInfor 模板对象
     * @return 数据库操作记录
     * @throws Exception e
     **/
    @Override
    public BizExamineTemplateInfor update(BizExamineTemplateInfor bizExamineTemplateInfor) throws Exception {
        boolean result = updateById(bizExamineTemplateInfor);
        if (result) {
            return bizExamineTemplateInforDao.selectByPrimaryKey(bizExamineTemplateInfor.getExamineTId());
        } else {
            throw new Exception("修改绩效考核模板信息失败！");
        }
    }

    /***
     * 根据条件分页查询考核模板信息
     * @author lucky
     * @date 2020/8/26
     * @param page 分页参数
     * @param templateName 模板名称
     **/
    @Override
    public Page<BizExamineTemplateInforVo> searchByTemplateName(Page page, String templateName) {
        Page<BizExamineTemplateInforVo> templateInforVoPage = bizExamineTemplateInforDao.selectByTemplateNamePage(page, templateName);
        for (BizExamineTemplateInforVo bizExamineTemplateInforVo : templateInforVoPage.getRecords()) {
            bizExamineTemplateInforVo.setBizExamineIndexInfors(bizIndexInforService.queryByTemplateId(bizExamineTemplateInforVo.getExamineTId()));
        }
        return templateInforVoPage;
    }

    /***
     * 根据主键删除考核模板信息以及其包含的指标信息
     * @author lucky
     * @date 2020/9/10
     * @param templateId 考核模板id
     **/
    @Override
    public void deleteByPrimaryKey(String templateId) throws Exception {
        //删除考核模板信息
        bizExamineTemplateInforDao.deleteByPrimaryKey(templateId);
        //删除考核模板包含的指标相关信息
        List<String> indexIds = bizIndexInforService.queryByTemplateId(templateId).stream().map(BizExamineIndexInfor::getIndexId).collect(Collectors.toList());
        for (String indexId : indexIds) {
            bizIndexInforService.deleteById(indexId);
        }
    }

    /***
     * 查询全部考核模板信息
     * @author lucky
     * @date 2020/9/10
     * @return 考核模板列表
     **/
    @Override
    public List<BizExamineTemplateInfor> queryAll() {
        return list();
    }
}
