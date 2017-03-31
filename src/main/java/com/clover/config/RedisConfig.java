package com.clover.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.redis.connection.jedis.JedisConnectionFactory;
import org.springframework.data.redis.core.RedisTemplate;

import java.util.UUID;

/**
 * Created by lkl on 2017/3/20.
 */
@Configuration
public class RedisConfig {

    @Autowired
    private JedisConnectionFactory factory;

    @Bean
    public RedisTemplate<String, Integer> redisTemplate() {
        RedisTemplate<String, Integer> redisTemplate =
                new RedisTemplate();
        redisTemplate.setConnectionFactory(factory);
        return redisTemplate;
    }

}
