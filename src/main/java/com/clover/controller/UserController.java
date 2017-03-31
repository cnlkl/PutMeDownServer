package com.clover.controller;

import com.clover.annotation.AuthNeed;
import com.clover.annotation.CurrentUserId;
import com.clover.constant.TokenConstant;
import com.clover.model.Response;
import com.clover.model.User;
import com.clover.service.TokenService;
import com.clover.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletResponse;

/**
 * Created by lkl on 2017/3/22.
 */
@RestController
@RequestMapping("/api")
public class UserController {

    private UserService userService;

    private TokenService tokenService;

    @Autowired
    public UserController(UserService userService, TokenService tokenService) {
        this.userService = userService;
        this.tokenService = tokenService;
    }

    @RequestMapping(path = "/user", method = RequestMethod.POST)
    public Response<User> regist(@RequestParam String account,
                                 @RequestParam String password,
                                 @RequestParam String authCode,
                                 HttpServletResponse httpServletResponse) {

        User user = userService.register(account, password, authCode, "86");
        //创建token并存入header
        setTokenToHeader(httpServletResponse, user);
        Response<User> response =
                new Response<>(200, "注册成功", user);
        return response;
    }

    @RequestMapping(path = "/token", method = RequestMethod.POST)
    public Response<User> login(@RequestParam String account,
                                @RequestParam String password,
                                HttpServletResponse httpServletResponse) {

        User user = userService.login(account, password);
        //创建token并存入header
        setTokenToHeader(httpServletResponse, user);
        Response<User> response =
                new Response<>(200, "登入成功", user);
        return response;
    }

    @RequestMapping(path = "/user/password", method = RequestMethod.PUT)
    public Response updatePassword(@RequestParam String account,
                                   @RequestParam String newPassword,
                                   @RequestParam String authCode) {

        userService.updatePassword(account, newPassword, authCode, "86");

        return new Response(200, "修改密码成功");
    }

    @AuthNeed
    @RequestMapping(path = "/user", method = RequestMethod.GET)
    public Response<User> userInfo(@CurrentUserId Integer uid) {

        return new Response<>(200,
                        "获取用户信息成功",
                        userService.userInfo(uid));

    }

    @AuthNeed
    @RequestMapping(path = "/user", method = RequestMethod.PUT)
    public Response<User> updateUserInfo(@RequestParam String name,
                                         @CurrentUserId Integer uid) {
        return new Response<User>(200,
                "修改成功",
                userService.updateUser(uid, name));
    }


    private void setTokenToHeader(
            HttpServletResponse httpServletResponse, User user) {

        String token = tokenService.createToken(user.getAccount(), user.getUid());
        httpServletResponse.setHeader(
                TokenConstant.HEAD_ATTRIBUTE.getV(), token);
    }

}
