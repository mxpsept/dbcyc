package com.kcm.modules.system.module.controller;

import com.kcm.common.core.domain.AjaxResult;
import com.kcm.modules.system.module.entity.SysModule;
import com.kcm.modules.system.module.service.ISysModuleService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;
import springfox.documentation.annotations.ApiIgnore;

import java.util.List;

/**
 * @author: lucky
 * @date: 2020/7/17
 * @description: 菜单信息表(SysModule)控制层
 **/
@Api(tags = "菜单管理接口")
@RestController
@RequestMapping("/system/sysModule")
@RequiredArgsConstructor
public class SysModuleController {

    private final ISysModuleService sysModuleService;

    /***
     * 通过菜单id查询菜单信息
     * @author lucky
     * @date 2020/7/21
     * @param moduleId 参数(菜单主键)
     * @return 实例对象
     **/
    @ApiIgnore
    @ApiOperation(value = "通过id查询菜单",notes = "queryById")
    @GetMapping(value = "/queryById/{moduleId}")
    public SysModule queryById(@PathVariable("moduleId") String moduleId) {
        return sysModuleService.queryById(moduleId);
    }

    /***
     * 查询所有菜单数据
     * @author lucky
     * @date 2020/7/21
     * @return 菜单列表
     **/
    @ApiIgnore
    @GetMapping(value = "/queryAll")
    public List<SysModule> queryAll() {
        return sysModuleService.queryAll();
    }

    /***
     * 新增菜单数据
     * @author lucky
     * @date 2020/7/21
     * @param sysModule 实例对象
     * @return 影响行数
     **/
    @ApiOperation(value = "新增菜单",notes = "insert")
    @PostMapping(value = "/insert")
    public AjaxResult insert(@RequestBody SysModule sysModule) {
        return sysModuleService.insertSelective(sysModule);
    }

    /***
     * 获取菜单树
     * @author lucky
     * @date 2020/8/4
     * @return 菜单树
     **/
    @ApiOperation(value = "获取菜单树",notes = "getSysModuleTree")
    @GetMapping("/getSysModuleTree")
    public AjaxResult getSysModuleTree() {
        return sysModuleService.getSysModuleTree();
    }

    /***
     * 修改菜单信息
     * @author lucky
     * @date 2020/8/6
     * @param sysModule 实例对象
     * @return 数据库记录id
     **/
    @ApiOperation(value = "修改菜单信息",notes = "updateByPrimaryKey")
    @PostMapping("/update")
    public AjaxResult updateByPrimaryKey(@RequestBody SysModule sysModule){
        return sysModuleService.updateByPrimaryKey(sysModule);
    }

    /***
     * 根据主键删除菜单
     * @author lucky
     * @date 2020/8/6
     * @param moduleId 主键
     * @return 数据库操作id
     **/
    @ApiOperation(value = "删除菜单信息",notes = "deleteByPrimaryKey")
    @DeleteMapping("/delete/{moduleId}")
    public AjaxResult deleteByPrimaryKey(@PathVariable("moduleId") String moduleId){
        return sysModuleService.deleteByPrimaryKey(moduleId);
    }


}
