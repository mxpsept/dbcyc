package com.kcm.modules.diagnosis.oilwell.dymyc.service.impl;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.kcm.modules.diagnosis.oilwell.dymyc.dao.AbnormalDymDao;
import com.kcm.modules.diagnosis.oilwell.dymyc.entity.AbnormalDymEntity;
import com.kcm.modules.diagnosis.oilwell.dymyc.entity.dto.DymParamDto;
import com.kcm.modules.diagnosis.oilwell.dymyc.service.AbnormalDymService;
import com.kcm.modules.diagnosis.oilwell.dymyc.vo.AbnormalDymVo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * <p>
 * 动液面异常表 服务实现类
 * </p>
 *
 * @author xublu
 * @since 2020-09-17
 */
@Service
@SuppressWarnings("all")
public class AbnormalDymServiceImpl extends ServiceImpl<AbnormalDymDao, AbnormalDymEntity> implements AbnormalDymService {

    @Autowired
    private AbnormalDymDao abnormalDymDao;


    /**
     * 动液面异常井分页查询
     *
     * @param pageNum 页码
     * @param size    每页记录数
     * @return 返回列表结果
     */
    @Override
    public Page<AbnormalDymVo> findAllByPage(Integer pageNum, Integer size) {
        // 分页判断
        if (pageNum == null || pageNum < 0) {
            pageNum = 1;
        }
        if (size == null || size < 0 || size > 100) {
            size = 10;
        }
        Page<AbnormalDymVo> dymEntityPage = abnormalDymDao.queryByPageAll(new Page<>(pageNum, size));

        return dymEntityPage;
    }

    /**
     * 分页 + 条件查询
     *
     * @param orgName   实体类
     * @param startTime 日期
     * @param endTime   日期
     * @param pageNum   当前页
     * @param size      页面尺寸
     * @return 查询结果
     */
    @Override
    public Page<AbnormalDymVo> findAllByPageAndCon(DymParamDto paramDto, Integer pageNum, Integer size) {
        // 分页判断
        if (pageNum == null || pageNum < 0) {
            pageNum = 1;
        }
        if (size == null || size < 0 || size > 100) {
            size = 10;
        }
        Page<AbnormalDymVo> dymEntityPageAndCon = abnormalDymDao.selectByTimeAndOrg(paramDto.getWellId(),paramDto.getCheckTime(),new Page(pageNum,size));
        return dymEntityPageAndCon;
    }

}
