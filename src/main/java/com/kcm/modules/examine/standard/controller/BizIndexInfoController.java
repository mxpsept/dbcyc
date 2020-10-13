package com.kcm.modules.examine.standard.controller;

import cn.hutool.core.exceptions.ExceptionUtil;
import com.kcm.common.core.domain.AjaxResult;
import com.kcm.common.enums.ResultCode;
import com.kcm.modules.examine.standard.entity.BizExamineIndexInfor;
import com.kcm.modules.examine.standard.service.BizIndexInforService;
import com.kcm.modules.examine.standard.service.IExaIndexDetailService;
import com.kcm.modules.examine.standard.vo.BizDetailInfoVo;
import io.swagger.annotations.*;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

import static com.kcm.common.core.domain.AjaxResult.error;
import static com.kcm.common.core.domain.AjaxResult.success;

/**
 * <p>
 * 考核指标信息表 前端控制器
 * </p>
 *
 * @author xublu
 * @since 2020-08-24
 */
@Api(tags = "考核指标信息接口")
@RestController
@RequestMapping("/examine/IndexInfo")
@RequiredArgsConstructor
public class BizIndexInfoController {

    private final BizIndexInforService infoService;

    private final IExaIndexDetailService detailService;

    /**
     * 查询考核指标全部数据列表[包含分页]
     * @param current  页码
     * @param pageSize  每页记录数
     * @return 分页结果数据
     */
    @ApiOperation(value = "分页查询全部数据列表", notes = "findListsByPage")
    @ApiResponses({
            @ApiResponse(code = 200, message = "查询成功"),
            @ApiResponse(code = 3003, message = "数据库查询失败")
    })
    @PostMapping("/findListsByPage")
    public AjaxResult findListsByPage(@RequestParam(required = false, defaultValue = "1") Integer current,
                                      @RequestParam(required = false, defaultValue = "10") Integer pageSize){
        try {
            return success(ResultCode.SUCCESS_QUERY, infoService.queryPageAll(current,pageSize));
        } catch (Exception e) {
            return error(ResultCode.ERR_SQL_SELECT_ERROR, e.getMessage());
        }
    }

    /**
     * 查询考核指标全部数据列表[包含分页和条件查询]
     * @param indexName 指标名称
     * @param current  页码
     * @param pageSize  每页记录数
     * @return 分页结果数据
     */
    @ApiOperation(value = "查询考核指标全部数据列表[包含分页和条件（指标名称）查询]", notes = "findListsByPageAndCon")
    @ApiResponses({
            @ApiResponse(code = 200, message = "查询成功"),
            @ApiResponse(code = 3003, message = "数据库查询失败")
    })
    @PostMapping("/findListsByPageAndCon")
    public AjaxResult findListsByPageAndCon(@RequestParam(required = false, defaultValue = "1") Integer current,
                                            @RequestParam(required = false, defaultValue = "10") Integer pageSize,
                                            @RequestParam(value = "indexName") String indexName){
        try {
            return success(ResultCode.SUCCESS_QUERY, infoService.findAllByPageAndCon(indexName,current, pageSize));
        } catch (Exception e) {
            return error(ResultCode.ERR_SQL_SELECT_ERROR, e.getMessage());
        }
    }

    /**
     * 查询考核指标全部数据[非分页]
     * @return 查询实体
     */
    @ApiOperation(value = "查询考核指标数据[非分页]", notes = "queryAll")
    @ApiResponses({
            @ApiResponse(code = 200, message = "查询成功"),
            @ApiResponse(code = 3003, message = "数据库查询失败")
    })
    @GetMapping("/queryAll")
    public AjaxResult queryAll(){
        List<BizExamineIndexInfor> bizExamineIndexInfors = infoService.queryAll();
        try {
            return success(ResultCode.SUCCESS_QUERY, bizExamineIndexInfors);
        } catch (Exception e) {
            return error(ResultCode.ERR_SQL_SELECT_ERROR, e.getMessage());
        }
    }

    /**
     * 新增保存【组合实体类】
     * @param entity 实体类
     * @return 新增成功
     */
    @ApiOperation(value = "新增考核指标和考核明细表数据", notes = "save")
    @ApiResponses({
            @ApiResponse(code = 200, message = "新增成功"),
            @ApiResponse(code = 3001, message = "数据库插入操作失败")
    })
    @PostMapping("/save")
    public AjaxResult save(@RequestBody BizDetailInfoVo entity){
        try {
            infoService.insertSelect(entity);
            return success(ResultCode.SUCCESS_ADD,"新增成功");
        } catch (Exception e) {
            return success(ResultCode.ERR_SQL_UPDATE_ERROR, ExceptionUtil.getRootCauseMessage(e));
        }
    }

    /**
     * 修改
     * @param entity 实体类
     * @return 是否成功的结果
     */
    @ApiOperation(value = "修改考核指标信息数据", notes = "UpdateById")
    @ApiResponses({
            @ApiResponse(code = 200, message = "修改成功"),
            @ApiResponse(code = 3003, message = "数据库修改失败")
    })
    @PutMapping("/updateById")
    public AjaxResult updateById(@RequestBody BizDetailInfoVo entity){
        try {
            infoService.update(entity);
            return success(ResultCode.SUCCESS_QUERY,"修改成功");
        } catch (Exception e) {
            return error(ResultCode.ERR_SQL_SELECT_ERROR, e.getMessage());
        }
    }

    /**
     * 单条记录数据逻辑删除
     * @param indexId 考核指标ID
     * @return 返回结果
     */
    @ApiOperation(value = "删除考核指标数据[单条记录数据删除]", notes = "deleteByPrimaryKey")
    @ApiImplicitParam(name = "indexId", value = "考核指标id", paramType = "query", dataType = "String", required = true)
    @DeleteMapping("/deleteByPrimaryKey")
    public AjaxResult deleteByPrimaryKey(@RequestParam String indexId){
        try {
            infoService.deleteById(indexId);
            return success(ResultCode.SUCCESS_DELETE);
        } catch (Exception e) {
            return success(ResultCode.ERR_SQL_UPDATE_ERROR, ExceptionUtil.getRootCauseMessage(e));
        }
    }

    /**
     *  批量删除考核指标数据
     * @param indexIds 模板信息集合
     * @return
     */
    @ApiOperation(value = "批量考核指标数据[批量记录数据删除]", notes = "deleteBatch")
    @ApiImplicitParam(name = "indexIds", value = "考核指标id集合", paramType = "body", allowMultiple = true, dataType = "String")
    @DeleteMapping("/deleteBatch")
    public AjaxResult deleteBatch(@RequestBody List<String> indexIds) {
        try {
            AjaxResult ajaxResult;
            if (!indexIds.isEmpty()) {
                for (String indexId : indexIds) {
                    infoService.deleteById(indexId);
                }
                ajaxResult = success(ResultCode.SUCCESS_DELETE);
            } else {
                ajaxResult = success(ResultCode.ERR_SQL_UPDATE_ERROR, "考核指标id集合为空");
            }
            return ajaxResult;
        } catch (Exception e) {
            return success(ResultCode.ERR_SQL_UPDATE_ERROR, ExceptionUtil.getRootCauseMessage(e));
        }
    }
}

