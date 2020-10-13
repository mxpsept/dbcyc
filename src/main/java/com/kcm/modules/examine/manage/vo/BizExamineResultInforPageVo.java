package com.kcm.modules.examine.manage.vo;

import com.baomidou.mybatisplus.annotation.TableId;
import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.util.Date;
import java.util.List;

/**
 * @Description 考核结果分页实体
 * @Author zhaoqingwang
 * @DATE 2020/9/15 10:22
 * @Version 1.0
 **/

@Data
@Builder(toBuilder = true)
@NoArgsConstructor
@AllArgsConstructor
public class BizExamineResultInforPageVo {

    private static final long serialVersionUID = 1L;

    /**
     * 主键ID
     */
    @TableId(value = "EXAMINE_R_ID")
    private String examineRId;

    /**
     * 参考单位/人员
     */

    private String takeObject;

    /**
     * 考核时间
     */
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "Asia/Shanghai")
    private Date examineDate;

    /**
     * 总得分
     */
    private BigDecimal totalScore;

    /**
     * 考核计划ID
     */
    private String examinePId;

    /**
     * 备注
     */
    private String remark;

    /**
     * 是否有效(0:无效,1:有效,默认值为1)
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
     * 修改人单位ID
     */
    private String editDeptId;


    private String planName;

    private String templateName;

    private String examineTId;

    private List<BizResultInforIndexVo> bizResultInforIndexVoList;

}
