package com.kcm.modules.diagnosis.oilwell.abnormalgt.controller;

import cn.hutool.core.exceptions.ExceptionUtil;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.kcm.common.core.domain.AjaxResult;
import com.kcm.common.enums.ResultCode;
import com.kcm.modules.diagnosis.knowledge.tile.service.IPcFdDynaDiaTService;
import com.kcm.modules.diagnosis.oilwell.abnormalgt.service.AbnormalGtService;
import io.swagger.annotations.Api;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.Date;

/**
 *  功图数据异常控制层
 *
 * @author  zhaoqingwang
 * @date  2020/9/17 13:47
 * @version  1.0
 **/

@RestController
@RequestMapping("/oilWell/abnormalGt")
@RequiredArgsConstructor
@Api(tags = "功图数据异常接口")
public class AbnormalGtController {

    private final AbnormalGtService abnormalGtService;

    private final IPcFdDynaDiaTService iPcFdDynaDiaTService;

    /**
     * 条件查询功图数据异常
     * @param current 当前页
     * @param pageSize 页面尺寸
     * @param checkDate 查询日期
     * @param orgName 加油站名称
     * @return 查询结果
     */
    @GetMapping("/abnormalGtAllPage")
    public AjaxResult selectAllPage(
            @RequestParam(required = false,defaultValue = "1") Integer current,
            @RequestParam(required = false,defaultValue = "10") Integer pageSize,
            @RequestParam(required = false)String checkDate,
            @RequestParam(required = false,defaultValue = "全站")String orgName
            ){
        try {

            if("0000-00-00".equals(checkDate)){
                Date date = new Date();
                checkDate= cn.hutool.core.date.DateUtil.formatDate(date);
            }
        return AjaxResult.success(ResultCode.SUCCESS_QUERY,abnormalGtService.selectAllPage(new Page(current,pageSize),checkDate,orgName));
        }catch (Exception e){
            return AjaxResult.error(ResultCode.ERR_SQL_SELECT_ERROR, ExceptionUtil.getRootCauseMessage(e));
        }
    }

    /**
     * 通过井号（井名）和日期查询功图数据异常
     * @param wellId 井号（井名）
     * @param checkDate 日期
     * @return 查询结果
     */
    @GetMapping("/abnormalGtList")
    public AjaxResult selectList(
            @RequestParam(required = false)String wellId,
            @RequestParam(required = false,defaultValue = "0000-00-00")String checkDate
    ){
        try {

            return AjaxResult.success(ResultCode.SUCCESS_QUERY,abnormalGtService.selectByIdAndDate(wellId,checkDate));
        }catch (Exception e){
            return AjaxResult.error(ResultCode.ERR_SQL_SELECT_ERROR, ExceptionUtil.getRootCauseMessage(e));
        }
    }

    @GetMapping("/GetGt")
    public AjaxResult selectGtData(String wellId,String checkDate){
        try {
            return AjaxResult.success(ResultCode.SUCCESS_UPDATE,iPcFdDynaDiaTService.selectByWellIdAndTime(wellId,checkDate));
        }catch (Exception e){
            return AjaxResult.error(ResultCode.ERR_SQL_UPDATE_ERROR, ExceptionUtil.getRootCauseMessage(e));
        }
    }

}
