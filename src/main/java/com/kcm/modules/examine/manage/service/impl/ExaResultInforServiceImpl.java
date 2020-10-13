package com.kcm.modules.examine.manage.service.impl;

import cn.hutool.core.date.DateUtil;
import cn.hutool.core.exceptions.ExceptionUtil;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.injector.methods.SelectPage;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.kcm.common.core.domain.AjaxResult;
import com.kcm.common.enums.ResultCode;
import com.kcm.common.other.StringUtils;
import com.kcm.modules.examine.manage.dao.BizExamineResultDetailDao;
import com.kcm.modules.examine.manage.dao.BizExamineResultInforDao;
import com.kcm.modules.examine.manage.entity.BizExamineResultDetail;
import com.kcm.modules.examine.manage.entity.BizExamineResultInfor;
import com.kcm.modules.examine.manage.service.IExaResultDetailService;
import com.kcm.modules.examine.manage.service.IExaResultInforService;
import com.kcm.modules.examine.manage.vo.*;
import com.kcm.modules.examine.standard.dao.BizExamineIndexDetailDao;
import com.kcm.modules.examine.standard.dao.BizExamineIndexInforDao;
import com.kcm.modules.examine.standard.entity.BizExamineIndexDetail;
import com.kcm.modules.examine.standard.entity.BizExamineIndexInfor;
import com.kcm.modules.system.code.entity.SysCodeType;
import com.kcm.modules.system.code.entity.SysTCodeInfor;
import com.kcm.modules.system.code.vo.SysCodeTypeVo;
import com.kcm.modules.system.user.entity.SysUser;
import com.kcm.modules.system.user.service.SysUserService;
import lombok.RequiredArgsConstructor;
import org.omg.CORBA.Current;
import org.springframework.beans.BeanUtils;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.UUID;


/**
 * 考核结果信息表（BIZ_EXAMINE_RESULT_INFOR）服务实现层
 *
 * @author zhaoqingwang
 * @version 1.0
 * @date 2020/08/26
 */
@Service
@RequiredArgsConstructor
public class ExaResultInforServiceImpl extends ServiceImpl<BizExamineResultInforDao, BizExamineResultInfor> implements IExaResultInforService {

    private final BizExamineResultInforDao bizExamineResultInforDao;
    private final IExaResultDetailService iExaResultDetailService;
    private final BizExamineIndexInforDao bizExamineIndexInforDao;
    private final BizExamineIndexDetailDao bizExamineIndexDetailDao;


    /**
     * 通过主键修改考核结果信息
     *
     * @param bizExamineResultInfor 考核结果信息实体
     * @return 影响行数
     */
    @Override
    public Integer updateByPrimary(BizExamineResultInfor bizExamineResultInfor) {
        return bizExamineResultInforDao.updateByPrimaryKey(bizExamineResultInfor);
    }

    /**
     * 修改考核结果以及考核结果明细
     * @param bizExamineResultInforVo 考核结果Vo
     * @return 修改结果
     */
    @Override
    public String updateByVo(BizExamineResultInforVo bizExamineResultInforVo) {
        BizExamineResultInfor bizExamineResultInfor = new BizExamineResultInfor();
        BeanUtils.copyProperties(bizExamineResultInforVo,bizExamineResultInfor);
        int result = updateByPrimary(bizExamineResultInfor);
        List<BizExamineResultDetail> bizExamineResultDetailList =iExaResultDetailService.selectExamineResultDetails(bizExamineResultInforVo.getExamineRId());
        for (BizExamineResultDetail bizExamineResultDetail : bizExamineResultDetailList) {
            bizExamineResultDetail.setActive("0");
        }
        iExaResultDetailService.updateBatchById(bizExamineResultDetailList);
        boolean resultDetail = iExaResultDetailService.updateBatchById(bizExamineResultInforVo.getBizExamineResultDetailList());
        if(resultDetail){
            if (result<=0){
                return "考核结果明细修改失败！";
            }else {
                return "修改成功！";
            }
        }else {
            if (result<=0){
                return "考核结果以及考核结果明细都修改失败！";
            }else {
                return "考核结果修改失败！";
            }
        }
    }

    /**
     * 通过主键查询考核结果信息
     *
     * @param examineResultId 考核结果ID
     * @return 查询结果
     */
    @Override
    public BizExamineResultInfor selectByPrimary(String examineResultId) {
        return bizExamineResultInforDao.selectByPrimaryKey(examineResultId);
    }

    /**
     * 通过考核结果查询总得分并保存
     *
     * @param examineResultId 考核结果ID
     * @return 查询结果
     */
    @Override
    public BigDecimal queryTotalScore(String examineResultId) {
        BigDecimal totalScore = new BigDecimal(0);
        List<BizExamineResultVo> bizExamineIndexDetailVoList = bizExamineResultInforDao.selectResultTotal(examineResultId);
        for (BizExamineResultVo bizExamineResultVo : bizExamineIndexDetailVoList) {
            if(bizExamineResultVo.getSingleScore()!=null){
                BigDecimal score = bizExamineResultVo.getSingleScore().multiply(bizExamineResultVo.getScoreWeight());
                totalScore = totalScore.add(score);
            }
        }
        BizExamineResultInfor bizExamineResultInfor = bizExamineResultInforDao.selectByPrimaryKey(examineResultId);
        bizExamineResultInfor.setTotalScore(totalScore);
        bizExamineResultInforDao.updateByPrimaryKey(bizExamineResultInfor);
        return totalScore;
    }

    /**
     * 批量插入考核结果信息
     *
     * @param bizExamineResultInforList 考核结果信息
     * @return 数据库操作记录
     * @throws Exception 批量插入考核结果信息失败
     */
    @Override
    public List<BizExamineResultInfor> insertBatch(List<BizExamineResultInfor> bizExamineResultInforList) throws Exception {
        boolean result = saveBatch(bizExamineResultInforList);
        if (result) {
            return bizExamineResultInforList;
        } else {
            throw new Exception("批量插入考核结果信息失败！");
        }
    }

    /**
     * 批量修改考核结果信息
     *
     * @param bizExamineResultInforList 考核结果信息
     * @return 数据库操作记录
     * @throws Exception 批量修改考核信息失败
     */
    @Override
    public List<BizExamineResultInfor> updateBatch(List<BizExamineResultInfor> bizExamineResultInforList) throws Exception {
        boolean result = updateBatchById(bizExamineResultInforList);
        if (result) {
            return bizExamineResultInforList;
        } else {
            throw new Exception("批量修改考核信息失败！");
        }
    }

    /**
     * 根据考核计划id查询所有考核结果信息
     *
     * @param examinePid 考核计划ID
     * @return 查询结果
     */
    @Override
    public List<BizExamineResultInfor> selectByExaminePId(String examinePid) {
        QueryWrapper<BizExamineResultInfor> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("EXAMINE_P_ID", examinePid).eq("ACTIVE", '1');
        List<BizExamineResultInfor> bizExamineResultInforList = bizExamineResultInforDao.selectList(queryWrapper);
        return bizExamineResultInforList;
    }


    /**
     * 根据id删除考核结果信息以及考核结果明细信息
     *
     * @param resultId 考核结果id
     * @throws Exception
     */
    @Override
    public void deleteByResultId(String resultId) throws Exception {

        //删除考核结果信息
        int deleteResult = bizExamineResultInforDao.deleteByPrimaryKey(resultId);
        //批量删除考核结果明细信息
        //根据考核结果id查询考核结果明细

        List<BizExamineResultDetail> bizExamineResultDetails = iExaResultDetailService.selectExamineResultDetails(resultId);
        //遍历考核结果明细信息，修改状态值
        for (BizExamineResultDetail bizExamineResultDetail : bizExamineResultDetails) {
            bizExamineResultDetail.setActive("0");
        }

        //批量修改考核结果明细信息
        boolean deleteBatchDetail = iExaResultDetailService.updateBatchById(bizExamineResultDetails);
        if (deleteResult < 0) {
            throw new Exception("删除考核结果信息失败！");
        }
        if (!deleteBatchDetail) {
            throw new Exception("批量删除考核结果明细信息失败！");
        }

    }

    /**
     * 通过考核结果id集合删除批量删除
     * @param ids 考核结果ID集合
     */
    @Override
    public Integer deleteByIdS(List<String> ids) throws Exception {
        int i=0;
        for (String id : ids) {
            deleteByResultId(id);
            i++;
        }
        return i;
    }



    /**
     * 通过考核结果信息查询考核指标明细集合
     * @param bizExamineResultInfor 考核结果信息
     * @return 查询结果
     */
    public List<BizExamineIndexDetail> selectIndexDetails(BizExamineResultInfor bizExamineResultInfor){
       return bizExamineResultInforDao.selectByResultId(bizExamineResultInfor.getExaminePId());
    }

    /**
     * 查询所有考核结果（分页）
     * @param current 当前页
     * @param pageSize 页面尺寸
     * @return 查询结果
     */
    @Override
    public Page<BizExamineResultInforPageVo> selectResultPage(Integer current,Integer pageSize) {
        Page<BizExamineResultInforPageVo> page = bizExamineResultInforDao.selectResultPage(new Page(current,pageSize));
        return insertDetail(page);
    }


    /**
     * 通过单位/人员查询考核结果
     * @param page 分页参数
     * @param takeObject 单位/人员
     * @return 查询结果
     */
    @Override
    public Page<BizExamineResultInforPageVo> selectResultByTakeObject(Page page, String takeObject) {
        Page<BizExamineResultInforPageVo> resultPage = bizExamineResultInforDao.selectResultByTakeObject(page,takeObject);
        return insertDetail(resultPage);
    }


    /**
     * 分页查询时在BizExamineResultInforPageVo中的list添加数据
     * @param page list为空的分页数据
     * @return 添加完list之后的分页数据
     */
    public  Page<BizExamineResultInforPageVo>insertDetail(Page<BizExamineResultInforPageVo> page){
        for (BizExamineResultInforPageVo bizExamineResultInforPageVo : page.getRecords()) {
            String examineTId = bizExamineResultInforPageVo.getExamineTId();
            List<BizExamineIndexInfor> bizExamineIndexInforList = bizExamineIndexInforDao.selectList(
                    new QueryWrapper<BizExamineIndexInfor>().eq("EXAMINE_T_ID",examineTId)
            );
            List<BizResultInforIndexVo> bizResultInforIndexVoList = new ArrayList<>();
            for (BizExamineIndexInfor bizExamineIndexInfor : bizExamineIndexInforList) {
                BizResultInforIndexVo bizResultInforIndexVo = new BizResultInforIndexVo();
                BeanUtils.copyProperties(bizExamineIndexInfor,bizResultInforIndexVo);
                bizResultInforIndexVo.setBizExamineIndexDetailList(bizExamineIndexDetailDao.selectList(
                        new QueryWrapper<BizExamineIndexDetail>().eq("INDEX_ID",bizResultInforIndexVo.getIndexId())
                ));
                bizResultInforIndexVoList.add(bizResultInforIndexVo);
            }
            bizExamineResultInforPageVo.setBizResultInforIndexVoList(bizResultInforIndexVoList);
        }
        return page;
    }
}
