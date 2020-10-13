package com.kcm.modules.diagnosis.knowledge.tile.service;

import com.kcm.modules.diagnosis.knowledge.tile.entity.MeasuresInfo;

import java.util.List;

/**
 * @author: lucky
 * @date: 2020/9/28
 * @description: 措施建议表（MEASURES_INFO）服务接口
 **/
public interface IMeasuresInfoService {

    /***
     * 查询所有措施建议
     * @author lucky
     * @date 2020/9/28
     * @return 措施建议列表
     **/
    List<MeasuresInfo> queryAllMeasures();
}
