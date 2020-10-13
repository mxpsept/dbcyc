package com.kcm.modules.diagnosis.abnormal.oilabnormal.service.impl;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.kcm.modules.diagnosis.abnormal.oilabnormal.dao.AbnormalProWellDao;
import com.kcm.modules.diagnosis.abnormal.oilabnormal.entity.AbnormalProWell;
import com.kcm.modules.diagnosis.abnormal.oilabnormal.service.IOilAbnormalService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

/**
 * @author: lucky
 * @date: 2020/9/19
 * @description: 油井异常信息表(ABNORMAL_PRO_WELL)服务实现层
 **/
@Service
@RequiredArgsConstructor
public class OilAbnormalServiceImpl extends ServiceImpl<AbnormalProWellDao, AbnormalProWell> implements IOilAbnormalService {


}
