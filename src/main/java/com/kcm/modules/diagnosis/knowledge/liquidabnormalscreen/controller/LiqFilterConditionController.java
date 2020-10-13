package com.kcm.modules.diagnosis.knowledge.liquidabnormalscreen.controller;

import cn.hutool.core.exceptions.ExceptionUtil;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.kcm.common.core.domain.AjaxResult;
import com.kcm.common.enums.ResultCode;
import com.kcm.modules.diagnosis.knowledge.liquidabnormalscreen.entity.LiqFilterCondition;
import com.kcm.modules.diagnosis.knowledge.liquidabnormalscreen.service.LiqFilterConditionService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;


/**
 * 流量异常参数筛选控制类（LIQ_FITTER_CONDITION）
 *
 * @author  zhaoqingwang
 * @date  2020/9/19 10:29
 * @version  1.0
 **/

@RestController
@RequestMapping("/knowledge/LiqFilterCondition")
@RequiredArgsConstructor
@Api(tags = "液量异常筛选接口条件")
public class LiqFilterConditionController {

    private final LiqFilterConditionService liquidAbnormalScreenService;

    /**
     * 新增液量异常筛选条件
     * @param liqFilterCondition 液量异常筛选条件实体
     * @return 数据库影响行数
     */
    @PostMapping("/LiqFilterCondition")
    @ApiOperation(value ="新增流量异常筛选条件信息",notes = "insert")
    public AjaxResult insert(@RequestBody LiqFilterCondition liqFilterCondition){
        try {
            return AjaxResult.success(ResultCode.SUCCESS_ADD,liquidAbnormalScreenService.insert(liqFilterCondition));
        }catch (Exception e){
            return AjaxResult.error(ResultCode.ERR_SQL_INSERT_ERROR, ExceptionUtil.getRootCauseMessage(e));
        }
    }

    /**
     * 修改液量异常筛选条件
     *
     * @param liqFilterCondition 液量异常筛选条件实体
     * @return 数据库影响行数
     */
    @PutMapping("/LiqFilterCondition")
    @ApiOperation(value ="修改流量异常筛选条件信息",notes = "updateByTerm")
    public AjaxResult updateByTerm(@RequestBody LiqFilterCondition liqFilterCondition){
        try {
            return AjaxResult.success(ResultCode.SUCCESS_UPDATE,liquidAbnormalScreenService.updateByWellName(liqFilterCondition));
        }catch (Exception e){
            return AjaxResult.error(ResultCode.ERR_SQL_UPDATE_ERROR, ExceptionUtil.getRootCauseMessage(e));
        }
    }

    /**
     * 删除液量异常筛选条件
     * @param liqFilterCondition 液量异常筛选条件实体
     * @return 删除结果
     */
    @DeleteMapping("/LiqFilterCondition")
    @ApiOperation(value ="删除流量异常筛选条件信息",notes = "deleteByTerm")
    public AjaxResult deleteByTerm(@RequestBody LiqFilterCondition liqFilterCondition){
        try {
            return AjaxResult.success(ResultCode.SUCCESS_DELETE,liquidAbnormalScreenService.deleteByWellNameAndDate(liqFilterCondition));
        }catch (Exception e){
            return AjaxResult.error(ResultCode.ERR_SQL_UPDATE_ERROR, ExceptionUtil.getRootCauseMessage(e));
        }
    }

    /**
     * 查询所有液量异常筛选条件（分页）
     *
     * @param current 当前页
     * @param pageSize 页面尺寸
     * @return 查询结果
     */
    @GetMapping("/LiqFilterConditionList")
    @ApiOperation(value ="查询所有流量异常筛选条件信息（分页）",notes = "selectAll")
    public AjaxResult selectAll(
            @RequestParam(required = false,defaultValue = "1") Integer current,
            @RequestParam(required = false,defaultValue = "20")Integer pageSize){
        try {
            return AjaxResult.success(ResultCode.SUCCESS_QUERY,liquidAbnormalScreenService.selectAll(new Page(current,pageSize)));
        }catch (Exception e){
            return AjaxResult.error(ResultCode.ERR_SQL_SELECT_ERROR, ExceptionUtil.getRootCauseMessage(e));
        }
    }

    /**
     * 条件查询
     *
     * @param prodDate 查询日期
     * @param orgName 加油站名称
     * @return 查询结果
     */
    @GetMapping("/LiqFilterConditionListTerm")
    @ApiOperation(value ="条件筛选流量异常晒选条件信息",notes = "selectByTerm")
    public AjaxResult selectByTerm(
            @RequestParam(required = false,defaultValue = "1") Integer current,
            @RequestParam(required = false,defaultValue = "20")Integer pageSize,
            @RequestParam String prodDate,
            @RequestParam(required = false,defaultValue = "全站") String orgName){
        try {
            return AjaxResult.success(ResultCode.SUCCESS_QUERY,liquidAbnormalScreenService.selectByTimeAndOrg(new Page(current,pageSize),prodDate,orgName));
        }catch (Exception e){
            return AjaxResult.error(ResultCode.ERR_SQL_SELECT_ERROR, ExceptionUtil.getRootCauseMessage(e));
        }
    }

}
