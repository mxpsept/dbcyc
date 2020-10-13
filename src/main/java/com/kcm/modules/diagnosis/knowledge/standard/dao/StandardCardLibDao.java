package com.kcm.modules.diagnosis.knowledge.standard.dao;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.kcm.modules.diagnosis.knowledge.standard.entity.StandardCardLib;
import org.springframework.stereotype.Repository;

/**
 * 标准功图库(STANDARD_CARD_LIB)数据访问层
 * @author lucky
 * @date 2020/10/9
 **/
@Repository
public interface StandardCardLibDao extends BaseMapper<StandardCardLib> {
    int deleteByPrimaryKey(String primaryId);

    int insertSelective(StandardCardLib record);

    /***
     * 根据主键查询标准功图信息
     * @author lucky
     * @date 2020/10/10
     * @param primaryId 主键
     * @return 实例对象
     **/
    StandardCardLib selectByPrimaryKey(String primaryId);

    int updateByPrimaryKeySelective(StandardCardLib record);

    int updateByPrimaryKey(StandardCardLib record);
}