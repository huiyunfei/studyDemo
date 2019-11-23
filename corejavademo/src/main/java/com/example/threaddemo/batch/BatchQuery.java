package com.example.threaddemo.batch;

/**
 * Created by hui.yunfei@qq.com on 2019/11/23
 */
public class BatchQuery {

    public static void main(String[] args) {

        int id = 0;
        int pageNow = 0;
        int pageSize = 10;


        // 第一批
//        List  list = queryList(id, pageNow, pageSize);
//        Integer i = 0;
//        while (i < list.size()){
//            try {
//                // doSmthing
//
//                // 下一批
//                if(i.equals(list.size() - 1)){
//                    id = list.get(list.size() - 1).getId();
//                    list = queryList(id, pageNow, pageSize);
//                    i = -1;
//                }
//            } catch (Exception e){
//                e.printStackTrace();
//                break;
//            } finally {
//                i++;
//            }
//        }
    }
}
