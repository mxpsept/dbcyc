package com.kcm.modules.system.department.dao;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.kcm.modules.system.department.entity.SysDepartment;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

/**
 *部门信息表（SYS_DEPARTMENT）数据库访问层接口
 *
 * @author zhaoqingwang
 * @version 1.0
 * @date 2020/08/03
 */
@Mapper
public interface SysDepartmentMapper extends BaseMapper<SysDepartment> {

    /**
     * 增加部门信息
     *
     * @param sysDepartment 部门信息集
     * @return 新增结果
     */
    Integer insertDepartment(SysDepartment sysDepartment);

    /**
     * 新增部门部分或全部信息
     *
     * @param sysDepartment 部门信息集
     * @return 新增结果
     */
    Integer insertSelective(SysDepartment sysDepartment);

    /**
     * 修改部门全部信息
     *
     * @param sysDepartment 部门信息集
     * @return 修改结果
     */
    Integer updateDepartmentByPrimaryKey(SysDepartment sysDepartment);

    /**
     * 修改部门全部或部分信息
     *
     * @param sysDepartment 部门信息集
     * @return 修改结果
     */
    Integer updateSelective(SysDepartment sysDepartment);

    /**
     * 通过主键查询部门信息
     *
     * @param departmentId 主键部门Id
     * @return 查询结果
     */
    SysDepartment selectByPrimary(String departmentId);

    /**
     * 查询所有编码类型（分页）
     *
//     * @param paramMap
     * @return 查询结果
     */
    List<SysDepartment> selectAll();

    /**
     * 通过主键删除部门信息
     *
     * @param departmentId 主键部门Id
     * @return 删除结果
     */
    Integer deleteByPrimary(String departmentId);


    /**
     * 通过父部门ID查询下级子部门
     *
     * @param parentDepartmentId 父部门Id
     * @return 子部门
     */
    List<SysDepartment> selectByParentId(String parentDepartmentId);

    /**
     * 通过部门名称查询部门信息
     *
     * @param departmentName 部门名称
     * @return 部门信息
     */
    SysDepartment selectByDepartmentName(String departmentName);

}