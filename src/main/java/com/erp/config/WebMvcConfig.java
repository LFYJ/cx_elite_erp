package com.erp.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.ViewControllerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
public class WebMvcConfig implements WebMvcConfigurer {



    @Bean
    public HandlerInterceptor getAccessLimitIntercept() {
        return new LaterConfig();
    }


    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        //注册自定义拦截器，添加拦截路径和排除拦截路径
/*        registry.addInterceptor(new InterceptorConfig()).addPathPatterns("/api/**").
                excludePathPatterns("/api/login","/api/list/commodity","/api/search/commodity","/api/mistake","/api/istoken");*/
        registry.addInterceptor(getAccessLimitIntercept()).addPathPatterns("/later/**").
                excludePathPatterns("/later","/later/","/later/short/message",
                        "/later/login/message","/later/login","/later/goindex","/later/**");

    }

    @Override
    public void addViewControllers(ViewControllerRegistry registry) {
//            addViewController：对应的地址栏请求
//            setViewName：渲染页面的实际路径
        registry.addViewController("/").setViewName("login");
    }

    /**
     * 静态资源
     * @param registry
     */
    @Override
    public void addResourceHandlers(ResourceHandlerRegistry registry) {
        registry.addResourceHandler("/static/**").addResourceLocations("classpath:/static/");
        registry.addResourceHandler("/templates/**").addResourceLocations("classpath:/templates/");

    }



}
