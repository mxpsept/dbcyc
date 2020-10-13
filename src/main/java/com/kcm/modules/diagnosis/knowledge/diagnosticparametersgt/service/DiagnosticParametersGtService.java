package com.kcm.modules.diagnosis.knowledge.diagnosticparametersgt.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.kcm.modules.diagnosis.knowledge.diagnosticparametersgt.entity.DiagnosticParametersGt;

import java.util.List;

public interface DiagnosticParametersGtService extends IService<DiagnosticParametersGt> {

    /**
     * 修改功图诊断参数
     * @param diagnosticParametersGt 功图诊断参数实体
     * @return 数据库影响行数
     */
    Integer updateByEntity(DiagnosticParametersGt diagnosticParametersGt);

    /**
     * 查询所有功图诊断参数
     * @return 查询结果
     */
    List<DiagnosticParametersGt> selectAll();
}
