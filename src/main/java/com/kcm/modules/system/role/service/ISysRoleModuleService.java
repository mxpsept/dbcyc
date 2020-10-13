package com.kcm.modules.system.role.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.kcm.modules.system.role.entity.SysRole;
import com.kcm.modules.system.role.entity.SysRoleModule;
import com.kcm.modules.system.role.vo.SysRoleVo;

import java.util.List;

/**
 * @author: lucky
 * @date: 2020/8/20
 * @description: 角色菜单信息(SysRoleModule)表服务接口
 **/
public interface ISysRoleModuleService {


    /***
     * 批量添加角色菜单信息
     * @author lucky
     * @date 2020/8/20
     * @param sysRoleVo 对象
     * @return 数据库操作记录
     * @throws Exception e
     **/
    List<String> insertBatch(SysRoleVo sysRoleVo) throws Exception;


    /***
     * 删除角色菜单中间表信息
     * @author lucky
     * @date 2020/8/21
     * @param roleModuleId 角色id
     * @throws Exception e
     **/
    void deleteRoleModule(String roleModuleId) throws Exception;

}
