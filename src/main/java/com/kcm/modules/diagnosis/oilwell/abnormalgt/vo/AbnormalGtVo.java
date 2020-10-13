package com.kcm.modules.diagnosis.oilwell.abnormalgt.vo;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.kcm.modules.diagnosis.oilwell.abnormalgt.entity.AbnormalGt;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;
import java.util.List;

/**
 * 功图数据异常Vo
 *
 * @author  zhaoqingwang
 * @date  2020/9/17 13:57
 * @version  1.0
 **/
@Data
@NoArgsConstructor
@AllArgsConstructor
public class AbnormalGtVo implements Serializable {

    private String primaryId;

    private String wellId;

    private String wellCommonName;

    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "Asia/Shanghai")
    private Date checkDate;

    private String dyna;

    private BigDecimal stroke;

    private BigDecimal frequency;

    private BigDecimal suspMaxLoad;

    private BigDecimal suspMinLoad;

    private String abnormalProblem;

    private String orgName;

    private String rn;

    private List<AbnormalGt> children;

    private static final long serialVersionUID = 1L;
}
