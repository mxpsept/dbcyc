package com.kcm.common.framework.security;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.kcm.common.core.domain.AjaxResult;
import com.kcm.common.enums.ResultCode;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.security.web.access.AccessDeniedHandler;
import org.springframework.stereotype.Component;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;

/**
 * 处理已登录用户访问无权限资源
 *
 * @author beiguoge
 * @version 1.0
 * @date 2020/9/3 10:31
 */
@Component
public class CustomAuthenticationAccessDeniedHandler implements AccessDeniedHandler {

    @Override
    public void handle(HttpServletRequest request, HttpServletResponse response, AccessDeniedException e) throws IOException {
        response.setContentType("application/json;charset=utf-8");
        response.setStatus(HttpServletResponse.SC_FORBIDDEN);
        PrintWriter writer = response.getWriter();
        writer.write(new ObjectMapper().writeValueAsString(AjaxResult.error(ResultCode.ERR_USER_ACCESS_DENIED)));
        writer.flush();
        writer.close();
    }

}
