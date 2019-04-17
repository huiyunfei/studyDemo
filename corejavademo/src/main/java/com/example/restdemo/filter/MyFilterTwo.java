package com.example.restdemo.filter;

import lombok.extern.slf4j.Slf4j;

import javax.servlet.*;
import javax.servlet.http.HttpServletRequest;
import java.io.IOException;

/**
 * Created by hui.yunfei@qq.com on 2019/4/15
 */
@Slf4j
//@Component
//@WebFilter(urlPatterns = "/testUser/*", filterName = "filterTwo")
public class MyFilterTwo implements Filter {
    @Override
    public void init(FilterConfig filterConfig) throws ServletException {
        log.info("MyFilterTwo 初始化");
        log.info("MyFilterOne 初始化 参数："+filterConfig.getInitParameter("testParameter2"));
    }

    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
        log.info("MyFilterTwo 请求处理");
        //对request、response进行一些预处理
        // 比如设置请求编码
        // request.setCharacterEncoding("UTF-8");
        // response.setCharacterEncoding("UTF-8");
        //TODO 进行业务逻辑
        HttpServletRequest req=(HttpServletRequest)servletRequest;
        String uri=req.getRequestURI();
        log.info("MyFilterTwo当前请求uri {}",uri);
        String name = req.getParameter("testParameter2");
        log.info("MyFilterOne name{}",name);
        //链路 直接传给下一个过滤器
        filterChain.doFilter(servletRequest, servletResponse);
    }

    @Override
    public void destroy() {
        log.info("MyFilterTwo 销毁");
    }
}
