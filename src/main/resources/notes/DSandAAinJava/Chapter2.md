# 本章重点

- 介绍抽象数据类型的概念
- 阐述如何有效的执行表的操作
- 介绍栈ADT及其在实现递归方面的应用
- 介绍队列ADT及其在操作系统和算法设计中的应用

本章提供实现两个库类重要子集ArrayList和LinkedList的代码。

# 3.1 抽象数据类型

1. 抽象数据类型(**Abstract data type, ADT**) ，是带有一组操作的一些对象的集合。

2. 表、集合、图以及与它们各自的操作一起形成的这些对象都可以被看做是抽象数据类型。

# 3.2 表 ADT

1. 表可以通过数组实现，也可以通过链表实现。链表又分为单链表和双链表。

2. 数组实现时：`printList`以线性时间执行，而`findKth`操作则只花费常数时间。但是插入`insert`和删除`remove`元素却开销很大。

3. 链表实现时：`printList`和`findKth`都是花费线性时间，而插入`insert`和删除`remove`只花费常数时间。

# 3.3 Java Collections API 中的表

## 3.3.1 Collection 接口

```java
public interface Collection<E> extends Iterable<E> {

    int size();
    boolean isEmpty();
    void clear();
    boolean contains(Object o);
    boolean add(E e);
    boolean remove(Object o);
    Iterator<E> iterator();

}
```

## 3.3.2 Iterator 接口

实现`Iterable`接口的集合必须提供一个`iterator`的方法。

看源码中的JavaDoc资料很容易就能理解功能

```java
public interface Iterator<E> {

    /**
     * Returns {@code true} if the iteration has more elements.
     * (In other words, returns {@code true} if {@link #next} would
     * return an element rather than throwing an exception.)
     *
     * @return {@code true} if the iteration has more elements
     */
    boolean hasNext();

    /**
     * Returns the next element in the iteration.
     *
     * @return the next element in the iteration
     * @throws NoSuchElementException if the iteration has no more elements
     */
    E next();

    /**
     * Removes from the underlying collection the last element returned
     * by this iterator (optional operation).  This method can be called
     * only once per call to {@link #next}.  The behavior of an iterator
     * is unspecified if the underlying collection is modified while the
     * iteration is in progress in any way other than by calling this
     * method.
     *
     * @implSpec
     * The default implementation throws an instance of
     * {@link UnsupportedOperationException} and performs no other action.
     *
     * @throws UnsupportedOperationException if the {@code remove}
     *         operation is not supported by this iterator
     *
     * @throws IllegalStateException if the {@code next} method has not
     *         yet been called, or the {@code remove} method has already
     *         been called after the last call to the {@code next}
     *         method
     */
    default void remove() {
        throw new UnsupportedOperationException("remove");
    }

   // 省略forEachRemaining方法
}
```

## 3.3.3 List接口、ArrayList类和LinkedList类

1. 表 - `list`；数组实现 - `ArrayList`；双链表实现 - `LinkedList`；
2. 通过`3.2`中的分析：
>a.ArrayList 优点：get和set请求花费常数时间；缺点：插入新项和删除旧项代价昂贵。 
>b.LinkedList 优点：插入新项和删除旧项开销很小；缺点：不能索引，get调用代价昂贵。
3. 对于搜索而言，ArrayList和LinkedList 都是低效的。
 

## 3.3.4 remove方法对LinkedList的使用，

# 3.4 ArrayList类的实现

实现需要概括一下细节
----------
1. `MyArrayList` 将保持基础数组，数组的容量，以及存储在MyArrayList中的当前项数。
2. `MyArrayList` 将提供一种机制以改变基础数组的容量。通过获得一个新数组，将老数组拷贝到新数组中来改变数组的容量，允许虚拟机回收老数组。
3. `MyArrayList` 将提供`get`和`set`的实现。
4. `MyArrayList` 将提供基本的例程，如`size`、`isEmpty`和`clear`，他们是典型的单行程序；还提供`remove`，以及两种不同版本的add。如果数组大小和容量相同，那么这两个add例程将会增加容量
5. `MyArrayList` 将提供一个实现`Iterator`接口的类。这个类将存储迭代序列中的下一项的下标，并提供`next`、`hasNext`和`remove`等方法的实现。MyArrayList的迭代器方法直接返回实现`Iterator`接口的该类的新构造的实例


具体看代码 `MyArrayList.java` 

# 3.5 LinkedList类的实现

考虑设计需要提供3个类：
----------
1. `MyLinkedList` 类本身，它包含到两端的链、表的大小以及一些方法
2. `Node` 类，它可能是一个私有的嵌套类。一个节点包含数据以及到前一个节点的链和到下一个节点的链，还有一个适当的构造方法。
3. `LinkedListIterator` 类，该类抽象了位置的概念，是一个私有类，并实现接口`Iterator`。它提供了方法`next`、`hasNext`和`remove`的实现


具体看代码 `MyLinkedList.java` 

# 3.6 ADT 栈

