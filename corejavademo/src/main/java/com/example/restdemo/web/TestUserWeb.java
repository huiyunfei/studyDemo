package com.example.restdemo.web;

import com.bjj.access.modal.ResultObj;
import com.example.restdemo.entity.TestUser;
import com.example.restdemo.model.TestUserModel;
import com.example.restdemo.service.TestUserService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

/**
 * Created by huiyunfei on 2019/4/12.
 */
@Slf4j
@RestController
@RequestMapping("/testUser")
public class TestUserWeb {


    @Autowired
    private TestUserService testUserService;

    @RequestMapping("/findById")
    public TestUser findById(@RequestParam int id){
        log.info("findById in：{} ",id);
        return testUserService.findById(id);
    }



    @RequestMapping("/testTrancation")
    public ResultObj testTrancation(@RequestBody TestUserModel userModel){

        ResultObj result = new ResultObj();
        try {
            testUserService.testTrancation(userModel);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return result;
    }

}











