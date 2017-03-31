package com.clover.config;

import com.clover.interceptor.AuthInterceptor;
import com.clover.resolver.UserMethodArgResolver;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.web.PageableHandlerMethodArgumentResolver;
import org.springframework.web.method.support.HandlerMethodArgumentResolver;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurationSupport;

import java.util.List;

/**
 * Created by lkl on 2017/3/22.
 */
@Configuration
public class ServletContextConfig extends WebMvcConfigurationSupport{

    @Bean
    public AuthInterceptor authInterceptor() {
        return new AuthInterceptor();
    }

    @Bean
    public UserMethodArgResolver userMethodArgResolver() {
        return new UserMethodArgResolver();
    }

    @Bean
    public PageableHandlerMethodArgumentResolver pageableHandlerMethodArgumentResolver () {
        return new PageableHandlerMethodArgumentResolver();
    }

    @Override
    protected void addArgumentResolvers(List<HandlerMethodArgumentResolver> argumentResolvers) {
        argumentResolvers.add(userMethodArgResolver());
        //需要添加才能自动将request中的参数转换成pageable
        argumentResolvers.add(pageableHandlerMethodArgumentResolver());
    }

    @Override
    protected void addInterceptors(InterceptorRegistry registry) {
        registry.addInterceptor(authInterceptor()).addPathPatterns("/**");
    }

}
