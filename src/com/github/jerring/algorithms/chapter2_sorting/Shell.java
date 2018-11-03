package com.github.jerring.algorithms.chapter2_sorting;

/**
 * 希尔排序：运用插入排序的思想，只不过步长变为了 h。当 h 为 1 时的排序完成了，整个排序就完成了
 * 相较于插入排序的优点在于：插入排序每次移动的步长为 1，希尔排序每次移动的步长为 h
 * 不稳定，运用 O(1) 额外空间
 */
public class Shell {

    private Shell() {}

    public static void sort(Comparable[] a) {
        int n = a.length;
        int h = 1;
        while (h < n / 3) {
            h = 3 * h + 1;      // 1, 4, 13, 40...（3x + 1 递增序列）
        }
        while (h >= 1) {        // h-sort 嵌套一个 insertionSort
            for (int i = h; i < n; ++i) {
                for (int j = i; j >= h && less(a[j], a[j - h]); j -= h) {
                    swap(a, j, j - h);
                }
            }
            h /= 3;
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
