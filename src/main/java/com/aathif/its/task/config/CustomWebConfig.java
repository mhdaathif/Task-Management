package com.aathif.its.task.config;

import com.aathif.its.task.dto.RequestMetaDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.context.annotation.RequestScope;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;

@Configuration
public class CustomWebConfig extends WebMvcConfigurerAdapter {
    @Autowired
    JwtInterceptorConfig jwtInterceptorConfig;
    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        registry.addInterceptor(jwtInterceptorConfig);
    }

    @Bean
    @RequestScope
    public RequestMetaDTO requestMetaDTO(){
        return new RequestMetaDTO();
    }

}
