package com.kcm.modules.system.user.service;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.IService;
import com.kcm.modules.system.user.entity.SysUser;
import com.kcm.modules.system.user.vo.SysUserVO;

import java.util.List;
import java.util.Map;

/**
 * 用户信息表(SysUser)表服务接口
 *
 * @author shawn
 * @date 2018-10-22 10:13:06
 * @version 1.0
 */
public interface SysUserService extends IService<SysUser> {

	/**
	 * 分页查询用户信息列表
	 *
	 * @param current 当前页
	 * @param pageSize 页面大小
	 * @return 用户列表
	 */
	Page<SysUserVO> queryByPage(Integer current, Integer pageSize);

	/**
	 * 根据登录名称查询用户信息
	 *
	 * @param current 当前页
	 * @param pageSize 页面大小
	 * @param loginName 登录名称
	 * @return 用户信息视图
	 */
	Page<SysUserVO> queryByLoginName(Integer current, Integer pageSize, String loginName);

	/**
	 * 根据部门ID查询用户列表
	 *
	 * @param deptId 部门ID
	 * @return 用户列表
	 */
	List<SysUser> queryByDeptId(String deptId);

	/**
	 * 根据所给部门ID分页查询其下所有子部门用户列表
	 *
	 * @param deptId 部门ID
	 * @param current 当前页
	 * @param pageSize 页面大小
	 * @return 用户列表
	 */
	Page<SysUserVO> queryByDeptIds(String deptId, Integer current, Integer pageSize);

	/**
	 * 新增数据
	 *
	 * @param sysUser 实例对象
	 * @return 新增结果
	 * @throws Exception e
	 */
	SysUser insert(SysUser sysUser) throws Exception;

	/**
	 * 更新用户信息
	 *
	 * @param sysUser 实例对象
	 * @return 修改结果
	 * @throws Exception e
	 */
	SysUser update(SysUser sysUser) throws Exception;

	/**
	 * 更新用户密码
	 *
	 * @param info 密码信息
	 * @param user 当前登录用户
	 * @return 执行结果
	 */
	boolean updatePassword(Map<String, String> info, SysUser user);

	/**
	 * 删除用户信息
	 *
	 * @param userId 用户id
	 * @throws Exception e
	 */
	void deleteById(String userId) throws Exception;

	/**
	 * 批量删除用户信息
	 *
	 * @param sysUserIds 用户id集合
	 * @throws Exception e
	 */
	void deleteBatch(List<String> sysUserIds) throws Exception;

	/**
	 * 获取当前系统登录用户
	 *
	 * @return 用户信息
	 */
	SysUser getCurrentUser();

}