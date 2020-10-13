package com.kcm.modules.diagnosis.oilwell.liquidvolume.vo;

import com.kcm.modules.diagnosis.oilwell.liquidvolume.entity.LiquidVolumeAbnormal;
import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * 液量异常汇总VO实体
 *
 * @author beiguoge
 * @version 1.0
 * @date 2020/9/17 15:11
 */
@Data
@EqualsAndHashCode(callSuper = true)
public class LiquidVolumeAbnormalVO extends LiquidVolumeAbnormal {

    private static final long serialVersionUID = 3006739326360147117L;

    private String orgName;

}
