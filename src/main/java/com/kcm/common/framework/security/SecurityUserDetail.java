package com.kcm.common.framework.security;

import com.fasterxml.jackson.annotation.JsonIgnore;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Objects;

/**
 * @Description: UserDetails实现类
 * @Author: nie wei
 * @Date: 2020/8/25 11:54
 * @Version: 1.0
 */
public class SecurityUserDetail implements UserDetails {

    private static final long serialVersionUID = 2173871734631761184L;

    /**
     * 用户ID
     */
    private String userId;

    /**
     * 用户登录账号
     */
    private String username;

    /**
     * 用户密码
     */
    private String password;

    /**
     * 用户权限集合
     */
    private List<String> permissionList;

    /**
     * 用户ID的Get方法
     * @return 用户ID
     */
    public String getUserId() {
        return userId;
    }

    /**
     * 用户ID的Set方法
     * @param userId 用户ID
     */
    public void setUserId(String userId) {
        this.userId = userId;
    }

    /**
     * 用户名的Set方法
     * @param username 用户名
     */
    public void setUsername(String username) {
        this.username = username;
    }

    /**
     * 密码的Set方法
     * @param password 密码
     */
    public void setPassword(String password) {
        this.password = password;
    }

    /**
     * security获取密码的函数
     * @return 密码
     */
    @Override
    @JsonIgnore
    public String getPassword() {
        return password;
    }

    /**
     * security获取用户名的函数
     * @return 用户名
     */
    @Override
    public String getUsername() {
        return username;
    }

    /**
     * 权限集合Get方法
     * @return 权限集合
     */
    public List<String> getPermissionList() {
        return permissionList;
    }

    /**
     * 权限集合Set方法
     * @param permissionList 权限集合
     */
    public void setPermissionList(List<String> permissionList) {
        this.permissionList = permissionList;
    }

    /**
     * security获取用户权限的函数
     * @return 用户权限
     */
    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        List<GrantedAuthority> authorities= new ArrayList<>();
        for(String permission : this.permissionList){
            authorities.add(new SimpleGrantedAuthority(permission));
        }
        return authorities;
    }

    /**
     * security获取账号是否过期的函数
     * @return bool
     */
    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    /**
     * security获取账号是否锁定的函数
     * @return bool
     */
    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    /**
     * security获取密码是否过期的函数
     * @return bool
     */
    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    /**
     * security获取用户是否可用的函数
     * @return bool
     */
    @Override
    public boolean isEnabled() {
        return true;
    }

    /**
     * 构造函数
     * @param userId 用户ID
     * @param username 用户名
     * @param password 密码
     * @param permissionList 权限
     */
    public SecurityUserDetail(String userId, String username, String password, List<String> permissionList){
        this.userId = userId;
        this.username = username;
        this.password = password;
        this.permissionList = permissionList;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        SecurityUserDetail user = (SecurityUserDetail) o;
        return Objects.equals(username, user.username);
    }

    @Override
    public int hashCode() {
        return this.username.hashCode();
    }

}
