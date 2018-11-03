package com.github.jerring.algorithms.chapter2_sorting;

/**
 * 插入排序：将待排序元素插入到已排序序列，从而完成排序
 * 稳定，运用 O(1) 额外空间
 */
public class Insection {

    private Insection() {
    }

    public static void sort(Comparable[] a) {
        int n = a.length;
        for (int i = 1; i < n; ++i) {
            // 查找下标 i 之前的元素，如果小于下标 i 的元素则交换两个元素
            for (int j = i; j > 0 && less(a[j], a[j - 1]); --j) {
                swap(a, j, j - 1);
            }
        }
    }

    private static boolean less(Comparable v, Comparable w) {
        return v.compareTo(w) < 0;
    }

    private static void swap(Object[] a, int i, int j) {
        Object t = a[i];
        a[i] = a[j];
        a[j] = t;
    }

}
