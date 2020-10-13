package com.kcm.modules.diagnosis.oilwell.abnormalgt.service.impl;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.kcm.modules.diagnosis.oilwell.abnormalgt.dao.AbnormalGtDao;
import com.kcm.modules.diagnosis.oilwell.abnormalgt.entity.AbnormalGt;
import com.kcm.modules.diagnosis.oilwell.abnormalgt.service.AbnormalGtService;
import com.kcm.modules.diagnosis.oilwell.abnormalgt.vo.AbnormalGtVo;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;


/**
 * 功图数据异常服务实现类
 *
 * @author  zhaoqingwang
 * @date  2020/9/17 13:50
 * @version  1.0
 **/
@Service
@RequiredArgsConstructor
public class AbnormalGtServiceImpl extends ServiceImpl<AbnormalGtDao, AbnormalGt> implements AbnormalGtService {

    private final AbnormalGtDao abnormalGtDao;

    /**
     * 条件查询功图数据异常
     * @param page 分页参数
     * @param checkDate 查询日期
     * @param orgName 加油站名称
     * @return 查询结果
     */
    @Override
    public Page<AbnormalGtVo> selectAllPage(Page page, String checkDate,String orgName) {
        Page<AbnormalGtVo> abnormalGtVoPage = abnormalGtDao.selectAllPage(page,checkDate,orgName);
        for (AbnormalGtVo abnormalGtVo : abnormalGtVoPage.getRecords()) {
            List<AbnormalGt> abnormalGts=abnormalGtDao.selectByIdAndDate(abnormalGtVo.getWellId(),checkDate);
            abnormalGts.remove(0);
            abnormalGtVo.setChildren(abnormalGts);
        }
        return abnormalGtVoPage;
    }

    /**
     * 通过井号（井名）和日期查询功图数据异常
     * @param wellId 井号（井名）
     * @param checkDate 日期
     * @return 查询结果
     */
    @Override
    public List<AbnormalGt> selectByIdAndDate(String wellId, String checkDate) {
        return abnormalGtDao.selectByIdAndDate(wellId,checkDate);
    }
}
