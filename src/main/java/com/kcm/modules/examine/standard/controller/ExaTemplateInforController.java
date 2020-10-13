package com.kcm.modules.examine.standard.controller;

import cn.hutool.core.exceptions.ExceptionUtil;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.kcm.common.core.controller.BaseController;
import com.kcm.common.core.domain.AjaxResult;
import com.kcm.common.enums.ResultCode;
import com.kcm.common.other.StringUtils;
import com.kcm.modules.examine.standard.entity.BizExamineTemplateInfor;
import com.kcm.modules.examine.standard.service.IExaTemplateInforService;
import com.kcm.modules.examine.standard.vo.BizExamineTemplateInforVo;
import io.swagger.annotations.*;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * @author: lucky
 * @date: 2020/8/24
 * @description: 考核模板信息表(BIZ_EXAMINE_TEMPLATE_INFOR)控制层
 **/
@Api(tags = "考核模板信息接口")
@RestController
@RequestMapping("/examine/templateInfor")
@RequiredArgsConstructor
public class ExaTemplateInforController extends BaseController {

    private final IExaTemplateInforService exaTemplateInforService;

    @ApiOperation(value = "分页查询绩效考核模板信息", notes = "queryByPageAll")
    @GetMapping("/queryTemplateAll")
    public AjaxResult queryByPageAll(
            @RequestParam(required = false, defaultValue = "1") Integer current,
            @RequestParam(required = false, defaultValue = "10") Integer pageSize) {
        try {
            Page<BizExamineTemplateInforVo> pageList = exaTemplateInforService.queryPageAll(current, pageSize);
            return success(ResultCode.SUCCESS_QUERY, pageList);
        } catch (Exception e) {
            return success(ResultCode.ERR_SQL_SELECT_ERROR, ExceptionUtil.getRootCauseMessage(e));
        }
    }

    /***
     * 新增考核模板信息
     * @author lucky
     * @date 2020/8/24
     * @param bizExamineTemplateInfor 绩效考核模板对象
     * @return 数据库操作记录
     **/
    @ApiOperation(value = "新增绩效考核模板信息", notes = "insert")
    @ApiImplicitParam(name = "bizExamineTemplateInfor", value = "绩效考核模板实体", paramType = "body", dataType = "BizExamineTemplateInfor", required = true)
    @ApiResponses({
            @ApiResponse(code = 200, message = "新增成功"),
            @ApiResponse(code = 3001, message = "数据库插入操作失败")
    })
    @PostMapping("/insert")
    public AjaxResult insert(@RequestBody BizExamineTemplateInfor bizExamineTemplateInfor) {
        try {
            BizExamineTemplateInfor templateInfo = exaTemplateInforService.insert(bizExamineTemplateInfor);
            return success(ResultCode.SUCCESS_ADD, templateInfo);
        } catch (Exception e) {
            return success(ResultCode.ERR_SQL_UPDATE_ERROR, ExceptionUtil.getRootCauseMessage(e));
        }
    }

    /***
     * 修改绩效考核模板信息
     * @author lucky
     * @date 2020/8/24
     * @param bizExamineTemplateInfor 绩效考核模板对象
     * @return 数据库操作记录
     **/
    @ApiOperation(value = "修改绩效考核模板信息", notes = "update")
    @ApiImplicitParam(name = "bizExamineTemplateInfor", value = "绩效考核模板实体", paramType = "body", dataType = "BizExamineTemplateInfor", required = true)
    @ApiResponses({
            @ApiResponse(code = 200, message = "修改成功"),
            @ApiResponse(code = 3002, message = "数据库更新操作失败")
    })
    @PutMapping("/update")
    public AjaxResult update(@RequestBody BizExamineTemplateInfor bizExamineTemplateInfor) {
        try {
            BizExamineTemplateInfor templateInfo = exaTemplateInforService.update(bizExamineTemplateInfor);
            return success(ResultCode.SUCCESS_UPDATE, templateInfo);
        } catch (Exception e) {
            return success(ResultCode.ERR_SQL_UPDATE_ERROR, ExceptionUtil.getRootCauseMessage(e));
        }
    }

    /***
     * 根据考核模板名称查询考核模板信息
     * @author lucky
     * @date 2020/8/26
     * @param current 当前页
     * @param pageSize 页面大小
     * @param templateName 考核模板名称
     * @return 考核模板列表
     **/
    @ApiOperation(value = "根据考核模板名称查询考核模板信息", notes = "searchBytemplateName")
    @ApiImplicitParams(
            @ApiImplicitParam(name = "templateName", value = "考核模板名称", paramType = "query", dataType = "String", required = true)
    )
    @GetMapping("/searchByTemplateName")
    public AjaxResult searchByTemplateName(
            @RequestParam(required = false, defaultValue = "1") Integer current,
            @RequestParam(required = false, defaultValue = "10") Integer pageSize,
            @RequestParam(value = "templateName") String templateName) {
        try {
            Page<BizExamineTemplateInforVo> page = new Page<>(current, pageSize);
            Page<BizExamineTemplateInforVo> templateInfoPage = exaTemplateInforService.searchByTemplateName(page, templateName);
            return success(ResultCode.SUCCESS_QUERY, templateInfoPage);
        } catch (Exception e) {
            return success(ResultCode.ERR_SQL_SELECT_ERROR, ExceptionUtil.getRootCauseMessage(e));
        }
    }

    /***
     * 根据考核模板id删除考核模板信息以及对应考核指标信息
     * @author lucky
     * @date 2020/9/10
     * @param templateId 考核模板id
     **/
    @ApiOperation(value = "根据考核模板id删除考核模板信息以及对应的考核指标信息")
    @ApiImplicitParam(name = "templateId", value = "考核模板id", paramType = "query", dataType = "String", required = true)
    @DeleteMapping("/deleteByPrimaryKey")
    public AjaxResult deleteByPrimaryKey(@RequestParam String templateId) {
        try {
            exaTemplateInforService.deleteByPrimaryKey(templateId);
            return success(ResultCode.SUCCESS_DELETE);
        } catch (Exception e) {
            return success(ResultCode.ERR_SQL_UPDATE_ERROR, ExceptionUtil.getRootCauseMessage(e));
        }
    }

    /***
     * 查询所有考核模板信息(非分页)
     * @author lucky
     * @date 2020/9/10
     * @return
     **/
    @ApiOperation(value = "查询所有考核模板信息(非分页)")
    @GetMapping("/queryAll")
    public AjaxResult queryAll() {
        try {
            return success(ResultCode.SUCCESS_QUERY, exaTemplateInforService.queryAll());
        } catch (Exception e) {
            return success(ResultCode.ERR_SQL_SELECT_ERROR, ExceptionUtil.getRootCauseMessage(e));
        }
    }

    /***
     * 批量删除模板信息
     * @author lucky
     * @date 2020/9/10
     * @param templateIds 模板信息集合
     **/
    @ApiOperation(value = "批量删除模板信息", notes = "deleteBatch")
    @ApiImplicitParam(name = "templateIds", value = "模板id集合", paramType = "body", allowMultiple = true, dataType = "String")
    @DeleteMapping("/deleteBatch")
    public AjaxResult deleteBatch(@RequestBody List<String> templateIds) {
        try {
            AjaxResult ajaxResult;
            if (!templateIds.isEmpty()) {
                for (String templateId : templateIds) {
                    exaTemplateInforService.deleteByPrimaryKey(templateId);
                }
                ajaxResult = success(ResultCode.SUCCESS_DELETE);
            } else {
                ajaxResult = success(ResultCode.ERR_SQL_UPDATE_ERROR, "模板id集合为空");
            }
            return ajaxResult;
        } catch (Exception e) {
            return success(ResultCode.ERR_SQL_UPDATE_ERROR, ExceptionUtil.getRootCauseMessage(e));
        }
    }
}
