package com.clover.service.impl;

import com.clover.repository.TokenRepository;
import com.clover.service.TokenService;
import com.clover.utils.EncryptUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * Created by lkl on 2017/3/22.
 */
@Service
public class TokenServiceImpl implements TokenService{

    @Autowired
    private TokenRepository tokenRepository;


    @Override
    public Integer getUid(String token) {

        Integer uid = null;

        if (token != null) {
            uid = tokenRepository.getId(token);
            if (uid != null) {
                //延长token过期时间
                tokenRepository.saveId(token, uid);
            }
        }

        return uid;
    }

    @Override
    public String createToken(String account, int uid) {

        String source = account + ".}}}_lkl_pmd_colver**()" + uid + System.currentTimeMillis();

        String token = EncryptUtil.md5(source);

        tokenRepository.saveId(token, uid);
        return token;
    }

    @Override
    public String deleteToken(String token) {

        if (token != null) {
            tokenRepository.deleteToken(token);
        }
        return token;
    }
}
