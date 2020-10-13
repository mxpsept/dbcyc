package com.kcm.modules.examine.notice.service;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.IService;
import com.kcm.modules.examine.notice.entity.BizNoticeFeedback;
import com.kcm.modules.examine.notice.entity.BizNoticeInfor;
import com.kcm.modules.examine.notice.entity.SysMessage;

import java.util.List;
import java.util.Map;

/**
 * 通知服务
 *
 * @author beiguoge
 * @version 1.0
 * @date 2020/8/25 11:15
 */
public interface NoticeService extends IService<BizNoticeInfor> {

    /**
     * 发布通知
     *
     * @param noticeInfor 通知信息
     * @param sysMessage 系统消息
     * @param userIdList 推送消息的人员ID列表
     */
    void publish(BizNoticeInfor noticeInfor, SysMessage sysMessage, List<Map<String, String>> userIdList);

    /**
     * 反馈通知
     *
     * @param noticeFeedback 反馈信息
     * @param sysMessage 反馈系统消息
     * @param userId 反馈用户ID
     */
    void feedback(BizNoticeFeedback noticeFeedback, SysMessage sysMessage, String userId);

    /**
     * 根据用户ID查询系统通知
     *
     * @param userId 用户ID
     * @param current 当前页
     * @param pageSize 页面大小
     * @return 系统通知列表
     */
    Page<BizNoticeInfor> queryNoticesByUserId(String userId, Integer current, Integer pageSize);

    /**
     * 根据用户ID查询系统消息
     *
     * @param userId 用户ID
     * @param current 当前页
     * @param pageSize 页面大小
     * @return 系统消息列表
     */
    Page<SysMessage> queryMessagesByUserId(String userId, Integer current, Integer pageSize);

    /**
     * 读取系统消息
     *
     * @param messageId 消息ID
     * @param userId 用户ID
     */
    void readMessageByMessageId(String messageId, String userId);

}
