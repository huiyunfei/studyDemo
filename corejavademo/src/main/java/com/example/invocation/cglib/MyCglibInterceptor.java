package com.example.invocation.cglib;

import org.springframework.cglib.proxy.Enhancer;
import org.springframework.cglib.proxy.MethodInterceptor;
import org.springframework.cglib.proxy.MethodProxy;

import java.lang.reflect.Method;


/**
 * cglib动态代理类
 * 可以代理非实现接口类
 * final修饰的方法不能被代理
 * Created by hui.yunfei@qq.com on 2019/4/22
 * 1：实现MethodInterceptor接口，intercept
 * 2：提供绑定业务对象（定义private Object target,提供构造器this.target=target）方法和返回代理类方法（可以二合一一个getInstance方法）
 * 3：客户端直接调用getInstance方法传入业务对象（new Person），获取代理对象Person
 * 4：代理对象直接调用接口方法
 */
public class MyCglibInterceptor implements MethodInterceptor {

    private Object target;//业务对象
    //相当于JDK动态代理中的绑定
    public Object getInstance(Object target){
        //绑定业务对象
        this.target=target;
        //创建加强器，用来创建动态代理类
        Enhancer enhancer = new Enhancer();
        //为加强器指定要代理的业务类（即：为下面生成的代理类指定父类）
        enhancer.setSuperclass(this.target.getClass());
        //设置回调：对于代理类上所有方法的调用，都会调用CallBack，而Callback则需要实现intercept()方法进行拦
        enhancer.setCallback(this);
        // 创建动态代理类对象并返回
        return enhancer.create();
    }

    /**
     * 实现方法回调
     * o: cglib生成的代理对象
     * method: 目标方法
     * objects: 目标方法的参数
     * methodProxy:代理方法
     */
    @Override
    public Object intercept(Object o, Method method, Object[] objects, MethodProxy methodProxy) throws Throwable {
        //这里调用切面方法before
        System.out.println("方法执行前");
        //当代理对象调用真实对象的方法时，其会自动的跳转到代理对象关联的handler对象的invoke方法来进行调用
        Object object = method.invoke(this.target,objects);//调用目标类的目标方法
        System.out.println("方法执行后");
        //这里调用切面方法after
        return object;
    }

    public static void main(String[] args) {
        MyCglibInterceptor myInterceptor = new MyCglibInterceptor();
        Person personProxy = (Person) myInterceptor.getInstance(new Person());
        personProxy.eat("noods");
        personProxy.sayOthers("1");
//        //实践证明final方法没有被代理
//        方法执行前
//        i'm a person ,i'm eatting noods
//                方法执行后
//        i'm a person ,i'm eatting other 1
    }
}




