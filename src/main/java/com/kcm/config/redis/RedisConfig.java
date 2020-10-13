package com.kcm.config.redis;

import com.alibaba.fastjson.parser.ParserConfig;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.apache.commons.pool2.impl.GenericObjectPoolConfig;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.cache.Cache;
import org.springframework.cache.CacheManager;
import org.springframework.cache.annotation.CachingConfigurerSupport;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.cache.interceptor.CacheErrorHandler;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import org.springframework.data.redis.cache.RedisCacheConfiguration;
import org.springframework.data.redis.cache.RedisCacheManager;
import org.springframework.data.redis.connection.*;
import org.springframework.data.redis.connection.jedis.JedisClientConfiguration;
import org.springframework.data.redis.connection.jedis.JedisConnectionFactory;
import org.springframework.data.redis.serializer.RedisSerializationContext;
import redis.clients.jedis.HostAndPort;
import redis.clients.jedis.JedisCluster;
import redis.clients.jedis.JedisPoolConfig;

import java.time.Duration;
import java.util.*;

/**
 * redis配置
 *
 * @author ZhangHao
 * @date 2020/7/30 16:43
 */
@Slf4j
@Configuration
@EnableCaching
public class RedisConfig extends CachingConfigurerSupport {

    @Value("${spring.redis.cluster.nodes}")
    private List<String> clusterNodes;

    @Value("${spring.redis.password}")
    private String password;

    @Value("${spring.redis.timeout}")
    private Duration timeout;

    @Value("${spring.redis.cluster.max-redirects}")
    private Integer clusterMaxRedirects;

    @Value("${spring.redis.jedis.pool.max-wait}")
    private Duration poolMaxWait;

    @Value("${spring.redis.jedis.pool.max-active}")
    private Integer poolMaxActive;

    @Value("${spring.redis.jedis.pool.max-idle}")
    private Integer poolMaxIdle;

    @Value("${spring.redis.jedis.pool.min-idle}")
    private Integer poolMinIdle;

    @Bean("jedisCluster")
    public JedisCluster getJedisCluster() {
        Set<HostAndPort> nodes = new HashSet<>();
        for (String node: clusterNodes) {
            String[] ipPortPair = node.split(":");
            nodes.add(new HostAndPort(ipPortPair[0], Integer.parseInt(ipPortPair[1])));
        }
        if (StringUtils.isBlank(password)) {
            return new JedisCluster(nodes, (int)timeout.toMillis(), 1000, 3, new GenericObjectPoolConfig());
        } else {
            return new JedisCluster(nodes, (int)timeout.toMillis(), 1000, 3, password, new GenericObjectPoolConfig());
        }
    }

    @Bean
    public RedisConnectionFactory redisConnectionFactory() {
        JedisPoolConfig poolConfig = new JedisPoolConfig();
        poolConfig.setMaxTotal(poolMaxActive);
        poolConfig.setMaxIdle(poolMaxIdle);
        poolConfig.setMinIdle(poolMinIdle);
        poolConfig.setMaxWaitMillis((int)poolMaxWait.toMillis());
        JedisClientConfiguration clientConfiguration = JedisClientConfiguration.builder()
                .usePooling()
                .poolConfig(poolConfig)
                .and()
                .readTimeout(Duration.ofMillis((int)timeout.toMillis()))
                .build();
        RedisClusterConfiguration clusterConfiguration = new RedisClusterConfiguration();
        clusterConfiguration.setMaxRedirects(clusterMaxRedirects);
        clusterConfiguration.setPassword(RedisPassword.of(password));
        for (String node: clusterNodes) {
            String[] ipPortPair = node.split(":");
            clusterConfiguration.clusterNode(ipPortPair[0], Integer.parseInt(ipPortPair[1]));
        }
        return new JedisConnectionFactory(clusterConfiguration, clientConfiguration);
    }

    /**
     * 配置CacheManager
     * @param factory RedisConnectionFactory
     * @return CacheManager
     */
    @Bean(name = "cacheManager")
    @Primary
    public CacheManager cacheManager(RedisConnectionFactory factory) {
        //序列化
        FastJsonRedisSerializer<Object> serializer = new FastJsonRedisSerializer<>(Object.class);
        RedisSerializationContext.SerializationPair pair = RedisSerializationContext.SerializationPair.fromSerializer(serializer);
        //默认缓存
        RedisCacheConfiguration defaultConfig = RedisCacheConfiguration.defaultCacheConfig()
                //设置缓存的默认过期时间
                .entryTtl(Duration.ofMinutes(5))
                //不缓存空值
                .disableCachingNullValues()
                //设置序列化value值
                .serializeValuesWith(pair);
        //设置白名单，反序列化时默认开启autoType检查，如果序列化信息中的类路径不在autoType中，
        //反序列化就会报com.alibaba.fastjson.JSONException: autoType is not support的异常
        ParserConfig.getGlobalInstance().addAccept("com.kcm.**.entity.");
        return RedisCacheManager.builder(factory).cacheDefaults(defaultConfig).build();
    }

    /**
     * redis异常处理
     *
     * @return 日志打印错误信息
     */
    @Bean
    @Override
    public CacheErrorHandler errorHandler() {
        return  new CacheErrorHandler() {
            @Override
            public void handleCacheGetError(RuntimeException e, Cache cache, Object o) {
                redisErrorException(e, cache);
            }

            @Override
            public void handleCachePutError(RuntimeException e, Cache cache, Object o, Object o1) {
                redisErrorException(e, cache);
            }

            @Override
            public void handleCacheEvictError(RuntimeException e, Cache cache, Object o) {
                redisErrorException(e, cache);
            }

            @Override
            public void handleCacheClearError(RuntimeException e, Cache cache) {
                redisErrorException(e, null);
            }
        };
    }

    private void redisErrorException(Exception e, Object key) {
        log.error("Redis exception: key={}", key, e);
    }

}
