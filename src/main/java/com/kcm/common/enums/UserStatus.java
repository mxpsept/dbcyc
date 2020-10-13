package com.kcm.common.enums;

/**
 * 用户状态
 * 
 * @author ruoyi
 */
public enum UserStatus
{
    /**
     * 状态正常为0
     */
    OK("0", "正常"),
    /**
     * 停用状态为1
     */
    DISABLE("1", "停用"),
    /**
     * 删除状态为2
     */
    DELETED("2", "删除");

    private final String code;
    private final String info;

    UserStatus(String code, String info)
    {
        this.code = code;
        this.info = info;
    }

    public String getCode()
    {
        return code;
    }

    public String getInfo()
    {
        return info;
    }
}
