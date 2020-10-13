package com.kcm.modules.diagnosis.knowledge.liquidabnormalscreen.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.kcm.modules.diagnosis.knowledge.liquidabnormalscreen.entity.YlYccs;

import java.util.List;

/**
 *  液量异常筛选参数表服务实现类
 *
 * @author  zhaoqingwang
 * @date  2020/9/22 9:58
 * @version  1.0
 **/
public interface YlYccsService extends IService<YlYccs> {

    /**
     * 修改夜量异常筛选参数
     * @param ylYccs 液量异常筛选参数实体
     * @return 数据库影响行数
     */
    Integer updateByEntity(YlYccs ylYccs);

    /**
     * 查询液量异常筛选参数
     * @return 查询结果
     */
    List<YlYccs> selectAll();


    /**
     * 查询液量异常筛选参数
     * @return 查询结果
     */
    YlYccs selectByWellName(String wellName);


}
