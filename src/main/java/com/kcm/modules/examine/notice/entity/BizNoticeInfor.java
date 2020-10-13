package com.kcm.modules.examine.notice.entity;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.kcm.common.core.BasePublicModel;
import lombok.*;

import java.util.Date;

/**
 * 通知信息
 *
 * @author beiguoge
 * @version 1.0
 * @date 2020/8/25 10:28
 */
@Data
@Builder(toBuilder = true)
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(callSuper = true)
public class BizNoticeInfor extends BasePublicModel {

    private static final long serialVersionUID = 8863790636414087626L;

    /**
     * 主键ID
     */
    private String noticeId;

    /**
     * 通知标题
     */
    private String noticeTopic;

    /**
     * 通知消息内容
     */
    private String noticeContent;

    /**
     * 允许回执天数
     */
    private Integer feedbackDayNum;

    /**
     * 发布状态(0:未发布,1:已发布)
     */
    private String releaseState;

    /**
     * 发布时间
     */
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss",timezone = "Asia/Shanghai")
    private Date releaseDate;

    /**
     * 发布人ID
     */
    private String userId;

    /**
     * 上级通知ID
     */
    private String noticepId;

    /**
     * 备注
     */
    private String remark;

    /**
     * 是否有效(0:无效,1:有效,默认值为1)
     */
    private String active;

}
