package com.kcm.modules.diagnosis.oilwell.dymyc.entity;

import com.baomidou.mybatisplus.annotation.TableName;
import com.baomidou.mybatisplus.extension.activerecord.Model;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;

/**
 * <p>
 * 动液面异常表
 * </p>
 *
 * @author xublu
 * @since 2020-09-17
 */
@Data
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
@TableName("ABNORMAL_DYM")
@ApiModel(value="AbnormalDymEntity对象", description="动液面异常表")
public class AbnormalDymEntity extends Model<AbnormalDymEntity> {

    private static final long serialVersionUID=1L;

    @ApiModelProperty(value = "主键ID")
    private String primaryId;

    @ApiModelProperty(value = "井号")
    private String wellId;

    @ApiModelProperty(value = "井名")
    private String wellName;

    @ApiModelProperty(value = "日期")
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


    @Override
    protected Serializable pkVal() {
        return null;
    }

}
