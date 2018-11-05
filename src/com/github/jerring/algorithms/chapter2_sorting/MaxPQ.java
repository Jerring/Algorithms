package com.github.jerring.algorithms.chapter2_sorting;

import java.util.Comparator;
import java.util.NoSuchElementException;

/**
 * 基于堆的优先队列
 * @param <Key> 泛型参数
 */
public class MaxPQ<Key extends  Comparable<Key>> {

    /**
     * 存储元素从 1 到 n
     */
    private Key[] pq;

    /**
     * 优先队列中元素数量
     */
    private int n;

    /**
     * 可选的比较器
     */
    private Comparator<Key> comparator;

    /**
     * 用给定的大小初始化优先队列
     * @param initCapacity  给定的大小
     */
    public MaxPQ(int initCapacity) {
        pq = (Key[]) new Comparable[initCapacity + 1];
        n = 0;
    }

    /**
     * 默认构造方法
     */
    public MaxPQ(){
        this(1);
    }

    /**
     * 指定初始容量和比较器的构造方法
     * @param initCapacity  初始容量
     * @param comparator    比较器
     */
    public MaxPQ(int initCapacity, Comparator<Key> comparator) {
        this(initCapacity);
        this.comparator = comparator;
    }

    /**
     * 带比较器的构造方法
     * @param comparator    比较器
     */
    public MaxPQ(Comparator<Key> comparator) {
        this(1, comparator);
    }

    /**
     * 用数组初始化的构造方法
     * @param keys  初始化的元素数组
     */
    public MaxPQ(Key[] keys) {
        pq = (Key[]) new Object[keys.length + 1];
        n = keys.length;
        for (int i = 0; i < n; ++i) {
            pq[i + 1] = keys[i];
        }
        for (int k = n / 2; k > 1; --k) {   // 建堆操作
            sink(k);
        }
    }

    /**
     * 获取最大元素
     * @return 最大元素
     */
    public Key max() {
        if (isEmpty()) {
            throw new NoSuchElementException("Priority queue underflow");
        }
        return pq[1];
    }

    /**
     * 优先队列是否为空
      * @return 是否为空
     */
    public boolean isEmpty() {
        return n == 0;
    }

    /**
     * 返回堆的大小
     * @return 堆的大小
     */
    public int size(){
        return n;
    }

    /**
     * 调整大小使存储空间翻倍
     * @param capacity 新的容量
     */
    private void resize(int capacity) {
        assert capacity > n;
        Key[] temp = (Key[]) new Comparable[capacity];
        for (int i = 1; i <= n; ++i) {
            temp[i] = pq[i];
        }
        pq = temp;
    }

    /**
     * 新增元素放于末尾，执行 swim(n) 进行调整使堆有序
     * @param x 待插入的元素
     */
    public void insert(Key x) {
        if (n == pq.length - 1) {
            resize(2 * pq.length);
        }
        pq[++n] = x;
        swim(n);
    }

    /**
     * 删除最大元素，将位置 1 和 n 的元素互换，执行 sink(1) 进行调整使堆有序
     * @return  最大元素
     */
    public Key delMax() {
        if (isEmpty()) {
            throw new NoSuchElementException("Priority queue underflow");
        }
        Key max = pq[1];
        swap(1, n);
        pq[n--] = null;     // 有助于垃圾回收
        sink(1);
        if ((n > 0) && (n == (pq.length - 1) / 4)) {
            resize(pq.length / 2);
        }
        return max;
    }

    /**
     * 上浮：由下到上使堆有序化
     * @param k 索引
     */
    private void swim(int k) {
        while (k > 1 && less(k / 2, k)) {
            swap(k / 2, k);
            k /= 2;
        }
    }

    /**
     * 下沉：由上到下使堆有序化
     * @param k 索引
     */
    private void sink(int k) {
        while (2 * k <= n) {
            int j = 2 * k;
            if (j < n && less(j, j + 1)) {  // 找到最大的子元素，再与父元素进行比较
                ++j;
            }
            if (!less(k, j)) {
                break;
            }
            swap(k, j);
            k = j;
        }
    }

    /**
     * 有比较器则用比较器进行比较
     * @param i 索引
     * @param j 索引
     * @return  pq[i] 是否小于 pq[j]
     */
    private boolean less(int i, int j) {
        if (comparator == null) {
            return pq[i].compareTo(pq[j]) < 0;
        } else {
            return comparator.compare(pq[i], pq[j]) < 0;
        }
    }

    /**
     * 交换方法
     * @param i 索引
     * @param j 索引
     */
    private void swap(int i, int j) {
        Key t = pq[i];
        pq[i] = pq[j];
        pq[j] = t;
    }

}
