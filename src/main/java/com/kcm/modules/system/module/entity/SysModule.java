package com.kcm.modules.system.module.entity;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;
import java.util.List;

/**
 * SYS_MODULE
 * @author 
 */
public class SysModule implements Serializable {
    /**
     * 模块ID
     */
    private String moduleId;

    /**
     * 模块名称
     */
    private String moduleName;

    /**
     * 父模块ID
     */
    private String parentModuleId;

    /**
     * 访问地址
     */
    private String moduleUrl;

    /**
     * 模块类型(0:目录,1:菜单,2:按钮)
     */
    private String moduleType;

    /**
     * 权限标识
     */
    private String permissionMark;

    /**
     * 菜单图标
     */
    private String icon;

    /**
     * 显示顺序
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

    private List<SysModule> children;

    public List<SysModule> getChildren() {
        return children;
    }

    public void setChildren(List<SysModule> children) {
        this.children = children;
    }

    private static final long serialVersionUID = 1L;

    public String getModuleId() {
        return moduleId;
    }

    public void setModuleId(String moduleId) {
        this.moduleId = moduleId;
    }

    public String getModuleName() {
        return moduleName;
    }

    public void setModuleName(String moduleName) {
        this.moduleName = moduleName;
    }

    public String getParentModuleId() {
        return parentModuleId;
    }

    public void setParentModuleId(String parentModuleId) {
        this.parentModuleId = parentModuleId;
    }

    public String getModuleUrl() {
        return moduleUrl;
    }

    public void setModuleUrl(String moduleUrl) {
        this.moduleUrl = moduleUrl;
    }

    public String getModuleType() {
        return moduleType;
    }

    public void setModuleType(String moduleType) {
        this.moduleType = moduleType;
    }

    public String getPermissionMark() {
        return permissionMark;
    }

    public void setPermissionMark(String permissionMark) {
        this.permissionMark = permissionMark;
    }

    public String getIcon() {
        return icon;
    }

    public void setIcon(String icon) {
        this.icon = icon;
    }

    public BigDecimal getSequence() {
        return sequence;
    }

    public void setSequence(BigDecimal sequence) {
        this.sequence = sequence;
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

    public SysModule(String moduleId, String moduleName, String parentModuleId, String moduleUrl, String moduleType, String permissionMark, String icon, BigDecimal sequence, String remark, String active, String createUserId, Date createTime, String createDeptId, String editUserId, Date editTime, String editDeptId) {
        this.moduleId = moduleId;
        this.moduleName = moduleName;
        this.parentModuleId = parentModuleId;
        this.moduleUrl = moduleUrl;
        this.moduleType = moduleType;
        this.permissionMark = permissionMark;
        this.icon = icon;
        this.sequence = sequence;
        this.remark = remark;
        this.active = active;
        this.createUserId = createUserId;
        this.createTime = createTime;
        this.createDeptId = createDeptId;
        this.editUserId = editUserId;
        this.editTime = editTime;
        this.editDeptId = editDeptId;
    }
}