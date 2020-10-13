package com.kcm.modules.system.department.controller;

import com.kcm.common.core.domain.AjaxResult;
import com.kcm.modules.system.department.entity.SysDepartment;
import com.kcm.modules.system.department.service.SysDepartmentService;
import io.swagger.annotations.*;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;


/**
 *部门信息表（SYS_DEPARTMENT）控制类
 *
 * @author zhaoqingwang
 * @version 1.0
 * @date 2020/08/03
 */
@Api(tags = "部门管理接口")
@RestController
@RequiredArgsConstructor
@RequestMapping("/system/department")
public class SysDepartmentController {

    private final SysDepartmentService sysDepartmentService;

    /**
     * 新增全部部门信息
     *
     * @param sysDepartment 部门信息集
     * @return 新增结果
     */
    @PostMapping("/insertDepartment")
    @ApiOperation(value = "新增一个部门全部信息",notes = "insertDepartment")
    @ApiImplicitParam(name = "sysDepartment",value = "部门信息",paramType = "body",dataType = "SysDepartment",required = true)
    public AjaxResult insertDepartment(@RequestBody SysDepartment sysDepartment){
        return sysDepartmentService.insertDepartment(sysDepartment);
    }

    /**
     * 新增部分或全部部门信息
     *
     * @param sysDepartment 部门信息集
     * @return 新增结果
     */
    @ApiOperation(value = "新增一个部门部分信息",notes = "insertSelective")
    @PostMapping("/insertSelective")
    public  AjaxResult insertSelective(@RequestBody SysDepartment sysDepartment){
        return sysDepartmentService.insertSelective(sysDepartment);
    }

    /**
     * 修改部门全部信息
     *
     * @param sysDepartment 部门信息集
     * @return 修改结果
     */
    @PutMapping("updateDepartmentByPrimaryKey")
    @ApiOperation(value = "通过主键部门ID修改一个部门全部信息",notes = "updateDepartmentByPrimaryKey")
    public AjaxResult updateDepartmentByPrimaryKey(@RequestBody SysDepartment sysDepartment){
        return sysDepartmentService.updateDepartmentByPrimaryKey(sysDepartment);
    }

    /**
     * 修改部门部分信息
     *
     * @param sysDepartment 部门信息集
     * @return 修改结果
     */
    @PutMapping("/updateSelective")
    @ApiOperation(value = "修改一个部门部分信息", notes = "updateSelective")
    public AjaxResult updateSelective(@RequestBody SysDepartment sysDepartment){
        return sysDepartmentService.updateSelective(sysDepartment);
    }

    /**
     * 通过主键查询部门信息
     *
     * @param departmentId 主键部门Id
     * @return 查询结果
     */
    @GetMapping("/selectByPrimary")
    @ApiOperation(value = "通过部门Id查询部门信息", notes = "selectByPrimary" )
    @ApiImplicitParam(name = "departmentId", value = "部门Id",paramType = "query", dataType = "String", required = true)
    public AjaxResult selectByPrimary(@RequestParam("departmentId") String departmentId){
        return sysDepartmentService.selectByPrimary(departmentId);
    }

    /**
     * 查询所有部门信息
     * @return 查询结果
     */
    @GetMapping("/selectAll")
    @ApiOperation(value = "查询所有部门信息", notes = "selectAll")
    public AjaxResult selectAll(){
        return sysDepartmentService.selectAll();
    }

    /**
     * 通过主键删除部门信息
     *
     * @param departmentId 主键部门Id
     * @return 删除结果
     */
    @DeleteMapping("/deleteByPrimary")
    @ApiOperation(value = "通过主键部门ID删除一个部门信息")
    @ApiImplicitParam(name = "departmentId", value = "部门Id", paramType = "query",dataType = "String", required = true)
    @ApiResponses({
            @ApiResponse( code = 200,message = "部门删除成功！"),
            @ApiResponse( code = 500,message = "部门删除失败！")
    })
    public AjaxResult deleteByPrimary(@RequestParam("departmentId") String departmentId){
        return sysDepartmentService.deleteByPrimary(departmentId);
    }

    /**
     * 批量删除部门信息
     *
     * @param departmentIds 前端返回的部门Id集
     * @return 删除结果
     */
    @DeleteMapping("/deleteByAllPK")
    @ApiOperation(value = "批量删除部门信息", notes = "deleteByAllPK")
    public AjaxResult deleteByAllPick(@RequestBody List<String> departmentIds){
        return sysDepartmentService.deleteAllByPick(departmentIds);
    }

    /**
     * 通过父部门ID查询子部门信息
     *
     * @param parentDepartmentId 父部门Id
     * @return 子部门列表
     */
    @GetMapping("/selectByParentId")
    @ApiOperation(value = "通过父部门ID查询子部门信息",notes = "selectByParentId")
    @ApiImplicitParam(name = "parentDepartmentId",value = "父部门ID",paramType = "query",dataType = "String",required = true)
    public AjaxResult  selectByParentId(@RequestParam("parentDepartmentId") String parentDepartmentId){
        return sysDepartmentService.selectByParentId(parentDepartmentId);
    }

    /**
     * 获取部门树
     *
     * @return 部门树
     */
    @GetMapping("/getDepartmentTree")
    @ApiOperation(value = "获取部门菜单树", notes = "getDepartmentTree")
    public AjaxResult getDepartmentTree(){
        return sysDepartmentService.getDepartmentTree();
    }

//    @GetMapping("/getDepartmentAndUser")
//    public  List<SysDepartmentVo> getDepartmentAndUser(){
//        return sysDepartmentService.getDepartmentAndUser();
//    }

    /**
     * 通过部门ID查询叶子部门
     *
     * @param parentId 部门ID
     * @return 查询结果
     */
    @GetMapping("/getDepartmentTreetop")
    @ApiOperation(value = "获取叶子部门", notes = "getDepartmentTreeTip")
    @ApiImplicitParam(name = "parentId",value = "查询部门Id",paramType = "query",dataType = "string",required = true)
    public AjaxResult getDepartmentTreeTip(@RequestParam("parentId") String parentId){
        return sysDepartmentService.getDepartmentTreeTip(parentId);
    }

}