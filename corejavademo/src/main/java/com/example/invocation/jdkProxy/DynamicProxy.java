package com.example.invocation.jdkProxy;

import com.example.invocation.Human;
import com.example.invocation.HumanImpl;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;

/**
 * Created by hui.yunfei@qq.com on 2019/4/22
 * jdk动态代理类
 * 1：实现InvocationHandler接口，实现invoke方法
 * 2：提供绑定业务对象（定义private Object target,提供构造器this.target=target）方法和返回代理类方法（可以二合一一个build方法）
 * 3：客户端直接调用build方法传入业务对象（new HumanImpl），获取代理对象Human
 * 4：代理对象直接调用接口方法
 */
public class DynamicProxy implements InvocationHandler {
    private Object target;//这其实业务实现类对象，用来调用具体的业务方法

    /**
     * 绑定业务对象并返回一个代理类
     */
    public Object build(Object target){
        this.target=target;//接收业务实现类对象参数
        //通过反射机制，创建一个代理类对象实例并返回。用户进行方法调用时使用
        //创建代理对象时，需要传递该业务类的类加载器（用来获取业务实现类的元数据，在包装方法是调用真正的业务方法）、接口、handler实现类
        return Proxy.newProxyInstance(target.getClass().getClassLoader(),
                target.getClass().getInterfaces(),
                this);
    }

    @Override
    public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
        System.out.println("预处理操作——————");
        Object object= method.invoke(target,args);
        System.out.println("调用后处理——————");
        return object;
    }



    public static void main(String[] args) {
        DynamicProxy dynamicProxy=new DynamicProxy();
        Human humanProxy= (Human) dynamicProxy.build(new HumanImpl());
//        Human humanProxy= (Human) Proxy.newProxyInstance(
//                human.getClass().getClassLoader(),
//                human.getClass().getInterfaces(),
//                dynamicProxy
//        );



        humanProxy.eat("noods");
    }

}
