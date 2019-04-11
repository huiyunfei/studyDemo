package com.example.annotiondemo.test;

/**
 * Created by huiyunfei on 2019/4/11.
 */

@MyAnnotation(value = "我是MyAnnotation的value值",name = "云飞",color = ColorEnum.BLUE,metaAnnotation = @MetaAnnotation("我是元注解的value值"))
public class AnnotationTest {

    public static void main(String[] args) {

        System.out.println("-------------自定义注解使用start----");
        MyAnnotation myAnnotation=AnnotationTest.class.getAnnotation(MyAnnotation.class);
        System.out.println("color的code:"+myAnnotation.color().code+"，color的value:"+myAnnotation.color().value);
        System.out.println("value值："+myAnnotation.value());
        System.out.println("name值："+myAnnotation.name());
        System.out.println("meta元注解的值："+myAnnotation.metaAnnotation().value());
        System.out.println("array的lenth:"+myAnnotation.array().length);

    }
}
