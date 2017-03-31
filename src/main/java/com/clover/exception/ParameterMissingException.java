package com.clover.exception;

/**
 * Created by lkl on 2017/3/22.
 */
public class ParameterMissingException extends RuntimeException{

    public ParameterMissingException() {
        this("parameter missing");
    }

    public ParameterMissingException(String message) {
        super(message);
    }
}
