package com.kcm.modules.examine.notice.service.impl;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.kcm.common.exception.BizException;
import com.kcm.config.rabbitmq.TopicRabbitConfig;
import com.kcm.modules.examine.notice.dao.NoticeDao;
import com.kcm.modules.examine.notice.entity.BizNoticeFeedback;
import com.kcm.modules.examine.notice.entity.BizNoticeInfor;
import com.kcm.modules.examine.notice.entity.SysMessage;
import com.kcm.modules.examine.notice.service.NoticeService;
import com.kcm.modules.system.user.entity.SysUser;
import com.kcm.modules.system.user.service.SysUserService;
import com.kcm.modules.system.user.service.impl.SysUserServiceImpl;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.amqp.rabbit.connection.CorrelationData;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.UUID;

/**
 * 通知服务
 *
 * @author beiguoge
 * @version 1.0
 * @date 2020/8/25 11:15
 */
@Slf4j
@Service
@RequiredArgsConstructor
public class NoticeServiceImpl extends ServiceImpl<NoticeDao, BizNoticeInfor> implements NoticeService {

    private final RabbitTemplate rabbitTemplate;

    private final NoticeDao noticeDao;

    private final SysUserService sysUserService;

    @Override
    public void publish(BizNoticeInfor noticeInfor, SysMessage sysMessage, List<Map<String, String>> userIdList) {
        SysUser user = sysUserService.getCurrentUser();
        String noticeId = UUID.randomUUID().toString().replaceAll("-", "");
        boolean result = save(noticeInfor.toBuilder().noticeId(noticeId).userId(user.getUserId()).build());
        if (result) {
            Map<String, Object> map = new HashMap<>(3);
            map.put("noticeId", noticeId);
            map.put("sysMessage", sysMessage);
            map.put("userIdList", userIdList);
            // 添加消息唯一标识
            CorrelationData correlationData = new CorrelationData(UUID.randomUUID().toString().replaceAll("-", ""));
            rabbitTemplate.convertAndSend(TopicRabbitConfig.EXCHANGE_TOPIC, TopicRabbitConfig.ROUTING_KEY_SYSTEM, map, correlationData);
        }
    }

    @Override
    public void feedback(BizNoticeFeedback noticeFeedback, SysMessage sysMessage, String userId) {
        SysUser user = sysUserService.getCurrentUser();
        boolean result = noticeDao.updateBizNoticeFeedback(noticeFeedback.toBuilder().userId(user.getUserId()).build());
        if (result) {
            Map<String, Object> map = new HashMap<>(3);
            map.put("userId", userId);
            map.put("sysMessage", sysMessage);
            CorrelationData correlationData = new CorrelationData(UUID.randomUUID().toString().replaceAll("-", ""));
            rabbitTemplate.convertAndSend(TopicRabbitConfig.EXCHANGE_TOPIC, TopicRabbitConfig.ROUTING_KEY_SYSTEM_FEEDBACK, map, correlationData);
        }
    }

    @Override
    public Page<BizNoticeInfor> queryNoticesByUserId(String userId, Integer current, Integer pageSize) {
        return noticeDao.queryByUserId(new Page<>(), userId);
    }

    @Override
    public Page<SysMessage> queryMessagesByUserId(String userId, Integer current, Integer pageSize) {
        return noticeDao.queryMessagesByUserId(new Page<>(), userId);
    }

    @Override
    public void readMessageByMessageId(String messageId, String userId) {
        boolean result = noticeDao.readMessageByMessageId(messageId, userId);
        if (!result) {
            throw new BizException("读取消息失败！");
        }
    }
}
