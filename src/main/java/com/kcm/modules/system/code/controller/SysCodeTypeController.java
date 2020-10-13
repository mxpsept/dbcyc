package com.kcm.modules.system.code.controller;

import cn.hutool.core.exceptions.ExceptionUtil;
import com.kcm.common.core.domain.AjaxResult;
import com.kcm.common.enums.ResultCode;
import com.kcm.common.other.StringUtils;
import com.kcm.modules.system.code.entity.SysCodeType;
import com.kcm.modules.system.code.service.SysCodeService;
import com.kcm.modules.system.code.service.SysCodeTypeService;
import com.kcm.modules.system.code.vo.SysCodeTypeVo;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.BeanUtils;
import org.springframework.web.bind.annotation.*;
import java.util.List;


/**
 * 编码类型表（SYS_T_CODE_TYPE_INFOR）表控制层
 *
 * @author zhaoqingwang
 * @version 1.0
 * @date 2020/08/05
 */
@RestController
@RequiredArgsConstructor
@RequestMapping("/system/codeType")
@Api(tags = "编码类型管理接口")
public class SysCodeTypeController {


    private final SysCodeTypeService sysCodeTypeService;
    private final SysCodeService sysCodeService;

    /**
     * 新增编码类型
     *
     * @param sysCodeTypeVo 编码类型
     * @return 新增结果
     */
    @PostMapping("/codeType")
    @ApiOperation(value = "插入一条编码类型信息",notes = "insertCodeType")
    public AjaxResult insertCodeType(@RequestBody SysCodeTypeVo sysCodeTypeVo){
        try {
            return AjaxResult.success(ResultCode.SUCCESS_ADD,sysCodeTypeService.insertCodeType(sysCodeTypeVo));
        }catch (Exception e){
            return AjaxResult.error(ResultCode.ERR_SQL_INSERT_ERROR,ExceptionUtil.getRootCauseMessage(e));
        }
    }

    /**
     * 通过主键查询编码类型信息
     *
     * @param codeTypeId 主键编码类型Id
     * @return 查询结果
     */
    @GetMapping("/codeType/{codeTypeId}")
    @ApiOperation(value = "通过主键codeTypeId查询编码类型信息", notes = "selectByCodeTypeId")
    @ApiImplicitParam(name = "codeTypeId", value = "编码类型id",paramType = "path",dataType = "String",required = true)
    public AjaxResult selectByPrimary(@PathVariable String codeTypeId){
        try {
            return AjaxResult.success(ResultCode.SUCCESS_QUERY,sysCodeTypeService.selectBySysCodeTypeId(codeTypeId));
        }catch (Exception e){
            return AjaxResult.error(ResultCode.ERR_SQL_UPDATE_ERROR,ExceptionUtil.getRootCauseMessage(e));
        }
    }



    /**
     * 条件查询（分页插件）
     *
     * @param current 当前页
     * @param pageSize 页面尺寸
     * @param codeType 编码类型
     * @param codeTName 编码类型名称
     * @return 查询结果
     */
    @GetMapping("/codeTypes1")
    @ApiOperation(value = "通过编码类型或编码类型名称查询",notes = "selectByTerm")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "codeType",value = "编码类型",paramType = "query",dataType = "String",required = false),
            @ApiImplicitParam(name = "codeTName",value = "编码类型名称",paramType = "query",dataType = "String",required = false),
    })
    public AjaxResult selectByTerm(
            @RequestParam(value = "codeTName",required = false) String codeTName,
            @RequestParam(value = "codeType",required = false) String codeType,
            @RequestParam(required = false,defaultValue = "1") Integer current,
            @RequestParam(required = false,defaultValue = "10") Integer pageSize
            ){
        return sysCodeTypeService.selectByTerm(codeTName,codeType,current,pageSize);
    }

    /**
     * 查询所有编码类型信息
     *
     * @return 查询结果
     */
    @GetMapping("/codeTypes")
    @ApiOperation(value = "查询所有编码类型信息",notes = "selectAll")
    public AjaxResult selectAll(){
        return sysCodeTypeService.selectAll();
    }

    /**
     * 通过主键修改编码类型信息
     *
     * @param sysCodeTypeVo 编码类型集
     * @return 修改结果
     */
    @PutMapping("/codeType")
    @ApiOperation(value = "通过主键修改编码类型信息",notes = "updateByPrimary")
    public AjaxResult updateByPrimary(@RequestBody SysCodeTypeVo sysCodeTypeVo){
        try {
            return AjaxResult.success(ResultCode.SUCCESS_UPDATE,sysCodeTypeService.updateByPrimary(sysCodeTypeVo));
        }catch (Exception e){
            return AjaxResult.error(ResultCode.ERR_SQL_UPDATE_ERROR,ExceptionUtil.getRootCauseMessage(e));
        }
    }

    /**
     * 通过主键删除
     *
     * @param codeTypeId 主键编码类型Id
     * @return 删除结果
     */
    @DeleteMapping("/codeType/{codeTypeId}")
    @ApiOperation(value = "通过主键codeTypeId删除编码类型信息以及对应该编码类型下所有编码详细信息",notes = "deleteByCodeTypeId")
//    @ApiImplicitParam(name = "CodeTypeId",value = "编码类型ID",paramType = "path",dataType = "String",required = true)
    public AjaxResult deleteByPrimary(@PathVariable String codeTypeId){
        return sysCodeTypeService.deleteByPrimary(codeTypeId);
    }

    /**
     * 批量删除（假删除）
     *
     * @param codeTypeIds 前端选择的复选框返回列表
     * @return 影响行数
     */
    @DeleteMapping("/codeTypes")
    @ApiOperation(notes ="deleteAllByPk",value = "批量删除编码类型")
    public AjaxResult deleteAllByPk(@RequestBody List<String> codeTypeIds){
        return sysCodeTypeService.deleteAllByPick(codeTypeIds);
    }

    /**
     *查询编码类型总数
     *
     * @return 编码类型总数
     */
    @GetMapping("/codeTypeCount")
    @ApiOperation(value = "编码类型总数",notes = "queryCount")
    public AjaxResult queryCount(){
        return sysCodeTypeService.queryCount();
    }

    /**
     * 分页查询编码类型
     *
     * @param current 当前页
     * @param pageSize 页面大小
     * @return 查询结果
     */
    @GetMapping("/codeTypeByPage")
     @ApiOperation(value = "分页查询编码类型",notes = "selectByPage")
    public AjaxResult selectByPage(
            @RequestParam(required = false,defaultValue = "1") Integer current,
            @RequestParam(required = false,defaultValue = "10") Integer pageSize){
        try {
            return AjaxResult.success(ResultCode.SUCCESS_QUERY,sysCodeTypeService.queryByPage(current,pageSize));
        }catch (Exception e){
            return AjaxResult.error(ResultCode.ERR_SQL_SELECT_ERROR, ExceptionUtil.getRootCauseMessage(e));
        }
    }

}
