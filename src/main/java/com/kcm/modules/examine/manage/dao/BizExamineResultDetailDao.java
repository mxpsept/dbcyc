package com.kcm.modules.examine.manage.dao;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.kcm.modules.examine.manage.entity.BizExamineResultDetail;
import com.kcm.modules.examine.manage.vo.BizExamineIndexDetailVo;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * 考核结果明细表(BIZ_EXAMINE_RESULT_DETAIL)数据库访问层
 *
 * @author lucky
 * @date 2020/8/24
 **/
@Repository
public interface BizExamineResultDetailDao extends BaseMapper<BizExamineResultDetail> {
    int deleteByPrimaryKey(String examineRdId);

    BizExamineResultDetail selectByPrimaryKey(String examineRdId);

    int updateByPrimaryKeySelective(BizExamineResultDetail record);

    int updateByPrimaryKey(BizExamineResultDetail record);

    /**
     * 根据考核结果id查询考核指标明细相关信息
     * @param examineRId 考核结果id
     * @return 考核指标明细信息
     */
    List<BizExamineIndexDetailVo> selectByRId(String examineRId);
}