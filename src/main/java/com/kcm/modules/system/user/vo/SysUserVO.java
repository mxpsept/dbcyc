package com.kcm.modules.system.user.vo;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.kcm.common.core.BasePublicModel;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.io.Serializable;
import java.util.Date;

/**
 * 用户信息视图
 *
 * @author ZhangHao
 * @version 1.0
 * @date 2020/8/11 10:40
 */
@Data
@EqualsAndHashCode(callSuper = true)
public class SysUserVO extends BasePublicModel implements Serializable {

    private static final long serialVersionUID = 5482922839374997694L;

    /**
     * 用户ID
     */
    private String userId;

    /**
     * 部门ID
     */
    private String departmentId;

    /**
     * 登录账号
     */
    private String loginName;

    /**
     * 用户名
     */
    private String userName;

    /**
     * 密码
     */
    private String password;

    /**
     * 默认密码
     */
    private String defaultPassword;

    /**
     * 用户邮箱
     */
    private String email;

    /**
     * 手机号码
     */
    private String phone;

    /**
     * 用户性别（0男 1女 2未知）
     */
    private String sex;

    /**
     * 职务ID
     */
    private String positionId;

    /**
     * 头像路径
     */
    private String avatar;

    /**
     * 微信公众号ID
     */
    private String openId;

    /**
     * 微信公众号ID
     */
    private String userNum;

    /**
     * 最后登陆IP
     */
    private String loginIp;

    /**
     * 最后登陆时间
     */
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss",timezone = "Asia/Shanghai")
    private Date loginDate;

    /**
     * 显示顺序
     */
    private Integer sequence;

    /**
     * 盐值
     */
    private String salt;

    /**
     * 帐号状态（0停用 1正常）
     */
    private String status;

    /**
     * 备注
     */
    private String remark;

    /**
     * 是否有效(0:无效,1:有效,默认值为1)
     */
    private String active;

    /**
     * 部门名称
     */
    private String departmentName;

}
