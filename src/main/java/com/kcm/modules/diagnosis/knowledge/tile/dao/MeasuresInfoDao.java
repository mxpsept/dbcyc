package com.kcm.modules.diagnosis.knowledge.tile.dao;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.kcm.common.core.controller.BaseController;
import com.kcm.modules.diagnosis.knowledge.tile.entity.MeasuresInfo;
import org.springframework.stereotype.Repository;

/**
 * 措施建议表（MEASURES_INFO）数据访问层
 * @author lucky
 * @date 2020/9/28
 **/
@Repository
public interface MeasuresInfoDao extends BaseMapper<MeasuresInfo> {
    int insertSelective(MeasuresInfo record);
}