package com.kcm.modules.diagnosis.knowledge.diagnosticparametersgt.entity;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;
import lombok.Data;

/**
 * CD_WELL_SOURCE
 * @author  zhaoqingwang
 */
@Data
public class CdWellSource implements Serializable {
    private String wellId;

    private String wellName;

    private String projectId;

    /**
     * 配水间
     */
    private String projectName;

    private String wellzoneId;

    /**
     * 层位
     */
    private String wellzoneName;

    private String stationId;

    /**
     * 注水站
     */
    private String stationName;

    private String wellGroupId;

    private String wellGroupName;

    private String wellPurposeCode;

    private String wellPurposeName;

    private String wellReasonCode;

    private String wellReasonName;

    private BigDecimal designDepth;

    private String geoDescription;

    private String structDescription;

    private BigDecimal geoOffsetEast;

    private BigDecimal geoOffsetNorth;

    private BigDecimal bottomDisplacement;

    private String pCompletionMode;

    private Date pCompletionDate;

    private Date prodDate;

    private String waterInjectionCode;

    private String waterInjectionName;

    private BigDecimal elevation;

    private BigDecimal kbElevation;

    private BigDecimal makeInclinedPoint;

    private String prodZoneCode;

    private String orgId;

    /**
     * 采油站
     */
    private String orgName;

    private Short status;

    /**
     * 1油井2水井
     */
    private Short wellType;

    private static final long serialVersionUID = 1L;
}