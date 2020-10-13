package com.kcm.modules.examine.manage.vo;

import com.baomidou.mybatisplus.annotation.TableId;
import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.util.Date;

/**
 * @Description 考核结果明细Vo
 * @Author zhaoqingwang
 * @DATE 2020/9/15 12:55
 * @Version 1.0
 **/

@Data
@Builder(toBuilder = true)
@NoArgsConstructor
@AllArgsConstructor
public class BizExamineResultVo {

    private static final long serialVersionUID = 1L;

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

    private BigDecimal scoreWeight;
}
