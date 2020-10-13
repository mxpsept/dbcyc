package com.kcm.modules.diagnosis.abnormal.oilabnormal.entity;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;

import com.baomidou.mybatisplus.annotation.TableId;
import com.fasterxml.jackson.annotation.JsonFormat;
import com.sun.tracing.dtrace.ArgsAttributes;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * ABNORMAL_PRO_WELL
 * 异常汇总(油井异常)实体
 * @author
 */
@Data
@Builder(toBuilder = true)
@NoArgsConstructor
@AllArgsConstructor
public class AbnormalProWell implements Serializable {

    private static final long serialVersionUID = 1L;

    /**
     * 主键
     */
    @TableId(value = "PRIMARY_ID")
    private String primaryId;

    /**
     * 井id
     */
    private String wellId;

    /**
     * 井名称
     */
    private String wellName;

    /**
     * 生产日期
     */
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "Asia/Shanghai")
    private Date prodDate;

    /**
     * 生产时间
     */
    private BigDecimal prodTime;

    /**
     * 异常问题
     */
    private String abnormalProblem;

    /**
     *
     */
    private BigDecimal liqProdDaily;

    private BigDecimal oilProdDaily;

    private BigDecimal waterProdDaily;

    private BigDecimal gasProdDaily;

    private BigDecimal waterCut;

    private BigDecimal saltCut;

    private String remarks;

    private BigDecimal normalLiqProdDaily;

    private BigDecimal normalWaterCut;

    private BigDecimal normalSaltCut;

    private String artificialConfirmResult;

    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "Asia/Shanghai")
    private Date confirmDatetime;

    private String confirmPerson;
}