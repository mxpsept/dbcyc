package com.kcm.modules.examine.standard.entity;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;

import com.baomidou.mybatisplus.annotation.TableId;
import com.fasterxml.jackson.annotation.JsonFormat;
import com.kcm.common.core.BasePublicModel;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * BIZ_EXAMINE_INDEX_DETAIL
 * 考核指标明细信息表实体类
 *
 * @author lucky
 */
@Data
@Builder(toBuilder = true)
@NoArgsConstructor
@AllArgsConstructor
public class BizExamineIndexDetail extends BasePublicModel implements Serializable {
    /**
     * 主键ID
     */
    @TableId(value = "INDEX_D_ID")
    private String indexDId;

    /**
     * 考核内容
     */
    private String examineContent;

    /**
     * 工作要求
     */
    private String requirement;

    /**
     * 考核标准
     */
    private String examineStandard;

    /**
     * 分值
     */
    private BigDecimal score;

    /**
     * 排列顺序
     */
    private BigDecimal sequence;

    /**
     * 考核指标ID
     */
    private String indexId;

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

    private static final long serialVersionUID = 1L;
}