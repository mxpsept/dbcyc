package com.kcm.modules.examine.manage.controller;


import cn.hutool.core.exceptions.ExceptionUtil;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.kcm.common.core.domain.AjaxResult;
import com.kcm.common.enums.ResultCode;
import com.kcm.modules.examine.manage.entity.BizExamineResultInfor;
import com.kcm.modules.examine.manage.service.IExaResultInforService;
import com.kcm.modules.examine.manage.vo.BizExamineResultInforVo;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiOperation;
import lombok.RequiredArgsConstructor;
import org.apache.ibatis.javassist.bytecode.ExceptionsAttribute;
import org.springframework.stereotype.Repository;
import org.springframework.web.bind.annotation.*;

import java.util.List;


/**
 * 考核结果信息表（BIZ_EXAMINE_RESULT_INFOR）控制层
 *
 * @author zhaoqingwang
 * @date 2020/08/26
 * @version 1.0
 */
@RestController
@RequiredArgsConstructor
@RequestMapping("/examine/resultInfor")
@Api(tags = "考核结果接口测试")
public class ExaResultInforController {

    private final IExaResultInforService iExaResultInforService;


    /**
     * 通过主键修改考核结果信息
     *
     * @param bizExamineResultInfor 考核结果信息实体
     * @return 影响行数
     */
    @PutMapping("/bizExamineResultInfor")
    @ApiOperation(notes = "updateByPrimary", value = "通过主键ID修改考核结果")
    public AjaxResult updateByPrimary(@RequestBody BizExamineResultInfor bizExamineResultInfor) {
        try {
            return AjaxResult.success(ResultCode.SUCCESS_UPDATE, iExaResultInforService.updateByPrimary(bizExamineResultInfor));
        } catch (Exception e) {
            return AjaxResult.error(ResultCode.ERR_SQL_UPDATE_ERROR, ExceptionUtil.getRootCauseMessage(e));
        }
    }




    /**
     * 修改考核结果以及考核结果明细
     * @param bizExamineResultInforVo 考核结果Vo
     * @return 修改结果
     */
    @PutMapping("/bizExamineResultInforVo")
    @ApiOperation(notes = "updateByBizExamineResultInforVo",value = "修改考核结果以及考核结果明细")
    public AjaxResult updateByBizExamineResultInforVo(@RequestBody BizExamineResultInforVo bizExamineResultInforVo){
        try {
            return AjaxResult.success(ResultCode.SUCCESS_UPDATE,iExaResultInforService.updateByVo(bizExamineResultInforVo));
        }catch (Exception e){
            return AjaxResult.error(ResultCode.ERR_SQL_UPDATE_ERROR,ExceptionUtil.getRootCauseMessage(e));
        }

    }
    /**
     * 通过主键查询考核结果信息
     *
     * @param examineResultId 考核结果ID
     * @return 查询结果
     */
    @GetMapping("/bizExamineResultInfor/{examineResultId}")
    @ApiOperation(notes = "selectByPrimary", value = "通过主键查询考核结果")
    public AjaxResult selectByPrimary(@PathVariable String examineResultId) {
        try {
            return AjaxResult.success(ResultCode.SUCCESS_QUERY, iExaResultInforService.selectByPrimary(examineResultId));
        } catch (Exception e) {
            return AjaxResult.error(ResultCode.ERR_SQL_SELECT_ERROR, ExceptionUtil.getRootCauseMessage(e));
        }
    }

    /**
     * 通过参考单位或个人查询考核结果信息
     *
     * @param takeObject 参考单位或个人
     * @return 查询结果
     */
    @GetMapping("/bizExamineResultInforTakeObject")
    @ApiOperation(notes = "selectByPrimary", value = "通过参考单位或个人查询考核结果")
    @ApiImplicitParam(name = "takeObject", value = "参考单位或个人",paramType = "query",dataType = "String", required = true)
    public AjaxResult selectByTakeObject(
            @RequestParam(required = false, defaultValue = "1") Integer current,
            @RequestParam(required = false, defaultValue = "10") Integer pageSize,
            @RequestParam String takeObject) {
        try {
            return AjaxResult.success(ResultCode.SUCCESS_QUERY,iExaResultInforService.selectResultByTakeObject(new Page(current,pageSize),takeObject) );
        } catch (Exception e) {
            return AjaxResult.error(ResultCode.ERR_SQL_SELECT_ERROR, ExceptionUtil.getRootCauseMessage(e));
        }
    }

    /**
     * 通过主键删除考核结果信息
     *
     * @param examineResultId 考核结果ID
     * @return 删除结果
     */
    @DeleteMapping("/bizExamineResultInfor/{examineResultId}")
    @ApiOperation(notes = "deleteByPrimary", value = "通过主键删除考核结果")
    public AjaxResult deleteByPrimary(@PathVariable String examineResultId) {
        try {
            iExaResultInforService.deleteByResultId(examineResultId);
            return AjaxResult.success(ResultCode.SUCCESS_DELETE);
        } catch (Exception e) {
            return AjaxResult.error(ResultCode.ERR_SQL_UPDATE_ERROR, ExceptionUtil.getRootCauseMessage(e));
        }
    }

    /**
     * 通过id集合删除考核结果信息
     *
     * @param examineResultIds 考核结果ID集合
     * @return 删除结果
     */
    @DeleteMapping("/bizExamineResultInfors")
    @ApiOperation(notes = "deleteByPrimary", value = "通过考核结果ID集合删除考核结果")
    public AjaxResult deleteByIdS(@RequestBody List<String> examineResultIds) {
        try {
            return AjaxResult.success(ResultCode.SUCCESS_DELETE, iExaResultInforService.deleteByIdS(examineResultIds));
        } catch (Exception e) {
            return AjaxResult.error(ResultCode.ERR_SQL_UPDATE_ERROR, ExceptionUtil.getRootCauseMessage(e));
        }
    }
    /**
     * 通过考核结果ID计算总得分
     *
     * @param examineResultId 考核结果ID
     * @return 总得分
     */
    @GetMapping("/totalScore/{examineResultId}")
    @ApiOperation(notes = "queryScoreByPrimary", value = "通过考核结果ID查询总的分")
    @ApiImplicitParam(name = "examineResultId", value = "考核结果ID", dataType = "String", required = true)
    public AjaxResult queryTotalScoreByPrimary(@PathVariable String examineResultId) {
        try {
            return AjaxResult.success(ResultCode.SUCCESS_QUERY, iExaResultInforService.queryTotalScore(examineResultId));
        } catch (Exception e) {
            return AjaxResult.error(ResultCode.ERR_SQL_SELECT_ERROR, ExceptionUtil.getRootCauseMessage(e));
        }
    }

    /**
     * 查询所有考核结果（分页）
     *
     * @param current  当前页
     * @param pageSize 页面尺寸
     * @return 查询结果
     */
    @GetMapping("/bizExamineResultInforByPage")
    @ApiOperation(notes = "selectByPage", value = "查询所有考核结果（分页插件）")
    public AjaxResult selectByPage(
            @RequestParam(required = false, defaultValue = "1") Integer current,
            @RequestParam(required = false, defaultValue = "10") Integer pageSize) {
        try {
            return AjaxResult.success(ResultCode.SUCCESS_QUERY, iExaResultInforService.selectResultPage(current, pageSize));
        } catch (Exception e) {
            return AjaxResult.error(ResultCode.ERR_SQL_SELECT_ERROR, ExceptionUtil.getRootCauseMessage(e));
        }
    }




}
