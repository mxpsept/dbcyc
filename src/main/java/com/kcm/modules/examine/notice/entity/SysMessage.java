package com.kcm.modules.examine.notice.entity;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.*;

import java.io.Serializable;
import java.util.Date;

/**
 * 系统消息
 *
 * @author beiguoge
 * @version 1.0
 * @date 2020/8/25 14:29
 */
@Data
@Builder(toBuilder = true)
@NoArgsConstructor
@AllArgsConstructor
public class SysMessage implements Serializable {

    private static final long serialVersionUID = -1732232565291950182L;

    /**
     * 主键ID
     */
    private String messageId;

    /**
     * 消息内容
     */
    private String messageContent;

    /**
     * 发送人ID
     */
    private String userId;

    /**
     * 发送时间
     */
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss",timezone = "Asia/Shanghai")
    private Date sendDate;

}
