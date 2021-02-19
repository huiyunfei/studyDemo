package com.example.mybatisFB;

import com.alibaba.fastjson.JSONObject;
import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.stereotype.Component;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import javax.servlet.http.HttpServletRequest;

/**
 * @Description:拦截请求，把apiVersion丢入ThreadLocal
 * @Author: HuiYunfei
 * @Date: 2020/7/3
 */
@Slf4j
@Aspect
@Component
public class ApiVersionAspect {

    // 记录当前线程版本号
    public final static ThreadLocal<String> API_VERSION = new ThreadLocal<String>();

    @Pointcut("execution(public * com.fhgl.shop.controller..*.*(..))")
    public void pointCut() {

    }


    @Before("pointCut()")
    public void controller(JoinPoint point) {
        ServletRequestAttributes attributes = (ServletRequestAttributes) RequestContextHolder.getRequestAttributes();
        if (attributes != null) {
            HttpServletRequest request = attributes.getRequest();
            try {
                String parameter = request.getParameter("params");
                JSONObject jsonObject = JSONObject.parseObject(parameter);
                if (jsonObject != null) {
                    this.API_VERSION.set(jsonObject.getString("apiVersion"));
                }
            } catch (Exception e) {
                log.error("apiVersion切面获取参数失败，{}", e);
            }
        }
    }

}
