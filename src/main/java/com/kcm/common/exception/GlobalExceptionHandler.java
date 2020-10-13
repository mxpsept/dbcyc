package com.kcm.common.exception;

import com.kcm.common.core.domain.AjaxResult;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import java.sql.SQLException;

/**
 * 全局异常处理
 *
 * @author beiguoge
 * @version 1.0
 * @date 2020/8/28 10:01
 */
@Slf4j
@ControllerAdvice
public class GlobalExceptionHandler {

    /**
     * 业务异常处理
     *
     * @param request request请求
     * @param e 业务异常
     * @return 统一返回结果
     */
    @ExceptionHandler(value = BizException.class)
    @ResponseBody
    public AjaxResult bizExceptionHandler(HttpServletRequest request, BizException e) {
        log.error("业务异常，cause:{}", e.getErrorMsg());
        return AjaxResult.error(e.getErrorCode(), e.getErrorMsg(), null);
    }

    /**
     * 数据库异常，只在后台日志中打印，避免在前端展示造成数据泄露
     *
     * @param request request请求
     * @param e 数据库异常
     * @return 统一返回结果
     */
    @ExceptionHandler(value = SQLException.class)
    @ResponseBody
    public AjaxResult sqlExceptionHandler(HttpServletRequest request, SQLException e) {
        log.error("数据异常，cause:{}", e.getMessage());
        return AjaxResult.error(500, "数据异常，请稍后再试！", null);
    }

}
