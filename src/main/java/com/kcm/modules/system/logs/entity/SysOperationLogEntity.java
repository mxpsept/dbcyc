package com.kcm.modules.system.logs.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import com.baomidou.mybatisplus.extension.activerecord.Model;
import com.fasterxml.jackson.annotation.JsonFormat;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Builder;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;

import java.io.Serializable;
import java.util.Date;

/**
 * <p>
 * 系统操作日志表
 * </p>
 *
 * @author xublu
 * @since 2020-08-11
 */
@Data
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
@Builder
@TableName("SYS_OPERATION_LOG")
@ApiModel(value="SysOperationLogEntity对象", description="系统操作日志表")
public class SysOperationLogEntity extends Model<SysOperationLogEntity> {

    private static final long serialVersionUID=1L;

    @ApiModelProperty(value = "日志主键")
    @TableId(value = "OPERATION_ID", type = IdType.ASSIGN_ID)
    private String operationId;

    @ApiModelProperty(value = "模块名称")
    private String moduleName;

    @ApiModelProperty(value = "操作类型(-1:未定义,0:新增,1:更新,2:删除,3:授权,4:导入,5:导出,6:重置,7:登录,8:退出)")
    private String operationType;

    @ApiModelProperty(value = "类名")
    @TableField("CLASS")
    private String class1;

    @ApiModelProperty(value = "方法名称")
    private String method;

    @ApiModelProperty(value = "来源渠道(0:PC端用户,1:移动端用户,99:其他)")
    private String channel;

    @ApiModelProperty(value = "操作人员")
    private String operatorName;

    @ApiModelProperty(value = "部门名称")
    private String departmentName;

    @ApiModelProperty(value = "请求URL")
    private String requestUrl;

    @ApiModelProperty(value = "请求IP")
    private String requestIp;

    @ApiModelProperty(value = "操作地点")
    private String requestLocation;

    @ApiModelProperty(value = "请求参数")
    private String requestParam;

    @ApiModelProperty(value = "接收参数[方法实参]")
    private String methodParam;

    @ApiModelProperty(value = "操作状态(0:正常,1:异常)")
    private String status;

    @ApiModelProperty(value = "操作消息")
    private String operationMsg;

    @ApiModelProperty(value = "操作详情[记录数据变化详情]")
    private String operationDetail;

    @ApiModelProperty(value = "操作时间")
    @JsonFormat(shape=JsonFormat.Shape.STRING,pattern="yyyy-MM-dd HH:mm:ss",timezone="GMT+8")
    private Date operationTime;

    @ApiModelProperty(value = "是否有效(0:无效,1:有效,默认值为1)")
    private String active;


    @Override
    protected Serializable pkVal() {
        return this.operationId;
    }

}
