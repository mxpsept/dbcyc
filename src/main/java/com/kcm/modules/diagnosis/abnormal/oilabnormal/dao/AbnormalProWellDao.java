package com.kcm.modules.diagnosis.abnormal.oilabnormal.dao;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.kcm.modules.diagnosis.abnormal.oilabnormal.entity.AbnormalProWell;
import org.springframework.stereotype.Repository;

/**
 * 油井异常信息表(ABNORMAL_PRO_WELL)数据访问层
 * @author lucky
 * @date 2020/9/19
 **/
@Repository
public interface AbnormalProWellDao extends BaseMapper<AbnormalProWell> {

    int insertSelective(AbnormalProWell record);
}