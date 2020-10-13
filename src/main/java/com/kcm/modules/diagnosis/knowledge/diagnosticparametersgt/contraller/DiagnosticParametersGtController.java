package com.kcm.modules.diagnosis.knowledge.diagnosticparametersgt.contraller;

import cn.hutool.core.exceptions.ExceptionUtil;
import com.kcm.common.core.domain.AjaxResult;
import com.kcm.common.enums.ResultCode;
import com.kcm.modules.diagnosis.knowledge.diagnosticparametersgt.entity.DiagnosticParametersGt;
import com.kcm.modules.diagnosis.knowledge.diagnosticparametersgt.service.CdWellSourceService;
import com.kcm.modules.diagnosis.knowledge.diagnosticparametersgt.service.DiagnosticParametersGtService;
import io.swagger.annotations.Api;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;


/**
 * 功图诊断参数控制类
 *
 * @author  zhaoqingwang
 * @date  2020/9/21 15:39
 * @version  1.0
 **/
@RestController
@RequestMapping("/knowledge/DiagnosticParametersGt")
@RequiredArgsConstructor
@Api(tags = "功图诊断参数接口")
public class DiagnosticParametersGtController {
    private final DiagnosticParametersGtService diagnosticParametersGtService;

    private final CdWellSourceService cdWellSourceService;


    /**
     * 修改功图诊断参数
     * @param diagnosticParametersGt 功图诊断参数实体
     * @return 数据库影响行数
     */
    @PutMapping("/DiagnosticParametersGt")
    public AjaxResult Update(
            @RequestBody DiagnosticParametersGt diagnosticParametersGt){
        try {
            return AjaxResult.success(ResultCode.SUCCESS_UPDATE,diagnosticParametersGtService.updateByEntity(diagnosticParametersGt));
        }catch (Exception e){
            return AjaxResult.error(ResultCode.ERR_SQL_UPDATE_ERROR, ExceptionUtil.getRootCauseMessage(e));
        }
    }

    /**
     * 新增功图诊断参数
     * @param diagnosticParametersGt 功图诊断实体
     * @return 数据库影响行数
     */
    @PostMapping("/DiagnosticParametersGt")
    public AjaxResult insert(@RequestBody DiagnosticParametersGt diagnosticParametersGt){
        try {
            return AjaxResult.success(ResultCode.SUCCESS_UPDATE,diagnosticParametersGtService.save(diagnosticParametersGt));
        }catch (Exception e){
            return AjaxResult.error(ResultCode.ERR_SQL_UPDATE_ERROR, ExceptionUtil.getRootCauseMessage(e));
        }
    }

    /**
     * 查询所有功图诊断参数
     * @return 查询结果
     */
    @GetMapping("/DiagnosticParametersGtList")
    public AjaxResult selectAll(){
        try {
            return AjaxResult.success(ResultCode.SUCCESS_UPDATE,diagnosticParametersGtService.selectAll());
        }catch (Exception e){
            return AjaxResult.error(ResultCode.ERR_SQL_UPDATE_ERROR, ExceptionUtil.getRootCauseMessage(e));
        }

    }

    @GetMapping("/CdWellSource")
    public AjaxResult selectOrgNameList(){
        try {
            return AjaxResult.success(ResultCode.SUCCESS_UPDATE,cdWellSourceService.selectOrgName());
        }catch (Exception e){
            return AjaxResult.error(ResultCode.ERR_SQL_UPDATE_ERROR, ExceptionUtil.getRootCauseMessage(e));
        }
    }

}
