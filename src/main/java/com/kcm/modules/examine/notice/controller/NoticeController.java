package com.kcm.modules.examine.notice.controller;

import com.kcm.common.core.controller.BaseController;
import com.kcm.common.core.domain.AjaxResult;
import com.kcm.common.enums.ResultCode;
import com.kcm.modules.examine.notice.entity.BizNoticeFeedback;
import com.kcm.modules.examine.notice.entity.BizNoticeInfor;
import com.kcm.modules.examine.notice.entity.SysMessage;
import com.kcm.modules.examine.notice.service.NoticeService;
import com.kcm.modules.system.user.entity.SysUser;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

/**
 * 通知服务
 *
 * @author beiguoge
 * @version 1.0
 * @date 2020/8/25 11:12
 */
@Slf4j
@RestController
@RequestMapping("/notice")
@RequiredArgsConstructor
public class NoticeController extends BaseController {

    private final NoticeService noticeService;

    /**
     * 发布通知，同时推送消息给具体人员
     *
     * @param noticeInfor 通知信息
     * @param sysMessage 系统消息
     * @param userIdList 推送消息的人员ID列表
     * @return 发布结果
     */
    @PostMapping("/publish")
    public AjaxResult publish(@RequestBody BizNoticeInfor noticeInfor, SysMessage sysMessage, List<Map<String, String>> userIdList) {
        try {
            noticeService.publish(noticeInfor, sysMessage, userIdList);
            return success();
        } catch (Exception e) {
            log.error("通知发布失败，e -> {}", e.getMessage());
            return error(500, e.getMessage(), null);
        }
    }

    /**
     * 反馈通知，同时推送消息给发布人员
     *
     * @param noticeFeedback 反馈通知信息
     * @param sysMessage 反馈消息
     * @param userId 发布人
     * @return 反馈结果
     */
    @PostMapping("/feedback")
    public AjaxResult feedback(@RequestBody BizNoticeFeedback noticeFeedback, SysMessage sysMessage, String userId) {
        try {
            noticeService.feedback(noticeFeedback, sysMessage, userId);
            return success();
        } catch (Exception e) {
            log.error("通知反馈失败，e -> {}", e.getMessage());
            return error(500, e.getMessage(), null);
        }
    }

    /**
     * 根据用户ID查询系统通知
     *
     * @param current 当前页
     * @param pageSize 页面大小
     * @return 系统通知列表
     */
    @GetMapping("/notices")
    public AjaxResult queryNotices(@RequestParam(required = false, defaultValue = "1") Integer current,
                                   @RequestParam(required = false, defaultValue = "10") Integer pageSize, Authentication authentication) {
        // 获取系统当前登录用户
        SysUser user = (SysUser) authentication.getPrincipal();
        try {
            return success(ResultCode.SUCCESS_QUERY, noticeService.queryNoticesByUserId(user.getUserId(), current, pageSize));
        } catch (Exception e) {
            return error(ResultCode.ERR_SQL_SELECT_ERROR, e.getMessage());
        }
    }

    /**
     * 根据用户ID查询系统消息
     *
     * @param current 当前页
     * @param pageSize 页面大小
     * @return 系统消息列表
     */
    @GetMapping("/sysMessages")
    public AjaxResult queryMessages(@RequestParam(required = false, defaultValue = "1") Integer current,
                       @RequestParam(required = false, defaultValue = "10") Integer pageSize, Authentication authentication) {
        SysUser user = (SysUser) authentication.getPrincipal();
        try {
            return success(ResultCode.SUCCESS_QUERY, noticeService.queryMessagesByUserId(user.getUserId(), current, pageSize));
        } catch (Exception e) {
            return error(ResultCode.ERR_SQL_SELECT_ERROR, e.getMessage());
        }
    }

    /**
     * 读取系统消息
     *
     * @param messageId 消息ID
     * @return 读取结果
     */
    @PutMapping("/readMessage")
    public AjaxResult readMessage(@RequestParam String messageId, Authentication authentication) {
        SysUser user = (SysUser) authentication.getPrincipal();
        try {
            noticeService.readMessageByMessageId(messageId, user.getUserId());
            return success(ResultCode.SUCCESS_UPDATE);
        } catch (Exception e) {
            return error(ResultCode.ERR_SQL_UPDATE_ERROR, e.getMessage());
        }
    }

}
