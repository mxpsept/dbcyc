package com.kcm.config.redis.cache;

import java.io.Serializable;
import java.util.List;
import java.util.Map;

/**
 * 缓存接口
 *
 * @author ZhangHao
 * @date 2020/8/3 10:07
 */
public interface ICacheManager {

    /**
     * 判断缓存是否存在
     *
     * @param cacheKey 缓存key
     * @return 判断结果
     */
    boolean exist(Serializable cacheKey);

    /**
     * 根据缓存key获取值
     *
     * @param cacheKey 缓存key
     * @return 缓存值
     */
    Object getCache(Serializable cacheKey);

    /**
     * 添加缓存数据
     *
     * @param cacheKey 缓存key
     * @param objValue 缓存值
     * @return 添加结果
     */
    boolean putCache(Serializable cacheKey, Object objValue);

    /**
     * 添加缓存数据，并设置失效时间，单位为秒
     *
     * @param cacheKey 缓存key
     * @param objValue 缓存值
     * @param expiration 失效时间
     * @return 添加结果
     */
    boolean putCache(Serializable cacheKey, Object objValue, int expiration);

    /**
     * 更新缓存数据
     * @param cacheKey 缓存key
     * @param objValue 更新的缓存值
     * @return 更新结果
     */
    boolean updateCache(Serializable cacheKey, Object objValue);

    /**
     * 清除缓存
     *
     * @param cacheKey 缓存key
     * @return 清除结果
     */
    boolean removeCache(Serializable cacheKey);

    /**
     * 向指定list集合中添加对象，在list尾部添加对象
     *
     * @param cacheKey 缓存key
     * @param objValue 缓存值
     * @return 添加结果
     */
    boolean putListCache(Serializable cacheKey, Object objValue);

    /**
     * 向指定list集合中添加对象，并指定位置坐标
     *
     * @param cacheKey 缓存key
     * @param objValue 缓存值
     * @param index 索引
     * @return 添加结果
     */
    boolean putListCache(Serializable cacheKey, Object objValue, int index);

    /**
     * 根据坐标，返回一段集合
     *
     * @param cacheKey 缓存key
     * @param start 起始坐标 头部为0
     * @param end 结束坐标 尾部为-1
     * @return 获取结果
     */
    List<Object> getListCache(Serializable cacheKey, int start, int end);

    /**
     * 获取List
     *
     * @param cacheKey 缓存key
     * @return 获取结果
     */
    List<Object> getListCache(Serializable cacheKey);

    /**
     * 截取list集合
     *
     * @param cacheKey 缓存key
     * @param start    起始坐标
     * @param end      结束坐标
     * @return 截取结果
     */
    boolean trimListCache(Serializable cacheKey, int start, int end);

    /**
     * 添加map集合
     *
     * @param cacheKey 缓存key
     * @param map 缓存map值
     * @return 添加结果
     */
    boolean putMapCache(Serializable cacheKey, Map<Object, Object> map);

    /**
     * 删除map中的键值
     *
     * @param cacheKey 缓存key
     * @param mapKey mapKey
     * @return 删除结果
     */
    boolean deleteMapCache(Serializable cacheKey, Serializable mapKey);

    /**
     * 获取map集合
     *
     * @param cacheKey 缓存key
     * @return 获取结果
     */
    Map<Object, Object> getMapKeyAndValueCache(Serializable cacheKey);

    /**
     * 获取map中key为mapKey的值
     *
     * @param cacheKey 缓存key
     * @param mapKey mapKey
     * @return 获取结果
     */
    Object getMapValueCache(Serializable cacheKey, Serializable mapKey);

}

