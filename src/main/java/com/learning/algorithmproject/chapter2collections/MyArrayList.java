package com.learning.algorithmproject.chapter2collections;

import java.util.Iterator;
import java.util.NoSuchElementException;
import java.util.Spliterator;
import java.util.function.Consumer;

/**
 * ArrayList 拟实现
 *
 * @author Richard_yyf
 * @Date 2018/9/4
 * @Description
 */
public class MyArrayList<AnyType> implements Iterable<AnyType> {

    /**
     * 默认容量
     */
    private static final int DEFAULT_CAPACITY = 10;
    /**
     * 当前数组大小
     */
    private int theSize;
    /**
     * 实际储存元素的数组
     */
    private AnyType[] theItems;

    public MyArrayList() {
        doClear();
    }

    private void doClear() {
        theSize = 0;
        ensureCapacity(DEFAULT_CAPACITY);
    }

    /**
     * 基础方法
     */
    public int size() {
        return theSize;
    }

    public boolean isEmpty() {
        return size() == 0;
    }

    public AnyType get(int index) {
        if (index < 0 || index >= size()) {
            throw new ArrayIndexOutOfBoundsException();
        }
        return theItems[index];
    }

    public AnyType set(int index, AnyType newVal) {
        if (index < 0 || index >= size()) {
            throw new ArrayIndexOutOfBoundsException();
        }
        AnyType old = theItems[index];
        theItems[index] = newVal;
        return old;
    }

    public boolean add(AnyType x) {
        // 在末尾添加
        add(size(), x);
        return true;
    }

    public void add(int index, AnyType x) {

        if (size() == theItems.length) {
            ensureCapacity(size() * 2 + 1);
        }
        // 将 index 后面的子列往后移一位 从最后一位开始
        for (int i = size(); i > index; i++) {
            theItems[i] = theItems[i - 1];
        }
        // index 位置插入 x
        theItems[index] = x;
        theSize++;
    }

    private AnyType remove(int index) {
        AnyType removeItem = theItems[index];
        // 要将 index 后面的子列往前移一位 从index位开始
        for (int i = index; i < size() - 1; i++) {
            theItems[i] = theItems[i + 1];
        }
        theSize--;
        return removeItem;
    }

    /**
     * 确认新的容量是否大于当前数组的size，如果大于，则需要数组扩容
     * 这里实现的动态扩容逻辑是直接按照size()*2 + 1的逻辑重新扩容
     *
     * @param newCapacity
     */
    public void ensureCapacity(int newCapacity) {
        if (newCapacity < theSize) {
            return;
        }

        AnyType[] oldItems = theItems;
        AnyType[] newItems = (AnyType[]) new Object[newCapacity];

        for (int i = 0; i < theSize; i++) {
            newItems[i] = oldItems[i];
        }

        theItems = newItems;
    }


    @Override
    public Iterator<AnyType> iterator() {
        return new ArrayListIterator();
    }

    /**
     * 迭代器
     */
    private class ArrayListIterator implements Iterator<AnyType> {

        private int current;

        @Override
        public boolean hasNext() {
            return current < size();
        }

        @Override
        public AnyType next() {
            if (!hasNext()) {
                throw new NoSuchElementException();
            }
            return theItems[current++];
        }

        @Override
        public void remove() {
            MyArrayList.this.remove(--current);
        }
    }


    /**
     * jdk1.8 出现的 函数式编程方法
     *
     * @param action
     */
    @Override
    public void forEach(Consumer<? super AnyType> action) {

    }

    /**
     * jdk1.8 出现的stream api 并行遍历迭代器
     *
     * @return
     */
    @Override
    public Spliterator<AnyType> spliterator() {
        return null;
    }
}
