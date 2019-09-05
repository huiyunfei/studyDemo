package com.example.rpc;

/**
 * Created by hui.yunfei@qq.com on 2019/9/5
 */
public class RpcProvider {

    public static void main(String[] args) throws Exception {
        HelloService service = new HelloServiceImpl();
        RpcFramework.export(service, 1234);
    }
}
