package com.example.sort;

/**
 * Created by hui.yunfei@qq.com on 2019/12/6
 */
public class CountDemo {
    // 计数排序，a 是数组，n 是数组大小。假设数组中存储的都是非负整数。
    public void countingSort(int[] a, int n) {
        if (n <= 1) {
            return;
        }

        // 查找数组中数据的范围
        int max = a[0];
        for (int i = 1; i < n; ++i) {
            if (max < a[i]) {
                max = a[i];
            }
        }

        int[] c = new int[max + 1]; // 申请一个计数数组 c，下标大小 [0,max]
        for (int i = 0; i <= max; ++i) {
            c[i] = 0;
        }

        // 计算每个元素的个数，放入 c 中
        for (int i = 0; i < n; ++i) {
            c[a[i]]++;
        }

        // 依次累加
        for (int i = 1; i <= max; ++i) {
            c[i] = c[i-1] + c[i];
        }

        // 临时数组 r，存储排序之后的结果
        int[] r = new int[n];
        // 计算排序的关键步骤，有点难理解
        for (int i = n - 1; i >= 0; --i) {
            int index = c[a[i]]-1;
            r[index] = a[i];
            c[a[i]]--;
        }

        // 将结果拷贝给 a 数组
        for (int i = 0; i < n; ++i) {
            a[i] = r[i];
        }
    }

}
