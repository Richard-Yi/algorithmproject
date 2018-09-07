package com.learning.algorithmproject.chapter2collections;

import java.util.ConcurrentModificationException;
import java.util.Iterator;
import java.util.NoSuchElementException;

/**
 * LinkedList 拟实现
 *
 * @author Richard_yyf
 * @Date 2018/9/7
 * @Description
 */
public class MyLinkedList<E> implements Iterable<E> {

    /**
     * 表的大小
     */
    private int theSize;

    /**
     * 自创建以来修改次数
     */
    private int modCount;

    /**
     * 头节点
     */
    private Node<E> beginMarker;

    /**
     * 尾节点
     */
    private Node<E> endMarker;

    /**
     * 构造方法
     */
    public MyLinkedList() {

    }

    /**
     * clear 例程
     */
    public void clear() {
        doClear();
    }

    /**
     * 初始化这个表
     */
    private void doClear() {
        // TODO 为什么书中没有直接用把endMarker放在beginMarker的构造函数中呢？
        beginMarker = new Node<>(null, null, null);
        endMarker = new Node<>(null, beginMarker, null);
        beginMarker.next = endMarker;

        theSize = 0;
        modCount++;
    }


    /**
     * size() 例程
     *
     * @return 表大小
     */
    public int size() {
        return theSize;
    }

    /**
     * isEmpty() 例程
     *
     * @return 是否为空
     */
    public boolean isEmpty() {
        return theSize == 0;
    }

    public void add(E ele) {

    }

    public void add(int index, E ele) {
        Node<E> node = getNode(index);
        Node<E> addNode = new Node<>(ele, node.prev, node);
        node.prev.next = addNode;
        addNode.next = node;
    }

    public E get(int index) {
        return getNode(index).data;
    }

    /**
     * set 例程
     *
     * @param index  下标
     * @param newVal 新值
     * @return oldVal 原值
     */
    public E set(int index, E newVal) {
        Node<E> node = getNode(index);
        E oldVal = node.data;
        node.data = newVal;

        return oldVal;
    }

    public E remove(int index) {
        return remove(getNode(index));
    }

    /**
     * 在指定节点前面插入新节点
     *
     * @param pNode node to add before
     * @param ele   新节点元素
     */
    private void addBefore(Node<E> pNode, E ele) {
        Node<E> node = new Node<>(ele, pNode.prev, pNode);
        pNode.prev.next = node;
        pNode.prev = node;

        theSize++;
        modCount++;
    }

    /**
     * 删除一个节点
     *
     * @param node node to remove
     * @param <E> 输入元素类型
     * @return data in the removed node
     */
    private <E> E remove(Node<E> node) {
        node.prev.next = node.next;
        node.next.prev = node.prev;

        theSize--;
        modCount++;
        return node.data;
    }

    /**
     * 获得指定index位置的节点 index 必须是介于0-size()-1之间
     *
     * @param index 搜索位置
     * @return 对应节点
     * @throws IndexOutOfBoundsException 下标越界是抛出
     */
    private Node<E> getNode(int index) {
        return getNode(index, 0, size() - 1);
    }

    private Node<E> getNode(int index, int lower, int upper) {
        Node<E> pNode;

        if (index < lower || index > upper) {
            throw new IndexOutOfBoundsException();
        }

        if (index < size() / 2) {
            // 从头节点开始找
            pNode = beginMarker.next;
            for (int i = 0; i < index; i++) {
                pNode = pNode.next;
            }
        } else {
            // 从尾节点开始
            pNode = endMarker.prev;
            for (int i = size() - 1; i > index; i--) {
                pNode = pNode.prev;
            }
        }

        return pNode;

    }

    @Override
    public Iterator<E> iterator() {
        return new LinkedListIterator<>();
    }

    private class LinkedListIterator<E> implements Iterator<E> {

        private Node<E> current;

        /**
         * 检测迭代期间集合被修改的情况
         */
        private int expectedModCount = modCount;

        private boolean okToReomove = false;

        @Override
        public boolean hasNext() {
            return current != endMarker;
        }

        @Override
        public E next() {
            if (modCount != expectedModCount) {
                throw new ConcurrentModificationException();
            }
            if (!hasNext()) {
                throw new NoSuchElementException();
            }

            E nextItem = current.data;
            current = current.next;
            okToReomove = true;
            return nextItem;
        }

        /**
         * 迭代一次只能执行一次remove例程
         */
        @Override
        public void remove() {
            if (modCount != expectedModCount) {
                throw new ConcurrentModificationException();
            }
            if (!okToReomove) {
                throw new IllegalStateException();
            }
            // 执行next操作之后才能进行remove
            MyLinkedList.this.remove(current.prev);
        }
    }



    /**
     * 私有类 节点
     *
     * @param <E>
     */
    private static class Node<E> {

        public E data;

        public Node<E> prev;

        public Node<E> next;

        public Node(E data, Node<E> prev, Node<E> next) {
            this.data = data;
            this.prev = prev;
            this.next = next;
        }
    }
}
