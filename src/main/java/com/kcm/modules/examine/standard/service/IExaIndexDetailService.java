package com.kcm.modules.examine.standard.service;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.service.IService;
import com.kcm.modules.examine.manage.entity.BizExamineResultDetail;
import com.kcm.modules.examine.standard.entity.BizExamineIndexDetail;

import java.util.List;

/**
 * 考核指标明细信息表(BIZ_EXAMINE_INDEX_DETAIL)服务接口
 *
 * @author zhaoqingwang
 * @date 2020/8/24
 * @version 1.0
 **/
public interface IExaIndexDetailService extends IService<BizExamineIndexDetail> {

    /**
     * 新增考核指标明细信息
     *
     * @author zhaoqingwang
     * @param bizExamineIndexDetail 考核指标明细实体
     * @return 影响行数
     */
    Integer insert(BizExamineIndexDetail bizExamineIndexDetail);

    /**
     * 通过主键修改考核指标明细信息
     *
     * @author zhaoqingwang
     * @param bizExamineIndexDetail 考核指标明细实体
     * @return 影响行数
     */
    Integer update(BizExamineIndexDetail bizExamineIndexDetail);

    /**
     * 通过主键修改考核指标明细部分信息
     *
     * @author zhaoqingwang
     * @param bizExamineIndexDetail 考核指标明细实体
     * @return 影响行数
     */
    Integer updateSelective(BizExamineIndexDetail bizExamineIndexDetail);

    /**
     * 通过主键查询考核指标明细信息
     *
     * @author zhaoqingwang
     * @param indexDId 考核指标明细ID
     * @return 考核指标明细实体
     */
    BizExamineIndexDetail selectByPrimary(String indexDId);

    /**
     * 通过考核内容模糊查询考核指标明细（分页插件）
     *
     * @param examineContent 考核内容模糊字段
     * @param current 当前页
     * @param pageSize 页面尺寸
     * @return 查询结果
     */
    IPage<BizExamineIndexDetail> selectByExamineContent(String examineContent,Integer current,Integer pageSize);

    /**
     * 查询所有考核指标明细（分页插件）
     *
     * @author zhaoqingwang
     * @param current 当前页
     * @param pageSize 每页尺寸
     * @return 查询结果
     */
    IPage<BizExamineIndexDetail> selectAll(Integer current,Integer pageSize);

    /**
     * 通过指标ID查询对应所有指标明细
     *
     * @author zhaoqingwang
     * @param indexId 指标ID
     * @return 查询结果
     */
    List<BizExamineIndexDetail> selectByIndexId(String indexId);

    /**
     * 通过主键删除考核指标明细信息
     *
     * @author zhaoqingwang
     * @param indexDId 考核指标明细ID
     * @return 考核指标明细实体
     */
    Integer deleteByPrimary(String indexDId);

    /**
     * 通过考核指标明细ID集合批量删除
     *
     * @author zhaoqingwang
     * @param indexDIds 考核指标明细ID集合
     * @return 影响行数
     */
    Integer deleteByIds(List<String> indexDIds);

    /**
     * 通过考核指标ID批量删除
     *
     * @param indexId 考核指标ID
     * @return 数据库操作记录
     */
    Integer deleteByIndexId(String indexId);
}
