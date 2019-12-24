package com.mall.web.config.redis;

import com.fasterxml.jackson.annotation.JsonAutoDetect;
import com.fasterxml.jackson.annotation.PropertyAccessor;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.mall.web.config.redis.serializer.FastJson2JsonRedisSerializer;
import org.springframework.cache.CacheManager;
import org.springframework.cache.interceptor.KeyGenerator;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.redis.cache.RedisCacheConfiguration;
import org.springframework.data.redis.cache.RedisCacheManager;
import org.springframework.data.redis.cache.RedisCacheWriter;
import org.springframework.data.redis.connection.RedisConnectionFactory;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.data.redis.serializer.StringRedisSerializer;

import java.lang.reflect.Method;
import java.time.Duration;

/**
 *
 *
 * @author liam
 * @version V1.0
 * @date 2019/12/19
 **/
@Configuration
public class RedisClusterConfiguration{

    /**
     * default the cache time (unit: seconds)
     */
    private final static int CACHE_TIME = 30;

    /**
     * default the cache key generate Strategy ： packageName + methodName + all the params
     * @return
     */
    @Bean
    public KeyGenerator wiselyKeyGenerator() {
        return (Object target, Method method, Object... params) -> {
            StringBuilder sb = new StringBuilder();
            sb.append(target.getClass().getName());
            sb.append(method.getName());
            for (Object obj : params) {
                sb.append(obj.toString());
            }
            return sb.toString();
        };
    }


    /**
     * CacheManager
     *
     * @param connectionFactory
     * @return
     */
    @Bean
    public CacheManager cacheManager(RedisConnectionFactory connectionFactory) {

        RedisCacheWriter redisCacheWriter = RedisCacheWriter.nonLockingRedisCacheWriter(connectionFactory);
        RedisCacheConfiguration defaultCacheConfig = RedisCacheConfiguration.defaultCacheConfig();
        /** config the cache expired time */
        RedisCacheConfiguration configuration = defaultCacheConfig.entryTtl(Duration.ofSeconds(CACHE_TIME));
        RedisCacheManager cacheManager = new RedisCacheManager(redisCacheWriter, configuration);
        return cacheManager;
    }


    /**
     *  injected the redisTemplate and config the serializer for the redis's key and value
     * @param redisConnectionfactory
     * @return
     */
    @Bean
    public RedisTemplate<String,String> redisTemplate(RedisConnectionFactory redisConnectionfactory){

        StringRedisTemplate redisTemplate = new StringRedisTemplate(redisConnectionfactory);

        // key's serializer is StringRedisSerializer
        redisTemplate.setKeySerializer(new StringRedisSerializer());
        redisTemplate.setHashKeySerializer(new StringRedisSerializer());

        FastJson2JsonRedisSerializer fastJson2JsonRedisSerializer = new FastJson2JsonRedisSerializer(Object.class);
        ObjectMapper mapper = new ObjectMapper();
        mapper.setVisibility(PropertyAccessor.ALL, JsonAutoDetect.Visibility.ANY);
        mapper.enableDefaultTyping(ObjectMapper.DefaultTyping.NON_FINAL);
        fastJson2JsonRedisSerializer.setObjectMapper(mapper);

        // value's serializer is fastJson2JsonRedisSerializer
        redisTemplate.setValueSerializer(fastJson2JsonRedisSerializer);
        redisTemplate.setValueSerializer(new StringRedisSerializer());

        redisTemplate.afterPropertiesSet();
        return redisTemplate;
    }
}
