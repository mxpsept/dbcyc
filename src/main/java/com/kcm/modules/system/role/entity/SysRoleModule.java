package com.kcm.modules.system.role.entity;

import com.baomidou.mybatisplus.annotation.TableId;
import com.kcm.common.core.BasePublicModel;
import lombok.*;

import java.io.Serializable;

/**
 * SYS_ROLE_MODULE
 *
 * @author
 */
@Data
@Builder(toBuilder = true)
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(callSuper = true)
public class SysRoleModule extends BasePublicModel implements Serializable {

    private static final long serialVersionUID = 1L;
    /**
     * 角色ID
     */
    @TableId(value = "ROLE_ID")
    private String roleId;

    /**
     * 模块ID
     */
    private String moduleId;
}