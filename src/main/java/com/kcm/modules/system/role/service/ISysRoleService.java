package com.kcm.modules.system.role.service;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.IService;
import com.kcm.common.core.domain.AjaxResult;
import com.kcm.modules.system.role.entity.SysRole;
import com.kcm.modules.system.role.entity.SysRoleModule;
import com.kcm.modules.system.role.vo.SysRoleVo;

import java.util.List;
import java.util.Map;

/**
 * @author: lucky
 * @date: 2020/8/4
 * @description: 角色信息表(SysRole)表服务接口
 **/
public interface ISysRoleService {

    /***
     * 通过角色id删除角色(假删除)
     * @author lucky
     * @date 2020/8/4
     * @param roleId 主键
     * @return 影响行数
     * @throws Exception e
     **/
    void deleteByPrimaryKey(String roleId) throws Exception;

    /***
     * 新增角色
     * @author lucky
     * @date 2020/8/4
     * @param sysRole 实例对象
     * @return 影响行数
     * @throws Exception e
     **/
    SysRole insert(SysRole sysRole) throws Exception;

    /***
     * 通过id查询角色
     * @author lucky
     * @date 2020/8/4
     * @param roleId 主键
     * @return 实例对象
     **/
    SysRole selectByPrimaryKey(String roleId);

    /***
     * 根据id修改角色
     * @author lucky
     * @date 2020/8/4
     * @param sysRole
     * @return 影响行数
     * @throws Exception e
     **/
    SysRole updateByPrimaryKey(SysRole sysRole) throws Exception;

    /***
     * 查询所有角色(普通分页查询)
     * @author lucky
     * @date 2020/8/5
     * @param paramMap 分页参数
     * @return
     **/
    AjaxResult queryRoleAll(Map<String, Object> paramMap);

    /***
     * 查询所有角色(分页插件查询)
     * @author lucky
     * @date 2020/8/17
     * @param current 当前页
     * @param pageSize 页面大小
     * @return 角色列表
     **/
    Page<SysRoleVo> queryByPageAll(Integer current, Integer pageSize);

    /***
     * 条件分页查询角色
     * @author lucky
     * @date 2020/8/20
     * @param page 分页参数
     * @param queryWrapper 条件参数
     * @return 角色列表
     **/
    Page<SysRole> queryRoleByTerm(Page page, QueryWrapper queryWrapper);

    /***
     * 批量删除角色
     * @author lucky
     * @date 2020/8/19
     * @param roleIds 角色id集合
     * @throws Exception e
     **/
    void deleteBatch(List<String> roleIds) throws Exception;


}
