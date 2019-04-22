package com.example;

import com.example.restdemo.model.TestUserModel;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.*;


/**
 * @Description:单元测试路径要与主程序目录一致
 * 单元测试可以直接设置参数
 * @Author:hui.yunfei@qq.com
 * @Date: 2019/4/15
 */
@RunWith(SpringRunner.class)
@SpringBootTest(value={"DEPLOY=dev"})
//@ContextConfiguration(locations = {"classpath:application*.yml"})
public class AnnotiondemoApplicationTests {

    @Test
    public void contextLoads() {
        System.out.println(123);
    }

    @Test
    public void Builder() {

        TestUserModel user=new TestUserModel().builder().id(1).username("yunfei").password("123").build();
        System.out.println(user.getId()+user.getUsername());
    }

    @Test
    public void testForeach(){
        List<String> list =Arrays.asList("aaa","fsa","ser","eere");
        Collections.sort(list, new Comparator<String>() {
            @Override
            public int compare(String o1, String o2) {
                return o2.compareTo(o1);
            }
        });
        for (String string : list) {
            System.out.println(string);
        }
    }

    @Test
    public void testaaa(){

        List<Integer> num = new ArrayList<Integer>();
        for(int i = 0; i < 10; i++) {
            num.add(i);
        }
        //输出排序结果
        System.out.println(num);
        //compare(x,y) x>y正整数-->升序
        num.sort(new Comparator<Integer>() {
            @Override
            public int compare(Integer o1, Integer o2) {
                return o1-o2;
            }
        });
        System.out.println(num);
    }

}
