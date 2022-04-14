package com.springboot.config;

import com.springboot.interceptor.LoginInterceptor;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

/**
 * @author 申浩东
 * Configuration  定义一个配置类,所以此类会被spring容器扫描到,实现 WebMvcConfigurer  设置该类为一个拦截器
 */
//@Configuration
public class MyIntercept implements WebMvcConfigurer {
    /**
     * addInterceptor  拦截器工厂添加一个拦截器
     * addPathPatterns  拦截哪些路径下
     * excludePathPatterns  放行哪些请求路径
     * addPathPatterns   //所有请求都会被拦截,包括静态资源
     * excludePathPatterns  //放行的请求(包括,静态资源放行,或者是静态资源再)
     * @param registry
     */
    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        InterceptorRegistration interceptorRegistration = registry.addInterceptor(new LoginInterceptor())
                .addPathPatterns("/**")
                .excludePathPatterns("/", "/login", "/css/**", "/fonts/**", "/images/**", "/js/**","/hello","/classpath");

    }
}
