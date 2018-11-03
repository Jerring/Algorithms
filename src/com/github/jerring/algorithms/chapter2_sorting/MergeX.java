package com.github.jerring.algorithms.chapter2_sorting;

/**
 * 自顶向下的归并排序（优化版）
 * 优化点：
 * 1. 交换原数组和辅助数组的作用，省去一次数组复制过程
 * 2. 数组较小直接进行插入排序
 * 3. 如果前一个子数组的最大值小于等于后一个数组的最小值，表明已经有序，不进行归并的比较过程，直接复制数组元素
 * 稳定，运用 O(n) 额外空间
 */
public class MergeX {

    private static final int CUTOFF = 7;  // 调用 insertionSort 的界限

    private MergeX() {}

    public static void sort(Comparable[] a) {
        Comparable[] aux = a.clone();
        sort(aux, a, 0, a.length - 1);
    }

    private static void sort(Comparable[] src, Comparable[] dst, int lo, int hi) {
        // 数组较小直接进行插入排序
        if (hi <= lo + CUTOFF) {
            insertionSort(dst, lo, hi);
            return;
        }
        int mid = lo + (hi - lo) / 2;
        sort(dst, src, lo, mid);
        sort(dst, src, mid + 1, hi);

        // 如果src[mid + 1] >= src[mid]，直接复制，省去归并进行比较的时间
        if (!less(src[mid + 1], src[mid])) {
            System.arraycopy(src, lo, dst, lo, hi - lo + 1);
            return;
        }

        merge(src, dst, lo, mid, hi);
    }

    private static void merge(Comparable[] src, Comparable[] dst, int lo, int mid, int hi) {
        int i = lo, j = mid + 1;
        for (int k = lo; k <= hi; k++) {
            if (i > mid) {
                dst[k] = src[j++];
            } else if (j > hi) {
                dst[k] = src[i++];
            } else if (less(dst[j], dst[i])) {      // 保证稳定性
                dst[k] = src[j++];
            } else {
                dst[k] = src[i++];
            }
        }
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
