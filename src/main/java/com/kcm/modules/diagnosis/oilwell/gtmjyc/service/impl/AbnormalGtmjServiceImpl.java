package com.kcm.modules.diagnosis.oilwell.gtmjyc.service.impl;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.kcm.modules.diagnosis.oilwell.dymyc.entity.dto.DymParamDto;
import com.kcm.modules.diagnosis.oilwell.gtmjyc.dao.AbnormalGtmjDao;
import com.kcm.modules.diagnosis.oilwell.gtmjyc.entity.AbnormalGtmjEntity;
import com.kcm.modules.diagnosis.oilwell.gtmjyc.service.AbnormalGtmjService;
import com.kcm.modules.diagnosis.oilwell.gtmjyc.vo.AbnormalGtmjVo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * <p>
 * 功图面积异常表 服务实现类
 * </p>
 *
 * @author xublu
 * @since 2020-09-18
 */
@Service
@SuppressWarnings("all")
public class AbnormalGtmjServiceImpl extends ServiceImpl<AbnormalGtmjDao, AbnormalGtmjEntity> implements AbnormalGtmjService {

    @Autowired
    private AbnormalGtmjDao abnormalGtmjDao;

    /**
     * 功图面积异常分页查询
     * @param pageNum 页码
     * @param size    每页记录数
     * @return 返回列表结果
     */
    @Override
    public Page<AbnormalGtmjVo> findAllByPage(Integer pageNum, Integer size) {
        Page<AbnormalGtmjVo> gtmjVoPage = abnormalGtmjDao.queryByPageAll(new Page(pageNum, size));
        List<AbnormalGtmjVo> gtmjVoPageRecords = gtmjVoPage.getRecords();
        return gtmjVoPage;
    }

    /**
     * 分页 + 条件查询
     *
     * @param paramDto 实体类
     * @param pageNum  当前页
     * @param size     页面尺寸
     * @return 查询结果
     */
    @Override
    public Page<AbnormalGtmjVo> findAllByPageAndCon(DymParamDto paramDto, Integer pageNum, Integer size) {
        // 分页判断
        if (pageNum == null || pageNum < 0) {
            pageNum = 1;
        }
        if (size == null || size < 0 || size > 100) {
            size = 10;
        }
        Page<AbnormalGtmjVo> dymEntityPageAndCon = abnormalGtmjDao.selectByTimeAndOrg(paramDto.getWellId(),paramDto.getCheckTime(),new Page(pageNum,size));
        return dymEntityPageAndCon;
    }

}
