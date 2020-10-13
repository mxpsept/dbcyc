package com.kcm.common.framework.security;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.kcm.common.core.domain.AjaxResult;
import com.kcm.common.enums.ResultCode;
import org.springframework.security.authentication.*;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.authentication.AuthenticationFailureHandler;
import org.springframework.stereotype.Component;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;

/**
 * 登陆失败处理
 *
 * @author beiguoge
 * @version 1.0
 * @date 2020/9/3 09:59
 */
@Component
public class IdentityCheckFailureHandler implements AuthenticationFailureHandler {

    @Override
    public void onAuthenticationFailure(HttpServletRequest request, HttpServletResponse response, AuthenticationException e) throws IOException {
        response.setContentType("application/json;charset=utf-8");
        PrintWriter writer = response.getWriter();
        AjaxResult result = AjaxResult.error(ResultCode.ERR_USER_NOT_LOGIN, null);
        if (e instanceof LockedException) {
            result.setMessage(ResultCode.ERR_USER_LOCKED);
        } else if (e instanceof CredentialsExpiredException) {
            result.setMessage(ResultCode.ERR_USER_CREDENTIALS_EXPIRED);
        } else if (e instanceof AccountExpiredException) {
            result.setMessage(ResultCode.ERR_USER_ACCOUNT_EXPIRED);
        } else if (e instanceof DisabledException) {
            result.setMessage(ResultCode.ERR_USER_DISABLED);
        } else if (e instanceof BadCredentialsException) {
            result.setMessage(ResultCode.ERR_USER_BAD_CREDENTIALS);
        }
        writer.write(new ObjectMapper().writeValueAsString(result));
        writer.flush();
        writer.close();
    }

}
