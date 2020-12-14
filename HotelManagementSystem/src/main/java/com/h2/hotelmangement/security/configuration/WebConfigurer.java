package com.h2.hotelmangement.security.configuration;

import com.h2.hotelmangement.security.interceptor.FormatResponseDataInterceptor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@EnableWebMvc
@Configuration
public class WebConfigurer implements WebMvcConfigurer {

    @Autowired
    private FormatResponseDataInterceptor formatResponseDataInterceptor;

    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        registry.addInterceptor(formatResponseDataInterceptor);
    }
}
