package com.kcm.modules.examine.notice.entity;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.util.Date;

/**
 * 通知反馈
 *
 * @author beiguoge
 * @version 1.0
 * @date 2020/8/26 15:39
 */
@Data
@Builder(toBuilder = true)
@NoArgsConstructor
@AllArgsConstructor
public class BizNoticeFeedback implements Serializable {

    private static final long serialVersionUID = -5600377481083310492L;

    /**
     * 主键ID
     */
    private String noticefId;

    /**
     * 反馈内容
     */
    private String feedbackContent;

    /**
     * 接收部门ID
     */
    private String mrDeptId;

    /**
     * 反馈时间
     */
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss",timezone = "Asia/Shanghai")
    private Date feedbackDate;

    /**
     * 反馈人ID
     */
    private String userId;

    /**
     * 反馈状态(0:未反馈,1:已反馈)
     */
    private String feedbackState;

    /**
     * 通知ID
     */
    private String noticeId;

}
