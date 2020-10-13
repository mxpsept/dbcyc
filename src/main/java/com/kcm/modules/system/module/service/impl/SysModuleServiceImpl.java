package com.kcm.modules.system.module.service.impl;

import cn.hutool.core.exceptions.ExceptionUtil;
import com.kcm.common.core.domain.AjaxResult;
import com.kcm.common.enums.ResultCode;
import com.kcm.common.other.UUIDGenerate;
import com.kcm.modules.system.module.dao.SysModuleDao;
import com.kcm.modules.system.module.entity.SysModule;
import com.kcm.modules.system.module.service.ISysModuleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.*;
import java.util.stream.Collectors;

/**
 * @author: lucky
 * @date: 2020/7/17
 * @description: 菜单信息表(SysModule)表服务实现类
 **/
@Service
public class SysModuleServiceImpl implements ISysModuleService {

    private SysModuleDao sysModuleDao;

    @Autowired
    public SysModuleServiceImpl(SysModuleDao sysModuleDao) {
        this.sysModuleDao = sysModuleDao;
    }

    /**
     * 通过菜单id查询单条数据
     *
     * @param moduleId 主键
     * @return 实例对象
     * @author lucky
     * @date 2020/7/17
     **/
    @Override
    public SysModule queryById(String moduleId) {
        return this.sysModuleDao.selectByPrimaryKey(moduleId);
    }

    /**
     * 查询所有菜单数据
     *
     * @return 菜单列表
     * @author lucky
     * @date 2020/7/17
     **/
    @Override
    public List<SysModule> queryAll() {
        return this.sysModuleDao.queryAll();
    }


    /**
     * 新增菜单数据
     *
     * @param sysModule 实例对象
     * @return 影响行数
     * @author lucky
     * @date 2020/7/17
     **/
    @Override
    public AjaxResult insertSelective(SysModule sysModule) {
        try {
            sysModule.setModuleId(UUIDGenerate.getUniqueUuserId());
            this.sysModuleDao.insertSelective(sysModule);
            return AjaxResult.success(ResultCode.SUCCESS_ADD, sysModule.getModuleId());
        } catch (Exception e) {
            return AjaxResult.success(ResultCode.ERR_SQL_INSERT_ERROR, ExceptionUtil.getRootCauseMessage(e));
        }
    }

    /***
     * 修改菜单
     * @author lucky
     * @date 2020/8/6
     * @param sysModule 实例对象
     * @return 数据库记录id
     **/
    @Override
    public AjaxResult updateByPrimaryKey(SysModule sysModule) {
        try {
            sysModuleDao.updateByPrimaryKey(sysModule);
            return AjaxResult.success(ResultCode.SUCCESS_UPDATE, sysModule.getModuleId());
        } catch (Exception e) {
            return AjaxResult.success(ResultCode.ERR_SQL_UPDATE_ERROR, ExceptionUtil.getRootCauseMessage(e));
        }
    }


    /***
     * 根据主键删除菜单(假删除)
     * @author lucky
     * @date 2020/8/6
     * @param moduleId 主键
     * @return 数据库记录id
     **/
    @Override
    public AjaxResult deleteByPrimaryKey(String moduleId) {
        try {
            //根据主键获取菜单及所有子菜单信息
            List<SysModule> sysModules = sysModuleDao.selectAllByPk(moduleId);
            //获取菜单ID集合
            List<String> moduleIds = sysModules.stream().map(SysModule::getModuleId).collect(Collectors.toList());
            //遍历菜单ID集合，进行业务操作
            for (String sId : moduleIds) {
                //根据id删除菜单
                sysModuleDao.updateForActiveByPk(sId);
            }
            return AjaxResult.success(ResultCode.SUCCESS_DELETE,moduleIds);
        } catch (Exception e) {
            return AjaxResult.success(ResultCode.ERR_SQL_UPDATE_ERROR,ExceptionUtil.getRootCauseMessage(e));
        }
    }

    /***
     * 获取菜单树
     * @author lucky
     * @date 2020/8/4
     * @return 菜单列表
     **/
    @Override
    public AjaxResult getSysModuleTree() {
        try {
            //查询所有菜单
            List<SysModule> sysModules = sysModuleDao.queryAll();
            //获取根节点菜单
            List<SysModule> rootModules = new ArrayList<SysModule>();
            for (SysModule sysModule : sysModules) {
                //父节点为0的为根节点
                if ("0".equals(sysModule.getParentModuleId())) {
                    rootModules.add(sysModule);
                }
            }
            //排序
            Collections.sort(rootModules, sequence());
            //为根菜单设置子菜单，getChild是递归调用的
            for (SysModule sysModule : rootModules) {
                //获取根节点下的所有子节点 使用getChild方法
                List<SysModule> childList = getChild(sysModule.getModuleId(), sysModules);
                //为根节点设置子节点
                sysModule.setChildren(childList);
            }
            return AjaxResult.success(ResultCode.SUCCESS_QUERY, rootModules);
        } catch (Exception e) {
            return AjaxResult.success(ResultCode.ERR_SQL_SELECT_ERROR, ExceptionUtil.getRootCauseMessage(e));
        }
    }


    /***
     * 根据显示顺序进行排序
     * @author lucky
     * @date 2020/8/4
     * @return 排序结果
     **/
    public Comparator<SysModule> sequence() {
        Comparator<SysModule> comparator = new Comparator<SysModule>() {
            @Override
            public int compare(SysModule o1, SysModule o2) {
                if (!o1.getSequence().equals(o2.getSequence())) {
                    return o1.getSequence().intValue() - o2.getSequence().intValue();
                }
                return 0;
            }
        };
        return comparator;
    }

    /***
     * 获取子节点
     * @author lucky
     * @date 2020/8/4 
     * @param moduleId 父节点id
     * @param sysModules 所有菜单列表
     * @return 每个根节点下所有的子菜单列表
     **/
    public List<SysModule> getChild(String moduleId, List<SysModule> sysModules) {
        //子菜单
        List<SysModule> childList = new ArrayList<SysModule>();
        for (SysModule sysModule : sysModules) {
            //遍历所有节点，将所有菜单的父id与传过来的根节点id进行比较，若相等则说明为该根节点的子节点
            if (moduleId.equals(sysModule.getParentModuleId())) {
                childList.add(sysModule);
            }
        }
        //递归
        for (SysModule sysModule : childList) {
            sysModule.setChildren(getChild(sysModule.getModuleId(), sysModules));
        }
        //排序
        Collections.sort(childList, sequence());
        //若节点下面无子节点，返回空的List。退出递归
        if (childList.size() == 0) {
            return new ArrayList<SysModule>();
        }
        return childList;
    }
}
