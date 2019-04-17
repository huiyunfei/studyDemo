package com.example.restdemo.filter;

import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Component;

/**
 * Created by hui.yunfei@qq.com on 2019/4/17
 */
@Component
public class MyFilterConfig {
    @Bean
    public FilterRegistrationBean filterOne() {
        FilterRegistrationBean registration = new FilterRegistrationBean(new MyFilterOne());
        registration.addUrlPatterns("/testUser/*"); //
        registration.addInitParameter("testParameter1", "yunfei1"); //
        registration.setName("filterOneName");
        registration.setOrder(1);
        return registration;
    }
    @Bean
    public FilterRegistrationBean filterTwo() {
        FilterRegistrationBean registration = new FilterRegistrationBean(new MyFilterTwo());
        registration.addUrlPatterns("/testUser/*"); //
        registration.addInitParameter("testParameter2", "yunfei2"); //
        registration.setName("filterTwoName");
        registration.setOrder(2);
        return registration;
    }

//    @Bean
//    public ServletListenerRegistrationBean listenerRegist() {
//        ServletListenerRegistrationBean srb = new ServletListenerRegistrationBean();
//        srb.setListener(new MyListener());
//        System.out.println("listener");
//        return srb;
//    }
}
