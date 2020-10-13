package com.kcm.common.core.domain;

import com.alibaba.fastjson.annotation.JSONField;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.kcm.common.enums.ResultCode;

import java.util.ArrayList;


/**
 * @Description: 返回结果VO
 * @Datetime: 3/4/2020 15:35
 * @Author: ningqipeng
 * @Email: ningqipeng@cnpc.com.cn
 */
public class AjaxResult extends Page {

    /**
     * 状态码
     */
    @JSONField(ordinal = 1)
    private int code;

    /**
     * 消息
     */
    @JSONField(ordinal = 2)
    private String message;

    /**
     * 数据对象
     */
    @JSONField(ordinal = 3)
    private Object data;

    public int getCode() {
        return code;
    }

    public void setCode(int resultCode) {
        this.code = resultCode;
    }

    public void setMessage(ResultCode resultCode) {
        this.message = resultCode.getMessage();
    }
    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public Object getData() {
        return data;
    }

    public void setData(Object data) {
        this.data = data;
    }


    /**
     * 初始化一个新创建的 AjaxResult 对象，使其表示一个空消息。
     */
    private AjaxResult() {
    }


    public static AjaxResult success() {
        AjaxResult result = new AjaxResult();
        result.setCode(ResultCode.SUCCESS.getCode());
        result.setMessage(ResultCode.SUCCESS.getMessage());
        return result;
    }

    /**
     * 返回成功数据
     *
     * @return 成功消息
     */
    public static AjaxResult success(Object data) {
        AjaxResult result = new AjaxResult();
        result.setCode(ResultCode.SUCCESS.getCode());
        result.setMessage(ResultCode.SUCCESS.getMessage());
        putData(data, result);
        return result;
    }

    /**
     * 返回成功消息
     * @param resultCode 状态码对象
     * @param data 数据对象
     * @return AjaxResult
     */
    public static AjaxResult success(ResultCode resultCode, Object data) {
        AjaxResult result = new AjaxResult();
        result.setCode(resultCode.getCode());
        result.setMessage(resultCode.getMessage());
        putData(data, result);
        return result;
    }

    /**
     * 返回成功消息
     * @param code 状态码
     * @param msg 消息
     * @param data 数据对象
     * @return AjaxResult
     */
    public static AjaxResult success(Integer code, String msg, Object data) {
        AjaxResult result = new AjaxResult();
        result.setCode(code);
        result.setMessage(msg);
        putData(data, result);
        return result;
    }

    /**
     * 失败消息
     * @param resultCode 状态码对象
     * @return AjaxResult
     */
    public static AjaxResult error(ResultCode resultCode) {
        AjaxResult result = new AjaxResult();
        result.setCode(resultCode.getCode());
        result.setMessage(resultCode.getMessage());
        return result;
    }

    /**
     * 失败消息
     * @param resultCode 状态码对象
     * @param data 数据对象
     * @return AjaxResult
     */
    public static AjaxResult error(ResultCode resultCode, Object data) {
        AjaxResult result = new AjaxResult();
        result.setCode(resultCode.getCode());
        result.setMessage(resultCode);
        putData(data, result);
        return result;
    }

    private static void putData(Object data, AjaxResult result) {
        if(data != null){
            result.setData(data);
        }else {
            result.setData(new ArrayList<String>());
        }
    }

    /**
     * 返回错误消息
     * @param code 状态码
     * @param msg 消息
     * @param data 数据对象
     * @return AjaxResult
     */
    public static AjaxResult error(Integer code, String msg, Object data) {
        AjaxResult result = new AjaxResult();
        result.setCode(code);
        result.setMessage(msg);
        putData(data, result);
        return result;
    }

    @Override
    public String toString() {
        return "AjaxResult{" +
                "code=" + code +
                ", message='" + message + '\'' +
                ", data=" + data +
                '}';
    }
}
