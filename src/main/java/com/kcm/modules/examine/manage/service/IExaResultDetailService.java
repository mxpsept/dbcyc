package com.kcm.modules.examine.manage.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.kcm.modules.examine.manage.entity.BizExamineResultDetail;
import com.kcm.modules.examine.manage.vo.BizExamineIndexDetailVo;

import java.math.BigDecimal;
import java.util.List;
import java.util.Map;

/**
 * 考核结果明细表(BIZ_EXAMINE_INDEX_DETAIL)服务接口
 * @author lucky
 * @date 2020/8/27
 **/
public interface IExaResultDetailService  extends IService<BizExamineResultDetail> {


    /**
     * 新增一个考核结果明细
     *
     * @param bizExamineResultDetail 考核结果明细实体
     * @return 数据库影响行数
     */
    Integer insert(BizExamineResultDetail bizExamineResultDetail);

    /**
     * 单个明细打分之后将对应的考核结果明细插入表中
     *
     * @param examineResultId 考核结果ID
     * @param indexDetailId 考核指标明细ID
     * @param score 该项明细所得分数
     */
    void insertResultDetail(String examineResultId, String indexDetailId, BigDecimal score);

    /**
     * 所有明细打分结束后批量插入数据
     *
     * @param examineResultId 考核结果ID
     * @param map key为考核指标明细ID，value为得分score的map
     */
    void insertResultDetails(String examineResultId, Map<String,BigDecimal> map);

    /**
     * 批量增加考核结果明细
     *
     * @param bizExamineResultDetailList 考核结果明细
     * @return 数据库操作记录
     * @throws Exception 批量插入失败
     */
    List<BizExamineResultDetail> insertBatch(List<BizExamineResultDetail> bizExamineResultDetailList)throws Exception;

    /**
     * 根据考核结果id查询考核指标明细相关信息
     * @param examineResultId 考核结果id
     * @return 考核指标明细信息
     */
    List<BizExamineIndexDetailVo> selectByResultId(String examineResultId);

    /***
     * 整理考核评分结果并批量插入考核结果明细
     * @author lucky
     * @date 2020/8/27
     * @param indexDetailVos 评分结果信息列表
     * @return 考核结果明细列表
     * @throws Exception e
     **/
    List<BizExamineResultDetail> insertBatchResultDetail(List<BizExamineIndexDetailVo> indexDetailVos) throws Exception;

    /**
     * 根据考核结果ID查询考核结果明细集合
     *
     * @param examineResultId 考核结果ID
     * @return 查询结果
     */
    List<BizExamineResultDetail> selectExamineResultDetails(String examineResultId);
}
