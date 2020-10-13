package com.kcm.modules.diagnosis.knowledge.tile.dao;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.kcm.modules.diagnosis.knowledge.tile.entity.PcFdPumpjackDynaDiaT;
import com.kcm.modules.diagnosis.knowledge.tile.vo.QueryBytermVo;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * 功图平铺(PC_FD_PUMPJACK_DYNA_DIA_T)数据访问层
 * @author lucky
 * @date 2020/9/24
 **/
@Repository
public interface PcFdPumpjackDynaDiaTDao extends BaseMapper<PcFdPumpjackDynaDiaT> {

    int insertSelective(PcFdPumpjackDynaDiaT record);

    /***
     * 根据条件查询功图平铺信息
     * @author lucky
     * @date 2020/9/24
     * @param queryBytermVo 条件参数
     * @return 数据列表
     **/
    List<PcFdPumpjackDynaDiaT> queryByterm(QueryBytermVo queryBytermVo);

    PcFdPumpjackDynaDiaT selectByWellIdAndTime(String wellId,String checkDate);

    /***
     * 查询井名称
     * @author lucky
     * @date 2020/9/25
     * @return 数据列表
     **/
    List<PcFdPumpjackDynaDiaT> queryWellName();

    /***
     * 分页查询总条目数
     * @author lucky
     * @date 2020/9/29
     * @return 总条目数
     **/
    Integer queryCount(QueryBytermVo queryBytermVo);
}