package com.lens.web;

import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

public class CORSConfig  implements WebMvcConfigurer {
    @Override
    public void addCorsMappings(CorsRegistry registry){
        registry.addMapping("/**")  //允许所有的访问请求（访问路径）
                .allowedMethods("*")   //允许所有的请求方法访问该跨域资源
                .allowedOrigins("*")  //允许所有的请求域名访问我们的跨域资源
                .allowedHeaders("*"); //允许所有的请求header访问

    }
}
