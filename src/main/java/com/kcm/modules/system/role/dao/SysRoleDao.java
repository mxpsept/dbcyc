package com.kcm.modules.system.role.dao;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.kcm.modules.system.role.entity.SysRole;
import com.kcm.modules.system.role.vo.SysRoleVo;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Map;

/**
 * 角色信息表(SysRole)数据库访问层
 * @author lucky
 * @date 2020/8/4
 **/
@Repository
public interface SysRoleDao extends BaseMapper<SysRole> {
    /***
     * 通过角色id删除角色
     * @author lucky
     * @date 2020/8/4 
     * @param roleId 主键
     * @return 影响行数
     **/
    int deleteByPrimaryKey(String roleId);

    /***
     * 新增角色
     * @author lucky
     * @date 2020/8/4 
     * @param sysRoleVo 实例对象
     * @return 影响行数
     **/
    int insert(SysRoleVo sysRoleVo);

    /***
     * 通过id查询角色
     * @author lucky
     * @date 2020/8/4 
     * @param roleId 主键
     * @return 实例对象
     **/
    SysRole selectByPrimaryKey(String roleId);

    /***
     * 查询所有角色(普通分页查询)
     * @author lucky
     * @date 2020/8/5
     * @param paramMap 分页参数
     * @return
     **/
    List<SysRole> queryRoleAll(Map<String,Object> paramMap);

    /***
     * 查询所有角色(分页插件查询)
     * @author lucky
     * @date 2020/8/17
     * @param page 分页参数
     * @return
     **/
    Page<SysRoleVo> queryByPageAll(Page page);

    /***
     * 根据id修改角色
     * @author lucky
     * @date 2020/8/4 
     * @param sysRole
     * @return 影响行数
     **/
    int updateByPrimaryKey(SysRole sysRole);

    /***
     * 根据条件查询角色
     * @author lucky
     * @date 2020/8/17
     * @param status 状态
     * @param roleName 角色名称
     * @param roleKey 权限字符
     * @return
     **/
    List<SysRole> queryRoleByTerm(String status, String roleName, String roleKey);

    /***
     * 根据主键删除角色
     * @author lucky
     * @date 2020/8/6
     * @param roleId 主键
     * @return 数据库记录id
     **/
    int updateForActiveByPk(String roleId);

}