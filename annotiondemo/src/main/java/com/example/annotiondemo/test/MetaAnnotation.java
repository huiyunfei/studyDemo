package com.example.annotiondemo.test;

/**
 * 元注解类（注解的注解）
 * Created by huiyunfei on 2019/4/11.
 */
public @interface MetaAnnotation {

//  如果一个注解中有一个名称为value的属性，且你只想设置value属性(即其他属性都采用默认值或者你只有一个value属性)，那么可以省略掉“value=”部分。
//  例如：@MetaAnnotation("哈哈哈")
    String value();//设置唯一的属性
}
