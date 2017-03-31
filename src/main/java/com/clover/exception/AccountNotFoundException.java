package com.clover.exception;

/**
 * Created by lkl on 2017/3/24.
 */
public class AccountNotFoundException extends RuntimeException{

    public AccountNotFoundException() {
        this("Account Not Found");
    }

    public AccountNotFoundException(String message) {
        super(message);
    }
}
