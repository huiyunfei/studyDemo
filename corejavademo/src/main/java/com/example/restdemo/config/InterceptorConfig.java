package com.example.restdemo.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;

/**
 * Created by hui.yunfei@qq.com on 2019/4/17
 */
//MyInterceptor中如果使用@Component注解，这里就可以直接注入使用
@Configuration
public class InterceptorConfig extends WebMvcConfigurerAdapter {
//@Autowired
//private MyCglibInterceptor myinterceptor

    @Override
    public void addInterceptors(InterceptorRegistry registry){
        //registry.addInterceptor(new MyCglibInterceptor()).addPathPatterns("/**");
    }

}
