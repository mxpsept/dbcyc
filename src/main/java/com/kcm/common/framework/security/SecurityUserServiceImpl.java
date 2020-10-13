package com.kcm.common.framework.security;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.kcm.modules.system.user.dao.SysUserDao;
import com.kcm.modules.system.user.entity.SysUser;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

/**
 * @Description: 安全框架用户服务层类
 * @Author: nie wei
 * @Date: 2020/8/25 9:40
 * @Version: 1.0
 */
@Service(value = "userDetailsService")
@RequiredArgsConstructor
public class SecurityUserServiceImpl implements UserDetailsService {

    private final SysUserDao sysUserDao;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        SysUser user = sysUserDao.selectOne(new QueryWrapper<SysUser>().eq("LOGIN_NAME", username));
        if (null == user) {
            throw new UsernameNotFoundException("用户不存在！");
        }
        // TODO(后期修改为动态获取权限)
        List<String> permissionList= new ArrayList<>();
        permissionList.add("sys:user:and");
        permissionList.add("sys:user:update");
        permissionList.add("sys:user:delete");
        return new SecurityUserDetail(user.getUserId(), user.getLoginName(), user.getPassword(), permissionList);
    }

}
