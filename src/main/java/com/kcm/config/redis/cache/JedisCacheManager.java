package com.kcm.config.redis.cache;

import com.kcm.config.redis.FastJsonRedisSerializer;
import lombok.RequiredArgsConstructor;
import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Component;
import redis.clients.jedis.JedisCluster;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

/**
 * 缓存实现
 *
 * @author ZhangHao
 * @date 2020/8/3 10:11
 */
@Component
@RequiredArgsConstructor
public class JedisCacheManager implements ICacheManager {

    private static final String JEDIS_SET_RETURN_OK = "OK";

    private FastJsonRedisSerializer<Object> serializer = new FastJsonRedisSerializer<>(Object.class);

    private final JedisCluster jedisCluster;

    @Override
    public boolean exist(Serializable cacheKey) {
        return jedisCluster.exists(serializer.serialize(cacheKey));
    }

    @Override
    public Object getCache(Serializable cacheKey) {
        return serializer.deserialize(jedisCluster.get(serializer.serialize(cacheKey)));
    }

    @Override
    public boolean putCache(Serializable cacheKey, Object objValue) {
        Long result = jedisCluster.setnx(serializer.serialize(cacheKey), serializer.serialize(objValue));
        return result != 0;
    }

    @Override
    public boolean putCache(Serializable cacheKey, Object objValue, int expiration) {
        String result = jedisCluster.setex(serializer.serialize(cacheKey), expiration, serializer.serialize(objValue));
        return StringUtils.equals(JEDIS_SET_RETURN_OK, result);
    }

    @Override
    public boolean updateCache(Serializable cacheKey, Object objValue) {
        if (exist(cacheKey)) {
            jedisCluster.getSet(serializer.serialize(cacheKey), serializer.serialize(objValue));
            return true;
        }
        return false;
    }

    @Override
    public boolean removeCache(Serializable cacheKey) {
        Long result = jedisCluster.del(serializer.serialize(cacheKey));
        return result != 0;
    }

    @Override
    public boolean putListCache(Serializable cacheKey, Object objValue) {
        Long num = jedisCluster.rpush(serializer.serialize(cacheKey), serializer.serialize(objValue));
        return num != 0;
    }

    @Override
    public boolean putListCache(Serializable cacheKey, Object objValue, int index) {
        String result = jedisCluster.lset(serializer.serialize(cacheKey), index, serializer.serialize(objValue));
        return StringUtils.equals(JEDIS_SET_RETURN_OK, result);
    }

    @Override
    public List<Object> getListCache(Serializable cacheKey, int start, int end) {
        List<byte[]> list = jedisCluster.lrange(serializer.serialize(cacheKey), start, end);
        if (null != list && list.size() > 0) {
            List<Object> objList = new ArrayList<>();
            for (byte[] b : list) {
                objList.add(serializer.deserialize(b));
            }
            return objList;
        }
        return null;
    }

    @Override
    public List<Object> getListCache(Serializable cacheKey) {
        return getListCache(cacheKey, 0, -1);
    }

    @Override
    public boolean trimListCache(Serializable cacheKey, int start, int end) {
        String result = jedisCluster.ltrim(serializer.serialize(cacheKey), start, end);
        return StringUtils.equals(JEDIS_SET_RETURN_OK, result);
    }

    @Override
    public boolean putMapCache(Serializable cacheKey, Map<Object, Object> map) {
        if (null != map && !map.isEmpty()) {
            Map<byte[], byte[]> byteMap = new HashMap<>(16);
            for (Entry<Object, Object> entry : map.entrySet()) {
                byteMap.put(serializer.serialize(entry.getKey()), serializer.serialize(entry.getValue()));
            }
            String result = jedisCluster.hmset(serializer.serialize(cacheKey), byteMap);
            if (StringUtils.equals(JEDIS_SET_RETURN_OK, result)) {
                return true;
            }
            return true;
        }
        return false;
    }

    @Override
    public boolean deleteMapCache(Serializable cacheKey, Serializable mapKey) {
        Long result = jedisCluster.hdel(serializer.serialize(cacheKey), serializer.serialize(mapKey));
        return result > 0;
    }

    @Override
    public Map<Object, Object> getMapKeyAndValueCache(Serializable cacheKey) {
        Map<Object, Object> map = new HashMap<>(16);
        for (Entry<byte[], byte[]> entry: jedisCluster.hgetAll(serializer.serialize(cacheKey)).entrySet()) {
            map.put(serializer.deserialize(entry.getKey()), serializer.deserialize(entry.getValue()));
        }
        return map;
    }

    @Override
    public Object getMapValueCache(Serializable cacheKey, Serializable mapKey) {
        List<byte[]> list = jedisCluster.hmget(serializer.serialize(cacheKey), serializer.serialize(mapKey));
        if (null != list && list.size() > 0) {
            return serializer.deserialize(list.get(0));
        }
        return null;
    }

}


