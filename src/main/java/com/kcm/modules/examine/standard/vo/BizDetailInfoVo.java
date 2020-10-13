package com.kcm.modules.examine.standard.vo;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.kcm.modules.examine.standard.entity.BizExamineIndexDetail;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.math.BigDecimal;
import java.util.Date;
import java.util.List;

/**
 * 绩效考核信息表和绩效考核明细表组合实体类
 */
@Data
public class BizDetailInfoVo {

    private static final long serialVersionUID=1L;

    @ApiModelProperty(value = "主键ID")
    @TableId(value = "INDEX_ID", type = IdType.ASSIGN_ID)
    private String indexId;

    @ApiModelProperty(value = "指标名称")
    private String indexName;

    @ApiModelProperty(value = "上级指标ID") //转成指标名称
    private String indexPId;

    @ApiModelProperty(value = "分值权重")
    private BigDecimal scoreWeight;

    @ApiModelProperty(value = "排列顺序")
    private BigDecimal sequence;

    @ApiModelProperty(value = "考核模板ID") //转成名称传过去
    private String examineTId;

    @ApiModelProperty(value = "备注")
    private String remark;

    @ApiModelProperty(value = "是否有效(0:无效,1:有效,默认值为1)")
    private String active;

    @ApiModelProperty(value = "创建人ID")
    private String createUserId;

    @ApiModelProperty(value = "创建时间")
    private Date createTime;

    @ApiModelProperty(value = "创建单位ID")
    private String createDeptId;

    @ApiModelProperty(value = "修改人ID")
    private String editUserId;

    @ApiModelProperty(value = "修改时间")
    private Date editTime;

    @ApiModelProperty(value = "修改人单位ID")
    private String editDeptId;

    // 考核明细
    private List<BizExamineIndexDetail> indexDetails;
}
