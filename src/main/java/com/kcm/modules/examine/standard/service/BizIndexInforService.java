package com.kcm.modules.examine.standard.service;


import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.IService;
import com.kcm.modules.examine.standard.entity.BizExamineIndexInfor;
import com.kcm.modules.examine.standard.vo.BizDetailInfoListPageVo;
import com.kcm.modules.examine.standard.vo.BizDetailInfoVo;
import com.kcm.modules.examine.standard.vo.BizIndexInforVo;

import java.util.List;


/**
 * <p>
 * 考核指标信息表 服务类
 * </p>
 *
 * @author xublu
 * @since 2020-08-24
 */
public interface BizIndexInforService extends IService<BizExamineIndexInfor> {

    /**
     * 非分页
     * @return 查询结果
     */
    List<BizExamineIndexInfor> queryAll();

    /**
     * 分页
     * @param pageNum  当前页
     * @param size     页面尺寸
     * @return 查询结果
     */
    Page<BizDetailInfoListPageVo> queryPageAll(Integer pageNum, Integer size);

    /**
     * 分页 + 条件查询
     *
     * @param indexName 指标信息实体类
     * @param pageNum   当前页
     * @param size      页面尺寸
     * @return 查询结果
     */
     IPage<BizIndexInforVo> findAllByPageAndCon(String indexName, Integer pageNum, Integer size);

    /**
     * 新增数据
     * @param detailInfo 实体
     */
     void insertSelect(BizDetailInfoVo detailInfo);

    /**
     * 逻辑删除
     * @param id 主键ID
     */
     void deleteById(String id);

    /**
     * 根据ID获取实体【回显数据】
     * @param id 主键ID
     * @return 返回结果
     */
    // BizDetailInfoVo findOne(String id);

    /**
     * 修改
     * @param detailInfo
     */
    void update(BizDetailInfoVo detailInfo);


    /***
     * 根据考核模板id查询所有指标信息
     * @author lucky
     * @date 2020/9/10
     * @param TemplateId 考核模板id
     * @return 指标列表
     **/
    List<BizExamineIndexInfor> queryByTemplateId(String TemplateId);


}
