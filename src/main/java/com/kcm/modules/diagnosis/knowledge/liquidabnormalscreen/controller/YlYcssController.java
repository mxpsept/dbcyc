package com.kcm.modules.diagnosis.knowledge.liquidabnormalscreen.controller;

import cn.hutool.core.exceptions.ExceptionUtil;
import com.kcm.common.core.domain.AjaxResult;
import com.kcm.common.enums.ResultCode;
import com.kcm.modules.diagnosis.knowledge.liquidabnormalscreen.entity.YlYccs;
import com.kcm.modules.diagnosis.knowledge.liquidabnormalscreen.service.YlYccsService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

/**
 * 液量异常筛选参数表控制层（YL_YCCS）
 *
 * @author  zhaoqingwang
 * @date  2020/9/22 10:13
 * @version  1.0
 **/
@RestController
@RequiredArgsConstructor
@Api(tags = "液量异常参数设置接口")
@RequestMapping("/knowledge/ylYcss")
public class YlYcssController {

    private final YlYccsService ylYccsService;

    /**
     * 修改夜量异常筛选参数
     * @param ylYccs 液量异常筛选参数实体
     * @return 数据库影响行数
     */
    @PutMapping("/ylYcss")
    @ApiOperation(value = "液量筛选参数修改",notes = "updateByEntity")
    public AjaxResult updateByEntity(@RequestBody YlYccs ylYccs){
        try {
            return AjaxResult.success(ResultCode.SUCCESS_UPDATE,ylYccsService.updateByEntity(ylYccs));
        }catch (Exception e){
            return AjaxResult.error(ResultCode.ERR_SQL_UPDATE_ERROR, ExceptionUtil.getRootCauseMessage(e));
        }
    }

    /**
     * 查询液量异常筛选参数
     * @return 查询结果
     */
    @GetMapping("/ylYccss")
    @ApiOperation(value = "查询所有参数",notes = "selectAll")
    public AjaxResult selectAll(){
        try {
            return AjaxResult.success(ResultCode.SUCCESS_QUERY,ylYccsService.selectAll());
        }catch (Exception e){
            return AjaxResult.error(ResultCode.ERR_SQL_SELECT_ERROR,ExceptionUtil.getRootCauseMessage(e));
        }
    }

    @GetMapping("/ylYccs")
    public AjaxResult selectByWellName(@RequestParam(required = false,defaultValue = "default") String wellName){
        try {
            return AjaxResult.success(ResultCode.SUCCESS_QUERY,ylYccsService.selectByWellName(wellName));
        }catch (Exception e){
            return AjaxResult.error(ResultCode.ERR_SQL_SELECT_ERROR,ExceptionUtil.getRootCauseMessage(e));
        }
    }
}
