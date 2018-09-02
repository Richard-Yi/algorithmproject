package com.learning.algorithmproject.chapter2collections;

import java.util.Scanner;

/**
 * @author Richard_yyf
 * @Date 2018/8/29
 * @Description 本题要求实现一个函数，将两个链表表示的递增整数序列合并为一个非递减的整数序列。
 * <p>
 * 函数接口定义：
 * List Merge( List L1, List L2 );
 * <p>
 * L1和L2是给定的带头结点的单链表，其结点存储的数据是递增有序的；
 * 函数Merge要将L1和L2合并为一个非递减的整数序列。应直接使用原序列中的结点，返回归并后的带头结点的链表头指针。
 * <p>
 * 输入样例：
 * 3
 * 1 3 5
 * 5
 * 2 4 6 8 10
 * <p>
 * 输出样例：
 * 1 2 3 4 5 6 8 10
 * NULL
 * NULL
 */
public class Week2Test1 {

    /**
     * 测试程序
     *
     * @param args
     */
    public static void main(String[] args) {
        int size1, size2;
        Scanner in = new Scanner(System.in);
        size1 = in.nextInt();
        Node head1 = null, next = null;
        for (int i = 0; i < size1; i++) {

        }
        System.out.println(head1.value);
    }

    private static class Node {

        /**
         * 指向下一结点
         */
        Node next;

        /**
         * 本结点的值
         */
        int value;

        Node(Node next, int value) {
            super();
            this.next = next;
            this.value = value;
        }
    }

}
