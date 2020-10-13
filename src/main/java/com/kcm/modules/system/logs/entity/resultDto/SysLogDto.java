package com.kcm.modules.system.logs.entity.resultDto;

import lombok.Data;

import java.util.Date;

@Data
public class SysLogDto {
    private String startTime;
    private String endTime;
    private String moduleName;
}
