package com.kcm.modules.system.employee.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import com.baomidou.mybatisplus.extension.activerecord.Model;
import com.fasterxml.jackson.annotation.JsonFormat;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;

import java.io.Serializable;
import java.util.Date;

/**
 * <p>
 * 职务信息表
 * </p>
 *
 * @author xublu
 * @since 2020-08-12
 */
@Data
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
@TableName("SYS_POSITION")
@ApiModel(value="SysPositionEntity对象", description="职务信息表")
public class SysPositionEntity extends Model<SysPositionEntity> {

    private static final long serialVersionUID=1L;

    @ApiModelProperty(value = "职务主键ID")
    @TableId(value = "POSITION_ID", type = IdType.ASSIGN_ID)
    private String positionId;

    @ApiModelProperty(value = "职务编码")
    private String positionCode;

    @ApiModelProperty(value = "职务名称")
    private String positionName;

    @ApiModelProperty(value = "排列顺序")
    private String sequence;

    @ApiModelProperty(value = "备注")
    private String remark;

    @ApiModelProperty(value = "是否有效(0:无效,1:有效,默认值为1)")
    private String active;

    @ApiModelProperty(value = "创建人ID")
    private String createUserId;

    @ApiModelProperty(value = "创建时间")
    @JsonFormat(shape=JsonFormat.Shape.STRING,pattern="yyyy-MM-dd HH:mm:ss",timezone="GMT+8")
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
        return this.positionId;
    }

}
