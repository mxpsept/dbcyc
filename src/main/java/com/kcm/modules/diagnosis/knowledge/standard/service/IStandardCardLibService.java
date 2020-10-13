package com.kcm.modules.diagnosis.knowledge.standard.service;

import com.kcm.modules.diagnosis.knowledge.standard.entity.StandardCardLib;

/**
 * @author: lucky
 * @date: 2020/10/10
 * @description: 标准功图(STANDARD_CARD_LIB)服务接口
 **/
public interface IStandardCardLibService {

    /***
     * 新增标准功图
     * @author lucky
     * @date 2020/10/10
     * @param standardCardLib 实例对象
     * @return 数据库操作记录
     * @throws Exception e
     **/
    StandardCardLib insert(StandardCardLib standardCardLib) throws Exception;
}
