package com.zqkh.webapi.context.configurtion;

import com.zqkh.webapi.context.configurtion.properties.CloudConfigProperties;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.CacheManager;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.redis.cache.RedisCacheManager;
import org.springframework.data.redis.connection.jedis.JedisConnectionFactory;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.serializer.JdkSerializationRedisSerializer;
import org.springframework.data.redis.serializer.StringRedisSerializer;
import redis.clients.jedis.JedisPoolConfig;

/**
 * @author weiyang
 */
@Configuration
@EnableCaching
@Slf4j
public class RedisConfiguration {
    //过期时间一天
    private static final int DEFAULT_EXPIRE_TIME = 3600 * 24;

    @Autowired
    private CloudConfigProperties cloudConfigProperties;

    /**
     * jedisPoolConfig config
     */
    @Bean
    public JedisPoolConfig jedisPoolConfig() {
        JedisPoolConfig jedisPoolConfig = new JedisPoolConfig();
        jedisPoolConfig.setMaxIdle(cloudConfigProperties.getRedis().getMaxIdle());
        jedisPoolConfig.setMinIdle(cloudConfigProperties.getRedis().getMinIdle());
        jedisPoolConfig.setTestOnBorrow(cloudConfigProperties.getRedis().getTestOnBorrow());
        jedisPoolConfig.setTestOnReturn(cloudConfigProperties.getRedis().getTestOnReturn());
        return jedisPoolConfig;
    }

    /**
     * JedisConnectionFactory
     */
    @Bean
    public JedisConnectionFactory jedisConnectionFactory(JedisPoolConfig jedisPoolConfig) {
        JedisConnectionFactory jedisConnectionFactory = new JedisConnectionFactory();
        jedisConnectionFactory.setHostName(cloudConfigProperties.getRedis().getHost());
        jedisConnectionFactory.setPort(cloudConfigProperties.getRedis().getPort());
        jedisConnectionFactory.setPassword(cloudConfigProperties.getRedis().getPassword());
        jedisConnectionFactory.setTimeout(cloudConfigProperties.getRedis().getTimeout());
        jedisConnectionFactory.setUsePool(true);
        jedisConnectionFactory.setPoolConfig(jedisPoolConfig);
        jedisConnectionFactory.afterPropertiesSet();
        return jedisConnectionFactory;
    }


    /**
     * RedisTemplate
     */
    @Bean
    public RedisTemplate redisTemplate(JedisConnectionFactory jedisConnectionFactory) {
        RedisTemplate<?, ?> redisTemplate = new RedisTemplate();
        redisTemplate.setConnectionFactory(jedisConnectionFactory);
        redisTemplate.setDefaultSerializer(new StringRedisSerializer());
        redisTemplate.setValueSerializer(new JdkSerializationRedisSerializer());
        redisTemplate.setHashValueSerializer(new JdkSerializationRedisSerializer());
        return redisTemplate;
    }

    @Bean
    public CacheManager cacheManager(RedisTemplate redisTemplate) {
        RedisCacheManager redisCacheManager = new RedisCacheManager(redisTemplate);
        redisCacheManager.setDefaultExpiration(DEFAULT_EXPIRE_TIME);
        return redisCacheManager;
    }

}
