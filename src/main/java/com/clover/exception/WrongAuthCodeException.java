package com.clover.exception;

/**
 * Created by lkl on 2017/3/22.
 */
public class WrongAuthCodeException extends RuntimeException{

    public WrongAuthCodeException() {
        this("验证码错误");
    }

    public WrongAuthCodeException(String message) {
        super(message);
    }
}
