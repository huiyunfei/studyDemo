package com.example.restdemo.model;

import org.springframework.beans.BeansException;
import org.springframework.beans.factory.config.BeanPostProcessor;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.stereotype.Component;

/**
 * Created by hui.yunfei@qq.com on 2019/7/19
 */
@Component
public class MySpringBeanPostProcessor implements BeanPostProcessor {
    @Override
    public Object postProcessAfterInitialization(Object bean, String beanName) throws BeansException {
        if (bean instanceof MySpringBean) {
            System.out.println("BeanPostProcessor-postProcessAfterInitialization......");
        }
        return bean;
    }

    @Override
    public Object postProcessBeforeInitialization(Object bean, String beanName) throws BeansException {
        if (bean instanceof MySpringBean) {
            System.out.println("BeanPostProcessor-postProcessBeforeInitialization......");
        }
        return bean;
    }

    public static void main(String[] args) {
        ApplicationContext context = new ClassPathXmlApplicationContext("applicationContext.xml");
        //Student student = (Student) context.getBean("student");
        MySpringBean bean=new MySpringBean();
    }
}