package chapter1_fundamentals;

import java.util.Iterator;
import java.util.NoSuchElementException;

/**
 * Bag 链表的实现
 * Bag 是一种不支持删除元素的集合数据类型，可以用来收集和遍历元素
 * @param <Item>
 */
public class Bag<Item> implements Iterable<Item> {

    private Node<Item> first;
    private int size;

    private static class Node<Item> {
        private Item item;
        private Node<Item> next;
    }

    public Bag() {
        first = null;
        size = 0;
    }

    public void add(Item item) {
        Node<Item> oldFirst = first;
        first = new Node<>();
        first.item = item;
        first.next = oldFirst;
        ++size;
    }

    public boolean isEmpty() {
        return first == null;   // 或 return size == 0;
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