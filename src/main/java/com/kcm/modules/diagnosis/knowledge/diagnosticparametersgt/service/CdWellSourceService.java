package com.kcm.modules.diagnosis.knowledge.diagnosticparametersgt.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.kcm.modules.diagnosis.knowledge.diagnosticparametersgt.entity.CdWellSource;

import java.util.List;

public interface CdWellSourceService extends IService<CdWellSource> {

    List<CdWellSource> selectOrgName();

    /***
     * 查询采油站名称
     * @author lucky
     * @date 2020/9/28
     * @return 采油站名称列表
     **/
    List<CdWellSource> queryorgName() throws Exception;

    /***
     * 根据采油站名称查询油井名称
     * @author lucky
     * @date 2020/9/29
     * @param orgName 采油站名称
     * @return 油井名称列表
     **/
    List<CdWellSource> queryWellNameByOrgName(String orgName) throws Exception;
}
