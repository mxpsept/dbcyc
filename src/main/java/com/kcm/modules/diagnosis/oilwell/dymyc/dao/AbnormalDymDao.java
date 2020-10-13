package com.kcm.modules.diagnosis.oilwell.dymyc.dao;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.kcm.modules.diagnosis.oilwell.dymyc.entity.AbnormalDymEntity;
import com.kcm.modules.diagnosis.oilwell.dymyc.vo.AbnormalDymVo;
import org.apache.ibatis.annotations.Param;


/**
 * <p>
 * 动液面异常表 Mapper 接口
 * </p>
 *
 * @author xublu
 * @since 2020-09-17
 */
public interface AbnormalDymDao extends BaseMapper<AbnormalDymEntity> {

    Page<AbnormalDymVo> queryByPageAll(Page page);

    Page<AbnormalDymVo> selectByTimeAndOrg(@Param("wellId") String wellId, @Param("checkTime") String checkTime, @Param("page") Page page);

    //Page<AbnormalDymVo> selectByTimeAndOrg(@Param("wellId") String wellId, @Param("page") Page page);
}
