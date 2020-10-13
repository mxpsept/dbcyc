package com.kcm.modules.diagnosis.knowledge.diagnosticparametersgt.dao;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.kcm.modules.diagnosis.knowledge.diagnosticparametersgt.entity.DiagnosticParametersGt;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface DiagnosticParametersGtDao extends BaseMapper<DiagnosticParametersGt> {

    int insert(DiagnosticParametersGt record);

    int insertSelective(DiagnosticParametersGt record);

    /**
     * 查询所有功图诊断参数
     * @return 查询结果
     */
    List<DiagnosticParametersGt> selectAll();
}