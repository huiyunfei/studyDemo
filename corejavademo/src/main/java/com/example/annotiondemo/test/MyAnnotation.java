package com.example.annotiondemo.test;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * 自定义注解类
 * Created by huiyunfei on 2019/4/11.
 */
@Retention(RetentionPolicy.RUNTIME)//标示该注解的生命周期是运行时
@Target({ElementType.ANNOTATION_TYPE,ElementType.METHOD,ElementType.TYPE})//标示该注解可以用于注解类（被@interface修饰的类）上和类和方法上，不添加注解默认可以用于所有地方
public @interface MyAnnotation {
    String name() default "yunfei";//设置默认值yunfei的字符串属性

    String value();//如果一个注解中只有一个value属性要设置，可以直接类似@MyAnnotation("hahah")来设置

    int[] array() default {1,2,3};//设置数组

    ColorEnum color() default ColorEnum.RED;//设置枚举

    MetaAnnotation metaAnnotation() default @MetaAnnotation("哦哦");


}





