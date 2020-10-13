package com.kcm.modules.system.employee.controller;

import com.kcm.common.core.domain.AjaxResult;
import com.kcm.common.enums.ResultCode;
import com.kcm.modules.system.employee.entity.SysPositionEntity;
import com.kcm.modules.system.employee.service.SysPositionService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import static com.kcm.common.core.domain.AjaxResult.error;
import static com.kcm.common.core.domain.AjaxResult.success;

/**
 * <p>
 * 职务信息表 前端控制器
 * </p>
 *
 * @author xublu
 * @since 2020-08-12
 */
@Api(tags = "岗位管理接口")
@RestController
@RequestMapping("/position")
@RequiredArgsConstructor
public class SysPositionController {

    private final SysPositionService positionService;

    /**
     * 分页查询【展示列表】
     * @param page 页码
     * @param size 每页记录数
     * @return 返回列表结果
     */
    @ApiOperation(value = "查询岗位全部数据【分页】", notes = "findListByPage")
    @ApiResponses({
            @ApiResponse(code = 200, message = "查询成功"),
            @ApiResponse(code = 3003, message = "数据库查询失败")
    })
    @GetMapping("/findListByPage")
    public AjaxResult findListByPage(@RequestParam(required = false, defaultValue = "1") Integer page,
                                     @RequestParam(required = false, defaultValue = "10") Integer size){
        try {
            return success(ResultCode.SUCCESS_QUERY, positionService.findAllByPage(page, size));
        } catch (Exception e) {
            return error(ResultCode.ERR_SQL_SELECT_ERROR, e.getMessage());
        }
    }

    /**
     * 查询实体
     * @param id 主键ID
     * @return 返回结果
     */
    @ApiOperation(value = "查询实体", notes = "findById")
    @ApiResponses({
            @ApiResponse(code = 200, message = "查询成功"),
            @ApiResponse(code = 3003, message = "数据库查询失败")
    })
    @GetMapping("/findById")
    public AjaxResult findById(String id){
        SysPositionEntity positionEntity = positionService.getById(id);
        try {
            return success(ResultCode.SUCCESS_QUERY, positionEntity);
        } catch (Exception e) {
            return error(ResultCode.ERR_SQL_SELECT_ERROR, e.getMessage());
        }
    }

    /**
     * 分页 + 条件查询【以时间段为条件进行查询】
     * @param positionName 条件字段
     * @param page 页码
     * @param size 每页记录数
     * @return 返回结果
     */
    @ApiOperation(value = "查询岗位全部数据【分页+条件查询】", notes = "findListsByPage")
    @ApiResponses({
            @ApiResponse(code = 200, message = "查询成功"),
            @ApiResponse(code = 3003, message = "数据库查询失败")
    })
    @PostMapping("/findListsByPage")
    public AjaxResult findListsByPage(@RequestParam(required = false, defaultValue = "1") Integer page,
                                      @RequestParam(required = false, defaultValue = "10") Integer size,
                                      @RequestParam(value = "positionName") String positionName){
        try {
            return success(ResultCode.SUCCESS_QUERY, positionService.findAllByPage(positionName,page, size));
        } catch (Exception e) {
            return error(ResultCode.ERR_SQL_SELECT_ERROR, e.getMessage());
        }
    }

    /**
     * 批量逻辑删除
     * @param ids 主键ID
     * @return 返回结果
     */
    @ApiOperation(value = "删除岗位数据【批量删除】", notes = "deleteById")
    @ApiResponses({
            @ApiResponse(code = 200, message = "删除成功"),
            @ApiResponse(code = 3003, message = "数据库数据删除失败")
    })
    @DeleteMapping("/deleteById")
    public AjaxResult deleteByIds(String[] ids){
        try {
            positionService.deleteById(ids);
            return success(ResultCode.SUCCESS_DELETE);
        } catch (Exception e) {
            return error(ResultCode.ERR_SQL_UPDATE_ERROR,e.getMessage());
        }
    }

    /**
     * 保存
     * @param entity 实体
     * @return 返回结果
     */
    @ApiOperation(value = "保存岗位管理数据", notes = "save")
    @ApiResponses({
            @ApiResponse(code = 200, message = "保存成功"),
            @ApiResponse(code = 3003, message = "数据库数据保存失败")
    })
    @PostMapping("/save")
    public AjaxResult save(@RequestBody SysPositionEntity entity){
        try {
            positionService.insert(entity);
            return success(ResultCode.SUCCESS_ADD);
        } catch (Exception e) {
            return error(ResultCode.ERR_SQL_UPDATE_ERROR, e.getMessage());
        }
    }

    /**
     * 修改
     * @param entity 实体
     * @return 返回结果
     */
    @ApiOperation(value = "修改岗位数据", notes = "UpdateById")
    @ApiResponses({
            @ApiResponse(code = 200, message = "修改成功"),
            @ApiResponse(code = 3003, message = "数据库修改失败")
    })
    @PutMapping("/updateById")
    public AjaxResult updateById(@RequestBody SysPositionEntity entity){
        try {
            boolean update = positionService.updateById(entity);
            return success(ResultCode.SUCCESS_UPDATE,update);
        } catch (Exception e) {
            return error(ResultCode.ERR_SQL_UPDATE_ERROR, e.getMessage());
        }
    }
}

