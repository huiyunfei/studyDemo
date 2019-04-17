package com.example.restdemo.listener;

import org.springframework.stereotype.Component;

import javax.servlet.annotation.WebListener;
import javax.servlet.http.HttpSessionEvent;
import javax.servlet.http.HttpSessionListener;

/**
 * Created by hui.yunfei@qq.com on 2019/4/17
 */
@Component
@WebListener
public class MyListener implements HttpSessionListener {
    public static int online = 0;

    @Override
    public void sessionCreated(HttpSessionEvent se) {
        System.out.println("创建session");
        online ++;
    }

    @Override
    public void sessionDestroyed(HttpSessionEvent se) {
        System.out.println("销毁session");

    }

}
