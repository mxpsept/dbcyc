package com.kcm.modules.examine.manage.service;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.IService;
import com.kcm.modules.examine.manage.entity.BizExamineResultInfor;
import com.kcm.modules.examine.manage.vo.BizExamineResultInforPageVo;
import com.kcm.modules.examine.manage.vo.BizExamineResultInforVo;

import java.math.BigDecimal;
import java.util.List;

/**
 * 考核结果信息表（BIZ_EXAMINE_RESULT_INFOR）服务层接口
 *
 * @author zhaoqingwang
 * @date 2020/08/26
 * @version 1.0
 */
public interface IExaResultInforService extends IService<BizExamineResultInfor> {


    /**
     * 通过主键修改考核结果信息
     *
     * @param bizExamineResultInfor 考核结果信息实体
     * @return 影响行数
     */
    Integer updateByPrimary(BizExamineResultInfor bizExamineResultInfor);

    /**
     * 修改考核结果以及考核结果明细
     * @param bizExamineResultInforVo 考核结果Vo
     * @return 修改结果
     */
    String updateByVo(BizExamineResultInforVo bizExamineResultInforVo);

    /**
     * 通过主键查询考核结果信息
     *
     * @param examineResultId 考核结果ID
     * @return 查询结果
     */
    BizExamineResultInfor selectByPrimary(String examineResultId);


    /**
     * 通过考核结果查询总得分并保存
     *
     * @param examineResultId 考核结果ID
     * @return 查询结果
     */
    BigDecimal queryTotalScore(String examineResultId);

    /**
     * 批量插入考核结果信息
     *
     * @param bizExamineResultInforList 考核信息集合
     * @return 数据库操作记录
     * @throws Exception e
     */
    List<BizExamineResultInfor> insertBatch(List<BizExamineResultInfor> bizExamineResultInforList) throws Exception;

    /**
     *
     * 批量修改考核结果信息
     *
     * @param bizExamineResultInforList 考核结果信息
     * @return 数据库操作记录
     * @throws Exception 数据库操作失败
     */
    List<BizExamineResultInfor> updateBatch(List<BizExamineResultInfor> bizExamineResultInforList) throws Exception;

    /**
     * 根据考核计划id查询所有考核结果信息
     *
     * @param examinePid 考核计划ID
     * @return 查询结果
     */
    List<BizExamineResultInfor> selectByExaminePId(String examinePid);


    /**
     * 根据考核结果id删除考核结果以及考核结果明细
     * @param resultId 考核结果id
     * @throws Exception e
     */
    void deleteByResultId(String resultId) throws Exception;

    /**
     * 通过考核结果id集合删除批量删除
     * @param ids 考核结果ID集合
     */
    Integer deleteByIdS(List<String> ids) throws Exception;

    /**
     * 查询所有考核结果（分页）
     * @param current 当前页
     * @param pageSize 页面尺寸
     * @return 查询结果
     */
    Page<BizExamineResultInforPageVo> selectResultPage(Integer current,Integer pageSize);


    /**
     * 通过单位/人员查询考核结果
     * @param page 分页参数
     * @param takeObject 单位/人员
     * @return 查询结果
     */
    Page<BizExamineResultInforPageVo> selectResultByTakeObject(Page page,String takeObject);
}
