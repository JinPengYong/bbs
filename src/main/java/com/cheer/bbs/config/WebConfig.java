package com.cheer.bbs.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
public class WebConfig implements WebMvcConfigurer {
    @Autowired // 注入spring容器
    private ApplicationContext applicationContext;


    // 处理静态资源css/js/image
    @Override
    public void addResourceHandlers(ResourceHandlerRegistry registry) {
        //将/**地址映射为file:C:/Users/Administrator/avatar地址
        registry.addResourceHandler("/**").addResourceLocations("file:C:/Users/Administrator/avatar");
        registry.addResourceHandler("/upload/**").addResourceLocations("/upload/");

    }

}