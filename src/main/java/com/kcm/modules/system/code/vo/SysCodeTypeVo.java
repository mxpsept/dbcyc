package com.kcm.modules.system.code.vo;

import com.kcm.modules.system.code.entity.SysTCodeInfor;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;
import java.util.List;

/**
 * @description： 编码类型VO
 * @author  zhaoqingwang
 * @date 2020/9/4 16:57
 * @version 1.0
 **/
@Data
@Builder(toBuilder = true)
@NoArgsConstructor
@AllArgsConstructor
public class SysCodeTypeVo{

    /**
     * 编码类型Id
     */
    private String codeTypeId;

    /**
     * 编码类型名称
     */
    private String codeTName;

    /**
     * 编码类型
     */
    private String codeType;

    /**
     * 备注
     */
    private String remark;

    /**
     * 是否有效（0：无效  1：有效 默认值为1）
     */
    private String active;

    /**
     * 创建者Id
     */
    private String createUserId;

    /**
     * 创建时间
     */
    private Date createTime;

    /**
     * 创建部门Id
     */
    private String createDeptId;

    /**
     * 修改者Id
     */
    private String editUserId;

    /**
     * 修改时间
     */
    private Date editTime;

    /**
     * 修改部门Id
     */
    private String editDeptId;

    /**
     * 编码详细信息列表
     */
    private List<SysTCodeInfor> sysTCodeInforList;

    /**
     * 编码详细信息ID串
     */
    private String codeIds;
}
