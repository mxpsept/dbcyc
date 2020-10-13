package com.kcm.modules.diagnosis.oilwell.liquidvolume.controller;

import com.kcm.common.core.controller.BaseController;
import com.kcm.common.core.domain.AjaxResult;
import com.kcm.common.enums.ResultCode;
import com.kcm.modules.diagnosis.oilwell.liquidvolume.service.LiquidVolumeAbnormalService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;

import java.util.Date;

/**
 * 液量异常汇总服务
 *
 * @author beiguoge
 * @version 1.0
 * @date 2020/9/17 11:34
 */
@Slf4j
@RestController
@RequestMapping("/oilWell/liquidVolumeAbnormal")
@RequiredArgsConstructor
public class LiquidVolumeAbnormalController extends BaseController {

    private final LiquidVolumeAbnormalService liquidVolumeAbnormalService;

    /**
     * 分页查询液量异常汇总信息（默认查询当天数据）
     *
     * @param current 当前页
     * @param pageSize 页面大小
     * @return 查询结果
     */
    @GetMapping("/page")
    public AjaxResult queryByPage(@RequestParam(required = false, defaultValue = "1") Integer current,
                          @RequestParam(required = false, defaultValue = "10") Integer pageSize) {
        try {
            return success(ResultCode.SUCCESS_QUERY, liquidVolumeAbnormalService.queryByPage(current, pageSize));
        } catch (Exception e) {
            log.error("液量异常汇总分页查询失败，cause:{}", e.getMessage());
        }
        return error(ResultCode.ERR_SQL_SELECT_ERROR);
    }

    /**
     * 根据采油站、日期等条件查询液量异常汇总信息
     *
     * @param current 当前页
     * @param pageSize 页面大小
     * @param orgName 采油站
     * @param date 日期
     * @return 查询结果
     */
    @GetMapping("/liquidVolumeAbnormal")
    public AjaxResult queryByOrgName(@RequestParam(required = false, defaultValue = "1") Integer current,
                         @RequestParam(required = false, defaultValue = "10") Integer pageSize,
                         @RequestParam(required = false) String orgName,
                         @RequestParam(required = false) Date date) {
        try {
            return success(ResultCode.SUCCESS_QUERY, liquidVolumeAbnormalService.queryByOrgName(current, pageSize, orgName, date));
        } catch (Exception e) {
            log.error("根据条件查询液量异常汇总信息失败，cause:{}", e.getMessage());
        }
        return error(ResultCode.ERR_SQL_SELECT_ERROR);
    }

    /**
     * 根据主键ID、日期条件查询液量异常汇总区间信息
     *
     * @param primaryId 主键ID
     * @param startDate 开始日期
     * @param endDate 结束日期
     * @return 查询结果
     */
    @GetMapping("/liquidVolumeAbnormal/{primaryId}")
    public AjaxResult queryByPrimaryId(@PathVariable String primaryId, @RequestParam Date startDate,
                         @RequestParam(required = false) Date endDate) {
        try {
            return success(ResultCode.SUCCESS_QUERY, liquidVolumeAbnormalService.queryByPrimaryId(primaryId, startDate, endDate));
        } catch (Exception e) {
            log.error("根据主键ID、日期条件查询液量异常汇总区间信息失败，cause:{}", e.getMessage());
        }
        return error(ResultCode.ERR_SQL_SELECT_ERROR);
    }

    /**
     * 查询所有采油站名
     *
     * @return 查询结果
     */
    @GetMapping("/orgNames")
    public AjaxResult queryOrgNames() {
        try {
            return success(ResultCode.SUCCESS_QUERY, liquidVolumeAbnormalService.queryOrgNames());
        } catch (Exception e) {
            log.error("查询采油站信息失败，cause:{}", e.getMessage());
        }
        return error(ResultCode.ERR_SQL_SELECT_ERROR);
    }

    /**
     * 根据主键删除异常信息
     *
     * @param primaryId 主键ID
     * @return 删除结果
     */
    @DeleteMapping("/liquidVolumeAbnormal/{primaryId}")
    public AjaxResult deleteByPrimaryId(@PathVariable String primaryId) {
        try {
            liquidVolumeAbnormalService.deleteByPrimaryId(primaryId);
            return success(ResultCode.SUCCESS_DELETE);
        } catch (Exception e) {
            log.error("删除液量异常汇总信息失败，cause:{}", e.getMessage());
        }
        return error(3005, "删除失败！", null);
    }

}
