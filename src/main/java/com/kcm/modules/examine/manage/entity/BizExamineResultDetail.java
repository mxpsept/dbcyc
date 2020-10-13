package com.kcm.modules.examine.manage.entity;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;

import com.baomidou.mybatisplus.annotation.TableId;
import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.*;

/**
 * BIZ_EXAMINE_RESULT_DETAIL
 * 考核结果明细表实体类
 * @author lucky
 */
@Data
@Builder(toBuilder = true)
@NoArgsConstructor
@AllArgsConstructor
public class BizExamineResultDetail implements Serializable {
    /**
     * 主键ID
     */
    @TableId(value = "EXAMINE_RD_ID")
    private String examineRdId;

    /**
     * 得分
     */
    private BigDecimal singleScore;

    /**
     * 评分人
     */
    private String rater;

    /**
     * 评分时间
     */
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "Asia/Shanghai")
    private Date rateDate;

    /**
     * 考核指标明细ID
     */
    private String indexDId;

    /**
     * 考核结果ID
     */
    private String examineRId;

    /**
     * 备注
     */
    private String remark;

    /**
     * 是否有效（0：无效，1：有效，默认值为1）
     */
    private String active;

    /**
     * 创建人ID
     */
    private String createUserId;

    /**
     * 创建时间
     */
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "Asia/Shanghai")
    private Date createTime;

    /**
     * 创建单位ID
     */
    private String createDeptId;

    /**
     * 修改人ID
     */
    private String editUserId;

    /**
     * 修改时间
     */
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "Asia/Shanghai")
    private Date editTime;

    /**
     * 修改单位ID
     */
    private String editDeptId;

    private static final long serialVersionUID = 1L;
}