package com.clover.exception;

/**
 * Created by lkl on 2017/3/21.
 */
public class DuplicateAccountException extends RuntimeException {

    public DuplicateAccountException() {
        this("用户名已被使用");
    }

    public DuplicateAccountException(String message) {
        super(message);
    }
}
