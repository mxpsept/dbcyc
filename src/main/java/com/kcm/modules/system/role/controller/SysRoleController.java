package com.kcm.modules.system.role.controller;

import cn.hutool.core.date.DateUtil;
import cn.hutool.core.exceptions.ExceptionUtil;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.kcm.common.core.controller.BaseController;
import com.kcm.common.core.domain.AjaxResult;
import com.kcm.common.enums.ResultCode;
import com.kcm.common.other.StringUtils;
import com.kcm.modules.system.role.entity.SysRole;
import com.kcm.modules.system.role.service.ISysRoleModuleService;
import com.kcm.modules.system.role.service.ISysRoleService;
import com.kcm.modules.system.role.vo.SysRoleVo;
import io.swagger.annotations.*;
import lombok.RequiredArgsConstructor;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.BeanUtils;
import org.springframework.web.bind.annotation.*;
import springfox.documentation.annotations.ApiIgnore;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * @author: lucky
 * @date: 2020/8/5
 * @description:
 **/
@Api(tags = "角色管理接口")
@RestController
@RequestMapping("/system/sysRole")
@RequiredArgsConstructor
public class SysRoleController extends BaseController {

    private final static String MODULE_NAME = "角色管理";

    private final ISysRoleService sysRoleService;
    private final RabbitTemplate rabbitTemplate;
    private final ISysRoleModuleService sysRoleModuleService;


    /***
     * 获取所有角色信息（普通分页查询）
     * @author lucky
     * @date 2020/8/5
     * @param paramMap 分页参数
     * @return 角色列表
     **/
    @ApiIgnore
    @ApiOperation(value = "获取所有角色信息", notes = "queryRoleAll")
    @PostMapping("/queryRoleAll")
    public AjaxResult queryRoleAll(@RequestBody Map<String, Object> paramMap) {
        return sysRoleService.queryRoleAll(paramMap);
    }

    /***
     * 获取所有角色信息（插件分页查询）
     * @author lucky
     * @date 2020/8/17
     * @param current 当前页
     * @param pageSize 页面大小
     * @return 角色列表
     **/
    @ApiOperation(value = "分页查询所有角色信息", notes = "queryByPageAll")
    @GetMapping("/queryRoleAll")
    public AjaxResult queryByPageAll(
            @RequestParam(required = false, defaultValue = "1") Integer current,
            @RequestParam(required = false, defaultValue = "10") Integer pageSize) {
        try {
            Page<SysRoleVo> pageList = sysRoleService.queryByPageAll(current, pageSize);
            return success(ResultCode.SUCCESS_QUERY, pageList);
        } catch (Exception e) {
            return success(ResultCode.ERR_SQL_SELECT_ERROR, ExceptionUtil.getRootCauseMessage(e));
        }

    }

    /***
     * 条件分页查询角色信息
     * @author lucky
     * @date 2020/8/17
     * @param status 状态
     * @param roleName 角色名称
     * @param roleKey 权限标识
     * @return 角色列表
     **/
    @ApiOperation(value = "条件查询角色信息", notes = "searchRole")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "status", value = "角色状态", paramType = "query", dataType = "String", required = false),
            @ApiImplicitParam(name = "roleName", value = "角色名称", paramType = "query", dataType = "String", required = false),
            @ApiImplicitParam(name = "roleKey", value = "权限标识", paramType = "query", dataType = "String", required = false)
    })
    @GetMapping("/searchRole")
    public AjaxResult queryRoleByTerm(
            @RequestParam(required = false, defaultValue = "1") Integer current,
            @RequestParam(required = false, defaultValue = "10") Integer pageSize,
            @RequestParam(value = "status", required = false) String status,
            @RequestParam(value = "roleName", required = false) String roleName,
            @RequestParam(value = "roleKey", required = false) String roleKey
    ) {
        try {
            Page<SysRole> page = new Page<>(current, pageSize);
            QueryWrapper<SysRole> queryWrapper = new QueryWrapper<>();
            queryWrapper.eq("ACTIVE", "1");
            queryWrapper.likeRight(!StringUtils.isEmpty(roleName), "ROLE_NAME", roleName);
            queryWrapper.likeRight(!StringUtils.isEmpty(roleKey), "ROLE_key", roleKey);
            queryWrapper.likeRight(!StringUtils.isEmpty(status), "STATUS", status);
            Page<SysRole> sysRoles = sysRoleService.queryRoleByTerm(page, queryWrapper);
            return success(ResultCode.SUCCESS_QUERY, sysRoles);
        } catch (Exception e) {
            return success(ResultCode.ERR_SQL_SELECT_ERROR, ExceptionUtil.getRootCauseMessage(e));
        }
    }

    /***
     * 新增角色
     * @author lucky
     * @date 2020/8/5
     * @param sysRoleVo 实例对象
     * @return 新增数据id
     **/
    @ApiOperation(value = "新增角色信息", notes = "insert")
    @ApiImplicitParam(name = "sysRoleVo", value = "角色实体", paramType = "body", dataType = "SysRoleVo", required = true)
    @ApiResponses({
            @ApiResponse(code = 200, message = "新增成功"),
            @ApiResponse(code = 3001, message = "数据库插入操作失败")
    })
    @PostMapping("/insert")
    public AjaxResult insert(@RequestBody SysRoleVo sysRoleVo) {
        try {
            SysRole sysRoleCopy = new SysRole();
            //添加角色ID
            sysRoleVo.setRoleId(StringUtils.uuid());
            //复制对象信息
            BeanUtils.copyProperties(sysRoleVo, sysRoleCopy);
            SysRole sysRole = sysRoleService.insert(sysRoleCopy);
            //新增角色菜单信息
            List<String> menuList = sysRoleModuleService.insertBatch(sysRoleVo);
            //构建返回信息
            sysRoleVo.setMenuIds(menuList);
            String log = operationLog("张三", DateUtil.now(), "角色新增");
            rabbitTemplate.convertAndSend("logExchange", "log.user.add", log);
            return success(ResultCode.SUCCESS_ADD, sysRoleVo);
        } catch (Exception e) {
            return success(ResultCode.ERR_SQL_UPDATE_ERROR, ExceptionUtil.getRootCauseMessage(e));
        }
    }

    /***
     * 修改角色
     * @author lucky
     * @date 2020/8/6
     * @param sysRoleVo 实例对象
     * @return 数据库记录id
     **/
    @ApiOperation(value = "修改角色信息", notes = "updateByPrimaryKey")
    @ApiImplicitParam(name = "sysRoleVo", value = "角色实体", paramType = "body", dataType = "SysRoleVo", required = true)
    @ApiResponses({
            @ApiResponse(code = 200, message = "修改成功"),
            @ApiResponse(code = 3002, message = "数据库更新操作失败")
    })
    @PutMapping("/update")
    public AjaxResult updateByPrimaryKey(@RequestBody SysRoleVo sysRoleVo) {
        try {
            SysRole sysRoleCopy = new SysRole();
            //复制对象信息
            BeanUtils.copyProperties(sysRoleVo, sysRoleCopy);
            //更新角色信息
            SysRole sysRole = sysRoleService.updateByPrimaryKey(sysRoleCopy);
            //根据主键删除角色菜单权限信息
            sysRoleModuleService.deleteRoleModule(sysRoleVo.getRoleId());
            //更新角色菜单权限信息
            sysRoleModuleService.insertBatch(sysRoleVo);
            String log = operationLog("张三", DateUtil.now(), "角色修改");
            rabbitTemplate.convertAndSend("logExchange", "log.user.update", log);
            return success(ResultCode.SUCCESS_UPDATE, sysRoleVo);
        } catch (Exception e) {
            return success(ResultCode.ERR_SQL_UPDATE_ERROR, ExceptionUtil.getRootCauseMessage(e));
        }
    }

    /***
     * 根据主键删除角色(假删除)
     * @author lucky
     * @date 2020/8/6
     * @param roleId 主键
     * @return 数据库记录id
     **/
    @ApiOperation(value = "根据主键删除角色", notes = "deleteByPk")
    @ApiImplicitParam(name = "roleId", value = "角色id", paramType = "query", dataType = "String")
    @DeleteMapping("/deleteByPk")
    public AjaxResult deleteByPrimaryKey(@RequestParam String roleId) {
        try {
            sysRoleService.deleteByPrimaryKey(roleId);
            String log = operationLog("张三", DateUtil.now(), "角色删除");
            rabbitTemplate.convertAndSend("logExchange", "log.user.del", log);
            return success(ResultCode.SUCCESS_DELETE, roleId);
        } catch (Exception e) {
            return success(ResultCode.ERR_SQL_UPDATE_ERROR, ExceptionUtil.getRootCauseMessage(e));
        }
    }

    /***
     * 批量删除角色(假删除)
     * @author lucky
     * @date 2020/8/19
     * @param roleIds 角色id集合
     * @return 数据库操作记录
     **/
    @ApiOperation(value = "批量删除角色", notes = "deleteBatch")
    @ApiImplicitParam(name = "roleIds", value = "角色id集合", paramType = "body", allowMultiple = true, dataType = "String")
    @DeleteMapping("/deleteBatch")
    public AjaxResult deleteBatch(@RequestBody List<String> roleIds) {
        try {
            sysRoleService.deleteBatch(roleIds);
            return success(ResultCode.SUCCESS_DELETE, roleIds);
        } catch (Exception e) {
            return success(ResultCode.ERR_SQL_UPDATE_ERROR, ExceptionUtil.getRootCauseMessage(e));
        }
    }

    /***
     * 用户角色操作日志消息信息
     * @author lucky
     * @date 2020/8/18
     * @return 日志对象
     **/
    public String operationLog(String username, String operationTime, String operationType) {
        String log = username + " 在" + operationTime + "时刻,在" + MODULE_NAME + "模块进行了" + operationType + "的操作！";
        return log;
    }
}
