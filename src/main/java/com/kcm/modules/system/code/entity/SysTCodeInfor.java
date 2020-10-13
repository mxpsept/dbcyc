package com.kcm.modules.system.code.entity;

import com.baomidou.mybatisplus.annotation.TableId;
import com.kcm.common.core.BasePublicModel;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;

/**
 * 编码详细信息表（SYS_T_CODE_INFOR）表实体
 *
 * @author zhqogingwang
 * @version 1.0
 * @date 2020/08/05
 */
@Data
@EqualsAndHashCode(callSuper = true)
public class SysTCodeInfor extends BasePublicModel implements Serializable {
    private static final long serialVersionUID = 867172641851984666L;

    /**
     * 主键ID
     */
    @TableId("CODE_ID")
    private String codeId;

    /**
     * 编码类型主键ID
     */
    private String codeTId;

    /**
     * 名称
     */
    private String codeName;

    /**
     * 值
     */
    private String codeValue;

    /**
     * 值类型(空为:普通值,0:正常值,1:异常值,L:最小值,B:最大值)
     */
    private String valueType;

    /**
     * 编号
     */
    private String codeNum;

    /**
     * 排列顺序
     */
    private BigDecimal sequence;

    /**
     * 描述
     */
    private String description;

    /**
     * 父类ID
     */
    private String codePId;

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
    private Date editTime;

    /**
     * 修改人单位ID
     */
    private String editDeptId;
}
