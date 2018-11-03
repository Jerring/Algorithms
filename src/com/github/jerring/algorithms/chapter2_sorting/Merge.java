package com.github.jerring.algorithms.chapter2_sorting;

/**
 * 自顶向下的归并排序：把待排序数组分为两个子数组（两个子数组可以递归再分），对这两个字数组进行排序，再合并为一个数组
 * 稳定，运用 O(n) 额外空间
 */
public class Merge {

    private Merge() {}

    public static void sort(Comparable[] a) {
        int n = a.length;
        Comparable[] aux = new Comparable[n];   // 一次分配额外空间，避免产生多个小数组
        sort(a, aux, 0, n - 1);
    }

    private static void sort(Comparable[] a, Comparable[] aux, int lo, int hi) {
        if (lo >= hi) {
            return;
        }
        int mid = lo + (hi - lo) / 2;
        sort(a, aux, lo, mid);
        sort(a, aux, mid + 1, hi);
        merge(a, aux, lo, mid, hi);
    }

    private static void merge(Comparable[] a, Comparable[] aux, int lo, int mid, int hi) {
        // 将 a[lo..hi] 复制到 aux[lo..hi]
        for (int k = lo; k <= hi; k++) {
            aux[k] = a[k];
        }

        // 归并到 a[lo..hi]
        int i = lo, j = mid + 1;
        for (int k = lo; k <= hi; k++) {
            if (i > mid) {              // 只剩下 a[j..hi]
                a[k] = aux[j++];
            } else if (j > hi) {        // 只剩下 a[i..mid]
                a[k] = aux[i++];
            } else if (less(aux[j], aux[i])) {
                a[k] = aux[j++];
            } else {
                a[k] = aux[i++];
            }
        }
    }

    private static boolean less(Comparable v, Comparable w) {
        return v.compareTo(w) < 0;
    }
}
