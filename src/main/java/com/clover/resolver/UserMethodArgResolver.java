package com.clover.resolver;

import com.clover.annotation.CurrentUserId;
import com.clover.constant.TokenConstant;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.core.MethodParameter;
import org.springframework.web.bind.support.WebDataBinderFactory;
import org.springframework.web.context.request.NativeWebRequest;
import org.springframework.web.context.request.RequestAttributes;
import org.springframework.web.method.support.HandlerMethodArgumentResolver;
import org.springframework.web.method.support.ModelAndViewContainer;

/**
 * Created by lkl on 2017/3/22.
 */

public class UserMethodArgResolver implements HandlerMethodArgumentResolver{

    @Override
    public boolean supportsParameter(MethodParameter parameter) {

        if (parameter.getParameterType().isAssignableFrom(Integer.class)
                && parameter.hasParameterAnnotation(CurrentUserId.class)) {
            return true;
        }
        return false;
    }

    @Override
    public Object resolveArgument(MethodParameter parameter,
                                  ModelAndViewContainer mavContainer,
                                  NativeWebRequest webRequest,
                                  WebDataBinderFactory binderFactory)
            throws Exception {
        Integer uid = (Integer) webRequest.getAttribute(TokenConstant.UID.getV(),
                        RequestAttributes.SCOPE_REQUEST);

        return uid;
    }
}
