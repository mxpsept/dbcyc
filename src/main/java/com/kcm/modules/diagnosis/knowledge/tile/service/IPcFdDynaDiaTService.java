package com.kcm.modules.diagnosis.knowledge.tile.service;

import com.kcm.modules.diagnosis.knowledge.tile.entity.PcFdPumpjackDynaDiaT;
import com.kcm.modules.diagnosis.knowledge.tile.vo.QueryBytermVo;

import java.util.List;

/**
 * @author: lucky
 * @date: 2020/9/24
 * @description: 功图平铺(PC_FD_PUMPJACK_DYNA_DIA_T)服务接口
 **/
public interface IPcFdDynaDiaTService {


    /***
     * 根据条件查询功图平铺数据
     * @author lucky
     * @date 2020/9/24
     * @param queryBytermVo 条件参数
     * @return 数据列表
     **/
    List<PcFdPumpjackDynaDiaT> queryByterm(QueryBytermVo queryBytermVo);

    PcFdPumpjackDynaDiaT selectByWellIdAndTime(String wellId,String checkDate);

    /***
     * 查询井名称信息
     * @author lucky
     * @date 2020/9/25
     * @return 数据列表
     **/
    List<PcFdPumpjackDynaDiaT> queryWellName();

    /***
     * 分页查询总条目数
     * @author lucky
     * @date 2020/9/29
     * @param queryBytermVo 参数
     * @return 总条目数
     **/
    Integer queryCount(QueryBytermVo queryBytermVo);
}
