package com.kcm.modules.diagnosis.oilwell.liquidvolume.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.kcm.common.exception.BizException;
import com.kcm.modules.diagnosis.oilwell.liquidvolume.dao.LiquidVolumeAbnormalDao;
import com.kcm.modules.diagnosis.oilwell.liquidvolume.entity.LiquidVolumeAbnormal;
import com.kcm.modules.diagnosis.oilwell.liquidvolume.service.LiquidVolumeAbnormalService;
import com.kcm.modules.diagnosis.oilwell.liquidvolume.vo.LiquidVolumeAbnormalVO;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.*;
import java.util.stream.Collectors;

/**
 * 液量异常汇总服务
 *
 * @author beiguoge
 * @version 1.0
 * @date 2020/9/17 14:39
 */
@Slf4j
@Service
@RequiredArgsConstructor
public class LiquidVolumeAbonrmalServiceImpl extends ServiceImpl<LiquidVolumeAbnormalDao, LiquidVolumeAbnormal>
        implements LiquidVolumeAbnormalService {

    private final LiquidVolumeAbnormalDao liquidVolumeAbnormalDao;

    @Override
    public Page<LiquidVolumeAbnormalVO> queryByPage(Integer current, Integer pageSize) {
        return liquidVolumeAbnormalDao.queryByPage(new Page<>(current, pageSize));
    }

    @Override
    public Page<LiquidVolumeAbnormalVO> queryByOrgName(Integer current, Integer pageSize, String orgName, Date date) {
        return liquidVolumeAbnormalDao.queryByOrgName(new Page<>(current, pageSize), orgName, date);
    }

    @Override
    public List<String> queryOrgNames() {
        return liquidVolumeAbnormalDao.queryOrgNames();
    }

    @Override
    public void deleteByPrimaryId(String primaryId) throws BizException {
        LiquidVolumeAbnormal liquidVolumeAbnormal = LiquidVolumeAbnormal.builder().primaryId(primaryId).build();
        //TODO 没有删除标记
    }

    @Override
    public Map<String, List<List<Object>>> queryByPrimaryId(String primaryId, Date startDate, Date endDate) {
        if (endDate == null) {
            endDate = new Date();
        }
        QueryWrapper<LiquidVolumeAbnormal> wrapper = new QueryWrapper<LiquidVolumeAbnormal>()
                .eq("PRIMARY_ID", primaryId).between("PROD_DATE", startDate, endDate);
        List<LiquidVolumeAbnormal> volumeAbnormals = list(wrapper);
        Map<String, List<List<Object>>> result = new HashMap<>(4);
        // 区间日产液量集合
        List<List<Object>> prodDaily = volumeAbnormals.stream().map(liq -> {
            List<Object> list1 = new ArrayList<>();
            list1.add(liq.getProdDate());
            list1.add(liq.getLiqProdDaily());
            return list1;
        }).collect(Collectors.toList());
        // 区间日产油量集合
        List<List<Object>> oilDaily = volumeAbnormals.stream().map(liq -> {
            List<Object> list2 = new ArrayList<>();
            list2.add(liq.getProdDate());
            list2.add(liq.getOilProdDaily());
            return list2;
        }).collect(Collectors.toList());
        // 区间日含水率集合
        List<List<Object>> waterDaily = volumeAbnormals.stream().map(liq -> {
            List<Object> list2 = new ArrayList<>();
            list2.add(liq.getProdDate());
            list2.add(liq.getWaterCut());
            return list2;
        }).collect(Collectors.toList());
        result.put("prodDaily", prodDaily);
        result.put("oilDaily", oilDaily);
        result.put("waterDaily", waterDaily);
        return result;
    }

}
