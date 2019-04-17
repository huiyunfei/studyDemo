package com.example.restdemo.web;

import com.bjj.access.modal.ResultObj;
import com.example.restdemo.entity.TestUser;
import com.example.restdemo.listener.MyListener;
import com.example.restdemo.model.TestUserModel;
import com.example.restdemo.service.TestUserService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.SQLException;

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


    @Autowired
    private DataSource dataSource;

    @RequestMapping("/dataSource")
    public String dataSource() {
        try {
            System.out.println("dataSource = "+dataSource);
            Connection conn = dataSource.getConnection();
            System.out.println("conn = "+conn);
            return "success";
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return "end.";
    }

    @RequestMapping("/test")
    public ResultObj test(HttpServletRequest req) {
        String name = req.getParameter("testParameter1");
        System.out.println("name... "+name);
        ResultObj result = new ResultObj();
        result.setInfo(100);
        System.out.println("test... ");
        return result;
    }

    @RequestMapping("/testSession")
    public ResultObj testSession(HttpServletRequest request) {
        System.out.println("当前在线人数："+ MyListener.online);
        ResultObj result = new ResultObj();
        HttpSession session = request.getSession(true);
        session.setAttribute("aa", "bb");
        result.setInfo(100);
        System.out.println("当前在线人数："+ MyListener.online);
        return  result;
    }
}











