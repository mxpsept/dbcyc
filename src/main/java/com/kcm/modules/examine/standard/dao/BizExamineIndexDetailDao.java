package com.kcm.modules.examine.standard.dao;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.kcm.modules.examine.standard.entity.BizExamineIndexDetail;

/**
 * 考核指标明细信息表(BIZ_EXAMINE_INDEX_DETAIL)数据库访问层
 *
 * @author lucky
 * @date 2020/8/24
 **/
public interface BizExamineIndexDetailDao extends BaseMapper<BizExamineIndexDetail> {

    int deleteByPrimaryKey(String indexDId);

    BizExamineIndexDetail selectByPrimaryKey(String indexDId);

    int updateByPrimaryKeySelective(BizExamineIndexDetail record);

    int updateByPrimaryKey(BizExamineIndexDetail record);

}