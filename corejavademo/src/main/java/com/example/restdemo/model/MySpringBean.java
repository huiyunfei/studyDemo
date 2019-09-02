package com.example.restdemo.model;

import org.springframework.beans.BeansException;
import org.springframework.beans.factory.BeanFactory;
import org.springframework.beans.factory.BeanFactoryAware;
import org.springframework.beans.factory.BeanNameAware;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.stereotype.Component;

import java.util.Objects;

/**
 * Created by hui.yunfei@qq.com on 2019/7/19
 */
@Component
public class MySpringBean implements BeanNameAware, BeanFactoryAware, InitializingBean, ApplicationContextAware {

    private ApplicationContext applicationContext;

    public void init() {
        System.out.println("MySpringBean init-method ing......");
    }
    @Override
    public void setBeanFactory(BeanFactory beanFactory) throws BeansException {
        System.out.println("BeanFactoryAware setBeanFaction ing...");
    }

    @Override
    public void setBeanName(String s) {
        System.out.println("BeanNameAware setBeanName ing...");
    }

    @Override
    public void afterPropertiesSet() throws Exception {
        System.out.println("InitializingBean afterPropertiesSet ing...");

    }

    @Override
    public void setApplicationContext(ApplicationContext applicationContext) throws BeansException {
        System.out.println("applicationContext setApplicationContext ing...");
        this.applicationContext = applicationContext;
    }


}










