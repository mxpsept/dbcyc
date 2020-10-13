package com.kcm.modules.examine.standard.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.kcm.common.core.domain.AjaxResult;
import com.kcm.common.enums.ResultCode;
import com.kcm.common.other.StringUtils;
import com.kcm.modules.examine.standard.dao.BizExamineIndexDetailDao;
import com.kcm.modules.examine.standard.dao.BizExamineIndexInforDao;
import com.kcm.modules.examine.standard.dao.BizExamineTemplateInforDao;
import com.kcm.modules.examine.standard.entity.BizExamineIndexDetail;
import com.kcm.modules.examine.standard.entity.BizExamineIndexInfor;
import com.kcm.modules.examine.standard.entity.BizExamineTemplateInfor;
import com.kcm.modules.examine.standard.service.BizIndexInforService;
import com.kcm.modules.examine.standard.vo.BizDetailInfoListPageVo;
import com.kcm.modules.examine.standard.vo.BizDetailInfoVo;
import com.kcm.modules.examine.standard.vo.BizIndexInforVo;
import com.kcm.modules.system.user.dao.SysUserDao;
import com.kcm.modules.system.user.entity.SysUser;
import com.kcm.modules.system.user.service.SysUserService;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;

/**
 * <p>
 * 考核指标信息表 服务实现类
 * </p>
 *
 * @author xublu
 * @since 2020-08-24
 */
@Service
@SuppressWarnings("all")
public class BizIndexInforServiceImpl extends ServiceImpl<BizExamineIndexInforDao, BizExamineIndexInfor> implements BizIndexInforService {

    @Autowired
    private BizExamineIndexInforDao inforDao;

    @Autowired
    private BizExamineIndexDetailDao indexDetailDao;

    @Autowired
    private BizExamineTemplateInforDao templateInforDao;

    @Autowired
    private SysUserDao userDao;

    @Autowired
    private SysUserService sysUserService;

    /**
     * 非分页
     *
     * @return 查询结果
     */
    @Override
    public List<BizExamineIndexInfor> queryAll() {
        return inforDao.selectList(null);
    }

    /**
     * 分页查询
     *
     * @param page 当前页
     * @param size    页面尺寸
     * @return 查询结果
     */
    @Override
    public Page<BizDetailInfoListPageVo> queryPageAll(Integer page, Integer size) {
        // 分页判断
        if (page == null || page < 0) {
            page = 1;
        }
        if (size == null || size < 0 || size > 100) {
            size = 10;
        }
        Page<BizDetailInfoListPageVo> InforDetailVoPage = inforDao.queryByPageAll(new Page<>(page, size));
        //遍历组装考核指标及考核指标明细数据
        for (BizDetailInfoListPageVo inforDetailVo : InforDetailVoPage.getRecords()) {
            QueryWrapper<BizExamineIndexInfor> queryWrapper = new QueryWrapper<>();
            queryWrapper.eq("INDEX_ID",inforDetailVo.getIndexId());
            BizExamineIndexInfor indexInfor = inforDao.selectOne(queryWrapper);
            if(null == indexInfor){
                return AjaxResult.error(ResultCode.ERR_USER_AUTHENTICATION);
            }
            // 查询考核模板名称
            BizExamineTemplateInfor templateInfor = templateInforDao.selectByPrimaryKey(indexInfor.getExamineTId());
            inforDetailVo.setExamineTName(templateInfor.getTemplateName());
            // 查询上级考核指标名称
            QueryWrapper queryWrapper2 = new QueryWrapper<>();
            queryWrapper2.eq("INDEX_P_ID",indexInfor.getIndexPId());
            String index = indexInfor.getIndexId();
            queryWrapper2.eq("INDEX_ID",indexInfor.getIndexId());
            queryWrapper2.eq("ACTIVE",indexInfor.getActive());
            BizExamineIndexInfor infor = inforDao.selectOne(queryWrapper2);
            if(null == infor){
                return AjaxResult.error(ResultCode.ERR_USER_AUTHENTICATION);
            }
            inforDetailVo.setIndexPName(infor.getIndexName());

            QueryWrapper queryWrapperInfo = new QueryWrapper<>();
            queryWrapperInfo.eq("INDEX_ID",inforDetailVo.getIndexId());
            List<BizExamineIndexDetail> indexDetails = indexDetailDao.selectList(queryWrapperInfo);
            inforDetailVo.setIndexDetails(indexDetails);
        }
        return InforDetailVoPage;
    }

    /**
     * 分页 + 条件查询
     *
     * @param indexInfo 指标信息实体类
     * @param pageNum   当前页
     * @param size      页面尺寸
     * @return 查询结果
     */
    @Override
    public IPage<BizIndexInforVo> findAllByPageAndCon(String indexName, Integer page, Integer size) {
        // 分页判断
        if (page == null || page < 0) {
            page = 1;
        }
        if (size == null || size < 0 || size > 100) {
            size = 10;
        }
        Page<BizIndexInforVo> entityIPage = inforDao.queryByPageAndConAll(indexName, new Page<>(page, size));
        for (BizIndexInforVo inforVo : entityIPage.getRecords()) {
            // 构建条件
            QueryWrapper queryWrapperIndex = new QueryWrapper<>();
            queryWrapperIndex.eq("INDEX_ID",inforVo.getIndexId());
            BizExamineIndexInfor examineIndexInfor = inforDao.selectOne(queryWrapperIndex);
            if(null == examineIndexInfor){
                return AjaxResult.error(ResultCode.ERR_USER_AUTHENTICATION);
            }
            // 查询考核模板名称
            BizExamineTemplateInfor templateInfor = templateInforDao.selectByPrimaryKey(examineIndexInfor.getExamineTId());
            inforVo.setExamineTName(templateInfor.getTemplateName());
        }
        return entityIPage;
    }

    /**
     * 新增数据【组合实体类】
     *
     * @param indexInfo 数据实体类
     */
    @Override
    public void insertSelect(BizDetailInfoVo detailInfo) {
        SysUser sysUser = selectUser();
        BizExamineIndexInfor indexInfo = new BizExamineIndexInfor();
        // 1.保存绩效考核指标信息表数据
        indexInfo.setIndexName(detailInfo.getIndexName());
        indexInfo.setScoreWeight(detailInfo.getScoreWeight());
        indexInfo.setSequence(detailInfo.getSequence());
        indexInfo.setCreateTime(new Date());
        indexInfo.setEditTime(new Date());
        indexInfo.setActive("1"); // 默认有效
        indexInfo.setIndexPId(detailInfo.getIndexPId());
        indexInfo.setIndexId(StringUtils.uuid()); // id使用uuid
        indexInfo.setExamineTId(detailInfo.getExamineTId()); // 模板ID由前端选择传入
        indexInfo.setCreateUserId(sysUser.getUserId());
        indexInfo.setCreateDeptId(sysUser.getDepartmentId());
        indexInfo.setEditUserId(sysUser.getUserId());
        indexInfo.setEditDeptId(sysUser.getDepartmentId());
        inforDao.insertSelect(indexInfo);
        // 2.保存绩效考核指标信息明细表数据
        List<BizExamineIndexDetail> indexDetails = detailInfo.getIndexDetails();
        for (BizExamineIndexDetail indexDetail : indexDetails) {
            indexDetail.setCreateTime(new Date());
            indexDetail.setEditTime(new Date());
            indexDetail.setActive("1"); // 默认有效
            indexDetail.setIndexId(indexInfo.getIndexId()); // 考核指标ID
            indexDetail.setCreateUserId(sysUser.getUserId());
            indexDetail.setCreateDeptId(sysUser.getDepartmentId());
            indexDetail.setEditUserId(sysUser.getUserId());
            indexDetail.setEditDeptId(sysUser.getDepartmentId());
            indexDetailDao.insert(indexDetail);
        }
    }

    /**
     * 查询登录用户信息
     */
    public SysUser selectUser() {
        // 获取spring-Security框架里面的用户登录名
        String username = SecurityContextHolder.getContext().getAuthentication().getName();
        QueryWrapper<SysUser> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("LOGIN_NAME", username);
        SysUser sysUser = userDao.selectOne(queryWrapper);
        return sysUser;
    }

    /**
     * 逻辑删除
     *
     * @param ids ID集合
     * @return 删除结果
     */
    @Override
    public void deleteById(String id) {
        BizExamineIndexInfor indexInfo = inforDao.selectById(id);
        indexInfo.setActive("0");
        inforDao.updateById(indexInfo);
        // 删除考核信息明细表数据【避免垃圾数据的遗留】
        QueryWrapper<BizExamineIndexDetail> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("INDEX_ID", id);
        List<BizExamineIndexDetail> indexDetails = indexDetailDao.selectList(queryWrapper);
        for (BizExamineIndexDetail indexDetail : indexDetails) {
            indexDetail.setActive("0");
            indexDetailDao.updateById(indexDetail);
        }
    }

    /**
     * 根据ID获取实体【回显修改的数据】
     *
     * @param id
     * @return
     */
//    @Override
//    public BizDetailInfoVo findOne(String id) {
//        // 回显绩效考核指标信息表数据
//        BizExamineIndexInfor indexInfor = inforDao.selectById(id);
//        // 回显绩效考核指标明细表数据【集合】
//        QueryWrapper<BizExamineIndexDetail> queryWrapper = new QueryWrapper<>();
//        queryWrapper.eq("INDEX_ID", id);
//        List<BizExamineIndexDetail> indexDetails = indexDetailDao.selectList(queryWrapper);
//        // 构建组合实体类返回结果
//        BizDetailInfoVo detailInfo = new BizDetailInfoVo();
//        BeanUtils.copyProperties(indexInfor,detailInfo);
//        detailInfo.setIndexDetails(indexDetails);
//        return detailInfo;
//    }

    /**
     * 修改
     */
    @Override
    public void update(BizDetailInfoVo indexInfo) {
        SysUser sysUser = selectUser();
        // 修改考核指标数据
        BizExamineIndexInfor examineIndexInfor = new BizExamineIndexInfor();
        BeanUtils.copyProperties(indexInfo,examineIndexInfor);
        inforDao.update(examineIndexInfor, null);
        // 修改考核指标明细数据  解决方案是【先删再存】
        // 1. 先删除数据
        QueryWrapper<BizExamineIndexDetail> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("INDEX_NAME", indexInfo.getIndexId());
        indexDetailDao.delete(queryWrapper);//删除
        for (BizExamineIndexDetail indexDetail : indexInfo.getIndexDetails()) {
            // 2. 再重新添加
            indexDetail.setCreateTime(new Date());
            indexDetail.setEditTime(new Date());
            indexDetail.setActive("1"); // 默认有效
            indexDetail.setIndexId(indexInfo.getIndexId()); // 考核指标ID
            indexDetail.setCreateUserId(sysUser.getUserId());
            indexDetail.setCreateDeptId(sysUser.getDepartmentId());
            indexDetail.setEditUserId(sysUser.getUserId());
            indexDetail.setEditDeptId(sysUser.getDepartmentId());
            indexDetailDao.insert(indexDetail);
        }
    }

    /***
     * 根据考核模板id查询所有指标信息
     * @author lucky
     * @date 2020/9/10
     * @param TemplateId 考核模板id
     * @return 指标列表
     **/
    @Override
    public List<BizExamineIndexInfor> queryByTemplateId(String TemplateId) {
        QueryWrapper<BizExamineIndexInfor> queryWrapper = new QueryWrapper<BizExamineIndexInfor>().eq("EXAMINE_T_ID", TemplateId);
        return list(queryWrapper);
    }
}
