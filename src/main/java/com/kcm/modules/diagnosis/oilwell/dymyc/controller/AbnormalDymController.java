package com.kcm.modules.diagnosis.oilwell.dymyc.controller;


import com.kcm.common.core.domain.AjaxResult;
import com.kcm.common.enums.ResultCode;
import com.kcm.modules.diagnosis.oilwell.dymyc.entity.dto.DymParamDto;
import com.kcm.modules.diagnosis.oilwell.dymyc.service.AbnormalDymService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import static com.kcm.common.core.domain.AjaxResult.error;
import static com.kcm.common.core.domain.AjaxResult.success;

/**
 * <p>
 * 动液面异常表 前端控制器
 * </p>
 *
 * @author xublu
 * @since 2020-09-17
 */
@RestController
@RequestMapping("/oilWell/dym")
@Api(tags = "动液面异常接口")
@RequiredArgsConstructor
public class AbnormalDymController {

    private final AbnormalDymService abnormalDymService;

    /**
     * 分页查询【展示列表】
     * @param page 页码
     * @param size 每页记录数
     * @return 返回列表结果
     */
    @ApiOperation(value = "查询动液面异常全部数据【分页】", notes = "findListByPage")
    @ApiResponses({
            @ApiResponse(code = 200, message = "查询成功"),
            @ApiResponse(code = 3003, message = "数据库查询失败")
    })
    @GetMapping("/findListByPage")
    public AjaxResult findListByPage(@RequestParam(required = false, defaultValue = "1") Integer page,
                                     @RequestParam(required = false, defaultValue = "10") Integer size){
        try {
            return success(ResultCode.SUCCESS_QUERY, abnormalDymService.findAllByPage(page, size));
        } catch (Exception e) {
            return error(ResultCode.ERR_SQL_SELECT_ERROR, e.getMessage());
        }
    }

    /**
     * 分页 + 条件查询【以时间段为条件进行查询】
     * @param dto 条件字段
     * @param page 页码
     * @param size 每页记录数
     * @return 返回结果
     */
    @ApiOperation(value = "查询动液面异常全部数据【分页+条件查询】", notes = "findListsByPage")
    @ApiResponses({
            @ApiResponse(code = 200, message = "查询成功"),
            @ApiResponse(code = 3003, message = "数据库查询失败")
    })
    @PostMapping("/findListsByPage")
    public AjaxResult findListsByPage(@RequestBody DymParamDto dto,
                                      @RequestParam(required = false, defaultValue = "1") Integer page,
                                      @RequestParam(required = false, defaultValue = "10") Integer size
                                      ){
        try {
            return success(ResultCode.SUCCESS_QUERY, abnormalDymService.findAllByPageAndCon(dto,page,size));
        } catch (Exception e) {
            return error(ResultCode.ERR_SQL_SELECT_ERROR, e.getMessage());
        }
    }

}

