package com.kcm.common.other;

import java.lang.reflect.Method;
import java.util.List;

/**
 * 树形结构list转普通list
 *
 * @author shawn
 * @version 1.0
 * @date 22/10/2018 19:06
 */
public class TreeToListUtil {
    public static List<Object> treeToList(List<?> dataList, List<Object> resultList) {
        for (Object obj : dataList) {
            try {
                resultList.add(obj);
                Method method = obj.getClass().getMethod("getChildren");
                List<?> list = (List<?>) method.invoke(obj);
                treeToList(list, resultList);
            } catch (Exception e) {
                e.printStackTrace();
            }
        }

        return resultList;
    }
}

