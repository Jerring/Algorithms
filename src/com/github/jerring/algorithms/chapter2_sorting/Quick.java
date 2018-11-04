package com.github.jerring.algorithms.chapter2_sorting;

/**
 * 快速排序：将数组分为两个子数组，将两部分独立地排序
 * 不稳定，运用 O(lgn) 额外空间（主要是递归时栈的空间开销）
 */
public class Quick {

    private Quick() {}

    public static void sort(Comparable[] a) {
        sort(a, 0, a.length - 1);
    }

    private static void sort(Comparable[] a, int lo, int hi) {
        if (hi <= lo) {
            return;
        }
        int j = partition(a, lo, hi);
        sort(a, lo, j - 1);
        sort(a, j + 1, hi);
    }

    /**
     * 分割方法，获得切分下标
     * 这种分割方法在有大量重复元素时算法效率较低
     * @param a     待分割数组
     * @param lo    待分割数组的开始下标
     * @param hi    待分割数组的结束下标
     * @return      切分下标
     */
    private static int partition(Comparable[] a, int lo, int hi) {
        int i = lo;
        int j = hi + 1;
        Comparable v = a[lo];
        while (true) {
            // 从左往右找到第一个大于等于 v 的位置
            while (less(a[++i], v)) {
                if (i == hi) {
                    break;
                }
            }
            // 从右往左找到第一个小于等于 v 的位置
            while (less(v, a[--j])) {
                if (j == lo) {      // 可省略（a[lo] 可充当哨兵）
                    break;
                }
            }
            if (i >= j) {
                break;
            }
            swap(a, i, j);
        }
        swap(a, lo, j);
        // 此时，a[lo..j-1] <= a[j] <= a[j+1..hi]
        return j;
    }

    /**
     * 选择第 k 小的元素（从 0 开始计数），使 a[k] 在排序后正确的位置上
     * @param a 数组 a
     * @param k 第 k 小
     * @return  a[k]
     * @throws IllegalArgumentException 除非 {@code 0 <= k < a.length}
     */
    public static Comparable select(Comparable[] a, int k) {
        if (k < 0 || k >= a.length) {
            throw new IllegalArgumentException("Index is not between 0 and " + a.length + ": " + k);
        }
        int lo = 0, hi = a.length - 1;
        while (hi > lo) {
            int i = partition(a, lo, hi);
            if      (i > k) hi = i - 1;
            else if (i < k) lo = i + 1;
            else return a[i];
        }
        return a[lo];
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
