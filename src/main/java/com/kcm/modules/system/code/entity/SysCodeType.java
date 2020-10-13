package com.kcm.modules.system.code.entity;


import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import com.kcm.common.core.BasePublicModel;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.io.Serializable;
import java.util.Date;

/**
 * 编码类型表（SYS_T_CODE_TYPE_INFOR）实体
 *
 * @author zhaoqingwang
 * @version 1.0
 * @date 2020/08/04
 */
@Data
@EqualsAndHashCode(callSuper = true)
@TableName(value = "SYS_T_CODE_TYPE_INFOR", resultMap = "codeTypeMap")
public class SysCodeType extends BasePublicModel implements Serializable {
    private static final long serialVersionUID = 867172641851984666L;

    //编码类型数据库（SYS_T_CODE_TYPE_INFOR）数据映射


    /**
     * 编码类型Id
     */
    @TableId(value = "CODE_T_ID")
    private String codeTypeId;

    /**
     * 编码类型Id
     */
    private String codeTName;

    /**
     * 编码类型
     */
    private String codeType;

    /**
     * 备注
     */
    private String remark;

    /**
     * 是否有效（0：无效  1：有效 默认值为1）
     */
    private String active;

    /**
     * 创建者Id
     */
    private String createUserId;

    /**
     * 创建时间
     */
    private Date createTime;

    /**
     * 创建部门Id
     */
    private String createDeptId;

    /**
     * 修改者Id
     */
    private String editUserId;

    /**
     * 修改时间
     */
    private Date editTime;

    /**
     * 修改部门Id
     */
    private String editDeptId;

    public String getCodeTypeId() {
        return codeTypeId;
    }

    public void setCodeTypeId(String codeTypeId) {
        this.codeTypeId = codeTypeId;
    }

    public String getcodeTName() {
        return codeTName;
    }

    public void setcodeTName(String codeTName) {
        this.codeTName = codeTName;
    }

    public String getCodeType() {
        return codeType;
    }

    public void setCodeType(String codeType) {
        this.codeType = codeType;
    }

    public String getRemark() {
        return remark;
    }

    public void setRemark(String remark) {
        this.remark = remark;
    }

    public String getActive() {
        return active;
    }

    public void setActive(String active) {
        this.active = active;
    }

    public String getCreateUserId() {
        return createUserId;
    }

    public void setCreateUserId(String createUserId) {
        this.createUserId = createUserId;
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    public String getCreateDeptId() {
        return createDeptId;
    }

    public void setCreateDeptId(String createDeptId) {
        this.createDeptId = createDeptId;
    }

    public String getEditUserId() {
        return editUserId;
    }

    public void setEditUserId(String editUserId) {
        this.editUserId = editUserId;
    }

    public Date getEditTime() {
        return editTime;
    }

    public void setEditTime(Date editTime) {
        this.editTime = editTime;
    }

    public String getEditDeptId() {
        return editDeptId;
    }

    public void setEditDeptId(String editDeptId) {
        this.editDeptId = editDeptId;
    }
}
