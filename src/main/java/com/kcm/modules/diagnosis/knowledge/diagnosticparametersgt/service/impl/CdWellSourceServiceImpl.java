package com.kcm.modules.diagnosis.knowledge.diagnosticparametersgt.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.kcm.modules.diagnosis.knowledge.diagnosticparametersgt.dao.CdWellSourceDao;
import com.kcm.modules.diagnosis.knowledge.diagnosticparametersgt.entity.CdWellSource;
import com.kcm.modules.diagnosis.knowledge.diagnosticparametersgt.service.CdWellSourceService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * 油井详细信息服务层实现类
 *
 * @author zhaoqingwang
 * @version 1.0
 * @date 2020/9/24 10:55
 **/
@Service
@RequiredArgsConstructor
public class CdWellSourceServiceImpl extends ServiceImpl<CdWellSourceDao, CdWellSource> implements CdWellSourceService {

    private final CdWellSourceDao cdWellSourceDao;

    @Override
    public List<CdWellSource> selectOrgName() {
        return cdWellSourceDao.selectOrgNameList();
    }

    /***
     * 查询采油站名称
     * @author lucky
     * @date 2020/9/28
     * @return 采油站名称列表
     **/
    @Override
    public List<CdWellSource> queryorgName() throws Exception {
        return cdWellSourceDao.queryorgName();
    }


    /***
     * 根据采油站名称查询油井名称
     * @author lucky
     * @date 2020/9/29
     * @param orgName 采油站名称
     * @return 油井名称列表
     **/
    @Override
    public List<CdWellSource> queryWellNameByOrgName(String orgName) throws Exception {
        return cdWellSourceDao.queryWellNameByOrgName(orgName);
    }
}
