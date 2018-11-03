package com.github.jerring.algorithms.chapter2_sorting;

/**
 * 自低向上的归并排序：把多个小数组归并排序，从而完成整个数组的排序
 * 稳定，运用 O(n) 额外空间
 */
public class MergeBU {

    private MergeBU() {}

    public static void sort(Comparable[] a) {
        int n = a.length;
        Comparable[] aux = new Comparable[n];   // 一次分配额外空间，避免产生多个小数组
        for (int len = 1; len < n; len += len) {
            for (int lo = 0; lo < n - len; lo += len + len) {
                int mid = lo + len - 1;
                int hi = Math.min(lo + len + len - 1, n - 1);
                merge(a, aux, lo, mid, hi);
            }
        }
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
