package com.kcm.modules.system.role.service.impl;

import cn.hutool.core.exceptions.ExceptionUtil;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.kcm.common.core.domain.AjaxResult;
import com.kcm.common.enums.ResultCode;
import com.kcm.modules.system.role.dao.SysRoleDao;
import com.kcm.modules.system.role.entity.SysRole;
import com.kcm.modules.system.role.service.ISysRoleService;
import com.kcm.modules.system.role.vo.SysRoleVo;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Map;

/**
 * @author: lucky
 * @date: 2020/8/4
 * @description: 角色信息表(SysRole)表服务实现类
 **/
@Service
public class SysRoleServiceImpl extends ServiceImpl<SysRoleDao, SysRole> implements ISysRoleService {

    private SysRoleDao sysRoleDao;

    public SysRoleServiceImpl(SysRoleDao sysRoleDao) {
        this.sysRoleDao = sysRoleDao;
    }


    /***
     * 新增角色
     * @author lucky
     * @date 2020/8/5
     * @param sysRole 实例对象
     * @return 数据库记录id
     **/
    @Override
    public SysRole insert(SysRole sysRole) throws Exception {
        int result = sysRoleDao.insert(sysRole);
        if (result == 1) {
            return sysRoleDao.selectByPrimaryKey(sysRole.getRoleId());
        } else {
            throw new Exception("添加用户失败！");
        }
    }

    @Override
    public SysRole selectByPrimaryKey(String roleId) {
        return null;
    }

    /***
     * 根据角色id修改角色
     * @author lucky
     * @date 2020/8/5
     * @param sysRole 实例对象
     * @return 数据库记录
     **/
    @Override
    public SysRole updateByPrimaryKey(SysRole sysRole) throws Exception {
        int result = sysRoleDao.updateByPrimaryKey(sysRole);
        if (result == 1) {
            return sysRoleDao.selectByPrimaryKey(sysRole.getRoleId());
        } else {
            throw new Exception("修改用户失败！");
        }
    }

    /***
     * 查询所有角色
     * @author lucky
     * @date 2020/8/5
     * @param paramMap 分页参数
     * @return 角色列表
     **/
    @Override
    public AjaxResult queryRoleAll(Map<String, Object> paramMap) {
        try {
            Map<String, Object> sqlParamMap = getSqlParamMap(paramMap);
            List<SysRole> sysRoles = sysRoleDao.queryRoleAll(sqlParamMap);
            return AjaxResult.success(ResultCode.SUCCESS_QUERY, sysRoles);
        } catch (Exception e) {
            return AjaxResult.success(ResultCode.ERR_SQL_SELECT_ERROR, ExceptionUtil.getRootCauseMessage(e));
        }

    }

    /***
     * 查询所有角色(分页插件查询)
     * @author lucky
     * @date 2020/8/17
     * @param current 当前页
     * @param pageSize 页面大小
     * @return
     **/
    @Override
    public Page<SysRoleVo> queryByPageAll(Integer current, Integer pageSize) {
        Page<SysRoleVo> pageList = sysRoleDao.queryByPageAll(new Page<>(current, pageSize));
        //遍历分页数据，组装菜单id数组
        for (SysRoleVo sysRoleVo : pageList.getRecords()) {
            if (!StringUtils.isEmpty(sysRoleVo.getModuleIds())) {
                //将字符串为list并添加到菜单集合
                sysRoleVo.setMenuIds(Arrays.asList(sysRoleVo.getModuleIds().split(",")));
            }
        }
        return pageList;
    }

    /***
     * 条件分页查询角色
     * @author lucky
     * @date 2020/8/20
     * @param page 分页参数
     * @param queryWrapper 条件参数
     * @return 角色列表
     **/
    @Override
    public Page<SysRole> queryRoleByTerm(Page page, QueryWrapper queryWrapper) {
        Page<SysRole> sysRoles = sysRoleDao.selectPage(page, queryWrapper);
        return sysRoles;
    }

    /***
     * 根据主键删除角色
     * @author lucky
     * @date 2020/8/6
     * @param roleId 主键
     * @return 数据库记录id
     **/
    @Override
    public void deleteByPrimaryKey(String roleId) throws Exception {
        int result = sysRoleDao.updateForActiveByPk(roleId);
        if (result != 1) {
            throw new Exception("删除角色信息失败！");
        }
    }

    /***
     * 分页参数处理
     * @author lucky
     * @date 2020/8/5
     * @param paramMap 参数
     * @return 处理后的参数
     **/
    private Map<String, Object> getSqlParamMap(Map<String, Object> paramMap) {
        //若传入参数为空，默认查询前十条数据
        int currIndex = StringUtils.isEmpty(paramMap.get("currIndex")) ? 1 : (Integer) paramMap.get("currIndex");
        int pageSize = StringUtils.isEmpty(paramMap.get("pageSize")) ? 10 : (Integer) paramMap.get("pageSize");
        paramMap.put("currIndex", (currIndex - 1) * pageSize);
        paramMap.put("pageSize", pageSize);
        return paramMap;
    }

    /***
     * 批量删除角色信息
     * @author lucky
     * @date 2020/8/19
     * @param roleIds 角色id集合
     * @throws Exception e
     **/
    @Override
    public void deleteBatch(List<String> roleIds) throws Exception {
        List<SysRole> roleList = new ArrayList<>();
        for (String roleId : roleIds) {
            roleList.add(SysRole.builder().roleId(roleId).active("0").build());
        }
        boolean result = updateBatchById(roleList);
        if (!result) {
            throw new Exception("批量删除角色信息失败！");
        }
    }
}
