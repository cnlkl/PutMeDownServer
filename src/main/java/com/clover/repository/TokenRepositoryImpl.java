package com.clover.repository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.stereotype.Repository;

import java.util.concurrent.TimeUnit;

/**
 * Created by lkl on 2017/3/20.
 */
@Repository
public class TokenRepositoryImpl implements TokenRepository{

    @Autowired
    private RedisTemplate<String, Integer> redisTemplate;


    @Override
    public void saveId(String token, Integer uid) {
        redisTemplate.opsForValue().set(token, uid, 7, TimeUnit.DAYS);
    }

    @Override
    public Integer getId(String token) {
        return redisTemplate.opsForValue().get(token);
    }

    @Override
    public void deleteToken(String token) {
        redisTemplate.delete(token);
    }
}
