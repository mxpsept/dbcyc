package com.kcm.modules.diagnosis.oilwell.gtmjyc.vo;

import com.fasterxml.jackson.annotation.JsonFormat;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.math.BigDecimal;

/**
 * @author xublu
 * @data 2020/9/21 17:58
 */

@Data
public class AbnormalGtmjVo {
    private static final long serialVersionUID=1L;

    @ApiModelProperty(value = "主键ID")
    private String primaryId;

    private String wellId;

    private String wellCommonName;

    @JsonFormat(shape=JsonFormat.Shape.STRING,pattern="yyyy-MM-dd HH:mm:ss",timezone="GMT+8")
    private String checkDate;

    private String dyna;

    private BigDecimal stroke;

    private BigDecimal frequency;

    private BigDecimal suspMaxLoad;

    private BigDecimal suspMinLoad;

    private String abnormalProblem;

    private String standardgtid;

    // 采油站
    private String orgName;

}
