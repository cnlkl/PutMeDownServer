package com.clover.controller;

import com.clover.annotation.AuthNeed;
import com.clover.model.Response;
import com.clover.service.SMSCodeService;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.constraints.NotNull;

/**
 * Created by lkl on 2017/3/20.
 */
@RestController
@RequestMapping("/")
public class TestController {

    SMSCodeService smsCodeService;

    @RequestMapping(value = "/test", method = RequestMethod.GET)
    @AuthNeed
    public Response test(@NotNull(message = "miss str") String str) {
        return new Response(200, "ok");
    }

}
