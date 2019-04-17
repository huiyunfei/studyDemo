package com.example;

import com.example.restdemo.filter.MyFilterOne;
import com.example.restdemo.filter.MyFilterTwo;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.transaction.annotation.EnableTransactionManagement;

@MapperScan("com.example.restdemo.mapper")//此处添加注解后dao接口无需再添加@mapper @repository注解
@SpringBootApplication
@EnableTransactionManagement // 如果mybatis中service实现类中加入事务注解，需要此处添加该注解
public class CorejavademoApplication {

    public static void main(String[] args) {
        SpringApplication.run(CorejavademoApplication.class, args);
    }

}
