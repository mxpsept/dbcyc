package com.kcm.modules.system.user.controller;

import com.kcm.config.redis.cache.ICacheManager;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * 测试服务
 *
 * @author ZhangHao
 * @version 1.0
 * @date 2020/8/13 10:43
 */
@Slf4j
@RestController
@RequestMapping("/test")
@RequiredArgsConstructor
public class CacheTestController {

    private final ICacheManager cacheManager;

    private static final String TEST_CACHE_KEY_PREFIX = "test-";

    @GetMapping("/cache")
    public void test() {
        String cacheKey = TEST_CACHE_KEY_PREFIX + 1;
        log.info("缓存->{}是否存在->{}", cacheKey, cacheManager.exist(cacheKey));
        log.info("添加缓存->{},缓存结果为->{}", cacheManager.putCache(cacheKey, "123"), cacheManager.getCache(cacheKey));
        log.info("再次添加缓存->{},缓存结果为->{}", cacheManager.putCache(cacheKey, "456"), cacheManager.getCache(cacheKey));
        log.info("更新缓存->{},缓存结果为->{}", cacheManager.updateCache(cacheKey, "789"), cacheManager.getCache(cacheKey));
        log.info("清除缓存->{}", cacheManager.removeCache(cacheKey));
        log.info("再次更新缓存->{},缓存结果为->{}", cacheManager.updateCache(cacheKey, "789"), cacheManager.getCache(cacheKey));
        log.info("再次清除缓存->{}", cacheManager.removeCache(cacheKey));
    }

}
