package com.example.annotiondemo.demo;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.Arrays;

/**
 * 检查一下注解是否起作用
 * Created by huiyunfei on 2019/4/11.
 */
public class CheckTest {

    public static void main(String[] args) {

        Method[] m = MyMethod.class.getDeclaredMethods();
        Arrays.stream(m).forEach(method -> {
            // 只有被 @CheckInterface 标注过的方法才为TRUE
            if(method.isAnnotationPresent(CheckInterface.class)){
                System.out.println("需要检查的方法名为："+method.getName());
                method.setAccessible(true);
                try {
                    //动态执行当前方法，标准写法方法类对象，方法参数
                    method.invoke(new MyMethod(), new Object[]{"哈哈哈"});
                } catch (IllegalAccessException e) {
                    e.printStackTrace();
                } catch (InvocationTargetException e) {
                    e.printStackTrace();
                }
            }else{
                System.out.println("不需要检查的方法名为："+method.getName());
            }
        });
    }

}





