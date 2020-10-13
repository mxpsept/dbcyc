package com.kcm.modules.diagnosis.knowledge.diagnosticparametersgt.dao;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.kcm.modules.diagnosis.knowledge.diagnosticparametersgt.entity.CdWellSource;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CdWellSourceDao  extends BaseMapper<CdWellSource> {
    int insertSelective(CdWellSource record);

    List<CdWellSource> selectOrgNameList();

    List<CdWellSource> queryorgName();

    List<CdWellSource> queryWellNameByOrgName(String orgName);
}