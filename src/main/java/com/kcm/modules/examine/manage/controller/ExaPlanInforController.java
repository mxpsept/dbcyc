package com.kcm.modules.examine.manage.controller;

import cn.hutool.core.exceptions.ExceptionUtil;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.kcm.common.core.controller.BaseController;
import com.kcm.common.core.domain.AjaxResult;
import com.kcm.common.enums.ResultCode;
import com.kcm.modules.examine.manage.entity.BizExaminePlanInfor;
import com.kcm.modules.examine.manage.service.IExaPlanInforService;
import com.kcm.modules.examine.manage.service.IExaResultInforService;
import com.kcm.modules.examine.manage.vo.BizExaminePlanInforVo;
import com.kcm.modules.examine.standard.vo.BizExamineTemplateInforVo;
import io.swagger.annotations.*;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.BeanUtils;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * @author: lucky
 * @date: 2020/8/24
 * @description: 考核计划信息表(BIZ_EXAMINE_PLAN_INFOR)控制层
 **/
@Api(tags = "考核计划信息接口")
@RestController
@RequestMapping("/examine/planInfor")
@RequiredArgsConstructor
public class ExaPlanInforController extends BaseController {

    private final IExaPlanInforService exaPlanInforService;
    private final IExaResultInforService exaResultInforService;

    /***
     * 查询所有考核计划信息(分页插件查询)
     * @author lucky
     * @date 2020/8/26
     * @param current 当前页
     * @param pageSize 页面大小
     * @return 考核计划列表
     **/
    @ApiOperation(value = "分页查询考核计划信息", notes = "queryByPageAll")
    @GetMapping("/queryByPageAll")
    public AjaxResult queryByPageAll(
            @RequestParam(required = false, defaultValue = "1") Integer current,
            @RequestParam(required = false, defaultValue = "10") Integer pageSize) {
        try {
            Page<BizExaminePlanInforVo> planInforVoPage = exaPlanInforService.queryByPageAll(current, pageSize);
            return success(ResultCode.SUCCESS_QUERY, planInforVoPage);
        } catch (Exception e) {
            return success(ResultCode.ERR_SQL_SELECT_ERROR, ExceptionUtil.getRootCauseMessage(e));
        }
    }

    /***
     * 新增考核计划信息
     * @author lucky
     * @date 2020/8/26
     * @param bizExaminePlanInforVo 考核计划视图对象
     * @return 数据库操作记录
     **/
    @ApiOperation(value = "新增考核计划信息并维护考核结果信息", notes = "insert")
    @ApiImplicitParam(name = "bizExaminePlanInforVo", value = "考核计划视图实体", paramType = "body", dataType = "BizExaminePlanInforVo", required = true)
    @ApiResponses({
            @ApiResponse(code = 200, message = "新增成功"),
            @ApiResponse(code = 3001, message = "数据库插入操作失败")
    })
    @PostMapping("/insert")
    public AjaxResult insert(@RequestBody BizExaminePlanInforVo bizExaminePlanInforVo) {
        try {
            BizExaminePlanInforVo planInforVo = exaPlanInforService.insert(bizExaminePlanInforVo);
            return success(ResultCode.SUCCESS_ADD, planInforVo);
        } catch (Exception e) {
            return success(ResultCode.ERR_SQL_UPDATE_ERROR, ExceptionUtil.getRootCauseMessage(e));
        }
    }

    /***
     * 根据主键获取考核计划信息以及对应的参考单位/人员 信息
     * @author lucky
     * @date 2020/8/26
     * @param examinePId 考核计划id
     * @return 数据库操作记录
     **/
    @ApiOperation(value = "根据考核计划id查询考核计划信息以及对应的参考单位/人员信息", notes = "selectByPrimaryKey")
    @GetMapping("/selectByPrimaryKey/{examinePId}")
    public AjaxResult selectByPrimaryKey(@PathVariable String examinePId) {
        try {
            BizExaminePlanInforVo planInforVo = exaPlanInforService.selectPlanAndResultByPId(examinePId);
            return success(ResultCode.SUCCESS_QUERY, planInforVo);
        } catch (Exception e) {
            return success(ResultCode.ERR_SQL_SELECT_ERROR, ExceptionUtil.getRootCauseMessage(e));
        }
    }

    /***
     * 根据考核计划id修改考核计划信息以及对应的参考单位/人员信息
     * @author lucky
     * @date 2020/8/26
     * @param bizExaminePlanInforVo 考核计划视图对象
     * @return 数据库操作记录
     **/
    @ApiOperation(value = "根据考核计划id修改考核计划信息以及对应的参考单位/人员信息", notes = "updateByPrimaryKey")
    @ApiImplicitParam(name = "bizExaminePlanInforVo", value = "考核计划视图实体", paramType = "body", dataType = "BizExaminePlanInforVo", required = true)
    @ApiResponses({
            @ApiResponse(code = 200, message = "修改成功"),
            @ApiResponse(code = 3002, message = "数据库更新操作失败")
    })
    @PutMapping("/updateByPrimaryKey")
    public AjaxResult updateByPrimaryKey(@RequestBody BizExaminePlanInforVo bizExaminePlanInforVo) {
        try {
            BizExaminePlanInfor bizExaminePlanInfor = new BizExaminePlanInfor();
            //复制考核计划信息
            BeanUtils.copyProperties(bizExaminePlanInforVo, bizExaminePlanInfor);
            //修改考核计划信息
            exaPlanInforService.update(bizExaminePlanInfor);
            //批量修改考核结果信息
            exaResultInforService.updateBatch(bizExaminePlanInforVo.getResultInforList());
            return success(ResultCode.SUCCESS_UPDATE, exaPlanInforService.selectPlanAndResultByPId(bizExaminePlanInforVo.getExaminePId()));
        } catch (Exception e) {
            return success(ResultCode.ERR_SQL_UPDATE_ERROR, ExceptionUtil.getRootCauseMessage(e));
        }
    }

    /***
     * 根据主键删除考核计划信息
     * @author lucky
     * @date 2020/9/12
     * @param examinePlanId 考核计划信息
     * @return 执行结果
     **/
    @ApiOperation(value = "根据主键删除考核模板信息以及考核结果信息", notes = "deleteByPrimaryKey")
    @ApiImplicitParam(name = "examinePlanId", value = "考核计划id", paramType = "query", dataType = "String", required = true)
    @DeleteMapping("/deleteByPrimaryKey")
    public AjaxResult deleteByPrimaryKey(@RequestParam String examinePlanId) {
        try {
            exaPlanInforService.deleteByPrimaryKey(examinePlanId);
            return success(ResultCode.SUCCESS_DELETE);
        } catch (Exception e) {
            return success(ResultCode.ERR_SQL_UPDATE_ERROR, ExceptionUtil.getRootCauseMessage(e));
        }
    }

    /***
     * 批量删除考核计划信息
     * @author lucky
     * @date 2020/9/12
     * @param examinePlanIds 考核计划id集合
     * @return 执行结果
     **/
    @ApiOperation(value = "批量删除考核计划信息以及对应的考核结果信息", notes = "deleteBatch")
    @ApiImplicitParam(name = "examinePlanIds", value = "考核计划id集合", paramType = "body", dataType = "String", required = true)
    @DeleteMapping("/deleteBatch")
    public AjaxResult deleteBatch(@RequestBody List<String> examinePlanIds) {
        try {
            for (String examinePlanId : examinePlanIds) {
                exaPlanInforService.deleteByPrimaryKey(examinePlanId);
            }
            return success(ResultCode.SUCCESS_DELETE);
        } catch (Exception e) {
            return success(ResultCode.ERR_SQL_UPDATE_ERROR, ExceptionUtil.getRootCauseMessage(e));
        }
    }


    /***
     * 根据条件分页查询考核计划信息
     * @author lucky
     * @date 2020/8/26
     * @param current 当前页
     * @param pageSize 页面大小
     * @param planName 考核计划名称
     * @return 考核计划列表
     **/
    @ApiOperation(value = "根据考核计划名称查询考核计划信息", notes = "searchByPlanName")
    @ApiImplicitParams(
            @ApiImplicitParam(name = "planName", value = "考核计划名称", paramType = "query", dataType = "String", required = true)
    )
    @GetMapping("/searchByPlanName")
    public AjaxResult searchByPlanName(
            @RequestParam(required = false, defaultValue = "1") Integer current,
            @RequestParam(required = false, defaultValue = "10") Integer pageSize,
            @RequestParam(value = "planName") String planName) {
        try {
            Page<BizExaminePlanInforVo> page = new Page<>(current, pageSize);
            Page<BizExaminePlanInforVo> planInforVoPage = exaPlanInforService.searchByPlanName(page, planName);
            return success(ResultCode.SUCCESS_QUERY, planInforVoPage);
        } catch (Exception e) {
            return success(ResultCode.ERR_SQL_SELECT_ERROR, ExceptionUtil.getRootCauseMessage(e));
        }
    }
}
