package com.learning.algorithmproject.chapter2collections;

import java.util.Scanner;

/**
 * 02-线性结构4 Pop Sequence （25 分）
 * Given a stack which can keep M numbers at most. Push N numbers in the order of 1, 2, 3, ..., N and pop randomly. You are supposed to tell if a given sequence of numbers is a possible pop sequence of the stack. For example, if M is 5 and N is 7, we can obtain 1, 2, 3, 4, 5, 6, 7 from the stack, but not 3, 2, 1, 7, 5, 6, 4.
 * <p>
 * Input Specification:
 * Each input file contains one test case. For each case, the first line contains 3 numbers (all no more than 1000): M (the maximum capacity of the stack), N (the length of push sequence), and K (the number of pop sequences to be checked). Then K lines follow, each contains a pop sequence of N numbers. All the numbers in a line are separated by a space.
 * <p>
 * Output Specification:
 * For each pop sequence, print in one line "YES" if it is indeed a possible pop sequence of the stack, or "NO" if not.
 * <p>
 * Sample Input:
 * 5 7 5
 * 1 2 3 4 5 6 7
 * 3 2 1 7 5 6 4
 * 7 6 5 4 3 2 1
 * 5 6 4 3 7 2 1
 * 1 7 6 5 4 3 2
 * <p>
 * Sample Output:
 * YES
 * NO
 * NO
 * YES
 * NO
 * <p>
 * 题意：
 * 栈的大小M，输入序列的长度N（默认序列即为1,2,3…,N），出栈的顺序不定。
 * 有K个测试序列，判断每一个测试序列是否是可能的出栈顺序。
 *
 * 从输入的test case来看，当这个case里的要被pop出来的数是x时，1<=x<=N，比x小的数必须先push进去，因为LIFO，并且是按照大小顺序入栈的
 *
 * @author Richard_yyf
 * @Date 2018/9/19
 * @Description
 */
public class Week2Test3 {

    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);

    }
}

class MyStack {

    int top;

    int[] data;


}
