package com.github.jerring.algorithms.chapter2_sorting;

/**
 * 堆排序
 * 说明：本实现采用待排序数组下标从 0 开始， 堆的下标从 1 开始（也可以改为从 0 开始）。
 * 不稳定，运用 O(1) 额外空间
 */
public class Heap {

    private Heap() {}

    public static void sort(Comparable[] a) {
        int n = a.length;
        for (int k = n / 2; k >= 1; --k) {
            sink(a, k, n);
        }
        while (n > 1) {
            swap(a, 1, n--);
            sink(a, 1, n);
        }
    }

    private static void sink(Comparable[] a, int k, int n) {
        while (2 * k <= n) {
            int j = 2 * k;
            if (j < n && less(a, j, j + 1)) {
                ++j;
            }
            if (!less(a, k, j)) {
                break;
            }
            swap(a, k, j);
            k = j;
        }
    }

    private static boolean less(Comparable[] a, int i, int j) {
        return a[i - 1].compareTo(a[j - 1]) < 0;
    }

    private static void swap(Object[] a, int i, int j) {
        Object t = a[i - 1];
        a[i - 1] = a[j - 1];
        a[j - 1] = t;
    }
}
