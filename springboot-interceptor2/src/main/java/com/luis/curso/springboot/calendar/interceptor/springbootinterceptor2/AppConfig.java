package com.luis.curso.springboot.calendar.interceptor.springbootinterceptor2;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.context.annotation.PropertySources;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
@PropertySources({@PropertySource(value =  "classpath:application.properties"
, encoding = "UTF-8")})
public class AppConfig implements WebMvcConfigurer{

    @Autowired
    @Qualifier("calendarInterceptor")
    private HandlerInterceptor calendar;

    @Override
    public void addInterceptors(InterceptorRegistry registry) {
    registry.addInterceptor(calendar).addPathPatterns("/foo");
    }

    
}
