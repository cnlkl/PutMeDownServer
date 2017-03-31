package com.clover.service;

/**
 * Created by lkl on 2017/3/22.
 */
public interface TokenService {

    /**
     * 检查token是否合法
     * @param token 待检查的token
     * @return 用户id
     */
    Integer getUid(String token);

    /**
     * 创建token
     * @param account 用户账号
     * @param uid   用户id
     * @return  创建的token
     */
    String createToken(String account, int uid);

    /**
     * 删除token
     * @param token
     * @return  已删除的token
     */
    String deleteToken(String token);
}
