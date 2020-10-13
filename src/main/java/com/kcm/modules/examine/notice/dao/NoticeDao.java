package com.kcm.modules.examine.notice.dao;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.kcm.modules.examine.notice.entity.BizNoticeFeedback;
import com.kcm.modules.examine.notice.entity.BizNoticeInfor;
import com.kcm.modules.examine.notice.entity.SysMessage;
import com.kcm.modules.examine.notice.entity.SysMessageFeedback;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * 通知服务
 *
 * @author beiguoge
 * @version 1.0
 * @date 2020/8/25 11:19
 */
@Mapper
public interface NoticeDao extends BaseMapper<BizNoticeInfor> {

    /**
     * 批量初始化系统通知反馈信息
     *
     * @param noticeFeedbacks 系统通知反馈信息集合
     * @return 插入结果
     */
    boolean saveBizNoticeFeedbackBatch(@Param("noticeFeedbacks") List<BizNoticeFeedback> noticeFeedbacks);

    /**
     * 填写系统通知反馈信息
     *
     * @param noticeFeedback 系统通知反馈信息
     * @return 插入结果
     */
    boolean updateBizNoticeFeedback(BizNoticeFeedback noticeFeedback);

    /**
     * 添加系统消息
     *
     * @param sysMessage 消息内容
     * @return 添加结果
     */
    boolean saveMessage(SysMessage sysMessage);

    /**
     * 批量初始化系统消息反馈信息
     *
     * @param feedbackList 消息反馈信息集合
     * @return 插入结果
     */
    boolean saveMessageFeedbackBatch(@Param("feedbackList") List<SysMessageFeedback> feedbackList);

    /**
     * 初始化系统消息反馈信息
     *
     * @param messageFeedback 消息反馈信息
     * @return 插入结果
     */
    boolean saveMessageFeedback(SysMessageFeedback messageFeedback);

    /**
     * 根据用户ID查询系统通知
     *
     * @param page 分页
     * @param userId 用户ID
     * @return 系统通知列表
     */
    Page<BizNoticeInfor> queryByUserId(Page<BizNoticeInfor> page, @Param("userId") String userId);

    /**
     * 根据用户ID查询系统消息
     *
     * @param userId 用户ID
     * @param page 分页
     * @return 系统消息列表
     */
    Page<SysMessage> queryMessagesByUserId(Page<SysMessage> page, @Param("userId") String userId);

    /**
     * 读取系统消息
     *
     * @param messageId 消息ID
     * @param userId 用户ID
     * @return 读取结果
     */
    boolean readMessageByMessageId(@Param("messageId") String messageId, @Param("userId") String userId);

}
