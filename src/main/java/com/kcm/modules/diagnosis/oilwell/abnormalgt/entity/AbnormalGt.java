package com.kcm.modules.diagnosis.oilwell.abnormalgt.entity;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * 功图数据异常实体类（ABNORMAL_GT）
 *
 * @author  zhaoqingwang
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class AbnormalGt implements Serializable {


    private String primaryId;

    private String wellId;

    private String wellCommonName;

    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "Asia/Shanghai")
    private Date checkDate;

    private String dyna;

    private BigDecimal stroke;

    private BigDecimal frequency;

    private BigDecimal suspMaxLoad;

    private BigDecimal suspMinLoad;

    private String abnormalProblem;

    private static final long serialVersionUID = 1L;
}