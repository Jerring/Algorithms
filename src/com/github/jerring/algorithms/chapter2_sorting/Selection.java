package com.github.jerring.algorithms.chapter2_sorting;

/**
 * 选择排序：不断选择待排序序列中最小的元素，与待排序序列的第一个元素进行交换，从而完成排序
 * 不稳定，运用 O(1) 额外空间
 */
public class Selection {

    private Selection() {}

    public static void sort(Comparable[] a) {
        int n = a.length;
        for (int i = 0; i < n; ++i) {
            int min = i;
            for (int j = i + 1; j < n; ++j) {
                if (less(a[j], a[min])) {
                    min = j;
                }
            }
            swap(a, i, min);
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
