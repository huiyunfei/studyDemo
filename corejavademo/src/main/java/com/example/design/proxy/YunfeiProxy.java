package com.example.design.proxy;


import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;

/**
 * Created by hui.yunfei@qq.com on 2019/9/24
 */
public class YunfeiProxy implements InvocationHandler {

    private Object obj;

    public Object Build(Object obj) {
        this.obj = obj;

        return  Proxy.newProxyInstance(obj.getClass().getClassLoader(),obj.getClass().getInterfaces(),this);
    }

    @Override
    public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
        System.out.println(" before ");
        return method.invoke(obj,args);
    }

    public static void main(String[] args) {
        YunfeiProxy proxy = new YunfeiProxy();
        User yunfei = (User) proxy.Build(new Yunfei());
        yunfei.eat("aaa");
    }
}


