package com.kcm.common.other;

import java.util.Random;
import java.util.UUID;

/**
 * 字符串工具类
 *
 * @author ZhangHao
 * @date 2020/6/25 14:28
 */
public class StringUtils {

    private static final char[] CHARS = {'0','1','2','3','4','5','6','7','8','9'};
    private static int char_length = CHARS.length;

    private StringUtils() {
        throw new AssertionError();
    }

    public static boolean isEmpty(String str) {
        return null == str || str.length() == 0;
    }

    public static boolean isNotEmpty(String str) {
        return !isEmpty(str);
    }

    public static boolean isBlank(String str) {
        int strLen;
        if (null == str || (strLen = str.length()) == 0) {
            return true;
        }
        for (int i = 0; i < strLen; i++) {
            if (!Character.isWhitespace(str.charAt(i))) {
                return false;
            }
        }
        return true;
    }

    public static boolean isNotBlank(String str) {
        return !isBlank(str);
    }

    public static String randomString(int length) {
        StringBuilder builder = new StringBuilder(length);
        Random random = new Random();
        for (int i = 0; i < length; i++) {
            builder.append(random.nextInt(char_length));
        }
        return builder.toString();
    }

    public static String uuid() {
        return UUID.randomUUID().toString().replace("-", "");
    }

}
