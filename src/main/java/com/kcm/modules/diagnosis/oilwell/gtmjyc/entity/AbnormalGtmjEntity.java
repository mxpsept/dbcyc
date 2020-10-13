package com.kcm.modules.diagnosis.oilwell.gtmjyc.entity;

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
 * 功图面积异常表
 * </p>
 *
 * @author xublu
 * @since 2020-09-18
 */
@Data
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
@TableName("ABNORMAL_GTMJ")
@ApiModel(value="AbnormalGtmjEntity对象", description="功图面积异常表")
public class AbnormalGtmjEntity extends Model<AbnormalGtmjEntity> {

    private static final long serialVersionUID=1L;

    @ApiModelProperty(value = "主键ID")
    private String primaryId;

    private String wellId;

    private String wellCommonName;

    private Date checkDate;

    private String dyna;

    private BigDecimal stroke;

    private BigDecimal frequency;

    private BigDecimal suspMaxLoad;

    private BigDecimal suspMinLoad;

    private String abnormalProblem;

    private String standardgtid;


    @Override
    protected Serializable pkVal() {
        return null;
    }

}
