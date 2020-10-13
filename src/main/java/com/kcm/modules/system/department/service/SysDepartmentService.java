package com.kcm.modules.system.department.service;

import com.kcm.common.core.domain.AjaxResult;
import com.kcm.modules.system.department.entity.SysDepartment;

import java.util.List;

/**
 *部门信息表（SYS_DEPARTMENT）服务层接口
 *
 * @author zhaoqingwang
 * @version 1.0
 * @date 2020/08/03
 */
public interface SysDepartmentService {


    /**
     * 新增部门信息
     *
     * @param sysDepartment 部门信息集
     * @return 新增结果
     */
    AjaxResult insertDepartment(SysDepartment sysDepartment);

    /**
     * 新增部门部分或全部信息
     *
     * @param sysDepartment 部门信息集
     * @return 新增结果
     */
    AjaxResult insertSelective(SysDepartment sysDepartment);

    /**
     * 修改部门全部信息
     *
     * @param sysDepartment 部门信息集
     * @return 修改结果
     */
    AjaxResult updateDepartmentByPrimaryKey(SysDepartment sysDepartment);


    /**
     * 修改部门全部或部分信息
     *
     * @param sysDepartment 部门信息集
     * @return 修改结果
     */
    AjaxResult updateSelective(SysDepartment sysDepartment);

    /**
     * 通过主键查询部门信息
     *
     * @param departmentId 主键部门Id
     * @return 查询结果
     */
    AjaxResult selectByPrimary(String departmentId);

    /**
     * 查询所有部门信息
     * @return 查询结果
     */
    AjaxResult  selectAll();

    /**
     * 通过主键删除部门信息
     *
     * @param departmentId 主键部门Id
     * @return 删除结果
     */
    AjaxResult deleteByPrimary(String departmentId);

    /**
     * 批量删除部门信息
     *
     * @param departmentIds 前端返回的部门IdJ集
     * @return 删除结果
     */
    AjaxResult deleteAllByPick(List<String> departmentIds);


    /**
     * 通过父部门Id查询子部门
     *
     * @param parentDepartmentId 父部门Id
     * @return 子部门
     */
    AjaxResult  selectByParentId(String parentDepartmentId);

    /**
     * 获取部门菜单树
     *
     * @return 返回部门菜单树
     */
    AjaxResult getDepartmentTree();


//    List<SysDepartmentVo> getDepartmentAndUser();


    /**
     * 通过部门ID查询叶子部门
     *
     * @param parentId 部门ID
     * @return 查询结果
     */
    AjaxResult getDepartmentTreeTip(String parentId);


    /**
     * 通过部门Id获得子部门Id列表
     *
     * @param departmentId 部门Id
     * @return 子部门Id列表
     */
    List<String> getAllChildDepartmentId(String departmentId);


}
