package com.clover.exceptionhandler;

import com.clover.exception.DuplicateAccountException;
import com.clover.exception.WrongAccountOrPasswordException;
import com.clover.exception.WrongAuthCodeException;
import com.clover.model.Response;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.security.auth.message.AuthException;

/**
 * Created by lkl on 2017/3/22.
 */
@ControllerAdvice
public class AuthExceptionHandler {

    @ExceptionHandler(AuthException.class)
    @ResponseBody
    public Response AuthException(AuthException e) {
        return new Response<>(403, e.getMessage());
    }

    @ExceptionHandler(WrongAuthCodeException.class)
    @ResponseBody
    public Response wrongAuthCodeException(WrongAuthCodeException e) {
        return new Response<>(422, e.getMessage());
    }

    @ExceptionHandler(DuplicateAccountException.class)
    @ResponseBody
    public Response duplicateAccountException(Exception e) {
        return  new Response<>(422, e.getMessage());
    }

    @ExceptionHandler(WrongAccountOrPasswordException.class)
    @ResponseBody
    public Response wrongAccountOrPasswordException(Exception e) {
        return new Response(403, e.getMessage());
    }
}
