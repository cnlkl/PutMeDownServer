package com.clover.exception;

/**
 * Created by lkl on 2017/3/22.
 */
public class WrongAccountOrPasswordException extends RuntimeException{
    public WrongAccountOrPasswordException() {
        this("账号或密码错误");
    }

    public WrongAccountOrPasswordException(String message) {
        super(message);
    }
}
