package com.kcm.modules.system.department.entity;

import com.baomidou.mybatisplus.annotation.TableField;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

/**
 *部门信息表（SysDepartment）实体类
 *
 * @author zhaoqingwang
 * @version 1.0
 * @date 2020/08/03
 */
public class SysDepartment implements Serializable {
    private static final long serialVersionUID = 867172641851984666L;





    /**
     * 部门Id
     */
    private String departmentId;

    /**
     * 父部门Id
     */
    private String parentDepartmentId;

    /**
     * 部门名称
     */
    private String departmentName;

    /**
     * 排列顺序
     */
    private Integer sequence;

    /**
     * 联系电话
     */
    private String phone;

    /**
     * 邮箱
     */
    private String email;

    /**
     * 部门类型（0：内部单位 1：管理机构 默认值为1）
     */
    private String departmentType;

    /**
     * 备注
     */
    private String remark;

    /**
     * 是否有效（0：无效 1：有效 默认为1）
     */
    private String active;

    /**
     * 创建人Id
     */
    private String createUserId;

    /**
     * 创建时间
     */
    private Date createTime;

    /**
     * 创建单位Id
     */
    private String createDeptId;

    /**
     * 修改人Id
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

    /**
     * 子部门
     */
    private List<SysDepartment> children;



    public String getDepartmentId() {
        return departmentId;
    }

    public void setDepartmentId(String departmentId) {
        this.departmentId = departmentId;
    }

    public String getParentDepartmentId() {
        return parentDepartmentId;
    }

    public void setParentDepartmentId(String parentDepartmentId) {
        this.parentDepartmentId = parentDepartmentId;
    }

    public String getDepartmentName() {
        return departmentName;
    }

    public void setDepartmentName(String departmentName) {
        this.departmentName = departmentName;
    }

    public Integer getSequence() {
        return sequence;
    }

    public void setSequence(Integer sequence) {
        this.sequence = sequence;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getDepartmentType() {
        return departmentType;
    }

    public void setDepartmentType(String departmentType) {
        this.departmentType = departmentType;
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

    public List<SysDepartment> getChildren() {
        return children;
    }

    public void setChildren(List<SysDepartment> children) {
        this.children = children;
    }

    @Override
    public String toString() {
        return "SysDepartment{" +
                "departmentId='" + departmentId + '\'' +
                ", parentDepartmentId='" + parentDepartmentId + '\'' +
                ", departmentName='" + departmentName + '\'' +
                ", sequence=" + sequence +
                ", phone='" + phone + '\'' +
                ", email='" + email + '\'' +
                ", departmentType='" + departmentType + '\'' +
                ", remark='" + remark + '\'' +
                ", active='" + active + '\'' +
                ", createUserId='" + createUserId + '\'' +
                ", createTime=" + createTime +
                ", createDeptId='" + createDeptId + '\'' +
                ", editUserId='" + editUserId + '\'' +
                ", editTime=" + editTime +
                ", editDeptId='" + editDeptId + '\'' +
                '}';
    }

    public SysDepartment(String departmentId,String parentDepartmentId,String departmentName,Integer sequence,String phone,String email,
                         String departmentType,String remark,String active,String createUserId,Date createTime,String createDeptId,
                         String editUserId,Date editTime,String editDeptId){
        this.departmentId=departmentId;
        this.parentDepartmentId=parentDepartmentId;
        this.departmentName=departmentName;
        this.sequence=sequence;
        this.phone=phone;
        this.email=email;
        this.departmentType=departmentType;
        this.remark=remark;
        this.active=active;
        this.createUserId=createUserId;
        this.createTime=createTime;
        this.createDeptId=createDeptId;
        this.editUserId=editUserId;
        this.editTime=editTime;
        this.editDeptId=editDeptId;
    }

    public SysDepartment(){}
}
