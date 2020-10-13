package com.kcm.modules.diagnosis.knowledge.tile.entity;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * 功图平铺(PC_FD_PUMPJACK_DYNA_DIA_T)实体类
 * @author lucky
 */
@Data
@Builder(toBuilder = true)
@NoArgsConstructor
@AllArgsConstructor
public class PcFdPumpjackDynaDiaT implements Serializable {
    private String dynaId;

    private String wellCommonName;

    private String wellId;

    private Date checkDate;

    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "Asia/Shanghai")
    private Date dynaCreateTime;

    private BigDecimal stroke;

    private BigDecimal frequency;

    private Short dynaPoints;

    private String displacement;

    private String dispLoad;

    private String dispCurrent;

    private String activePower;

    private String reactivePower;

    private String powerFactor;

    private BigDecimal suspMaxLoad;

    private BigDecimal suspMinLoad;

    private BigDecimal upgoingMaxCurrent;

    private BigDecimal downgoingMaxCurrent;

    private String dyna;

    private static final long serialVersionUID = 1L;
}