package com.kcm.modules.system.logs.controller;

import com.kcm.common.core.domain.AjaxResult;
import com.kcm.common.enums.ResultCode;
import com.kcm.modules.system.logs.entity.SysOperationLogEntity;
import com.kcm.modules.system.logs.entity.resultDto.SysLogDto;
import com.kcm.modules.system.logs.service.SysLogService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletResponse;

import static com.kcm.common.core.domain.AjaxResult.error;
import static com.kcm.common.core.domain.AjaxResult.success;
@Api(tags = "登录日志接口")
@RestController
@RequiredArgsConstructor
@RequestMapping("/loginLog")
public class SysLoginLogController {

    private final SysLogService logService;

    /**
     * 分页查询（展示列表）
     * @param page 页码
     * @param size 每页记录数
     * @return 返回列表结果
     */
    @ApiOperation(value = "查询登录日志全部数据【分页】", notes = "findListLoginByPage")
    @ApiResponses({
            @ApiResponse(code = 200, message = "查询成功"),
            @ApiResponse(code = 3003, message = "数据库查询失败")
    })
    @GetMapping("/findListLoginByPage")
    public AjaxResult findListLoginByPage(@RequestParam(required = false, defaultValue = "1") Integer page,
                                          @RequestParam(required = false, defaultValue = "10") Integer size) {
        try {
            return success(ResultCode.SUCCESS_QUERY, logService.findAllLoginByPage(page,size));
        } catch (Exception e) {
            return error(ResultCode.ERR_SQL_SELECT_ERROR, e.getMessage());
        }
    }

    /**
     * 查询实体
     * @param id 主键ID
     * @return 返回结果
     */
    @ApiOperation(value = "查询实体", notes = "findLoginById")
    @ApiResponses({
            @ApiResponse(code = 200, message = "查询成功"),
            @ApiResponse(code = 3003, message = "数据库查询失败")
    })
    @GetMapping("/findLoginById")
    public AjaxResult findLoginById(String id) {
        SysOperationLogEntity operationLog = logService.getById(id);
        try {
            return success(ResultCode.SUCCESS_QUERY, operationLog);
        } catch (Exception e) {
            return error(ResultCode.ERR_SQL_SELECT_ERROR, e.getMessage());
        }
    }

    /**
     * 批量逻辑删除
     * @param ids 主键ID
     */
    @ApiOperation(value = "删除登录日志数据【批量删除】", notes = "deleteLoginById")
    @ApiResponses({
            @ApiResponse(code = 200, message = "删除成功"),
            @ApiResponse(code = 3003, message = "数据库数据删除失败")
    })
    @DeleteMapping("/deleteLoginById")
    public AjaxResult deleteLoginById(String[] ids) {
        if(ids == null && ids.length < 0){
            return AjaxResult.error(ResultCode.ERR_PARAM_IS_BLANK);
        }
        try {
            logService.deleteById(ids);
            return success(ResultCode.SUCCESS_QUERY);
        } catch (Exception e) {
            return error(ResultCode.ERR_SQL_SELECT_ERROR, e.getMessage());
        }
    }

    /**
     * 分页 + 条件查询【以时间段为条件进行查询】
     * @param logDto 条件实体
     * @param page 页码
     * @param size 每页记录数
     * @return 返回列表结果
     */
    @ApiOperation(value = "查询登录日志全部数据【分页+条件查询】", notes = "findListsLoginByPage")
    @ApiResponses({
            @ApiResponse(code = 200, message = "查询成功"),
            @ApiResponse(code = 3003, message = "数据库查询失败")
    })
    @PostMapping("/findListsLoginByPage")
    public AjaxResult findListLoginByPage(@RequestBody SysLogDto logDto,
                                          @RequestParam(required = false, defaultValue = "1") Integer page,
                                          @RequestParam(required = false, defaultValue = "10") Integer size) {
        try {
            return success(ResultCode.SUCCESS_QUERY, logService.findAllLoginByPage(logDto, page, size));
        } catch (Exception e) {
            return error(ResultCode.ERR_SQL_SELECT_ERROR, e.getMessage());
        }
    }

    /**
     * 登录日志导出
     * @return
     */
    @ApiOperation(value = "登录日志导出", notes = "excelexport")
    @ApiResponses({
            @ApiResponse(code = 200, message = "导出成功"),
            @ApiResponse(code = 3003, message = "导出失败")
    })
    @GetMapping("/excelexport")
    public AjaxResult excelExport(HttpServletResponse response) {
        try {
            logService.exportData(response);
            return success(ResultCode.SUCCESS_QUERY);
        } catch (Exception e) {
            return error(ResultCode.ERR_SQL_SELECT_ERROR, e.getMessage());
        }
    }

}
