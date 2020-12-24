package com.example.sort;

/**
 * Created by hui.yunfei@qq.com on 2019/12/5
 */
public class InsertDemo {
    // 插入排序，a 表示数组，n 表示数组大小
    public static void insertionSort(int[] a, int n) {
        if (n <= 1) {
            return;
        }

        for (int i = 1; i < n; ++i) {
            int value = a[i];
            int j = i - 1;
            // 查找插入的位置
            for (; j >= 0; --j) {
                if (a[j] > value) {
                    a[j+1] = a[j];  // 数据移动
                } else {
                    break;
                }
            }
            a[j+1] = value; // 插入数据
        }
    }


    public static void main(String[] args) {
        int[] a={4,5,6,1,3,2};
        insertionSort(a,6);
    }
}
