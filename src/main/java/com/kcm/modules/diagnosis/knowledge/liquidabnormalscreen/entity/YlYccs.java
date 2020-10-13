package com.kcm.modules.diagnosis.knowledge.liquidabnormalscreen.entity;

import java.io.Serializable;
import java.math.BigDecimal;
import lombok.Data;

/**
 * 液量异常筛选条件表（YL_YCCS）表实体
 *
 */
@Data
public class YlYccs implements Serializable {
    private String wellName;

    private BigDecimal cs1;

    private BigDecimal cs2;

    private BigDecimal cs3;

    private BigDecimal cs4;

    private BigDecimal cs5;

    private static final long serialVersionUID = 1L;
}