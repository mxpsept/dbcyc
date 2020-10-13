package com.kcm.modules.diagnosis.knowledge.tile.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.kcm.modules.diagnosis.knowledge.tile.dao.PcFdPumpjackDynaDiaTDao;
import com.kcm.modules.diagnosis.knowledge.tile.entity.PcFdPumpjackDynaDiaT;
import com.kcm.modules.diagnosis.knowledge.tile.service.IPcFdDynaDiaTService;
import com.kcm.modules.diagnosis.knowledge.tile.vo.QueryBytermVo;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @author: lucky
 * @date: 2020/9/24
 * @description: 功图平铺(PC_FD_PUMPJACK_DYNA_DIA_T)服务实现类
 **/
@Service
public class PcFdDynaDiaTServiceImpl extends ServiceImpl<PcFdPumpjackDynaDiaTDao, PcFdPumpjackDynaDiaT> implements IPcFdDynaDiaTService {


    private PcFdPumpjackDynaDiaTDao pcFdPumpjackDynaDiaTDao;

    public PcFdDynaDiaTServiceImpl(PcFdPumpjackDynaDiaTDao pcFdPumpjackDynaDiaTDao) {
        this.pcFdPumpjackDynaDiaTDao = pcFdPumpjackDynaDiaTDao;
    }

    /***
     * 根据条件查询功图平铺信息
     * @author lucky
     * @date 2020/9/24
     * @param queryBytermVo 条件参数
     * @return 数据列表
     **/
    @Override
    public List<PcFdPumpjackDynaDiaT> queryByterm(QueryBytermVo queryBytermVo) {
        return pcFdPumpjackDynaDiaTDao.queryByterm(queryBytermVo);
    }

    @Override
    public PcFdPumpjackDynaDiaT selectByWellIdAndTime(String wellId, String checkDate) {
        return pcFdPumpjackDynaDiaTDao.selectByWellIdAndTime(wellId,checkDate);
    }

    /***
     * 查询井名称信息
     * @author lucky
     * @date 2020/9/25
     * @return 数据列表
     **/
    @Override
    public List<PcFdPumpjackDynaDiaT> queryWellName() {
        return pcFdPumpjackDynaDiaTDao.queryWellName();
    }

    /***
     * 分页查询总条目数
     * @author lucky
     * @date 2020/9/29
     * @param queryBytermVo 参数
     * @return 总条目数
     **/
    @Override
    public Integer queryCount(QueryBytermVo queryBytermVo) {
        return pcFdPumpjackDynaDiaTDao.queryCount(queryBytermVo);
    }
}
