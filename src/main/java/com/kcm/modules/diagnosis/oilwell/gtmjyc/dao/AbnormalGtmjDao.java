package com.kcm.modules.diagnosis.oilwell.gtmjyc.dao;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.kcm.modules.diagnosis.oilwell.gtmjyc.entity.AbnormalGtmjEntity;
import com.kcm.modules.diagnosis.oilwell.gtmjyc.vo.AbnormalGtmjVo;
import org.apache.ibatis.annotations.Param;


/**
 * <p>
 * 功图面积异常表 Mapper 接口
 * </p>
 *
 * @author xublu
 * @since 2020-09-18
 */
public interface AbnormalGtmjDao extends BaseMapper<AbnormalGtmjEntity> {
    Page<AbnormalGtmjVo> queryByPageAll(Page page);

    Page<AbnormalGtmjVo> selectByTimeAndOrg(@Param("wellId") String wellId, @Param("checkTime") String checkTime, @Param("page") Page page);
}
