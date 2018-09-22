package com.learning.algorithmproject.chapter2collections;

import java.util.Scanner;

/**
 * @author Richard_yyf
 * @Date 2018/8/29
 * @Description Reversing Linked List
 * Given a constant K and a singly linked list L, you are supposed to reverse the links of every K elements on L.
 * For example, given L being 1→2→3→4→5→6, if K=3, then you must output 3→2→1→6→5→4; if K=4, you must output 4→3→2→1→5→6.
 * <p>
 * Input Specification:
 * Each input file contains one test case. For each case,
 * the first line contains the address of the first node,
 * a positive N (≤10​5​​) which is the total number of nodes,
 * and a positive K (≤N) which is the length of the sublist to be reversed.
 * The address of a node is a 5-digit nonnegative integer, and NULL is represented by -1.
 * Then N lines follow, each describes a node in the format:
 * <p>
 * Address Data Next
 * <p>
 * where Address is the position of the node, Data is an integer, and Next is the position of the next node.
 * <p>
 * Output Specification:
 * For each case, output the resulting ordered linked list.
 * Each node occupies a line, and is printed in the same format as in the input.
 * <p>
 * Sample Input:
 * <p>
 * 00100 6 4
 * 00000 4 99999
 * 00100 1 12309
 * 68237 6 -1
 * 33218 3 00000
 * 99999 5 68237
 * 12309 2 33218
 * <p>
 * Sample Output:
 * <p>
 * 00000 4 33218
 * 33218 3 12309
 * 12309 2 00100
 * 00100 1 99999
 * 99999 5 68237
 * 68237 6 -1
 *
 */
public class Week2Test2 {

    public static void main(String[] args) {
        ArrayNode[] array = new ArrayNode[100000];
        Scanner in = new Scanner(System.in);
        int headIndex;
        int size;
        int reverse;
        headIndex = in.nextInt();
        size = in.nextInt();
        reverse = in.nextInt();
        int index;
        for (int i = 0; i < size; i++) {
            index = in.nextInt();
            ArrayNode node = new ArrayNode();
            node.data = in.nextInt();
            node.next = in.nextInt();
            array[index] = node;
        }
        // 更新有效的节点个数
        size = ensureSize(headIndex, array);
        if (reverse > 1) {
            reverseArray(array, headIndex, size, reverse);
        }
    }

    private static int ensureSize(int headIndex, ArrayNode[] array) {
        int realSize = 0;
        while (headIndex == -1) {
            headIndex = array[headIndex].next;
            realSize++;
        }
        return realSize;
    }

    private static void reverseArray(ArrayNode[] array, int headIndex, int size, int reverse) {
        // 交易的两个节点位置
        int start, end;
        int split;
        // 全交换
        if (reverse > size) {
            reverse = size;
        }
        // 求中界线
        if (reverse % 2 == 0) {
            split = reverse / 2;
        } else {
            split = reverse/2 + 1;
        }
        start = headIndex;
        end = headIndex;
        while (reverse > split) {
            for (int i = 0; i < reverse; i++) {
                end = array[end].next;
            }
            swap(array, start, end);
            start = array[start].next;
            end = array[start].next;
            reverse--;
        }

    }

    public static void swap(ArrayNode[] array, int start, int end){
        ArrayNode temp = array[start];
        array[start] = array[end];
        array[end] = temp;
    }

    /**
     * 按照题目要求打印
     */
    public static void print(int headIndex, ArrayNode[] array){
        // 位置不足5位数要补0
    }
}

class ArrayNode {
    int data;
    int next;
}