package com.kcm.modules.diagnosis.oilwell.liquidvolume.entity;

import com.baomidou.mybatisplus.annotation.TableName;
import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.*;

import java.io.Serializable;
import java.util.Date;

/**
 * 液量异常汇总实体
 *
 * @author beiguoge
 * @version 1.0
 * @date 2020/9/17 11:46
 */
@Data
@Builder(toBuilder = true)
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode()
@TableName(value = "ABNORMAL_PRO_WELL")
public class LiquidVolumeAbnormal implements Serializable {

    private static final long serialVersionUID = 5067554777523142477L;

    /**
     * 主键ID
     */
    private String primaryId;

    /**
     * 井ID
     */
    private String wellId;

    /**
     * 井号
     */
    private String wellName;

    /**
     * 日期
     */
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss",timezone = "Asia/Shanghai")
    private Date prodDate;

    /**
     * 生产时间
     */
    private Double prodTime;

    /**
     * 诊断结果
     */
    private String abnormalProblem;

    /**
     * 日产液量
     */
    private Double liqProdDaily;

    /**
     * 日产油量
     */
    private Double oilProdDaily;

    /**
     * 日产水量
     */
    private Double waterProdDaily;

    /**
     * 日产气量
     */
    private Double gasProdDaily;

    /**
     * 含水率
     */
    private Double waterCut;

    /**
     * 含盐
     */
    private Double saltCut;

    /**
     * 备注
     */
    private String remarks;

    /**
     * 正常产液量
     */
    private Double normalLiqProdDaily;

    /**
     * 正常含水
     */
    private Double normalWaterCut;

    /**
     * 正常含盐
     */
    private Double normalSaltCut;

    /**
     * 人工确认结果
     */
    private String artificialConfirmResult;

    /**
     * 确认时间
     */
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss",timezone = "Asia/Shanghai")
    private Date confirmDatetime;

    /**
     * 确认人
     */
    private String confirmPerson;

}
