package com.kcm.modules.diagnosis.knowledge.liquidabnormalscreen.dao;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.kcm.modules.diagnosis.knowledge.liquidabnormalscreen.entity.YlYccs;

/**
 * 液量异常参数表持久层接口
 *
 */
public interface YlYccsDao extends BaseMapper<YlYccs> {
    int deleteByPrimaryKey(String wellName);

    int insert(YlYccs record);

    int insertSelective(YlYccs record);

    YlYccs selectByPrimaryKey(String wellName);

    int updateByPrimaryKeySelective(YlYccs record);

    int updateByPrimaryKey(YlYccs record);

    YlYccs selectByWellName(String wellName);
}