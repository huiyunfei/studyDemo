package com.example.annotiondemo.test;

import java.util.Arrays;

/**
 * 枚举类
 * Created by huiyunfei on 2019/4/11.
 */
public enum ColorEnum {
    RED(1,"红色"),YELLOW(2,"黄色"),BLUE(3,"蓝色");

    public int code;

    public String value;

    ColorEnum(int code, String value) {
        this.code = code;
        this.value = value;
    }

    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }

    public String getValue() {
        return value;
    }

    public void setValue(String value) {
        this.value = value;
    }

    //method getValueByCode
    public static ColorEnum getByCode(int code){
        return Arrays.asList(ColorEnum.values()).stream().filter(
                x->x.code==code
        ).findFirst().get();
    }
//    在java8中，我们可以这么玩
//
//1，查找集合中的第一个对象。
//
//    Optional<A> firstA= AList.stream() .filter(a -> "hanmeimei".equals(a.getUserName())) .findFirst();
//    关于Optional，java API中给了解释。
//
//    A container object which may or may not contain a non-null value. If a value is present, isPresent() will return true and get() will return the value.
//    所以，我们可以这样子使用
//
//            复制代码
//if (firstA.isPresent()) {
//        A a = firstA.get();   //这样子就取到了这个对象呢。
//    }
//else {
//        //没有查到的逻辑
//    }
//    复制代码
//2，如果想返回集合呢。可是使用这个
//
//    List<A> firstA= AList.stream() .filter(a -> "hanmeimei".equals(a.getUserName())) .collect(Collectors.toList());
//3,抽取对象中所有的id的集合
//
//1
//    List<Long> idList = AList.stream.map(A::getId).collect(Collectors.toList());
    public static void main(String[] args) {
        //测试枚举自定义方法的使用
        ColorEnum c=ColorEnum.getByCode(1);
        System.out.println(c.value);
    }
}
