package com.kcm.modules.diagnosis.knowledge.liquidabnormalscreen.service.serviceImpl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.kcm.modules.diagnosis.knowledge.liquidabnormalscreen.dao.YlYccsDao;
import com.kcm.modules.diagnosis.knowledge.liquidabnormalscreen.entity.YlYccs;
import com.kcm.modules.diagnosis.knowledge.liquidabnormalscreen.service.YlYccsService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * 液量异常筛选参数表服务实现类
 *
 * @author  zhaoqingwang
 * @date  2020/9/22 9:58
 * @version  1.0
 **/
@Service
@RequiredArgsConstructor
public class YlYccsServiceImpl extends ServiceImpl<YlYccsDao, YlYccs> implements YlYccsService {

    private  final  YlYccsDao ylYccsDao;

    /**
     * 修改夜量异常筛选参数
     * @param ylYccs 液量异常筛选参数实体
     * @return 数据库影响行数
     */
    @Override
    public Integer updateByEntity(YlYccs ylYccs) {
        if (ylYccs.getWellName()==null||"string".equals(ylYccs.getWellName())||"".equals(ylYccs.getWellName())||!"default".equals(ylYccs.getWellName())){
            ylYccs.setWellName("default");
        }
        return ylYccsDao.update(ylYccs,new QueryWrapper<YlYccs>().eq("WELL_NAME",ylYccs.getWellName()));
    }

    /**
     * 查询液量异常筛选参数
     * @return 查询结果
     */
    @Override
    public List<YlYccs> selectAll() {
        return ylYccsDao.selectList(null);
    }

    @Override
    public YlYccs selectByWellName(String wellName) {
        return ylYccsDao.selectByWellName(wellName);
    }
}
