package com.kcm.modules.diagnosis.knowledge.liquidabnormalscreen.entity;

import java.io.Serializable;
import java.util.Date;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * 流量异常筛选表（LIQ_FILTER_CONDITION）表实体
 * @author 赵庆旺
 * @version 1.0
 * @date 2020-08-19
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class LiqFilterCondition implements Serializable {
    /**
     * 井名
     */
    private String wellName;

    /**
     * 查询日期（可能会和其他表有关联）
     */
    private Date prodDate;

    /**
     * 条件
     */
    private String filter;

    /**
     * 指定日期
     */
    @JsonFormat(pattern = "yyyy-MM-dd", timezone = "Asia/Shanghai")
    private Date appointDate;

    private static final long serialVersionUID = 1L;
}