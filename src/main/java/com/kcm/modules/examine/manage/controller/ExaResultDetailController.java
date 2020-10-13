package com.kcm.modules.examine.manage.controller;

import cn.hutool.core.exceptions.ExceptionUtil;
import com.kcm.common.core.controller.BaseController;
import com.kcm.common.core.domain.AjaxResult;
import com.kcm.common.enums.ResultCode;
import com.kcm.modules.examine.manage.entity.BizExamineResultDetail;
import com.kcm.modules.examine.manage.service.IExaResultDetailService;
import com.kcm.modules.examine.manage.service.IExaResultInforService;
import com.kcm.modules.examine.manage.vo.BizExamineIndexDetailVo;
import com.kcm.modules.examine.standard.entity.BizExamineIndexDetail;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * @author: lucky
 * @date: 2020/8/27
 * @description: 考核结果明细表(BIZ_EXAMINE_INDEX_DETAIL)控制层
 **/
@Api(tags = "考核结果明细信息接口")
@RestController
@RequestMapping("/examine/resultDetail")
@RequiredArgsConstructor
public class ExaResultDetailController extends BaseController {

    private final IExaResultDetailService exaResultDetailService;
    private final IExaResultInforService iExaResultInforService;


    /***
     * 根据考核结果id查询考核指标明细相关信息
     * @author lucky
     * @date 2020/8/27
     * @param examineResultId 考核结果id
     * @return 考核指标明细相关信息列表
     **/
    @ApiOperation(value = "根据考核结果id查询考核详情信息", notes = "selectByResultId")
    @GetMapping("/selectByRId")
    public AjaxResult selectByResultId(@RequestParam String examineResultId) {
        try {
            List<BizExamineIndexDetailVo> bizExamineIndexDetailVos = exaResultDetailService.selectByResultId(examineResultId);
            return success(ResultCode.SUCCESS_QUERY, bizExamineIndexDetailVos);
        } catch (Exception e) {
            return success(ResultCode.ERR_SQL_SELECT_ERROR, ExceptionUtil.getRootCauseMessage(e));
        }
    }

    @ApiOperation(value = "增加考核结果明细信息", notes = "insertResultDetail")
    @PostMapping("/insertResultDetail")
    public AjaxResult insertResultDetail(@RequestBody List<BizExamineIndexDetailVo> indexDetailVos) {
        try {
            //插入考核详情信息
            List<BizExamineResultDetail> bizExamineResultDetails = exaResultDetailService.insertBatchResultDetail(indexDetailVos);
            //修改总得分
            iExaResultInforService.queryTotalScore(indexDetailVos.get(0).getExamineRId());
            return success(ResultCode.SUCCESS_ADD, bizExamineResultDetails);
        } catch (Exception e) {
            return success(ResultCode.ERR_SQL_INSERT_ERROR, ExceptionUtil.getRootCauseMessage(e));
        }
    }


}
