package com.kcm.modules.diagnosis.knowledge.tile.controller;

import cn.hutool.core.exceptions.ExceptionUtil;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.kcm.common.core.controller.BaseController;
import com.kcm.common.core.domain.AjaxResult;
import com.kcm.common.enums.ResultCode;
import com.kcm.modules.diagnosis.knowledge.diagnosticparametersgt.entity.CdWellSource;
import com.kcm.modules.diagnosis.knowledge.diagnosticparametersgt.service.CdWellSourceService;
import com.kcm.modules.diagnosis.knowledge.tile.entity.PcFdPumpjackDynaDiaT;
import com.kcm.modules.diagnosis.knowledge.tile.service.IMeasuresInfoService;
import com.kcm.modules.diagnosis.knowledge.tile.service.IPcFdDynaDiaTService;
import com.kcm.modules.diagnosis.knowledge.tile.vo.QueryBytermVo;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import jdk.nashorn.internal.ir.annotations.Ignore;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;
import springfox.documentation.annotations.ApiIgnore;

import java.nio.file.FileAlreadyExistsException;
import java.util.List;

/**
 * @author: lucky
 * @date: 2020/9/24
 * @description: 功图平铺(PC_FD_PUMPJACK_DYNA_DIA_T)控制层
 **/
@Api(tags = "功图平铺信息接口")
@RestController
@RequestMapping("/diagnosis/knowledge/tile")
@RequiredArgsConstructor
public class PcFdDynaDiaTController extends BaseController {

    private final IPcFdDynaDiaTService pcFdDynaDiaTService;
    private final IMeasuresInfoService MeasuresInfoService;
    private final CdWellSourceService cdWellSourceService;

    /***
     * 根据条件查询功图平铺信息
     * @author lucky
     * @date 2020/9/24
     * @param queryBytermVo 参数
     * @return
     **/
    @ApiOperation(value = "根据条件查询功图平铺数据", notes = "queryByterm")
    @PostMapping("/queryByterm")
    public AjaxResult queryByterm(@RequestBody QueryBytermVo queryBytermVo) {
        try {
            //查询总条目数
            Integer queryCount = pcFdDynaDiaTService.queryCount(queryBytermVo);
            Page<PcFdPumpjackDynaDiaT> pages = new Page<>(queryBytermVo.getCurrentPage(), queryBytermVo.getPageSize(), queryCount);
            //定义分页查询参数
            Integer current = (queryBytermVo.getCurrentPage() - 1) * queryBytermVo.getPageSize() + 1;
            Integer pageSize = queryBytermVo.getCurrentPage() * queryBytermVo.getPageSize();
            queryBytermVo.setCurrentPage(current);
            queryBytermVo.setPageSize(pageSize);
            //分页查询数据
            List<PcFdPumpjackDynaDiaT> pcFdPumpjackDynaDiaTS = pcFdDynaDiaTService.queryByterm(queryBytermVo);
            pages.setRecords(pcFdPumpjackDynaDiaTS);
            return success(ResultCode.SUCCESS_QUERY, pages);
        } catch (Exception e) {
            return success(ResultCode.ERR_SQL_SELECT_ERROR, ExceptionUtil.getRootCauseMessage(e));
        }
    }

    /***
     * 查询所有井名称
     * @author lucky
     * @date 2020/9/25
     * @return 数据列表
     **/
    @ApiIgnore
    @ApiOperation(value = "查询所有井名称", notes = "queryWellName")
    @GetMapping("/queryWellName")
    public AjaxResult queryWellName() {
        try {
            List<PcFdPumpjackDynaDiaT> pcFdPumpjackDynaDiaTS = pcFdDynaDiaTService.queryWellName();
            return success(ResultCode.SUCCESS_QUERY, pcFdPumpjackDynaDiaTS);
        } catch (Exception e) {
            return success(ResultCode.ERR_SQL_SELECT_ERROR, ExceptionUtil.getRootCauseMessage(e));
        }
    }

    /***
     * 查询所有措施建议信息
     * @author lucky
     * @date 2020/9/28
     * @return 措施建议信息
     **/
    @ApiOperation(value = "查询所有措施建议信息", notes = "queryAllMeasures")
    @GetMapping("queryAllMeasures")
    public AjaxResult queryAllMeasures() {
        try {
            return success(ResultCode.SUCCESS_QUERY, MeasuresInfoService.queryAllMeasures());
        } catch (Exception e) {
            return success(ResultCode.ERR_SQL_SELECT_ERROR, ExceptionUtil.getRootCauseMessage(e));
        }
    }

    /***
     * 查询采油站名称
     * @author lucky
     * @date 2020/9/28
     * @return 采油站名称
     **/
    @ApiOperation(value = "查询采油站名称", notes = "queryorgName")
    @GetMapping("queryorgName")
    public AjaxResult queryorgName() {
        try {
            return success(ResultCode.SUCCESS_QUERY, cdWellSourceService.queryorgName());
        } catch (Exception e) {
            return success(ResultCode.ERR_SQL_SELECT_ERROR, ExceptionUtil.getRootCauseMessage(e));
        }
    }

    /***
     * 根据采油站名称查询油井名称
     * @author lucky
     * @date 2020/9/29
     * @param orgName 井名称
     * @return 井名称列表
     **/
    @ApiOperation(value = "根据采油站名称查询油井名称", tags = "queryWellNameByOrgName")
    @GetMapping("queryWellNameByOrgName")
    public AjaxResult queryWellNameByOrgName(@RequestParam String orgName) {
        try {
            List<CdWellSource> orgNames = cdWellSourceService.queryWellNameByOrgName(orgName);
            return success(ResultCode.SUCCESS_QUERY, orgNames);
        } catch (Exception e) {
            return success(ResultCode.ERR_SQL_SELECT_ERROR, ExceptionUtil.getRootCauseMessage(e));
        }
    }
}
