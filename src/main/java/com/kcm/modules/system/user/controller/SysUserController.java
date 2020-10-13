package com.kcm.modules.system.user.controller;

import com.kcm.common.core.controller.BaseController;
import com.kcm.common.core.domain.AjaxResult;
import com.kcm.common.enums.ResultCode;
import com.kcm.modules.system.user.entity.SysUser;
import com.kcm.modules.system.user.service.SysUserService;
import io.swagger.annotations.*;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;
import java.util.UUID;

/**
 * 用户信息表(SysUser)表控制层
 *
 * @author shawn
 * @version 1.0
 * @date 2018-10-22 10:41:18
 */
@Api(tags = "用户服务")
@Slf4j
@RestController
@RequestMapping("/system/sysUser")
@RequiredArgsConstructor
public class SysUserController extends BaseController {

    private final SysUserService userService;

    /**
     * 获取所有用户信息
     *
     * @return 用户列表
     */
    @ApiOperation(value = "获取所有用户信息", notes = "queryAll")
    @ApiResponses({
            @ApiResponse(code = 200, message = "查询成功"),
            @ApiResponse(code = 3003, message = "数据库查询失败")
    })
    @GetMapping("/users")
    public AjaxResult queryAll() {
        try {
            return success(ResultCode.SUCCESS_QUERY, userService.list());
        } catch (Exception e) {
            return error(ResultCode.ERR_SQL_SELECT_ERROR, e.getMessage());
        }
    }

    /**
     * 根据登录名称查询用户信息
     *
     * @param loginName 登录名称
     * @return 用户信息
     */
    @GetMapping("/user")
    public AjaxResult queryByLoginName(@RequestParam(required = false, defaultValue = "1") Integer current,
                       @RequestParam(required = false, defaultValue = "10") Integer pageSize,
                       @RequestParam(required = false, defaultValue = "all") String loginName) {
        try {
            if (!StringUtils.isEmpty(loginName) && !StringUtils.isBlank(loginName)) {
                return success(ResultCode.SUCCESS_QUERY, userService.queryByLoginName(current, pageSize, loginName));
            }
            return success(ResultCode.SUCCESS_QUERY, null);
        } catch (Exception e) {
            return error(ResultCode.ERR_SQL_SELECT_ERROR, e.getMessage());
        }
    }

    /**
     * 分页查询用户信息列表
     *
     * @param current 当前页
     * @param pageSize 页面大小
     * @return 用户列表
     */
    @GetMapping("/users/page")
    public AjaxResult queryByPage(@RequestParam(required = false, defaultValue = "1") Integer current,
                        @RequestParam(required = false, defaultValue = "10") Integer pageSize) {
        try {
            return success(ResultCode.SUCCESS_QUERY, userService.queryByPage(current, pageSize));
        } catch (Exception e) {
            return error(ResultCode.ERR_SQL_SELECT_ERROR, e.getMessage());
        }
    }

    /**
     * 根据所给部门ID分页查询其下所有子部门用户列表
     *
     * @param deptId 部门ID
     * @param current 当前页
     * @param pageSize 页面大小
     * @return 用户列表
     */
    @GetMapping("/users/{deptId}")
    public AjaxResult queryByDeptId(@PathVariable String deptId,
                        @RequestParam(required = false, defaultValue = "1") Integer current,
                        @RequestParam(required = false, defaultValue = "10") Integer pageSize) {
        try {
            return success(ResultCode.SUCCESS_QUERY, userService.queryByDeptIds(deptId, current, pageSize));
        } catch (Exception e) {
            return error(ResultCode.ERR_SQL_SELECT_ERROR, e.getMessage());
        }
    }

    /**
     * 新增用户
     *
     * @param sysUser 用户实体
     * @return 添加结果
     */
    @ApiOperation(value = "新增用户", notes = "save")
    @ApiImplicitParam(name = "sysUser", value = "用户实体", paramType = "body", dataType = "SysUser", required = true)
    @ApiResponses({
            @ApiResponse(code = 200, message = "新增成功"),
            @ApiResponse(code = 3001, message = "数据库插入操作失败")
    })
    @PostMapping("/user")
    public AjaxResult save(@RequestBody SysUser sysUser) {
        try {
            sysUser.setUserId(UUID.randomUUID().toString().replace("-", ""));
            userService.insert(sysUser);
            return success(ResultCode.SUCCESS_ADD);
        } catch (Exception e) {
            return error(ResultCode.ERR_SQL_INSERT_ERROR, e.getMessage());
        }
    }

    /**
     * 更新用户信息
     *
     * @param sysUser 用户实体
     * @return 修改结果
     */
    @PutMapping("/user")
    public AjaxResult update(@RequestBody SysUser sysUser) {
        try {
            userService.update(sysUser);
            return success(ResultCode.SUCCESS_UPDATE);
        } catch (Exception e) {
            return error(ResultCode.ERR_SQL_UPDATE_ERROR, e.getMessage());
        }
    }

    /**
     * 修改密码
     *
     * @param info 密码信息
     * @param authentication 当前登录用户
     * @return 修改结果
     */
    @PutMapping("/user/password")
    public AjaxResult updateUserPass(@RequestBody Map<String, String> info, Authentication authentication) {
        SysUser user = (SysUser) authentication.getPrincipal();
        try {
            if (userService.updatePassword(info, user)) {
                return success(ResultCode.SUCCESS_UPDATE);
            }
        } catch (Exception e) {
            log.error("用户密码修改失败，cause:{}", e.getMessage());
        }
        return error(ResultCode.ERR_SQL_UPDATE_ERROR);
    }

    /**
     * 删除用户信息
     *
     * @param userId 用户ID
     * @return 删除结果
     */
    @DeleteMapping("/user/{userId}")
    public AjaxResult deleteById(@PathVariable String userId) {
        try {
            userService.deleteById(userId);
            return success(ResultCode.SUCCESS_DELETE);
        } catch (Exception e) {
            return error(3005, e.getMessage(), null);
        }
    }

    /**
     * 批量删除用户信息
     *
     * @param sysUserIds 用户id集合
     * @return 删除结果
     */
    @DeleteMapping("/users")
    public AjaxResult deleteBatch(@RequestBody List<String> sysUserIds) {
        try {
            userService.deleteBatch(sysUserIds);
            return success(ResultCode.SUCCESS_DELETE);
        } catch (Exception e) {
            return error(3005, e.getMessage(), null);
        }
    }

}