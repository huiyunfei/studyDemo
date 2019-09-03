//package com.example.restdemo.web.swagger;
//
//import com.alibaba.fastjson.JSONObject;
//import com.bjj.access.modal.ResultObj;
//import com.example.restdemo.entity.TestGroup;
//import com.example.restdemo.entity.TestUser;
//import com.example.restdemo.model.TestUserModel;
//import com.example.restdemo.service.TestUserService;
//import io.swagger.annotations.Api;
//import io.swagger.annotations.ApiImplicitParam;
//import io.swagger.annotations.ApiImplicitParams;
//import io.swagger.annotations.ApiOperation;
//import lombok.extern.slf4j.Slf4j;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.web.bind.annotation.*;
//import springfox.documentation.annotations.ApiIgnore;
//
//import java.util.List;
//
///**
// * Created by hui.yunfei@qq.com on 2019/4/18
// */
//@Slf4j
//@RestController
//@RequestMapping("/testSwagger")
//@Api(description = "测试swagger接口")
//public class SwaggerTestWeb {
//
//
//
//    @Autowired
//    private TestUserService testUserService;
//
//    /**
//     * @Description:参数连接在请求后xxx/id，paramType要用path，直接使用PathVariable注解接收
//     * @Author:hui.yunfei@qq.com
//     * @Date: 2019/4/19
//     */
//    @ApiOperation(value = "根据id获取用户信息",notes  ="测试path上链接参数和header")
//    @ApiImplicitParams({
//            @ApiImplicitParam(name = "id", value = "用户id", paramType = "path", required = true, dataType = "Integer"),
//            @ApiImplicitParam(name = "token", value = "请求token", required = true, paramType = "header")
//    })
//    @RequestMapping(value="/findById/{id}",method= RequestMethod.GET)
//    public TestUser findById(@PathVariable(value="id") Integer id,@RequestHeader String token){
//        log.info("findById in id:{},header token:{} ",id,token);
//        return testUserService.findById(id);
//    }
//
//    /**
//     * @Description:参数连接在请求后拼接的get请求xxx?id=，paramType要用query，使用RequestParam注解
//     * @Author:hui.yunfei@qq.com
//     * @Date: 2019/4/19
//     */
//    @ApiOperation(value = "根据name获取用户信息",notes  ="测试get请求")
//    @ApiImplicitParam(name = "username", value = "用户名", paramType = "query", required = true, dataType = "String")
//    @RequestMapping(value="/findByName",method= RequestMethod.GET)
//    public ResultObj findByName(@RequestParam(value="username") String username){
//        log.info("findByName in：{} ",username);
//        ResultObj obj=new ResultObj();
//        obj.setInfo(200);
//        return obj;
//    }
//
//    /**
//     * @Description:参数连接在请求后拼接的post请求xxx?id=，paramType要用query，使用RequestParam注解，支持非必传
//     * @Author:hui.yunfei@qq.com
//     * @Date: 2019/4/19
//     */
//    @ApiOperation(value = "根据用户名和密码获取用户信息",notes  ="测试requestParam参数post请求")
//    @ApiImplicitParams({
//            @ApiImplicitParam(name = "username", value = "用户名",required = true, dataType = "String", paramType = "query"),
//            @ApiImplicitParam(name = "password", value = "密码",required = false, dataType = "String", paramType = "query")
//    })
//    @RequestMapping(value="/findByUsernameAndPassword",method= RequestMethod.POST)
//    public ResultObj findByUsernameAndPassword(@RequestParam(value="username") String username,@RequestParam(value="password",required = false) String password){
//        log.info("findByUsername in：{} ",username,password);
//        //return testUserService.findById(username,);
//        ResultObj obj=new ResultObj();
//        obj.setInfo(200);
//        return obj;
//    }
//
//    /**
//     * @Description:参数在请求体中的post请求，paramType要用query，使用RequestBody注解
//     * @Author:hui.yunfei@qq.com
//     * @Date: 2019/4/19
//     */
//    @ApiOperation(value = "新增用户",notes = "测试post对象请求")
//    @ApiImplicitParam(name = "testUserModel", value = "用户实体对象", required = true, dataType = "TestUserModel")
//    @RequestMapping(value="/insert",method= RequestMethod.POST)
//    public ResultObj insert(@RequestBody TestUserModel testUserModel){
//        log.info("insert user：{} ",testUserModel.toString());
//        ResultObj obj=new ResultObj();
//        obj.setInfo(200);
//        return obj;
//    }
//
//    @ApiIgnore//使用该注解忽略这个API
//    @RequestMapping(value="/testMybatisCollection",method=RequestMethod.POST)
//    public ResultObj testMybatisCollection(@RequestBody JSONObject obj) {
//        ResultObj result = new ResultObj();
//        List<TestGroup> groupList=this.testUserService.findGroupById(obj.getIntValue("groupNo"));
//        result.setInfo(100);
//        result.setObj(groupList);
//        return result;
//    }
//}
