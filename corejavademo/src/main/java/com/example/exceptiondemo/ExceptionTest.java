package com.example.exceptiondemo;

/**
 * Created by huiyunfei on 2019/4/12.
 */
public class ExceptionTest {

    public String test2() throws Exception {
        throw new Exception("自定义异常");
    }

    public static void main(String[] args) {
        ExceptionTest exceptionTest=new ExceptionTest();
        try{
            System.out.println("AAAAAAA");
            //testWeb.test2();
            try{
                //testWeb.test2();
                System.out.println("BBBBBBBBBBBB");

                try {
                    exceptionTest.test2();
                } catch (Exception e) {
                    System.out.println("FFFFFFFFFFFFFFFF");
                }
            }catch(Exception e) {
                System.out.println("CCCCCCCCCCCCCCCCC");
            }

            System.out.println("DDDDDDDDDDDDDDDDD");
        }
        catch(Exception e){
            System.out.println("EEEEEEEEEEEEEEEEEEEEE");
        }
    }



}
