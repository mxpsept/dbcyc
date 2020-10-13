package com.kcm.modules.system.role.service.impl;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.IService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.kcm.modules.system.role.dao.SysRoleModuleDao;
import com.kcm.modules.system.role.entity.SysRole;
import com.kcm.modules.system.role.entity.SysRoleModule;
import com.kcm.modules.system.role.service.ISysRoleModuleService;
import com.kcm.modules.system.role.vo.SysRoleVo;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

/**
 * @author: lucky
 * @date: 2020/8/20
 * @description: 角色菜单信息(SysRoleModule)表服务接口
 **/
@Service
public class SysRoleModuleServiceImpl extends ServiceImpl<SysRoleModuleDao, SysRoleModule> implements ISysRoleModuleService {

    private SysRoleModuleDao sysRoleModuleDao;

    public SysRoleModuleServiceImpl(SysRoleModuleDao sysRoleModuleDao) {
        this.sysRoleModuleDao = sysRoleModuleDao;
    }

    /***
     * 批量添加角色菜单信息
     * @author lucky
     * @date 2020/8/20
     * @param sysRoleVo 对象
     * @return 数据库操作记录
     **/
    @Override
    public List<String> insertBatch(SysRoleVo sysRoleVo) throws Exception {

        List<SysRoleModule> sysRoleModuleList = new ArrayList<>();
        //遍历菜单数组,将每一个菜单添加到对象中
        for (String moduleId : sysRoleVo.getMenuIds()) {
            SysRoleModule sysRoleModule = new SysRoleModule();
            sysRoleModule.setRoleId(sysRoleVo.getRoleId());
            sysRoleModule.setModuleId(moduleId);
            sysRoleModuleList.add(sysRoleModule);
        }

        boolean result = saveBatch(sysRoleModuleList);
        if (result) {
            return sysRoleVo.getMenuIds();
        } else {
            throw new Exception("批量添加角色菜单信息失败");
        }
    }

    /***
     * 删除角色菜单中间表信息
     * @author lucky
     * @date 2020/8/21
     * @param roleModuleId 角色id
     **/
    @Override
    public void deleteRoleModule(String roleModuleId) throws Exception {
        boolean result = removeById(roleModuleId);
        if (!result) {
            throw new Exception("删除角色菜单中间表信息失败");
        }
    }
}
