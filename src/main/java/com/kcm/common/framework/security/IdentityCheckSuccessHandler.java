package com.kcm.common.framework.security;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.kcm.common.core.domain.AjaxResult;
import com.kcm.common.enums.ResultCode;
import com.kcm.modules.system.user.entity.SysUser;
import org.springframework.security.core.Authentication;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;
import org.springframework.stereotype.Component;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;

/**
 * 登陆成功处理
 *
 * @author beiguoge
 * @version 1.0
 * @date 2020/9/3 17:06
 */
@Component
public class IdentityCheckSuccessHandler implements AuthenticationSuccessHandler {

    @Override
    public void onAuthenticationSuccess(HttpServletRequest request, HttpServletResponse response, Authentication authentication) throws IOException {
        response.setContentType("application/json;charset=utf-8");
        PrintWriter writer = response.getWriter();
        SecurityUserDetail user = (SecurityUserDetail) authentication.getPrincipal();
        AjaxResult result = AjaxResult.success(ResultCode.SUCCESS_USER_LOGIN, user);
        writer.write(new ObjectMapper().writeValueAsString(result));
        writer.flush();
        writer.close();
    }

}
