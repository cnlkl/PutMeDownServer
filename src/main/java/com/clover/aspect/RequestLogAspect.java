package com.clover.aspect;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import javax.servlet.http.HttpServletRequest;
import java.util.Arrays;
import java.util.Enumeration;

/**
 * Created by lkl on 2017/3/22.
 */
@Aspect
@Component
@Order(-5)//设置自动加载的优先级
public class RequestLogAspect {

    private static final Logger logger =
            LoggerFactory.getLogger(RequestLogAspect.class);

    @Pointcut("execution(* com.clover.controller.*.*(..))")
    public void controllerPointcut(){}

    @Before("controllerPointcut()")
    public void doBefore(JoinPoint joinPoint) {

        ServletRequestAttributes attributes =
                (ServletRequestAttributes) RequestContextHolder
                        .getRequestAttributes();

        HttpServletRequest request = attributes.getRequest();

        logger.info("IP: " + request.getRemoteAddr());
        logger.info("URL: " + request.getRequestURL());
        logger.info("URI: " + request.getRequestURI());
        logger.info("HTTP_METHOD: " + request.getMethod());

        //请求的所有参数
        Enumeration<String> enu=request.getParameterNames();
        while(enu.hasMoreElements()){
            String paraName=(String)enu.nextElement();
            logger.info(paraName+": "+request.getParameter(paraName));
        }

        logger.info("CLASS_METHOD: "
                + joinPoint.getSignature().getDeclaringTypeName()
                + "."
                +  joinPoint.getSignature().getName());

        logger.info("ARGS : " + Arrays.toString(joinPoint.getArgs()));
    }

}
