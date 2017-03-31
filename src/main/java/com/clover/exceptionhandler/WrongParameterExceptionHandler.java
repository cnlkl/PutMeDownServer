package com.clover.exceptionhandler;

import com.clover.exception.AccountNotFoundException;
import com.clover.exception.FriendAlreadExistException;
import com.clover.exception.FriendRequestAlreadyExistsExcption;
import com.clover.exception.ParameterMissingException;
import com.clover.model.Response;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * Created by lkl on 2017/3/29.
 */
@ControllerAdvice
public class WrongParameterExceptionHandler {

    @ExceptionHandler(ParameterMissingException.class)
    @ResponseBody
    public Response parameterMissingException(Exception e) {
        return new Response(400, e.getMessage());
    }

    @ExceptionHandler(AccountNotFoundException.class)
    @ResponseBody
    public Response accountNotFoundException(Exception e) {
        return new Response(422, e.getMessage());
    }

    @ExceptionHandler(FriendRequestAlreadyExistsExcption.class)
    @ResponseBody
    public Response friendRqslAreadyExistsException(Exception e) {
        return new Response(422, e.getMessage());
    }

    @ExceptionHandler(FriendAlreadExistException.class)
    @ResponseBody
    public Response FriendAlreadyExistsException(Exception e) {
        return new Response(422, e.getMessage());
    }
}
