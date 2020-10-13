package com.kcm.modules.system.module.dao;

import com.kcm.modules.system.module.entity.SysModule;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * @author: lucky
 * @date: 2020/7/17
 * @description: 菜单信息表(SysModule)数据库访问层
 **/
@Repository
public interface SysModuleDao {




    /***
     * 根据主键删除菜单(真删除)
     * @author lucky
     * @date 2020/8/6
     * @param moduleId 主键
     * @return 数据库记录id
     **/
    int deleteByPrimaryKey(String moduleId);

    /***
     * 根据主键删除菜单(假删除)
     * @author lucky
     * @date 2020/8/6
     * @param moduleId 主键
     * @return 数据库记录id
     **/
    int updateForActiveByPk(String moduleId);


    /***
     * 新增菜单
     * @author lucky
     * @date 2020/8/6
     * @param sysModule 实例对象
     * @return 数据库记录id
     **/
    int insertSelective(SysModule sysModule);

    /**
     * 通过菜单id查询单条数据
     *
     * @param moduleId 主键
     * @return 实例对象
     * @author lucky
     * @date 2020/7/17
     **/
    SysModule selectByPrimaryKey(String moduleId);

    /***
     * 修改菜单
     * @author lucky
     * @date 2020/8/6
     * @param sysModule 实例对象
     * @return 数据库记录id
     **/
    int updateByPrimaryKey(SysModule sysModule);

    /**
     * 查询所有菜单数据
     *
     * @return 菜单列表
     * @author lucky
     * @date 2020/7/17
     **/
    List<SysModule> queryAll();

    /***
     * 根据主键查询菜单及所有子菜单
     * @author lucky
     * @date 2020/8/6
     * @param moduleId 主键
     * @return 菜单列表
     **/
    List<SysModule> selectAllByPk(String moduleId);
}