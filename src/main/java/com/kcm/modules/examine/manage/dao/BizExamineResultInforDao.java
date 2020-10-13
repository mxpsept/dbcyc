package com.kcm.modules.examine.manage.dao;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.kcm.modules.examine.manage.entity.BizExamineResultInfor;
import com.kcm.modules.examine.manage.vo.BizExamineResultInforPageVo;
import com.kcm.modules.examine.manage.vo.BizExamineResultVo;
import com.kcm.modules.examine.standard.entity.BizExamineIndexDetail;
import org.springframework.stereotype.Repository;

import java.math.BigDecimal;
import java.util.List;

/**
 * 考核结果信息表(BIZ_EXAMINE_RESULT_INFOR)数据访问层
 * @author lucky
 * @date 2020/8/24
 **/
public interface BizExamineResultInforDao extends BaseMapper<BizExamineResultInfor> {
    int deleteByPrimaryKey(String examineRId);

    BizExamineResultInfor selectByPrimaryKey(String examineRId);

    int updateByPrimaryKeySelective(BizExamineResultInfor record);

    int updateByPrimaryKey(BizExamineResultInfor record);

    /**
     * 通过考核计划ID查询考核指标明细集合
     * @param examinePId 考核计划id
     * @return 查询结果
     */
    List<BizExamineIndexDetail> selectByResultId(String examinePId);

    Page<BizExamineResultInforPageVo> selectResultPage(Page page);

    Page<BizExamineResultInforPageVo> selectResultByTakeObject(Page page,String takeObject);

    List<BizExamineResultVo> selectResultTotal(String examineRID);

}