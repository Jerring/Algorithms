package com.github.jerring.algorithms.chapter1_fundamentals;

import java.util.Iterator;
import java.util.NoSuchElementException;

/**
 * Queue 链表的实现
 * Queue 是一种基于 FIFO 策略的集合类型
 * @param <Item>
 */
public class Queue<Item> implements Iterable<Item> {

    private Node<Item> first;
    private Node<Item> last;
    private int size;

    private static class Node<Item> {
        private Item item;
        private Node<Item> next;
    }

    public Queue() {
        first = null;
        last = null;
        size = 0;
    }

    public void enqueue(Item item) {
        // 新建结点
        Node<Item> oldLast = last;
        last = new Node<>();
        last.item = item;
        last.next = null;
        // 根据队列是否为空，选择入队的策略
        if (isEmpty()) {
            first = last;
        } else {
            oldLast.next = last;
        }
        // 更新队列元素数量
        ++size;
    }

    public Item dequeue() {
        if (isEmpty()) {
            throw new NoSuchElementException("Queue underflow");
        }
        Item item = first.item;
        first = first.next;
        // 若此时队列为空，尾结点置空
        if (isEmpty()) {
            last = null;
        }
        // 更新队列元素数量
        --size;
        return item;
    }

    public boolean isEmpty() {
        return first == null;
    }

    public int size() {
        return size;
    }

    /**
     * 实现 Iterable 接口的 iterator() 方法
     * @return
     */
    @Override
    public Iterator<Item> iterator() {
        return new ListIterator<Item>(first);
    }

    /**
     * 定义类 ListIterator 实现 Iterator 接口
     * @param <Item>
     */
    private class ListIterator<Item> implements Iterator<Item> {

        private Node<Item> current;

        public ListIterator(Node<Item> first) {
            current = first;
        }

        @Override
        public boolean hasNext() {
            return current != null;
        }

        @Override
        public Item next() {
            if (!hasNext()) {
                throw new NoSuchElementException();
            }
            Item item = current.item;
            current = current.next;
            return item;
        }
    }
}
