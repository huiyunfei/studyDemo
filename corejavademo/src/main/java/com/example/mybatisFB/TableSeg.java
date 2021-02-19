package com.example.mybatisFB;

import java.lang.annotation.*;

@Target({ElementType.TYPE, ElementType.METHOD})
@Retention(RetentionPolicy.RUNTIME)
@Documented
@Inherited
public @interface TableSeg {
    //表名
    public String tableName() default "tb_shop_order";

    // 分表方式
    public String shardType() default "";

    //根据什么字段分表 ,多个字段用逗号,隔开
    public String shardBy();


}
