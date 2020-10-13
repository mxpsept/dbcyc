package com.kcm.modules.diagnosis.knowledge.tile.vo;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

/**
 * @author: lucky
 * @date: 2020/9/24
 * @description: 条件查询参数
 **/
@Data
@Builder(toBuilder = true)
@NoArgsConstructor
@AllArgsConstructor
public class QueryBytermVo {

    private Integer currentPage;
    private Integer pageSize;
    private Integer total;
    private String oilStation;
    private String wellId;
    private String startTime;
    private String endTime;
}
