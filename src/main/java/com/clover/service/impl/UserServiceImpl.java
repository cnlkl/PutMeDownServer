package com.clover.service.impl;

import com.clover.exception.DuplicateAccountException;
import com.clover.exception.ParameterMissingException;
import com.clover.exception.WrongAccountOrPasswordException;
import com.clover.exception.WrongAuthCodeException;
import com.clover.model.User;
import com.clover.repository.UserRepository;
import com.clover.service.SMSCodeService;
import com.clover.service.UserService;
import com.clover.utils.DateUtil;
import com.clover.utils.EncryptUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CachePut;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import javax.transaction.Transactional;

/**
 * Created by lkl on 2017/3/21.
 */
@Transactional
@Service
public class UserServiceImpl implements UserService{

    private SMSCodeService smsCodeService;

    private UserRepository repository;

    @Autowired
    public UserServiceImpl(SMSCodeService smsCodeService,
                           UserRepository userRepository) {
        this.smsCodeService = smsCodeService;
        this.repository = userRepository;
    }

    @Override
    @CachePut(value = "users", key = "#result.uid")
    public User register(
            String account, String password, String code, String... args) {

        if(StringUtils.isEmpty(account)
                || StringUtils.isEmpty(password)
                || StringUtils.isEmpty(code)) {
            throw new ParameterMissingException();
        }

        if(repository.findByAccount(account) != null) {
            throw new DuplicateAccountException("account: " + account + " already exists!");
        }

        if(smsCodeService.verifyCode(account, code, args)) {
            User user = new User();
            user.setAccount(account);
            user.setPhoneNumber(account);
            user.setPassword(EncryptUtil.md5(password));
            String date = DateUtil.getCurrentDate();
            user.setRegTime(date);
            user.setLastLoginTime(date);
            return repository.save(user);
        } else {
            throw new WrongAuthCodeException();
        }

    }

    @Override
    @CachePut(value = "users", key = "#result.uid")
    public User updatePassword(
            String account, String newPassword, String code, String... args) {

        if(StringUtils.isEmpty(account)
                || StringUtils.isEmpty(newPassword)
                || StringUtils.isEmpty(code)) {
            throw new ParameterMissingException();
        }

        User user = repository.findByAccount(account);

        if(user == null) {
            throw new WrongAccountOrPasswordException();
        }

        if(smsCodeService.verifyCode(account, code, args)) {
            user.setPassword(EncryptUtil.md5(newPassword));
            return repository.save(user);
        } else {
            throw new WrongAuthCodeException();
        }

    }

    @Override
    @CachePut(value = "users", key = "#result.uid")
    public User login(String account, String password) {

        if (account == null || password == null) {
            throw new ParameterMissingException();
        }

        User user = repository.findByAccount(account);
        String truePassword = user.getPassword();

        if (user == null
                || !EncryptUtil.md5(password).equals(truePassword)) {
            throw new WrongAccountOrPasswordException();
        }

        user.setLastLoginTime(DateUtil.getCurrentDate());
        repository.save(user);

        return  user;
    }

    @Override
    @Cacheable(value = "users", key = "#id")
    public User userInfo(Integer id) {
        return repository.findOne(id);
    }

    @Override
    @CachePut(value = "users", key = "#result.uid")
    public User updateUser(Integer id, String name) {

        User user = repository.findOne(id);
        user.setName(name);

        return repository.save(user);
    }
}
