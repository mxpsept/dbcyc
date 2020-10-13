package com.kcm.modules.examine.manage.service.impl;

import cn.hutool.core.date.DateTime;
import cn.hutool.core.date.DateUtil;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.kcm.common.other.StringUtils;
import com.kcm.modules.examine.manage.dao.BizExamineResultDetailDao;
import com.kcm.modules.examine.manage.entity.BizExamineResultDetail;
import com.kcm.modules.examine.manage.service.IExaResultDetailService;
import com.kcm.modules.examine.manage.service.IExaResultInforService;
import com.kcm.modules.examine.manage.vo.BizExamineIndexDetailVo;
import com.kcm.modules.system.user.entity.SysUser;
import com.kcm.modules.system.user.service.SysUserService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * 考核结果明细表(BIZ_EXAMINE_INDEX_DETAIL)服务实现类
 *
 * @author lucky
 * @date 2020/8/27
 **/
@Service
@RequiredArgsConstructor
public class ExaResultDetailServiceImpl extends ServiceImpl<BizExamineResultDetailDao, BizExamineResultDetail> implements IExaResultDetailService {

    private final BizExamineResultDetailDao bizExamineResultDetailDao;

    private final SysUserService sysUserService;


    /**
     * 新增一个考核结果明细
     *
     * @param bizExamineResultDetail 考核结果明细实体
     * @return 数据库影响行数
     */
    @Override
    public Integer insert(BizExamineResultDetail bizExamineResultDetail) {
        return bizExamineResultDetailDao.insert(bizExamineResultDetail);
    }

    /**
     * 单个考核明细打分之后将对应的考核结果明细插入表中
     *
     * @param examineResultId 考核结果ID
     * @param indexDetailId   考核指标明细ID
     * @param score           该项明细所得分数
     */
    @Override
    public void insertResultDetail(String examineResultId, String indexDetailId, BigDecimal score) {
        SysUser sysUser = sysUserService.getCurrentUser();
        BizExamineResultDetail bizExamineResultDetail = new BizExamineResultDetail();
        bizExamineResultDetail.setExamineRdId(examineResultId);
        bizExamineResultDetail.setIndexDId(indexDetailId);
        bizExamineResultDetail.setSingleScore(score);
        bizExamineResultDetail.setExamineRdId(examineResultId + indexDetailId);
        bizExamineResultDetail.setActive("1");
        bizExamineResultDetail.setCreateTime(new DateTime());
        bizExamineResultDetail.setCreateUserId(sysUser.getUserId());
        bizExamineResultDetail.setCreateDeptId(sysUser.getDepartmentId());
        bizExamineResultDetail.setEditDeptId(sysUser.getEditDeptId());
        bizExamineResultDetail.setEditUserId(sysUser.getEditUserId());
        bizExamineResultDetail.setEditTime(new DateTime());
        bizExamineResultDetail.setRateDate(new DateTime());
        bizExamineResultDetail.setRater(sysUser.getUserName());
        bizExamineResultDetailDao.insert(bizExamineResultDetail);
    }

    /**
     * 所有明细打分结束后批量插入数据
     *
     * @param examineResultId 考核结果ID
     * @param map             key为考核指标明细ID，value为得分score的map
     */
    @Override
    public void insertResultDetails(String examineResultId, Map<String, BigDecimal> map) {
        for (String key : map.keySet()) {
            insertResultDetail(examineResultId, key, map.get(key));
        }
    }

    /**
     * 查询当前登陆用户信息
     *
     * @return 用户信息
     */
    private SysUser selectUser() {
        return sysUserService.getCurrentUser();
    }

    /**
     * 批量增加考核结果明细
     *
     * @param bizExamineResultDetailList 考核结果明细
     * @return 数据库操作记录
     * @throws Exception 批量插入失败
     */
    @Override
    public List<BizExamineResultDetail> insertBatch(List<BizExamineResultDetail> bizExamineResultDetailList) throws Exception {
        boolean result = saveBatch(bizExamineResultDetailList);
        if (result) {
            return bizExamineResultDetailList;
        } else {
            throw new Exception("批量插入失败!");
        }
    }

    /***
     * 根据考核结果id查询考核指标明细相关信息
     * @author lucky
     * @date 2020/8/27
     * @param examineRId 考核结果id
     * @return 考核指标明细相关信息列表
     **/
    @Override
    public List<BizExamineIndexDetailVo> selectByResultId(String examineRId) {
        return bizExamineResultDetailDao.selectByRId(examineRId);
    }

    /***
     * 整理考核评分结果并批量插入考核结果明细
     * @author lucky
     * @date 2020/8/27
     * @param indexDetailVos 评分结果信息列表
     * @return 考核结果明细列表
     * @throws Exception e
     **/
    @Override
    public List<BizExamineResultDetail> insertBatchResultDetail(List<BizExamineIndexDetailVo> indexDetailVos) throws Exception {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        List<BizExamineResultDetail> bizExamineIndexDetailList = new ArrayList<>();
        for (BizExamineIndexDetailVo indexDetailVo : indexDetailVos) {
            BizExamineResultDetail resultDetail = new BizExamineResultDetail();
            resultDetail.setExamineRdId(StringUtils.uuid());
            resultDetail.setSingleScore(indexDetailVo.getSingleScore());
            resultDetail.setRater(authentication.getName());
            resultDetail.setRateDate(DateUtil.parse(DateUtil.now()));
            resultDetail.setIndexDId(indexDetailVo.getIndexDId());
            resultDetail.setExamineRId(indexDetailVo.getExamineRId());
            resultDetail.setRemark(indexDetailVo.getRemark());
            resultDetail.setActive("1");
            bizExamineIndexDetailList.add(resultDetail);
        }
        boolean result = saveBatch(bizExamineIndexDetailList);
        if (result) {
            return bizExamineIndexDetailList;
        } else {
            throw new Exception("新增考核结果详情信息失败！");
        }
    }


    /**
     * 根据考核结果ID查询考核结果明细集合
     *
     * @param examineResultId 考核结果ID
     * @return 查询结果
     */
    @Override
    public List<BizExamineResultDetail> selectExamineResultDetails(String examineResultId) {
        QueryWrapper<BizExamineResultDetail> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("EXAMINE_R_ID", examineResultId);
        return bizExamineResultDetailDao.selectList(queryWrapper);
    }

}
