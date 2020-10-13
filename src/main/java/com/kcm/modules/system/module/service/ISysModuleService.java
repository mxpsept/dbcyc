package com.kcm.modules.system.module.service;

import com.kcm.common.core.domain.AjaxResult;
import com.kcm.modules.system.module.entity.SysModule;

import java.util.List;

/**
 * @author: lucky
 * @date: 2020/7/17
 * @description: 菜单信息表(SysModule)表服务接口
 **/
public interface ISysModuleService {

    /**
     * 通过菜单id查询单条数据
     *
     * @param moduleId 主键
     * @return 实例对象
     * @author lucky
     * @date 2020/7/17
     **/
    SysModule queryById(String moduleId);

    /**
     * 查询所有菜单数据
     *
     * @return 菜单列表
     * @author lucky
     * @date 2020/7/17
     **/
    List<SysModule> queryAll();

    /***
     * 新增菜单
     * @author lucky
     * @date 2020/8/6
     * @param sysModule 实例对象
     * @return 数据库记录id
     **/
    AjaxResult insertSelective(SysModule sysModule);


    /***
     * 获取菜单树
     * @author lucky
     * @date 2020/8/4
     * @return
     **/
    AjaxResult getSysModuleTree();

    /***
     * 修改菜单
     * @author lucky
     * @date 2020/8/6
     * @param sysModule 实例对象
     * @return 数据库记录id
     **/
    AjaxResult updateByPrimaryKey(SysModule sysModule);

    /***
     * 根据主键删除菜单(假删除)
     * @author lucky
     * @date 2020/8/6
     * @param moduleId 主键
     * @return 数据库记录id
     **/
    AjaxResult deleteByPrimaryKey(String moduleId);

}
