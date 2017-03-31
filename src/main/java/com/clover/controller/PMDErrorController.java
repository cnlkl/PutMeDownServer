package com.clover.controller;

import com.clover.constant.TokenConstant;
import com.clover.model.Response;
import org.springframework.boot.autoconfigure.web.ErrorController;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;

/**
 * Created by lkl on 2017/3/21.
 */
@RestController
public class PMDErrorController implements ErrorController {

    @RequestMapping("/error")
    public Response error() {
        Response response =
                new Response(404, "Resource not found,please check parameter or url");
        return response;
    }

    @RequestMapping("/authError")
    public Response authError(HttpServletRequest request) {
        Response response =
                new Response<>(403,
                        (String)request.getAttribute(
                                TokenConstant.ERROR_ATTRIBUTE.getV()));
        return response;
    }

    @Override
    public String getErrorPath() {
        return null;
    }
}
