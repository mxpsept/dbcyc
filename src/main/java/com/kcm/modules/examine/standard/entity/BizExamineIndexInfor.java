package com.kcm.modules.examine.standard.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import com.baomidou.mybatisplus.extension.activerecord.Model;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;

/**
 * <p>
 * 考核指标信息表
 * </p>
 *
 * @author xublu
 * @since 2020-08-24
 */
@Data
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
@TableName("BIZ_EXAMINE_INDEX_INFOR")
@ApiModel(value="BizExamineIndexInforEntity对象", description="考核指标信息表")
public class BizExamineIndexInfor extends Model<BizExamineIndexInfor> {

    private static final long serialVersionUID=1L;

    @ApiModelProperty(value = "主键ID")
    @TableId(value = "INDEX_ID", type = IdType.ASSIGN_ID)
    private String indexId;

    @ApiModelProperty(value = "指标名称")
    private String indexName;

    @ApiModelProperty(value = "上级指标ID")
    private String indexPId;

    @ApiModelProperty(value = "分值权重")
    private BigDecimal scoreWeight;

    @ApiModelProperty(value = "排列顺序")
    private BigDecimal sequence;

    @ApiModelProperty(value = "考核模板ID")
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


    @Override
    protected Serializable pkVal() {
        return this.indexId;
    }

}

