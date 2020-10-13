package com.kcm.modules.examine.notice.entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

/**
 * 系统消息反馈信息
 *
 * @author beiguoge
 * @version 1.0
 * @date 2020/8/26 15:05
 */
@Data
@Builder(toBuilder = true)
@NoArgsConstructor
@AllArgsConstructor
public class SysMessageFeedback implements Serializable {

    private static final long serialVersionUID = 8942627296794898978L;

    /**
     * 主键ID
     */
    private String messagefId;

    /**
     * 接收人ID
     */
    private String userId;

    /**
     * 已读状态(0:未读,1:已读)
     */
    private String readState;

    /**
     * 消息ID
     */
    private String messageId;

}
