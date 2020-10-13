package com.kcm.modules.examine.manage.vo;

import com.baomidou.mybatisplus.annotation.TableId;
import com.fasterxml.jackson.annotation.JsonFormat;
import com.kcm.common.core.BasePublicModel;
import com.kcm.modules.examine.standard.entity.BizExamineIndexDetail;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;

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
public class BizExamineIndexDetailVo extends BasePublicModel implements Serializable {

    /**
     * 主键ID
     */
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
     * 考核结果id
     */
    private String examineRId;

    /**
     * 指标名称
     */
    private String indexName;

    /**
     * 分值权重
     */
    private BigDecimal scoreWeight;

    /**
     * 得分
     */
    private BigDecimal singleScore;



    private static final long serialVersionUID = 1L;
}