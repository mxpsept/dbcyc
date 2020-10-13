package com.kcm.modules.examine.standard.vo;

import com.baomidou.mybatisplus.annotation.TableId;
import com.kcm.modules.examine.standard.entity.BizExamineIndexInfor;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;
import java.util.List;

/**
 * BIZ_EXAMINE_TEMPLATE_INFOR
 * 考核模板信息表实体类
 *
 * @author lucky
 */
@Data
@Builder(toBuilder = true)
@NoArgsConstructor
@AllArgsConstructor
public class BizExamineTemplateInforVo implements Serializable {
    private static final long serialVersionUID = 1L;

    /**
     * 主键ID
     */
    @TableId(value = "EXAMINE_T_ID")
    private String examineTId;

    /**
     * 模板名称
     */
    private String templateName;

    /**
     * 制定单位
     */
    private String formulationUnit;

    /**
     * 制定时间
     */
    private Date formulationDate;

    /**
     * 排列顺序
     */
    private BigDecimal sequence;

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
    private Date editTime;

    /**
     * 修改人单位ID
     */
    private String editDeptId;

    /**
     * 考核指标信息
     */
    private List<BizExamineIndexInfor> bizExamineIndexInfors;

}