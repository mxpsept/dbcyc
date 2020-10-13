package com.kcm.common.framework.security;

import com.alibaba.fastjson.JSON;
import com.kcm.common.core.domain.AjaxResult;
import com.kcm.common.enums.ResultCode;
import org.springframework.security.web.session.SessionInformationExpiredEvent;
import org.springframework.security.web.session.SessionInformationExpiredStrategy;
import org.springframework.stereotype.Component;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * 会话信息过期策略
 *
 * @author beiguoge
 * @version 1.0
 * @date 2020/9/3 10:48
 */
@Component
public class CustomSessionInformationExpiredStrategy implements SessionInformationExpiredStrategy {

    @Override
    public void onExpiredSessionDetected(SessionInformationExpiredEvent sessionInformationExpiredEvent) throws IOException {
        AjaxResult result = AjaxResult.error(ResultCode.ERR_USER_SESSION_INFORMATION_EXPIRED_STRATEGY);
        HttpServletResponse response = sessionInformationExpiredEvent.getResponse();
        response.setContentType("application/json;charset=utf-8");
        response.getWriter().write(JSON.toJSONString(result));
    }

}
