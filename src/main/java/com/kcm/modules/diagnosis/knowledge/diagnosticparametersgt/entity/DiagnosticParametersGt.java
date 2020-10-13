package com.kcm.modules.diagnosis.knowledge.diagnosticparametersgt.entity;

import java.io.Serializable;
import java.math.BigDecimal;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * DIAGNOSTIC_PARAMETERS_GT
 * @author zhaoqingwang
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class DiagnosticParametersGt implements Serializable {
    /**
     * 诊断步骤
     */
    private String diagnosticStep;

    /**
     * 序号
     */
    private BigDecimal orderNumber;

    /**
     * 诊断结果
     */
    private String abnormalProblem;

    /**
     * 诊断依据
     */
    private String diagnosticBasis;

    private static final long serialVersionUID = 1L;
}