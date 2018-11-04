package com.github.jerring.algorithms.chapter2_sorting;

/**
 * 3 路快排，适用于有大量重复元素的情况
 * 不稳定，运用 O(lgn) 额外空间（主要是递归时栈的空间开销）
 */
public class Quick3way {

    private Quick3way() {}

    public static void sort(Comparable[] a) {
        sort(a, 0, a.length - 1);
    }

    /**
     * 维护一个指针 l 使得 a[lo..l-1] 中的元素都小于 v
     * 维护一个指针 r 使得 a[r+1..hi] 中的元素都大于 v
     * 维护一个指针 i 使得 a[l..i] 中的元素都等于 v
     * @param a
     * @param lo
     * @param hi
     */
    private static void sort(Comparable[] a, int lo, int hi) {
        if (hi <= lo) {
            return;
        }

        int l = lo, i = lo + 1, r = hi;
        Comparable v = a[lo];

        while (i <= r) {
            int cmp = a[i].compareTo(v);
            if (cmp < 0)        swap(a, l++, i++);  // a[i] < v，交换 a[l] 和 a[i]，让 l 和 i 自增
            else if (cmp > 0)   swap(a, i, r--);    // a[i] > v，交换 a[r] 和 a[i]，让 r 自减
            else                ++i;                // 让 i 自增
        }

        sort(a, lo, l - 1);
        sort(a, r + 1, hi);
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
