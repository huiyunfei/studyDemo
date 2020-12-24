package com.example.sort;

/**
 * Created by hui.yunfei@qq.com on 2019/12/6
 */
public class FastDemo {


    public static void main(String[] args) {
        int a[] = {6,11,3,9,8,5,7};
        //6 3 5 7 11 9 8
        fastArray(a,0,a.length-1);
    }

    /**
     * @Description:分区方法
     * @Author: HuiYunfei
     * @Date: 2019/12/6
     */
    private static void fastArray(int[] a, int p, int r) {

        int point = a[r];
        int i=p;
        for(int j=p;j<=r;j++){
            if(a[j]<point){
                //交换a[i] 和 a[j]
                int tem = a[i];
                a[i] = a[j];
                a[j] = tem;
                i++;
            }
        }
        int tem2 = a[i];
        a[i]=point;
        a[r]=tem2;

        for(int k=0;k<a.length;k++){
            System.out.print(a[k]+",");
        }
    }

}
