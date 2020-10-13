package com.kcm.modules.diagnosis.knowledge.standard.entity;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;

import com.baomidou.mybatisplus.annotation.TableId;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * STANDARD_CARD_LIB
 * @author lucky
 */
@Data
@Builder(toBuilder = true)
@NoArgsConstructor
@AllArgsConstructor
public class StandardCardLib implements Serializable {

    @TableId(value = "PRIMARY_ID")
    private String primaryId;

    private String wellId;

    private String wellCommonName;

    private Date checkDate;

    private String dyna;

    private BigDecimal stroke;

    private BigDecimal frequency;

    private BigDecimal suspMaxLoad;

    private BigDecimal suspMinLoad;

    private BigDecimal upgoingMaxCurrent;

    private BigDecimal downgoingMaxCurrent;

    private String label;

    private Date authDate;

    private String author;

    private String dynaId;

    private static final long serialVersionUID = 1L;
}