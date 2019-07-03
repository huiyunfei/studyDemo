package com.example.threaddemo.complicate;

import java.io.File;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.concurrent.ForkJoinPool;
import java.util.concurrent.RecursiveAction;

/**
 * Created by hui.yunfei@qq.com on 2019/7/2
 */
public class ForkJoinTest {
    static class FindDirsFiles extends RecursiveAction {

        private File path;

        public FindDirsFiles(File path){
            this.path=path;
        }

        @Override
        protected void compute() {
            List<FindDirsFiles> list = new ArrayList<>();
            File[] files = path.listFiles();
            if(files!=null){
                for(File file:files){
                    if(file.isDirectory()){
                        list.add(new FindDirsFiles(file));
                    }else{
                        System.out.println("文件："+file.getAbsolutePath());
                    }
                }
                if(!list.isEmpty()){
                    Collection<FindDirsFiles> findDirsFiles = invokeAll(list);
                    for (FindDirsFiles findDirsFile : findDirsFiles) {
                        findDirsFile.join();
                    }
                }
            }

        }

        public static void main(String[] args) {
            ThreadLocal threadLocal=new ThreadLocal();
            ForkJoinPool pool=new ForkJoinPool();
            FindDirsFiles dirsFiles = new FindDirsFiles(new File("E:/"));
            /**异步提交*/
            pool.execute(dirsFiles);
            //同步
            //pool.invoke(dirsFiles);
            //主线程做自己的事情
            for (int i=0;i<100;i++) {
                System.out.println("main do work:"+i);
            }
            try {
                dirsFiles.join();//阻塞方法
            } catch (Exception e) {
                e.printStackTrace();
            }
            System.out.println("main do work end");

        }
    }


}























