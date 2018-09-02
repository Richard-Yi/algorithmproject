package com.learning.algorithmproject.chapter1Base;

import java.util.Scanner;

/**
 * @author Richard_yyf
 * @Date 2018/8/28
 * @Description 实现二分查找算法
 * <p>
 * 函数接口定义：
 * Position BinarySearch( List L, ElementType X );
 * <p>
 * L是用户传入的一个线性表，其中ElementType元素可以通过>、==、<进行比较，并且题目保证传入的数据是递增有序的。
 * 函数BinarySearch要查找X在Data中的位置，即数组下标（注意：元素从下标1开始存储）。
 * 找到则返回下标，否则返回一个特殊的失败标记NotFound。
 * <p>
 * 输入样例1：
 * 5
 * 12 31 55 89 101
 * 31
 * 输出样例1：
 * 2
 * <p>
 * 输入样例2：
 * 3
 * 26 78 233
 * 31
 * 输出样例2：
 * 0
 */
public class Week1Test3 {

    /**
     * 测试程序
     *
     * @param args
     */
    public static void main(String[] args) {
        int i, k, ele;
        int[] nums = new int[10];
        Scanner in = new Scanner(System.in);
        //输入序列的长度
        k = in.nextInt();
        //输入序列
        for (i = 0; i < k; i++) {
            nums[i] = in.nextInt();
        }
        ele = in.nextInt();
        System.out.println(binarySearch(nums, ele));
    }

    private static int binarySearch(int[] nums, int ele) {
        int left = 0;
        int right = nums.length - 1;
        return search(nums, left, right, ele);
    }

    private static int search(int[] nums, int left, int right, int ele) {
        int center = (left + right) / 2;

        if (left >= right) {
            return 0;
        }

        if (ele == nums[center]) {
            return center + 1;
        } else if (ele > nums[center]) {
            left = center + 1;
            return search(nums, left, right, ele);
        } else {
            right = center;
            return search(nums, left, right, ele);
        }
    }
}
