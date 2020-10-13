package com.kcm.modules.diagnosis.oilwell.dymyc.vo;

import com.fasterxml.jackson.annotation.JsonFormat;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.math.BigDecimal;
import java.util.Date;

/**
 * @author xublu
 * @data 2020/9/21 17:46
 */
@Data
public class AbnormalDymVo {
    private static final long serialVersionUID=1L;

    @ApiModelProperty(value = "主键ID")
    private String primaryId;

    @ApiModelProperty(value = "井号")
    private String wellId;

    @ApiModelProperty(value = "井名")
    private String wellName;

    @ApiModelProperty(value = "日期")
    @JsonFormat(shape=JsonFormat.Shape.STRING,pattern="yyyy-MM-dd HH:mm:ss",timezone="GMT+8")
    private Date prodDate;

    @ApiModelProperty(value = "时间")
    private BigDecimal prodTime;

    private String abnormalProblem;

    private BigDecimal dym;

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

    private Date confirmDatetime;

    private String confirmPerson;

    private BigDecimal normalDym;

    // 采油站
    private String orgName;

}
