package com.example.restdemo.model;

import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * Created by huiyunfei on 2019/4/12.
 */
@Data
@Builder//为类提供一个内部的Builder
@AllArgsConstructor
@NoArgsConstructor
//@ApiModel(value="好像是返回对象的描述")
public class TestUserModel {

    @ApiModelProperty(value="用户id",example="1",dataType = "Integer",required=false)
    private int id;

    @ApiModelProperty(value="用户名",example="huiyunfei",dataType = "String",required=true)
    private String username;

    @ApiModelProperty(value="密码",example="123456",dataType = "String",required=true)
    private String password;


}
