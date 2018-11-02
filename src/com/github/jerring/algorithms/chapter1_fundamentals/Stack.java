package com.github.jerring.algorithms.chapter1_fundamentals;

import java.util.Iterator;
import java.util.NoSuchElementException;

/**
 * Stack 链表的实现
 * Stack 是一种基于 LIFO 策略的集合类型
 * @param <Item>
 */
public class Stack<Item> implements Iterable<Item> {

    private Node<Item> first;
    private int size;

    private static class Node<Item> {
        private Item item;
        private Node<Item> next;
    }

    public Stack() {
        first = null;
        size = 0;
    }

    public void push(Item item) {
        Node<Item> oldFirst = first;
        first = new Node<>();
        first.item = item;
        first.next = oldFirst;
        ++size;
    }

    public Item pop() {
        if (isEmpty()) {
            throw new NoSuchElementException("Stack underflow");
        }
        Item item = first.item;
        first = first.next;
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
