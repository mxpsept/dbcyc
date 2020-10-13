package com.kcm.modules.system.user.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.kcm.common.framework.security.SecurityUserDetail;
import com.kcm.config.redis.cache.ICacheManager;
import com.kcm.modules.system.department.service.SysDepartmentService;
import com.kcm.modules.system.user.dao.*;
import com.kcm.modules.system.user.entity.SysUser;
import com.kcm.modules.system.user.service.SysUserService;
import com.kcm.modules.system.user.vo.SysUserVO;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.cache.annotation.CacheConfig;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * 用户信息表(SysUser)表服务实现类
 *
 * @author ZhangHao
 * @version 1.0
 * @date 2018-10-22 10:13:07
 */
@Slf4j
@Service
@RequiredArgsConstructor
@CacheConfig(cacheNames = "sys-user")
public class SysUserServiceImpl extends ServiceImpl<SysUserDao, SysUser> implements SysUserService {

	private final SysUserDao sysUserDao;

	private final SysDepartmentService departmentService;

	private final ICacheManager cacheManager;

	private static final String SYS_USER_CACHE_KEY_PREFIX = "sys-user-";

	private static final String SYS_USER_DEFAULT_PASSWORD = "123456";

	@Override
	@Cacheable(key = "'sys-user-'+#loginName")
	public Page<SysUserVO> queryByLoginName(Integer current, Integer pageSize, String loginName) {
		return sysUserDao.queryByLoginName(new Page<>(current, pageSize), loginName);
	}

	@Override
	@SuppressWarnings("unchecked")
	public Page<SysUserVO> queryByPage(Integer current, Integer pageSize) {
		String cacheKey = SYS_USER_CACHE_KEY_PREFIX + "page-" + current + "-" + pageSize;
		if (cacheManager.exist(cacheKey)) {
			return (Page<SysUserVO>) cacheManager.getCache(cacheKey);
		}
		Page<SysUserVO> page = sysUserDao.queryAll(new Page<>(current, pageSize));
		boolean result = cacheManager.putCache(cacheKey, page, 60 * 5);
		if (!result) {
			log.error("Redis exception: key={}已经存在", cacheKey);
		}
		return page;
	}

	@Override
	public List<SysUser> queryByDeptId(String deptId) {
		return list(new QueryWrapper<SysUser>().eq("department_id", deptId));
	}

	@Override
	@SuppressWarnings("unchecked")
	public Page<SysUserVO> queryByDeptIds(String deptId, Integer current, Integer pageSize) {
		String cacheKey = SYS_USER_CACHE_KEY_PREFIX + "page-deptId-" + deptId + "-" + current + "-" + pageSize;
		if (cacheManager.exist(cacheKey)) {
			return (Page<SysUserVO>) cacheManager.getCache(cacheKey);
		}
		List<String> deptIds = departmentService.getAllChildDepartmentId(deptId);
		Page<SysUserVO> page = sysUserDao.queryByDeptId(new Page<>(current, pageSize), deptIds);
		boolean result = cacheManager.putCache(cacheKey, page, 60 * 5);
		if (!result) {
			log.error("Redis exception: key={}已经存在", cacheKey);
		}
		return page;
	}

	@Override
	public SysUser insert(SysUser sysUser) throws Exception {
		BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();
		String encodePass = encoder.encode(SYS_USER_DEFAULT_PASSWORD);
		boolean result = save(sysUser.toBuilder().password(encodePass).defaultPassword(encodePass).build());
		if (result) {
			return sysUser;
		} else {
			throw new Exception("添加用户失败！");
		}
	}

	@Override
	public SysUser update(SysUser sysUser) throws Exception {
		boolean result = updateById(sysUser);
		if (result) {
			return sysUserDao.selectById(sysUser.getUserId());
		} else {
			throw new Exception("更新用户信息失败！");
		}
	}

	@Override
	public boolean updatePassword(Map<String, String> info, SysUser user) {
		String oldPass = info.get("oldPass");
		String newPass = info.get("newPass");
		BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();
		if (encoder.matches(oldPass, user.getPassword())) {
			String encodePass = encoder.encode(newPass);
			user.setPassword(encodePass);
			return updateById(user);
		}
		return false;
	}

	@Override
	public void deleteById(String userId) throws Exception {
		SysUser user = SysUser.builder().userId(userId).active("0").build();
		boolean result = updateById(user);
		if (!result) {
			throw new Exception("删除用户信息失败！");
		}
	}

	@Override
	public void deleteBatch(List<String> sysUserIds) throws Exception {
		List<SysUser> list = new ArrayList<>();
		for (String sysUserId: sysUserIds) {
			SysUser user = new SysUser();
			user.setUserId(sysUserId);
			user.setActive("0");
			list.add(user);
		}
		boolean result = updateBatchById(list);
		if (!result) {
			throw new Exception("批量删除用户信息失败！");
		}
	}

	@Override
	public SysUser getCurrentUser() {
		SecurityUserDetail userDetail = (SecurityUserDetail) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
		return sysUserDao.selectOne(new QueryWrapper<SysUser>().eq("LOGIN_NAME", userDetail.getUsername()));
	}

}