package com.kcm.common.framework.security;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.kcm.common.core.domain.AjaxResult;
import com.kcm.common.enums.ResultCode;
import org.springframework.security.authentication.InsufficientAuthenticationException;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.AuthenticationEntryPoint;
import org.springframework.stereotype.Component;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;

/**
 * 处理匿名用户访问资源
 *
 * @author beiguoge
 * @version 1.0
 * @date 2020/9/3 10:38
 */
@Component
public class CustomAuthenticationEntryPoint implements AuthenticationEntryPoint {

    @Override
    public void commence(HttpServletRequest request, HttpServletResponse response, AuthenticationException e) throws IOException {
        response.setContentType("application/json;charset=utf-8");
        response.setStatus(401);
        PrintWriter writer = response.getWriter();
        AjaxResult result = AjaxResult.error(ResultCode.ERR_USER_AUTHENTICATION);
        if (e instanceof InsufficientAuthenticationException) {
            result.setMessage(ResultCode.ERR_USER_INSUFFICIENT_AUTHENTICATION);
        }
        writer.write(new ObjectMapper().writeValueAsString(result));
        writer.flush();
        writer.close();
    }

}
