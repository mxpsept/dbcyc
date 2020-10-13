package com.kcm.modules.examine.manage.service.impl;

import com.baomidou.mybatisplus.core.injector.methods.DeleteBatchByIds;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.kcm.common.other.StringUtils;
import com.kcm.modules.examine.manage.dao.BizExaminePlanInforDao;
import com.kcm.modules.examine.manage.entity.BizExaminePlanInfor;
import com.kcm.modules.examine.manage.entity.BizExamineResultInfor;
import com.kcm.modules.examine.manage.service.IExaPlanInforService;
import com.kcm.modules.examine.manage.service.IExaResultInforService;
import com.kcm.modules.examine.manage.vo.BizExaminePlanInforVo;
import com.kcm.modules.examine.notice.entity.BizNoticeInfor;
import com.kcm.modules.examine.notice.entity.SysMessage;
import com.kcm.modules.examine.notice.service.NoticeService;
import com.kcm.modules.examine.standard.entity.BizExamineIndexInfor;
import com.kcm.modules.examine.standard.vo.BizExamineTemplateInforVo;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

/**
 * @author: lucky
 * @date: 2020/8/24
 * @description: 考核计划信息表(BIZ_EXAMINE_PLAN_INFOR)服务实现类
 **/
@Service
public class ExaPlanInforServiceImpl extends ServiceImpl<BizExaminePlanInforDao, BizExaminePlanInfor> implements IExaPlanInforService {

    private BizExaminePlanInforDao bizExaminePlanInforDao;
    private IExaResultInforService exaResultInforService;
    private NoticeService noticeService;

    public ExaPlanInforServiceImpl(BizExaminePlanInforDao bizExaminePlanInforDao, IExaResultInforService exaResultInforService, NoticeService noticeService) {
        this.bizExaminePlanInforDao = bizExaminePlanInforDao;
        this.exaResultInforService = exaResultInforService;
        this.noticeService = noticeService;
    }

    /***
     * 查询所有考核计划信息(分页插件查询)
     * @author lucky
     * @date 2020/8/25
     * @param current 当前页
     * @param pageSize 页面大小
     * @return 考核计划列表
     **/
    @Override
    public Page<BizExaminePlanInforVo> queryByPageAll(Integer current, Integer pageSize) {
        Page<BizExaminePlanInforVo> planInforVoPage = bizExaminePlanInforDao.queryByPageAll(new Page(current, pageSize));
        for (BizExaminePlanInforVo bizExaminePlanInforVo : planInforVoPage.getRecords()) {
            bizExaminePlanInforVo.setResultInforList(exaResultInforService.selectByExaminePId(bizExaminePlanInforVo.getExaminePId()));
        }
        return planInforVoPage;
    }

    /***
     * 新增考核计划信息
     * @author lucky
     * @date 2020/8/25
     * @param bizExaminePlanInforVo 考核计划对象
     * @return 数据库操作记录
     **/
    @Override
    public BizExaminePlanInforVo insert(BizExaminePlanInforVo bizExaminePlanInforVo) throws Exception {
        BizExaminePlanInfor bizExaminePlanInforCopy = new BizExaminePlanInfor();
        //添加考核计划信息id
        bizExaminePlanInforVo.setExaminePId(StringUtils.uuid());
        //复制考核计划对象信息
        BeanUtils.copyProperties(bizExaminePlanInforVo, bizExaminePlanInforCopy);
        //插入考核计划信息
        boolean result = save(bizExaminePlanInforCopy);
        //遍历考核结果对象数组，添加考核计划ID、考核结果ID
        for (BizExamineResultInfor bizExamineResultInfor : bizExaminePlanInforVo.getResultInforList()) {
            bizExamineResultInfor.setExamineRId(StringUtils.uuid());
            bizExamineResultInfor.setExaminePId(bizExaminePlanInforCopy.getExaminePId());
        }
        //调用考核结果批量插入方法,批量插入考核结果信息
        exaResultInforService.insertBatch(bizExaminePlanInforVo.getResultInforList());
        if (result) {
            Map<String, String> map = new HashMap<>(1);
            List<Map<String, String>> userIdList = new ArrayList<>();
            //将制定计划信息通知给相应单位/人员
            BizNoticeInfor noticeInfor = new BizNoticeInfor();
            noticeInfor.setNoticeTopic("考核计划的制定");
            SysMessage sysMessage = new SysMessage();
            sysMessage.setMessageContent("测试的考核计划内容");
            //获取通知单位集合
            List<String> collect = bizExaminePlanInforVo.getResultInforList().stream().map(BizExamineResultInfor::getTakeObject).collect(Collectors.toList());
            //将计划执行人list信息转为字符串格式
            map.put("考核计划执行人", String.join(",", collect));
            userIdList.add(map);
            noticeService.publish(noticeInfor, sysMessage, userIdList);
            return selectPlanAndResultByPId(bizExaminePlanInforCopy.getExaminePId());
        } else {
            throw new Exception("新增考核计划信息失败");
        }
    }

    /***
     * 修改考核计划信息
     * @author lucky
     * @date 2020/8/25
     * @param bizExaminePlanInfor 考核计划对象
     * @return 数据库操作记录
     **/
    @Override
    public BizExaminePlanInfor update(BizExaminePlanInfor bizExaminePlanInfor) throws Exception {
        boolean result = updateById(bizExaminePlanInfor);
        if (result) {
            return bizExaminePlanInforDao.selectByPrimaryKey(bizExaminePlanInfor.getExaminePId());
        } else {
            throw new Exception("修改考核计划信息失败");
        }
    }

    /***
     * 根据考核计划id查询考核计划信息
     * @author lucky
     * @date 2020/8/26
     * @param examinePId 考核计划id
     * @return 考核计划信息
     **/
    @Override
    public BizExaminePlanInfor selectByPrimaryKey(String examinePId) {
        return bizExaminePlanInforDao.selectByPrimaryKey(examinePId);
    }

    /***
     * 根据考核计划id查询考核计划信息和考核结果信息
     * @author lucky
     * @date 2020/8/26
     * @param examinePId 考核计划id
     * @return 考核计划信息和考核结果信息
     **/
    @Override
    public BizExaminePlanInforVo selectPlanAndResultByPId(String examinePId) {
        BizExaminePlanInforVo bizExaminePlanInforVo = new BizExaminePlanInforVo();
        //复制考核计划信息到VO
        BeanUtils.copyProperties(selectByPrimaryKey(examinePId), bizExaminePlanInforVo);
        //获取考核结果信息
        bizExaminePlanInforVo.setResultInforList(exaResultInforService.selectByExaminePId(examinePId));
        return bizExaminePlanInforVo;
    }

    /***
     * 根据考核计划id删除考核计划信息以及考核结果信息
     * @author lucky
     * @date 2020/9/12
     * @param examinePlanId 考核计划id
     * @throws Exception e
     **/
    @Override
    public void deleteByPrimaryKey(String examinePlanId) throws Exception {
        //删除考核计划信息
        boolean deletePlan = bizExaminePlanInforDao.deleteByPrimaryKey(examinePlanId);
        //删除考核计划包含的考核结果相关信息
        List<BizExamineResultInfor> bizExamineResultInfors = exaResultInforService.selectByExaminePId(examinePlanId);
        //遍历考核结果信息
        for (BizExamineResultInfor bizExamineResultInfor : bizExamineResultInfors) {
            exaResultInforService.deleteByResultId(bizExamineResultInfor.getExamineRId());
        }
        if (!deletePlan) {
            throw new Exception("删除考核计划信息失败!");
        }
    }

    /***
     * 根据计划名称查询考核计划信息
     * @author lucky
     * @date 2020/8/26
     * @param page 分页参数
     * @param planName 模板名称
     **/
    @Override
    public Page<BizExaminePlanInforVo> searchByPlanName(Page page, String planName) {
        Page<BizExaminePlanInforVo> planInforVoPages = bizExaminePlanInforDao.selectByPlanNamePage(page, planName);
        for (BizExaminePlanInforVo bizExaminePlanInforVo : planInforVoPages.getRecords()) {
            bizExaminePlanInforVo.setResultInforList(exaResultInforService.selectByExaminePId(bizExaminePlanInforVo.getExaminePId()));
        }
        return planInforVoPages;
    }
}
