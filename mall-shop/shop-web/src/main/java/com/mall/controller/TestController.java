package com.mall.controller;

import lombok.extern.slf4j.Slf4j;
import org.slf4j.Logger;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.ValueOperations;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import java.util.Map;

/**
 * TODO :please describe it in one sentence
 *
 * @author liam
 * @version V1.0
 * @date 2019/12/19
 **/
@Slf4j
@RestController
@RequestMapping("/test")
public class TestController {

    @Resource
    private RedisTemplate<String, String> redisTemplate;

    @GetMapping("/redis/get/{key}")
    public Map<String, Object> test01(@PathVariable("key") String key) {
        ValueOperations<String, String> operations = redisTemplate.opsForValue();
        String value = operations.get(key);
        log.info("redis get {}={}", key, value);
        return Map.of(key, value == null ? "value is null" : value);
    }

    @GetMapping("/redis/set/{key}/{value}")
    public Map<String, Object> test01(@PathVariable("key") String key, @PathVariable("value") String value) {
        ValueOperations<String, String> operations = redisTemplate.opsForValue();
        operations.set(key, value);
        return Map.of(key, value);
    }
}