package com.kcm.common.utils;

import cn.hutool.core.util.StrUtil;

/**
 * 字符串工具类
 * @author lucky
 * @date 2020/8/4
 **/
public class StrUtils {
    /**
     * 是否包含字符串
     *
     * @param str 验证字符串
     * @param strs 字符串组
     * @return 包含返回true
     */
    public static boolean inStringIgnoreCase(String str, String... strs)
    {
        if (str != null && strs != null)
        {
            for (String s : strs)
            {
                if (str.equalsIgnoreCase(StrUtil.trim(s)))
                {
                    return true;
                }
            }
        }
        return false;
    }
}
