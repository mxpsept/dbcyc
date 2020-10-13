package com.kcm.modules.system.code.controller;

import com.kcm.common.core.domain.AjaxResult;
import com.kcm.modules.system.code.entity.SysTCodeInfor;
import com.kcm.modules.system.code.service.SysCodeService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiOperation;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * 编码详细信息表（SYS_T_CODE_INFOR）控制层
 *
 * @author zhaoqingwang
 * @date 2020/08/05
 * @version 1.0
 */
@Api(tags = "编码详细信息管理接口")
@RestController
@RequiredArgsConstructor
@RequestMapping("/system/code")
public class SysCodeController {


    private final SysCodeService sysCodeService;

    /**
     * 新增一条编码详细信息
     *
     * @param sysTCodeInfor 编码详细信息集
     * @return 新增结果
     */
    @PostMapping("/code")
    @ApiOperation(value = "新增一条编码详细信息",notes = "insert")
    public AjaxResult insert(@RequestBody SysTCodeInfor sysTCodeInfor){
        return sysCodeService.insert(sysTCodeInfor);
    }

    /**
     * 通过主键修改编码详细信息
     *
     * @param sysTCodeInfor 编码详细信息集
     * @return 修改结果
     */
    @PutMapping("/code")
    @ApiOperation(value = "通过主键codeID更新编码详细信息",notes = "updateByPrimary")
    public AjaxResult updateByPrimary(@RequestBody SysTCodeInfor sysTCodeInfor){
        return sysCodeService.updateByPrimary(sysTCodeInfor);
    }

    /**
     * 通过主键查询编码详细信息
     *
     * @param codeId 编码Id
     * @return 查询结果
     */
    @GetMapping("/code/{codeId}")
    @ApiOperation(value = "通过主键codeID查询编码详细信息",notes = "selectByPrimary")
    @ApiImplicitParam(name = "codeId",value = "编码ID",paramType = "path",dataType = "String",required = true)
    public AjaxResult selectByPrimary(@PathVariable String codeId){
        return sysCodeService.selectByPrimary(codeId);
    }

    /**
     * 通过名称查询编码详细信息
     *
     * @param codeName 名称
     * @return 查询结果
     */
    @GetMapping("/code1/{codeName}")
    @ApiOperation(value = "通过名称codeName查询编码详细信息", notes = "selectByCodeName")
    @ApiImplicitParam(name = "codeName",value = "编码名称",paramType = "path",dataType = "String",required = true)
    public AjaxResult selectByCodeName(@PathVariable String codeName){
        return sysCodeService.selectByCodeName(codeName);
    }

    /**
     * 通过编码类型表中编码名称查询编码详细信息
     *
     * @param codeTName 编码类型表中编码类型名称
     * @return 查询结果
     */
    @GetMapping("/codes/{codeTName}")
    @ApiOperation(value = "通过编码类型表中编码类型名称codeTName查询编码详细信息", notes = "selectBycodeTName")
    @ApiImplicitParam(name = "codeTName",value = "编码类型名称",paramType = "path",dataType = "String",required = true)
    public AjaxResult selectBycodeTName(@PathVariable String codeTName){
        return sysCodeService.selectBycodeTName(codeTName);
    }

    /**
     * 查询所有编码详细信息
     *
     * @return 查询结果
     */
    @ApiOperation(value = "查询所有编码详细信息", notes = "selectAll")
    @GetMapping("/codes")
    public AjaxResult selectAll(){
        return sysCodeService.selectAll();
    }

    /**
     * 通过主键删除编码详细信息
     *
     * @param codeId 编码Id
     * @return 删除结果
     */
    @DeleteMapping("/code/{codeId}")
    @ApiOperation(value = "通过主键codeId删除编码信息",notes ="deleteByPrimary")
    @ApiImplicitParam(name = "codeId",value = "编码ID",paramType = "path",dataType = "String",required = true)
    public AjaxResult deleteByPrimary(@PathVariable String codeId){
        return sysCodeService.deleteByPrimary(codeId);
    }

    /**
     *
     * @param codeIds 前端传回的编码Id集
     * @return 删除结果
     */
    @DeleteMapping("/codes")
    @ApiOperation(value = "批量删除",notes = "deleteAllByPK")
    public AjaxResult deleteAllByPick(@RequestBody List<String> codeIds){
        return sysCodeService.deleteAllByPick(codeIds);
    }

}
