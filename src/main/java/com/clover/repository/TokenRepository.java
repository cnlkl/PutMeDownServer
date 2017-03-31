package com.clover.repository;

/**
 * Created by lkl on 2017/3/20.
 */
public interface TokenRepository {

    void saveId(String token, Integer uid);

    Integer getId(String token);

    void deleteToken(String token);
}
