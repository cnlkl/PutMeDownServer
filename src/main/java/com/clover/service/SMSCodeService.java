package com.clover.service;

/**
 * Created by lkl on 2017/3/21.
 */
public interface SMSCodeService {

    /**
     * 验证短信验证码是否正确
     * @param phone 用户手机
     * @param code  用户收到的短信验证码
     * @param args  用于防止更改短信验证码服务提供商时接口参数变化
     *              目前需要：
     *              zone：区号
     * @return  验证是否成功
     */
    boolean verifyCode(String phone, String code, String... args);


}
