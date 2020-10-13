package com.kcm.modules.examine.notice.mq;

import com.alibaba.fastjson.JSON;
import com.kcm.config.rabbitmq.TopicRabbitConfig;
import com.kcm.modules.examine.notice.dao.NoticeDao;
import com.kcm.modules.examine.notice.entity.BizNoticeFeedback;
import com.kcm.modules.examine.notice.entity.SysMessage;
import com.kcm.modules.examine.notice.entity.SysMessageFeedback;
import com.kcm.modules.system.user.entity.SysUser;
import com.kcm.modules.system.user.service.SysUserService;
import com.kcm.modules.system.user.service.impl.SysUserServiceImpl;
import com.rabbitmq.client.Channel;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.amqp.core.Message;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.UUID;
import java.util.stream.Collectors;

/**
 * 系统消息消费者服务
 *
 * @author beiguoge
 * @version 1.0
 * @date 2020/8/25 15:41
 */
@Slf4j
@Component
@RequiredArgsConstructor
public class ConsumerReceive {

    private final NoticeDao noticeDao;

    private final SysUserService sysUserService;

    /**
     * 接收系统发布通知
     *
     * @param message 消息
     * @param channel 通道
     * @throws IOException IOException
     */
    @SuppressWarnings("unchecked")
    @Transactional(rollbackFor = {RuntimeException.class, Exception.class})
    @RabbitListener(queues = TopicRabbitConfig.QUEUE_SYSTEM)
    public void consumerSystem(Message message, Channel channel) throws IOException {
        // 获取监听队列中的消息
        Map<String, Object> map = JSON.parseObject(message.getBody(), HashMap.class);
        String noticeId = (String) map.get("noticeId");
        SysMessage sysMessage = (SysMessage) map.get("sysMessage");
        List<Map<String, String>> userIdList = (List<Map<String, String>>) map.get("userIdList");
        // 批量初始化通知反馈信息
        List<BizNoticeFeedback> noticeFeedbacks = userIdList.stream()
                .map(e -> BizNoticeFeedback.builder()
                        .noticefId(UUID.randomUUID().toString().replaceAll("-",""))
                        .feedbackContent("请填写反馈通知")
                        .mrDeptId(e.get("detpId"))
                        .userId(e.get("userId"))
                        .feedbackState("0")
                        .noticeId(noticeId)
                        .build()
                ).collect(Collectors.toList());
        boolean result = noticeDao.saveBizNoticeFeedbackBatch(noticeFeedbacks);
        // 获取系统当前登录用户
        SysUser user = sysUserService.getCurrentUser();
        String messageId = UUID.randomUUID().toString().replaceAll("-","");
        if (result) {
            // 发送消息
            result = noticeDao.saveMessage(sysMessage.toBuilder()
                    .messageId(messageId).userId(user.getUserId()).build());
            if (result) {
                // 批量初始化消息反馈信息
                List<SysMessageFeedback> feedbackList = userIdList.stream()
                        .map(e -> SysMessageFeedback.builder()
                                .messagefId(UUID.randomUUID().toString().replaceAll("-",""))
                                .userId(e.get("userId"))
                                .messageId(messageId)
                                .readState("0")
                                .build()
                        ).collect(Collectors.toList());
                result = noticeDao.saveMessageFeedbackBatch(feedbackList);
            }
        }
        if (result) {
            // 确认消息，不开启批量确认
            channel.basicAck(message.getMessageProperties().getDeliveryTag(), false);
        } else {
            // 拒绝消息， 不开启批量拒绝，被拒绝消息重新入队
            channel.basicNack(message.getMessageProperties().getDeliveryTag(), false, true);
        }
    }

    /**
     * 接收系统反馈通知
     *
     * @param message 消息
     * @param channel 通道
     * @throws IOException IOException
     */
    @Transactional(rollbackFor = {RuntimeException.class, Exception.class})
    @RabbitListener(queues = TopicRabbitConfig.QUEUE_SYSTEM_FEEDBACK)
    public void consumerSystemFeedback(Message message, Channel channel) throws IOException {
        Map<String, Object> map = JSON.parseObject(message.getBody(), HashMap.class);
        String userId = (String) map.get("userId");
        SysMessage sysMessage = (SysMessage) map.get("sysMessage");
        SysUser user = sysUserService.getCurrentUser();
        String messageId = UUID.randomUUID().toString().replaceAll("-","");
        boolean result = noticeDao.saveMessage(sysMessage.toBuilder()
                .messageId(messageId).userId(user.getUserId()).build());
        if (result) {
            result = noticeDao.saveMessageFeedback(SysMessageFeedback.builder()
                    .messagefId(UUID.randomUUID().toString().replaceAll("-",""))
                    .userId(userId)
                    .messageId(messageId)
                    .readState("0")
                    .build());
        }
        if (result) {
            // 确认消息，不开启批量确认
            channel.basicAck(message.getMessageProperties().getDeliveryTag(), false);
        } else {
            // 拒绝消息， 不开启批量拒绝，被拒绝消息重新入队
            channel.basicNack(message.getMessageProperties().getDeliveryTag(), false, true);
        }
    }

}
