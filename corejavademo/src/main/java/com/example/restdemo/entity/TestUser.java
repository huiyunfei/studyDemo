package com.example.restdemo.entity;

import lombok.Data;

/**
 * Created by huiyunfei on 2019/4/12.
 */
@Data//lombok注解，可以省略set get等等代码
//@ApiModel(value="实体类描述")
public class TestUser {

    //@ApiModelProperty(value="用户id",example="1",dataType = "Integer",required=false)
    private int id;

    //@ApiModelProperty(value="用户名",example="huiyunfei",dataType = "String",required=true)
    private String username;

    //@ApiModelProperty(value="密码",example="123456",dataType = "String",required=true)
    private String password;


}
