package com.clover.interceptor;

import com.clover.annotation.AuthNeed;
import com.clover.constant.TokenConstant;
import com.clover.service.TokenService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.method.HandlerMethod;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.lang.reflect.Method;

/**
 * Created by lkl on 2017/3/22.
 */

public class AuthInterceptor implements HandlerInterceptor{

    @Autowired
    private TokenService tokenService;

    @Override
    public boolean preHandle(HttpServletRequest httpServletRequest,
                             HttpServletResponse httpServletResponse,
                             Object o) throws Exception {

        HandlerMethod handlerMethod = (HandlerMethod) o;
        Method method = handlerMethod.getMethod();

        //从请求头获取token
        String token = httpServletRequest.getHeader(TokenConstant.HEAD_ATTRIBUTE.getV());
        Integer uid = tokenService.getUid(token);

        if (uid != null) {
            httpServletRequest.setAttribute(TokenConstant.UID.getV(), uid);
            return true;
        }

        //判断请求的方法是否有权限要求
        if (method.getAnnotation(AuthNeed.class) != null) {

            String msg;
            if (token != null) {
                msg = "token过期，请重新登入";
            } else {
                msg = "请登入后操作";
            }

            httpServletRequest
                    .setAttribute(TokenConstant.ERROR_ATTRIBUTE.getV(), msg);
            httpServletRequest.getRequestDispatcher("/authError")
                    .forward(httpServletRequest, httpServletResponse);
            return false;
        }

        return true;
    }

    @Override
    public void postHandle(HttpServletRequest httpServletRequest,
                           HttpServletResponse httpServletResponse,
                           Object o, ModelAndView modelAndView) throws Exception {

    }

    @Override
    public void afterCompletion(HttpServletRequest httpServletRequest,
                                HttpServletResponse httpServletResponse,
                                Object o, Exception e) throws Exception {
    }
}
