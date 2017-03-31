package com.clover.service;

import com.clover.model.User;

/**
 * Created by lkl on 2017/3/21.
 */
public interface UserService {

    /**
     * 注册服务
     * @param account   用户账户
     * @param password  用户密码
     * @param code  验证码
     * @param args  备用参数
     *              目前需要
     *              zone：区域编号，如86
     * @return  注册成功后创建的用户，失败返回null
     */
    User register(String account, String password, String code, String... args);

    /**
     * 修改密码
     * @param account   用户名
     * @param newPassword   新密码
     * @param code  验证码
     * @param args  备用参数
     *              目前需要
     *              zone：区域编号，如86
     * @return  true：修改成功，false：修改失败
     */
    User updatePassword(String account, String newPassword, String code, String... args);

    User login(String username, String password);

    User userInfo(Integer id);

    User updateUser(Integer id, String name);

}
