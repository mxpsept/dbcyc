package com.kcm.modules.system.logs.vo;

import com.alibaba.excel.annotation.ExcelProperty;
import com.alibaba.excel.annotation.write.style.ColumnWidth;
import com.alibaba.excel.annotation.write.style.ContentRowHeight;
import com.alibaba.excel.annotation.write.style.HeadRowHeight;
import com.baomidou.mybatisplus.annotation.TableField;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * <p>
 * 系统操作日志表
 * </p>
 *
 * @author xublu
 * @since 2020-08-11
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@ContentRowHeight(30)
@HeadRowHeight(value = 20)
public class ExcelLogEntity {

    private static final long serialVersionUID=1L;


    @ExcelProperty(value = "模板名称", index = 0)
    @ColumnWidth(value = 15)
    private String moduleName;

    @ExcelProperty(value = "操作类型", index = 1)
    @ColumnWidth(value = 15)
    private String operationType;

    @TableField("CLASS")
    @ExcelProperty(value = "类名", index = 2)
    @ColumnWidth(value = 15)
    private String class1;

    @ExcelProperty(value = "方法名称", index = 3)
    @ColumnWidth(value = 15)
    private String method;

    @ExcelProperty(value = "来源渠道", index = 4)
    @ColumnWidth(value = 15)
    private String channel;

    @ExcelProperty(value = "操作人员", index = 5)
    @ColumnWidth(value = 15)
    private String operatorName;

    @ExcelProperty(value = "部门名称", index = 6)
    @ColumnWidth(value = 15)
    private String departmentName;

    @ExcelProperty(value = "请求URL", index = 7)
    @ColumnWidth(value = 15)
    private String requestUrl;

    @ExcelProperty(value = "请求IP", index = 8)
    @ColumnWidth(value = 15)
    private String requestIp;

    @ExcelProperty(value = "操作地点", index = 9)
    @ColumnWidth(value = 15)
    private String requestLocation;

    @ExcelProperty(value = "请求参数", index = 10)
    @ColumnWidth(value = 15)
    private String requestParam;

    @ExcelProperty(value = "接收参数", index = 11)
    @ColumnWidth(value = 15)
    private String methodParam;


    @ExcelProperty(value = "操作时间", index = 12)
    @ColumnWidth(value = 15)
    private String operationTime;


}
