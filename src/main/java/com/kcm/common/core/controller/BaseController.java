package com.kcm.common.core.controller;

import cn.hutool.core.date.DateUtil;
import com.kcm.common.core.domain.AjaxResult;
import com.kcm.common.enums.ResultCode;
import com.kcm.common.utils.ServletUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.InitBinder;

import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.beans.PropertyEditorSupport;
import java.util.Date;

import static com.kcm.common.utils.ServletUtils.getRequest;

/**
 * web层通用数据处理
 *
 * @author lucky
 * @date 2020/8/4
 **/
public class BaseController {

    private final Logger logger = LoggerFactory.getLogger(BaseController.class);

    /**
     * 将前台传递过来的日期格式的字符串，自动转化为Date类型
     */
    @InitBinder
    public void initBinder(WebDataBinder binder) {
        // Date 类型转换
        binder.registerCustomEditor(Date.class, new PropertyEditorSupport() {
            @Override
            public void setAsText(String text) {
                setValue(DateUtil.parseDate(text));
            }
        });
        logger.info("经过了baseController 处理");
    }

    /**
     * 获取response
     */
    public HttpServletResponse getResponse() {
        return ServletUtils.getResponse();
    }

    /**
     * 获取session
     */
    public HttpSession getSession() {
        return getRequest().getSession();
    }

    /**
     * 返回成功
     */
    public AjaxResult success() {
        return AjaxResult.success();
    }

    /**
     * 返回成功消息
     */
    public AjaxResult success(Object data) {
        return AjaxResult.success(data);
    }

    /**
     * 返回成功消息
     */
    public AjaxResult success(ResultCode resultCode, Object data) {
        return AjaxResult.success(resultCode, data);
    }

    /**
     * 返回成功消息
     */
    public AjaxResult success(Integer code, String msg, Object data) {
        return AjaxResult.success(code, msg, data);
    }


    /**
     * 返回错误消息
     */
    public AjaxResult error(ResultCode resultCode, Object data) {
        return AjaxResult.error(resultCode, data);
    }

    /**
     * 返回错误消息
     */
    public AjaxResult error(Integer code, String msg, Object data) {
        return AjaxResult.error(code, msg, data);
    }

    /**
     * 返回错误消息
     */
    public AjaxResult error(ResultCode resultCode) {
        return AjaxResult.error(resultCode);
    }

}
