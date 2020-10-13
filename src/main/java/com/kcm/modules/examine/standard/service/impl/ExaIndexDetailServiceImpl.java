package com.kcm.modules.examine.standard.service.impl;


import cn.hutool.core.date.DateTime;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.kcm.modules.examine.manage.entity.BizExamineResultDetail;
import com.kcm.modules.examine.standard.dao.BizExamineIndexDetailDao;
import com.kcm.modules.examine.standard.entity.BizExamineIndexDetail;
import com.kcm.modules.examine.standard.service.BizIndexInforService;
import com.kcm.modules.examine.standard.service.IExaIndexDetailService;

import com.kcm.modules.system.user.entity.SysUser;
import com.kcm.modules.system.user.service.SysUserService;
import com.sun.org.apache.regexp.internal.RE;
import lombok.RequiredArgsConstructor;

import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * 考核指标明细信息表(BIZ_EXAMINE_INDEX_DETAIL)服务实现类
 *
 * @author zhaoqingwang
 * @date 2020/8/24
 * @version 1.0
 **/
@Service
@RequiredArgsConstructor
public class ExaIndexDetailServiceImpl extends ServiceImpl<BizExamineIndexDetailDao,BizExamineIndexDetail> implements IExaIndexDetailService {


    private final BizExamineIndexDetailDao bizExamineIndexDetailDao;

    private final SysUserService sysUserService;

    /**
     * 新增考核指标明细信息
     *
     * @author zhaoqingwang
     * @param bizExamineIndexDetail 考核指标明细实体
     * @return 影响行数
     */
    @Override
    public Integer insert(BizExamineIndexDetail bizExamineIndexDetail){
        SysUser sysUser = sysUserService.getCurrentUser();
        if (!"0".equals(bizExamineIndexDetail.getActive())){
            bizExamineIndexDetail.setActive("1"); 
        }
        bizExamineIndexDetail.setCreateDeptId(sysUser.getDepartmentId());
        bizExamineIndexDetail.setCreateUserId(sysUser.getUserId());
        bizExamineIndexDetail.setCreateTime(new DateTime());
        bizExamineIndexDetail.setEditDeptId(sysUser.getDepartmentId());
        bizExamineIndexDetail.setEditTime(new DateTime());
        bizExamineIndexDetail.setEditUserId(sysUser.getUserId());
        return  bizExamineIndexDetailDao.insert(bizExamineIndexDetail);
    }

    /**
     * 通过主键修改考核指标明细信息
     *
     * @author zhaoqingwang
     * @param bizExamineIndexDetail 考核指标明细实体
     * @return 影响行数
     */
    @Override
    public Integer update(BizExamineIndexDetail bizExamineIndexDetail){
        return bizExamineIndexDetailDao.updateByPrimaryKey(bizExamineIndexDetail);
    }

    /**
     * 通过主键修改考核指标明细部分信息
     *
     * @author zhaoqingwang
     * @param bizExamineIndexDetail 考核指标明细实体
     * @return 影响行数
     */
    @Override
    public  Integer updateSelective(BizExamineIndexDetail bizExamineIndexDetail){
        return bizExamineIndexDetailDao.updateByPrimaryKeySelective(bizExamineIndexDetail);
    }

    /**
     * 通过主键查询考核指标明细信息
     *
     * @author zhaoqingwang
     * @param indexDId 考核指标明细ID
     * @return 考核指标明细实体
     */
    @Override
    public BizExamineIndexDetail selectByPrimary(String indexDId){
        return bizExamineIndexDetailDao.selectByPrimaryKey(indexDId);
    }

    /**
     * 查询所有考核指标明细（分页插件）
     *
     * @author zhaoqingwang
     * @param current 当前页
     * @param pageSize 每页尺寸
     * @return 查询结果
     */
    @Override
    public IPage<BizExamineIndexDetail> selectAll(Integer current,Integer pageSize){
        IPage<BizExamineIndexDetail> page = new Page<>(current,pageSize);
        return bizExamineIndexDetailDao.selectPage(page,new QueryWrapper<BizExamineIndexDetail>().eq("ACTIVE","1"));
    }

    /**
     * 通过指标ID查询对应所有指标明细
     *
     * @author zhaoqingwang
     * @param indexId 指标ID
     * @return 查询结果
     */
    @Override
    public List<BizExamineIndexDetail> selectByIndexId(String indexId){
        QueryWrapper<BizExamineIndexDetail> wapper = new QueryWrapper<>();
        wapper.eq("INDEX_ID",indexId)
                .eq("ACTIVE","1");
        return bizExamineIndexDetailDao.selectList(wapper);
    }

    /**
     * 通过考核内容模糊查询考核指标明细（分页插件）
     *
     * @param examineContent 考核内容模糊字段
     * @param current 当前页
     * @param pageSize 页面尺寸
     * @return 查询结果
     */
    @Override
    public IPage<BizExamineIndexDetail> selectByExamineContent(String examineContent,Integer current,Integer pageSize) {
        QueryWrapper<BizExamineIndexDetail> queryWrapper = new QueryWrapper<>();
        queryWrapper.like("EXAMINE_CONTENT",examineContent).eq("ACTIVE","1");
        Page page = new Page(current,pageSize);
        IPage<BizExamineIndexDetail> bizExamineIndexDetailIPage = bizExamineIndexDetailDao.selectPage(page,queryWrapper);
        return bizExamineIndexDetailIPage;
    }

    /**
     * 通过主键删除考核指标明细信息
     *
     * @author zhaoqingwang
     * @param indexDId 考核指标明细ID
     * @return 考核指标明细实体
     */
    @Override
    public Integer deleteByPrimary(String indexDId){
        return bizExamineIndexDetailDao.deleteByPrimaryKey(indexDId);
    }

    /**
     * 通过考核指标明细ID集合批量删除
     *
     * @author zhaoqingwang
     * @param indexDIds 考核指标明细ID集合
     * @return 影响行数
     */
    @Override
    public Integer deleteByIds(List<String> indexDIds){
        int i = 0;
        for (String indexDId : indexDIds) {
            bizExamineIndexDetailDao.deleteByPrimaryKey(indexDId);
            i++;
        }
        return i;
    }

    /**
     * 通过考核指标ID批量删除
     *
     * @param indexId 考核指标ID
     * @return 数据库操作记录
     */
    @Override
    public Integer deleteByIndexId(String indexId){
        QueryWrapper<BizExamineIndexDetail> wapper =new QueryWrapper<>();
        wapper.eq("INDEX_ID",indexId).eq("ACTIVE","1");
        List<BizExamineIndexDetail> bizExamineIndexDetailList = bizExamineIndexDetailDao.selectList(wapper);
        Integer result = 0;
        for (BizExamineIndexDetail bizExamineIndexDetail : bizExamineIndexDetailList) {
            bizExamineIndexDetailDao.deleteByPrimaryKey(bizExamineIndexDetail.getIndexDId());
            result++;
        }
        return result;
    }


}
