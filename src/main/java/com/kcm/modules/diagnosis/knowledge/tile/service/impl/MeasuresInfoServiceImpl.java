package com.kcm.modules.diagnosis.knowledge.tile.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.kcm.modules.diagnosis.knowledge.tile.dao.MeasuresInfoDao;
import com.kcm.modules.diagnosis.knowledge.tile.entity.MeasuresInfo;
import com.kcm.modules.diagnosis.knowledge.tile.service.IMeasuresInfoService;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @author: lucky
 * @date: 2020/9/28
 * @description: 措施建议表（MEASURES_INFO）服务实现类
 **/
@Service
public class MeasuresInfoServiceImpl extends ServiceImpl<MeasuresInfoDao, MeasuresInfo> implements IMeasuresInfoService {

    /***
     * 查询所有措施建议信息
     * @author lucky
     * @date 2020/9/28
     * @return 措施建议列表
     **/
    @Override
    public List<MeasuresInfo> queryAllMeasures() {
        QueryWrapper<MeasuresInfo> queryWrapper = new QueryWrapper<>();
        queryWrapper.orderByAsc("PRIMARY_ID");
        return list(queryWrapper);
    }
}
