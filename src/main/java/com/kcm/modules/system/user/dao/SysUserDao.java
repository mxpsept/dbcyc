package com.kcm.modules.system.user.dao;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.kcm.modules.system.user.entity.SysUser;
import com.kcm.modules.system.user.vo.SysUserVO;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * 用户信息表(SysUser)数据库访问层
 *
 * @author shawn
 * @date 2018-10-22 10:13:05
 * @version 1.0
 */
@Mapper
public interface SysUserDao extends BaseMapper<SysUser> {

    /**
     * 根据登录名称查询用户信息
     *
     * @param page 分页
     * @param loginName 登录名称
     * @return 用户信息视图
     */
    Page<SysUserVO> queryByLoginName(Page<SysUserVO> page, @Param("loginName") String loginName);

    /**
     * 根据所给部门ID分页查询其下所有子部门用户列表
     *
     * @param page 分页
     * @param deptIds 部门ID列表
     * @return 用户信息视图
     */
    Page<SysUserVO> queryByDeptId(Page<SysUserVO> page, @Param("deptIds") List<String> deptIds);

    /**
     * 分页查询用户信息列表
     *
     * @param page 分页
     * @return 用户信息视图
     */
    Page<SysUserVO> queryAll(Page<SysUserVO> page);

    /**
     * 根据ID查询用户信息
     *
     * @param userId 用户ID
     * @return 用户信息
     */
    SysUser selectById(String userId);

}