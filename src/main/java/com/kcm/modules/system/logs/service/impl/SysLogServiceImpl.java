package com.kcm.modules.system.logs.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.kcm.common.core.domain.AjaxResult;
import com.kcm.common.date.DateUtils;
import com.kcm.common.enums.ResultCode;
import com.kcm.common.utils.ExcelUtil;
import com.kcm.modules.system.logs.dao.SysOperationLogDao;
import com.kcm.modules.system.logs.entity.SysOperationLogEntity;
import com.kcm.modules.system.logs.entity.resultDto.SysLogDto;
import com.kcm.modules.system.logs.service.SysLogService;
import com.kcm.modules.system.logs.vo.ExcelLogEntity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpServletResponse;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;

/**
 * <p>
 * 系统操作日志表 服务实现类
 * </p>
 *
 * @author xublu
 * @since 2020-08-11
 */
@Service
@SuppressWarnings("all")
public class SysLogServiceImpl extends ServiceImpl<SysOperationLogDao, SysOperationLogEntity> implements SysLogService {

    @Autowired
    private SysOperationLogDao logDao;

    /**
     * 分页查询
     *
     * @param pageNum
     * @param size
     * @return
     */
    @Override
    public IPage<SysOperationLogEntity> findAllByPage(int pageNum, int size) {
        QueryWrapper<SysOperationLogEntity> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("ACTIVE","1"); // 表是否有效【1有效，0无效】
        IPage<SysOperationLogEntity> page = new Page<>(pageNum,size);
        IPage<SysOperationLogEntity> entityIPage = logDao.selectPage(page, queryWrapper);
        List<SysOperationLogEntity> records = entityIPage.getRecords();
        for (SysOperationLogEntity entity:records) {
            String operationType = entity.getOperationType();
            //Object的equals方法容易抛空指针异常，应使用常量或确定有值的对象来调用equals。
            if("1".equals(operationType)){
                String replace = operationType.replace("1", "更新");
                entity.setOperationType(replace);
            }
            if("2".equals(operationType)){
                String replace = operationType.replace("2", "删除");
                entity.setOperationType(replace);
            }
            if("3".equals(operationType)){
                String replace = operationType.replace("3", "授权");
                entity.setOperationType(replace);
            }
            if("4".equals(operationType)){
                String replace = operationType.replace("4", "导入");
                entity.setOperationType(replace);
            }
            if("5".equals(operationType)){
                String replace = operationType.replace("5", "导出");
                entity.setOperationType(replace);
            }
            if("6".equals(operationType)){
                String replace = operationType.replace("6", "导出");
                entity.setOperationType(replace);
            }
            if("7".equals(operationType)){
                String replace = operationType.replace("7", "登录");
                entity.setOperationType(replace);
            }
            if("8".equals(operationType)){
                String replace = operationType.replace("8", "退出");
                entity.setOperationType(replace);
            }
            String channel = entity.getChannel();
            if("0".equals(channel)){
                String replace = channel.replace("0", "PC端用户");
                entity.setChannel(replace);
            }
            if("1".equals(channel)){
                String replace = channel.replace("1", "移动端用户");
                entity.setChannel(replace);
            }
            if("99".equals(channel)){
                String replace = channel.replace("99", "其它");
                entity.setChannel(replace);
            }

        }
        return entityIPage;
    }

    /**
     * 分页 + 条件查询
     *
     * @param logEntity
     * @param pageNum
     * @param size
     * @return
     */
    @Override
    public IPage<SysOperationLogEntity>  findAllByPage(SysLogDto logDto, int pageNum, int size) {
        QueryWrapper<SysOperationLogEntity> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("active",1); // 表是否有效【1有效，0无效】
        queryWrapper.like("MODULE_NAME",logDto.getModuleName()); // 根据日志模块名称模糊查询
        queryWrapper.between("OPERATION_TIME",logDto.getStartTime(),logDto.getEndTime()); // 封装两个时间参数【根据时间段来进行查询日志记录】
        IPage<SysOperationLogEntity> page = new Page<>(pageNum,size);
        IPage<SysOperationLogEntity> entityIPage = logDao.selectPage(page, queryWrapper);
        return entityIPage;
    }

    /**
     * 批量逻辑删除
     *
     * @param ids
     * @return
     */
    @Override
    public void deleteById(String[] ids) {
        for (String id : ids) {
            SysOperationLogEntity logEntity = logDao.selectById(id);
            logEntity.setActive("0");
            logDao.updateById(logEntity);
        }
    }

    /**
     * 登录操作日志分页查询
     *
     * @param pageNum
     * @param size
     * @return
     */
    @Override
    public IPage<SysOperationLogEntity> findAllLoginByPage(Integer pageNum, Integer size) {
        Map map = new HashMap<>();
        QueryWrapper<SysOperationLogEntity> queryWrapper = new QueryWrapper<>();
        map.put("ACTIVE","1"); // 表是否有效【1有效，0无效】
        map.put("OPERATION_TYPE","7"); // 7代表登录
        queryWrapper.allEq(map);
        //SysOperationLogEntity logEntity = new SysOperationLogEntity();
        IPage<SysOperationLogEntity> page = new Page<>(pageNum,size);
        IPage<SysOperationLogEntity> entityIPage = logDao.selectPage(page, queryWrapper);
        List<SysOperationLogEntity> records = entityIPage.getRecords();
        for (SysOperationLogEntity entity:records) {
            String operationType = entity.getOperationType();
            String replace = operationType.replace("7", "登录");
            entity.setOperationType(replace);
        }
        return entityIPage;
    }

    /**
     * 分页 + 条件查询
     *
     * @param logDto
     * @param pageNum
     * @param size
     * @return
     */
    @Override
    public IPage<SysOperationLogEntity> findAllLoginByPage(SysLogDto logDto, Integer pageNum, Integer size) throws ParseException {
        if(logDto == null && logDto.equals("")){
            return AjaxResult.error(ResultCode.ERR_PARAM_IS_BLANK);
        }
        Map map = new HashMap<>();
        QueryWrapper<SysOperationLogEntity> queryWrapper = new QueryWrapper<>();
        map.put("ACTIVE","1"); // 表是否有效【1有效，0无效】
        map.put("OPERATION_TYPE","7"); // 7代表登录
        queryWrapper.allEq(map);
        queryWrapper.like("MODULE_NAME",logDto.getModuleName()); // 根据日志模块名称模糊查询
        // 日期格式化
        Date startDate = DateUtils.stringToDate(logDto.getStartTime());
        Date endDate = DateUtils.stringToDate(logDto.getEndTime());
        queryWrapper.between("OPERATION_TIME",startDate,endDate); // 封装两个时间参数【根据时间段来进行查询日志记录】
        IPage<SysOperationLogEntity> page = new Page<>(pageNum,size);
        IPage<SysOperationLogEntity> entityIPage = logDao.selectPage(page, queryWrapper);
        return entityIPage;
    }


    /**
     * poi导出数据
     */
    @Override
    public void exportData(HttpServletResponse response) {
        String fileName = "文件名";
        String sheetName = "文件名";
        // 批量导出数据
        List<SysOperationLogEntity> logEntities = logDao.selectList(null);
        List<ExcelLogEntity> logExcelList = new ArrayList<>();
        for (SysOperationLogEntity logEntity : logEntities) {
            DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
            ExcelLogEntity logExcel = ExcelLogEntity.builder()
                    .moduleName(logEntity.getModuleName())
                    .operationType(logEntity.getOperationType())
                    .operatorName(logEntity.getOperatorName())
                    .method(logEntity.getMethod())
                    .method(logEntity.getMethodParam())
                    .requestIp(logEntity.getRequestIp())
                    .requestLocation(logEntity.getRequestLocation())
                    .requestParam(logEntity.getRequestParam())
                    .requestUrl(logEntity.getRequestUrl())
                    .operationTime(dateFormat.format(logEntity.getOperationTime()))
                    .departmentName(logEntity.getDepartmentName())
                    .channel(logEntity.getChannel())
                    .class1(logEntity.getClass1())
                    .build();
            //.createdTime(dateFormat.format(user.getCreatedTime())).build();
            logExcelList.add(logExcel);
        }
        try {
            ExcelUtil.writeExcel(response, logExcelList, fileName, sheetName, ExcelLogEntity.class);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

}
