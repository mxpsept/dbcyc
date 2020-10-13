package com.kcm.modules.examine.manage.entity;

import java.io.Serializable;
import java.util.Date;

import com.baomidou.mybatisplus.annotation.TableId;
import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.*;

/**
 * BIZ_EXAMINE_PLAN_INFOR
 * 考核计划信息表实体类
 *
 * @author lucky
 */
@Data
@Builder(toBuilder = true)
@NoArgsConstructor
@AllArgsConstructor
public class BizExaminePlanInfor implements Serializable {
    /**
     * 主键ID
     */
    @TableId(value = "EXAMINE_P_ID")
    private String examinePId;

    /**
     * 计划名称
     */
    private String planName;

    /**
     * 开始时间
     */
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "Asia/Shanghai")
    private Date startDate;

    /**
     * 结束时间
     */
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "Asia/Shanghai")
    private Date endDate;

    /**
     * 考核模板ID
     */
    private String examineTId;

    /**
     * 备注
     */
    private String remark;

    /**
     * 是否有效(0:无效，1:有效，默认值为1)
     */
    private String active;

    /**
     * 创建人ID
     */
    private String createUserId;

    /**
     * 创建时间
     */
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "Asia/Shanghai")
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
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "Asia/Shanghai")
    private Date editTime;

    /**
     * 修改单位ID
     */
    private String editDeptId;

    private static final long serialVersionUID = 1L;
}