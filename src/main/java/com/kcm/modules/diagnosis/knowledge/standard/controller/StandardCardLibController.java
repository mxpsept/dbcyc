package com.kcm.modules.diagnosis.knowledge.standard.controller;

import cn.hutool.core.exceptions.ExceptionUtil;
import com.kcm.common.core.controller.BaseController;
import com.kcm.common.core.domain.AjaxResult;
import com.kcm.common.enums.ResultCode;
import com.kcm.modules.diagnosis.knowledge.standard.entity.StandardCardLib;
import com.kcm.modules.diagnosis.knowledge.standard.service.IStandardCardLibService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author: lucky
 * @date: 2020/10/10
 * @description:
 **/
@Api(tags = "标准功图信息接口")
@RestController
@RequestMapping("/diagnosis/knowledge/standardCard")
@RequiredArgsConstructor
public class StandardCardLibController extends BaseController {

    private final IStandardCardLibService standardCardLibService;

    @ApiOperation(value = "添加标准功图信息", notes = "insert")
    @PostMapping("/insert")
    public AjaxResult insert(@RequestBody StandardCardLib standardCardLib) {
        try {
            StandardCardLib cardLib = standardCardLibService.insert(standardCardLib);
            return success(ResultCode.SUCCESS_ADD, cardLib);
        } catch (Exception e) {
            return success(ResultCode.ERR_SQL_UPDATE_ERROR, ExceptionUtil.getRootCauseMessage(e));
        }
    }

}
