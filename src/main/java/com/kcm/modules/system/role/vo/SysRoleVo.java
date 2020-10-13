package com.kcm.modules.system.role.vo;

import com.baomidou.mybatisplus.annotation.TableId;
import com.fasterxml.jackson.annotation.JsonFormat;
import com.kcm.common.core.BasePublicModel;
import lombok.*;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;
import java.util.List;

/**
 * SYS_ROLE
 * 角色实体类
 * @author 
 */
@Data
@Builder(toBuilder = true)
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(callSuper = true)
public class SysRoleVo extends BasePublicModel implements Serializable {

    private static final long serialVersionUID = 1L;

    /**
     * 角色ID
     */
    @TableId(value = "ROLE_ID")
    private String roleId;

    /**
     * 角色名称
     */
    private String roleName;

    /**
     * 角色权限字符串
     */
    private String roleKey;

    /**
     * 排列顺序
     */
    private BigDecimal sequence;

    /**
     * 角色状态(0:停用,1:正常)
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
     * 创建人ID
     */
    private String createUserId;

    /**
     * 创建时间
     */
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss",timezone = "Asia/Shanghai")
    private Date createTime;

    /**
     * 创建单位ID
     */
    private String createDeptId;

    /**
     * 修改人ID
     */
    private String editUserId;

    /**
     * 修改时间
     */
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss",timezone = "Asia/Shanghai")
    private Date editTime;

    /**
     * 修改人单位ID
     */
    private String editDeptId;

    /**
     * 菜单id串
     */
    private String moduleIds;

    /**
     * 菜单ID集合
     */
    private List<String> menuIds;
}