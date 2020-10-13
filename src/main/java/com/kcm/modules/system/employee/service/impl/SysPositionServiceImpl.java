package com.kcm.modules.system.employee.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.kcm.modules.system.employee.dao.SysPositionDao;
import com.kcm.modules.system.employee.entity.SysPositionEntity;
import com.kcm.modules.system.employee.service.SysPositionService;
import com.kcm.modules.system.user.dao.SysUserDao;
import com.kcm.modules.system.user.entity.SysUser;
import com.kcm.modules.system.user.service.SysUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;

/**
 * <p>
 * 职务信息表 服务实现类
 * </p>
 *
 * @author xublu
 * @since 2020-08-12
 */
@Service
@SuppressWarnings("all")
public class SysPositionServiceImpl extends ServiceImpl<SysPositionDao, SysPositionEntity> implements SysPositionService {

    @Autowired
    private SysPositionDao positionDao;

    @Autowired
    private SysUserDao userDao;

    @Autowired
    private SysUserService sysUserService;

    /**
     * 岗位管理分页查询
     * @param pageNum
     * @param size
     * @return
     */
    @Override
    public IPage<SysPositionEntity> findAllByPage(Integer pageNum, Integer size) {
        QueryWrapper<SysPositionEntity> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("ACTIVE","1"); // 表是否有效【1有效，0无效】
        IPage<SysPositionEntity> page = new Page<>(pageNum,size);
        IPage<SysPositionEntity> entityIPage = positionDao.selectPage(page, queryWrapper);
        return entityIPage;
    }

    /**
     * 分页 + 条件查询
     * @param positionEntity
     * @param pageNum
     * @param size
     * @return
     */
    @Override
    public IPage<SysPositionEntity> findAllByPage(String positionName, Integer pageNum, Integer size) {
        QueryWrapper<SysPositionEntity> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("active",1); // 表是否有效【1有效，0无效】
        queryWrapper.like("POSITION_NAME",positionName); // 模糊查询【根据岗位编码和岗位名称】
        IPage<SysPositionEntity> page = new Page<>(pageNum,size);
        IPage<SysPositionEntity> entityIPage = positionDao.selectPage(page, queryWrapper);
        return entityIPage;
    }

    /**
     * 新增数据
     * @param entity
     */
    @Override
    public void insert(SysPositionEntity entity) {
        // shrio能获取到登录用户名，在以这个为条件在用户表查询所需字段
        SysUser user = sysUserService.getCurrentUser();
        entity.setCreateTime(new Date());
        entity.setEditTime(new Date());
        entity.setActive("1"); // 默认有效
        QueryWrapper<SysUser> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("LOGIN_NAME",user.getLoginName());
        SysUser sysUser = userDao.selectOne(queryWrapper);
        entity.setCreateUserId(sysUser.getUserId());
        entity.setCreateDeptId(sysUser.getDepartmentId());
        entity.setEditUserId(sysUser.getUserId());
        entity.setEditDeptId(sysUser.getDepartmentId());
        positionDao.insert(entity);
    }

    /**
     * 批量逻辑删除
     * @param ids
     * @return
     */
    @Override
    public void deleteById(String[] ids) {
        for (String id : ids) {
            SysPositionEntity positionEntity = positionDao.selectById(id);
            positionEntity.setActive("0");
            positionDao.updateById(positionEntity);
        }
    }
}
