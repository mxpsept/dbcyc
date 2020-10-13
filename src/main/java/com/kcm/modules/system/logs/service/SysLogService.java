package com.kcm.modules.system.logs.service;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.service.IService;
import com.kcm.modules.system.logs.entity.SysOperationLogEntity;
import com.kcm.modules.system.logs.entity.resultDto.SysLogDto;

import javax.servlet.http.HttpServletResponse;
import java.text.ParseException;


/**
 * <p>
 * 系统操作日志表 服务类
 * </p>
 *
 * @author xublu
 * @since 2020-08-11
 */
public interface SysLogService extends IService<SysOperationLogEntity> {

    /**
     * 系统操作日志分页查询
     * @param pageNum 当前页
     * @param size 页面尺寸
     * @return 查询结果
     */
    public IPage<SysOperationLogEntity> findAllByPage(int pageNum, int size);

    /**
     * 分页 + 条件查询
     * @param logDto 日志Dto
     * @param pageNum 当前页
     * @param size 页面尺寸
     * @return 查询结果
     */
    public IPage<SysOperationLogEntity> findAllByPage(SysLogDto logDto, int pageNum, int size);

    /**
     * 批量逻辑删除
     * @param ids Id集合
     * @return 删除结果
     */
    public void deleteById(String[] ids);


    /**
     * 登录操作日志分页查询
     * @param pageNum 当前页
     * @param size 页面尺寸
     * @return 查询结果
     */
    public IPage<SysOperationLogEntity> findAllLoginByPage(Integer pageNum, Integer size);

    /**
     * 分页 + 条件查询
     * @param logDto 日志Dto
     * @param pageNum 当前页
     * @param size 页面尺寸
     * @throws ParseException 解析异常
     * @return 查询结果
     */
    public IPage<SysOperationLogEntity> findAllLoginByPage(SysLogDto logDto, Integer pageNum, Integer size) throws ParseException;

    /**
     * poi导出数据
     */
    public void exportData(HttpServletResponse response);


}
