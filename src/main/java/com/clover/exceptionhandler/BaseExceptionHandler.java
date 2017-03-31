package com.clover.exceptionhandler;

import com.clover.model.Response;
import org.springframework.util.StringUtils;
import org.springframework.web.HttpRequestMethodNotSupportedException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * Created by lkl on 2017/3/21.
 */
@ControllerAdvice
public class BaseExceptionHandler {

    private static final String UNKNOWN_ERROR_MSG =
            "Unknown Error, Please Contact Admin: nowlinlei@outlook.com";

//    @ExceptionHandler
    @ResponseBody
    public Response unknownException(Exception e) {
        Response response = new Response(500, UNKNOWN_ERROR_MSG);
        if(!StringUtils.isEmpty(e.getMessage())) {
            response.setMsg(e.getMessage());
        }
        return response;
    }

    @ExceptionHandler(HttpRequestMethodNotSupportedException.class)
    @ResponseBody
    public Response methodNotSupportException(Exception e) {
        return new Response(405, e.getMessage());
    }

}
