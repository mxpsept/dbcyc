package com.kcm.modules.diagnosis.knowledge.standard.service.impl;

import cn.hutool.core.exceptions.ExceptionUtil;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.kcm.common.other.StringUtils;
import com.kcm.modules.diagnosis.knowledge.standard.dao.StandardCardLibDao;
import com.kcm.modules.diagnosis.knowledge.standard.entity.StandardCardLib;
import com.kcm.modules.diagnosis.knowledge.standard.service.IStandardCardLibService;
import org.springframework.security.core.parameters.P;
import org.springframework.stereotype.Service;

/**
 * @author: lucky
 * @date: 2020/10/10
 * @description: 标准功图(STANDARD_CARD_LIB)服务服务实现类
 **/
@Service
public class IStandardCardLibServiceImpl extends ServiceImpl<StandardCardLibDao, StandardCardLib> implements IStandardCardLibService {

    private StandardCardLibDao standardCardLibDao;

    public IStandardCardLibServiceImpl(StandardCardLibDao standardCardLibDao) {
        this.standardCardLibDao = standardCardLibDao;
    }

    /***
     * 新增标准功图
     * @author lucky
     * @date 2020/10/10
     * @param standardCardLib 实例对象
     * @return 数据库操作记录
     * @throws Exception e
     **/
    @Override
    public StandardCardLib insert(StandardCardLib standardCardLib) throws Exception {
        standardCardLib.setPrimaryId(StringUtils.uuid());
        boolean result = save(standardCardLib);
        if (result) {
            return standardCardLibDao.selectByPrimaryKey(standardCardLib.getPrimaryId());
        } else {
            throw new Exception("新增标准功图失败!");
        }
    }
}
