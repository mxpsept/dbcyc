package com.kcm.modules.examine.standard.controller;

import cn.hutool.core.exceptions.ExceptionUtil;
import com.kcm.common.core.domain.AjaxResult;
import com.kcm.common.enums.ResultCode;
import com.kcm.modules.examine.standard.entity.BizExamineIndexDetail;
import com.kcm.modules.examine.standard.service.IExaIndexDetailService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiOperation;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;


/**
 * 考核指标明细信息表(BIZ_EXAMINE_INDEX_DETAIL)控制层
 *
 * @author zhaoqingwang
 * @date 2020/8/24
 * @version 1.0
 **/
@RestController
@RequiredArgsConstructor
@Api(tags="考核指标明细信息接口测试" )
@RequestMapping("/examine/IndexDetail")
public class ExaIndexDetailController {

    private final IExaIndexDetailService iExaIndexDetailService;

    /**
     * 新增考核指标明细信息
     *
     * @author zhaoqingwang
     * @param bizExamineIndexDetail 考核指标明细实体
     * @return 影响行数
     */
    @PostMapping("/bizExamineIndexDetail")
    @ApiOperation(value = "新增一条考核指标明细",notes = "insert")
    public AjaxResult insert(@RequestBody BizExamineIndexDetail bizExamineIndexDetail){
        try {
            Integer result = iExaIndexDetailService.insert(bizExamineIndexDetail);
            return AjaxResult.success(ResultCode.SUCCESS_ADD,result);
        }catch (Exception e){
            return AjaxResult.error(ResultCode.ERR_SQL_INSERT_ERROR, ExceptionUtil.getRootCauseMessage(e));
        }
    }


    /**
     * 通过主键修改考核指标明细信息
     *
     * @author zhaoqingwang
     * @param bizExamineIndexDetail 考核指标明细实体
     * @return 影响行数
     */
    @PutMapping("/bizExamineIndexDetail")
    @ApiOperation(value = "通过主键修改考核指标明细",notes = "update")
    public AjaxResult update(@RequestBody BizExamineIndexDetail bizExamineIndexDetail){
        try {
            Integer result = iExaIndexDetailService.update(bizExamineIndexDetail);
            return AjaxResult.success(ResultCode.SUCCESS_UPDATE,result);
        }catch (Exception e){
            return AjaxResult.error(ResultCode.ERR_SQL_UPDATE_ERROR,ExceptionUtil.getRootCauseMessage(e));
        }
    }

    /**
     * 通过主键修改考核指标明细部分信息
     *
     * @author zhaoqingwang
     * @param bizExamineIndexDetail 考核指标明细实体
     * @return 影响行数
     */
    @PutMapping("/bizExamineIndexDetailSelective")
    @ApiOperation(value = "通过主键修改一条考核指标明细的部分信息",notes = "updateSelective")
    public  AjaxResult updateSelective(@RequestBody BizExamineIndexDetail bizExamineIndexDetail){
        try {
            Integer result = iExaIndexDetailService.updateSelective(bizExamineIndexDetail);
            return AjaxResult.success(ResultCode.SUCCESS_UPDATE,result);
        }catch (Exception e){
            return AjaxResult.error(ResultCode.ERR_SQL_UPDATE_ERROR,ExceptionUtil.getRootCauseMessage(e));
        }
    }

    /**
     * 通过主键查询考核指标明细信息
     *
     * @author zhaoqingwang
     * @param indexDId 考核指标明细ID
     * @return 考核指标明细实体
     */
    @GetMapping("/bizExamineIndexDetail")
    @ApiOperation(notes = "selectByPrimary",value = "通过主键查询一条考核标准明细")
    @ApiImplicitParam(name = "indexDId",value = "指标明细ID",paramType = "query",dataType = "String",required = true)
    public AjaxResult selectByPrimary(@RequestParam("indexDId") String indexDId){
        try {
            return AjaxResult.success(ResultCode.SUCCESS_QUERY,iExaIndexDetailService.selectByPrimary(indexDId));
        }catch (Exception e){
            return AjaxResult.error(ResultCode.ERR_SQL_SELECT_ERROR,ExceptionUtil.getRootCauseMessage(e));
        }
    }

    /**
     * 通过考核内容模糊查询考核指标明细（分页插件）
     *
     * @param examineContent 考核内容模糊字段
     * @param current 当前页
     * @param pageSize 页面尺寸
     * @return 查询结果
     */
    @GetMapping("/selectByContent")
    @ApiOperation(notes = "selectByExamineContent",value = "通过考核内容查询")
    @ApiImplicitParam(name = "examineContent",value = "考核内容字段",paramType = "query",dataType ="string",required = true)
    public  AjaxResult selectByExamineContent(
            @RequestParam("examineContent") String examineContent,
            @RequestParam(required = false,defaultValue = "1") Integer current,
            @RequestParam(required = false,defaultValue = "10") Integer pageSize){
        try {
            return AjaxResult.success(ResultCode.SUCCESS_QUERY,iExaIndexDetailService.selectByExamineContent(examineContent,current,pageSize));
        }catch (Exception e){
            return AjaxResult.error(ResultCode.ERR_SQL_SELECT_ERROR,ExceptionUtil.getRootCauseMessage(e));
        }
    }

    /**
     * 查询所有考核指标明细（分页插件）
     *
     * @author zhaoqingwang
     * @param current 当前页
     * @param pageSize 每页尺寸
     * @return 查询结果
     */
    @GetMapping("/bizExamineIndexDetails")
    @ApiOperation(notes = "selectAll",value = "查询所有指标详细信息（分页插件）")
    public AjaxResult selectAll(@RequestParam(required = false,defaultValue = "1") Integer current,
                                @RequestParam(required = false,defaultValue = "10") Integer pageSize){
        try {
            return AjaxResult.success(ResultCode.SUCCESS_QUERY,iExaIndexDetailService.selectAll(current,pageSize));
        }catch (Exception e){
            return AjaxResult.error(ResultCode.ERR_SQL_SELECT_ERROR,ExceptionUtil.getRootCauseMessage(e));
        }
    }

    /**
     * 通过主键删除考核指标明细信息
     *
     * @author zhaoqingwang
     * @param indexDId 考核指标明细ID
     * @return 考核指标明细实体
     */
    @DeleteMapping("/bizExamineIndexDetail/{indexDId}")
    @ApiOperation(notes = "deleteByPrimary",value = "通过主键删除考核标注明细")
    public AjaxResult deleteByPrimary(@PathVariable String indexDId){
        try {
            return AjaxResult.success(ResultCode.SUCCESS_DELETE, iExaIndexDetailService.deleteByPrimary(indexDId));
        }catch (Exception e){
            return AjaxResult.error(ResultCode.ERR_SQL_UPDATE_ERROR,ExceptionUtil.getRootCauseMessage(e));
        }
    }

    /**
     * 通过考核指标明细ID集合批量删除
     *
     * @author zhaoqingwang
     * @param indexDIds 考核指标明细ID集合
     * @return 影响行数
     */
    @DeleteMapping("/bizExamineIndexDetails")
    @ApiOperation(notes = "deleteByIds",value = "通过ID集合批量删除指标明细")
    public AjaxResult deleteByIds(@RequestBody List<String> indexDIds){
        try {
            return AjaxResult.success(ResultCode.SUCCESS_DELETE,iExaIndexDetailService.deleteByIds(indexDIds));
        }catch (Exception e){
            return AjaxResult.error(ResultCode.ERR_SQL_UPDATE_ERROR,ExceptionUtil.getRootCauseMessage(e));
        }

    }
}
