package com.github.jerring.algorithms.chapter2_sorting;

/**
 * 快速排序（优化版）
 * 优化点：
 * 1. 数组较小直接进行插入排序
 * 2. 取 a[lo], a[mid], a[hi] 三者的中位数来进行分割
 * 3. 分割基准（即 a[lo]）不为区间最值才进行分割过程
 * 不稳定，运用 O(lgn) 额外空间（主要是递归时栈的空间开销）
 */
public class QuickX {

    private static final int INSERTION_SORT_CUTOFF = 8; // 调用 insertionSort 的界限

    private QuickX() {}

    public static void sort(Comparable[] a) {
        sort(a, 0, a.length - 1);
    }

    private static void sort(Comparable[] a, int lo, int hi) {
        if (hi <= lo) {
            return;
        }
        if (hi - lo + 1 <= INSERTION_SORT_CUTOFF) {
            insertionSort(a, lo, hi);
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
        // 将三者的中位数放在数组头部，再来分割
        int m = median3(a, lo, lo + (hi - lo + 1) / 2, hi);
        swap(a, lo, m);

        int i = lo;
        int j = hi + 1;
        Comparable v = a[lo];

        // a[lo] 是唯一最大的元素
        while (less(a[++i], v)) {
            if (i == hi) {
                swap(a, lo, hi);
                return hi;
            }
        }

        // a[lo] 是唯一最小的元素
        while (less(v, a[--j])) {
            if (j == lo + 1) {
                return lo;
            }
        }

        while (i < j) {
            swap(a, i, j);
            while (less(a[++i], v)) ;
            while (less(v, a[--j])) ;
        }
        swap(a, lo, j);
        // 此时，a[lo..j-1] <= a[j] <= a[j+1..hi]
        return j;
    }

    /**
     * 返回 a[i], a[j], a[k] 的中位数的下标
     * @param a 数组
     * @param i 下标 i
     * @param j 下标 j
     * @param k 下标 k
     * @return
     */
    private static int median3(Comparable[] a, int i, int j, int k) {
        return (less(a[i], a[j]) ?
                (less(a[j], a[k]) ? j : less(a[i], a[k]) ? k : i) :
                (less(a[k], a[j]) ? j : less(a[k], a[i]) ? k : i));
    }

    private static void insertionSort(Comparable[] a, int lo, int hi) {
        for (int i = lo; i <= hi; i++) {
            for (int j = i; j > lo && less(a[j], a[j - 1]); --j) {
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
