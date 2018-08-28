package com.learning.algorithmproject.chapter1Base;

import java.util.Scanner;

/**
 * @author Richard_yyf
 * @Date 2018/8/27
 * @Description 01-复杂度1 最大子列和问题
 * 输入格式:
 * 输入第1行给出正整数K (≤100000)；第2行给出K个整数，其间以空格分隔。
 * 输出格式:
 * 在一行中输出最大子列和。如果序列中所有整数皆为负数，则输出0。
 * 输入样例:
 * 6
 * -2 11 -4 13 -5 -2
 * 输出样例:
 * 20
 */
public class Week1Test {


    /**
     * 测试函数
     *
     * @param args
     */
    public static void main(String[] args) {
        int i, k;
        int[] nums = new int[10];
        Scanner in = new Scanner(System.in);
        //输入序列的长度
        System.out.println("please input a number");
        k = in.nextInt();
        //输入序列
        System.out.println("please input the numbers");
        for (i = 0; i < k; i++) {
            nums[i] = in.nextInt();
        }
        System.out.println("the number row is:");
        for (i = 0; i < k; i++) {
            System.out.print(nums[i] + " ");
        }

        System.out.println("The sum of biggest row is:" + max_sub3(nums, k));
    }

    /**
     * 优化穷举法
     *
     * @param nums
     * @param k
     * @return
     */
    public static int max_sub1(int nums[], int k) {
        int maxSum = 0;
        for (int i = 0; i < k; i++) {
            int thisSum = 0;
            for (int j = i; j < k; j++) {
                thisSum += nums[j];
                if (thisSum > maxSum) {
                    maxSum = thisSum;
                }
            }
        }
        return maxSum;
    }

    /**
     * 分而治之
     *
     * @param nums
     * @param k
     * @return
     */
    private static int max_sub2(int[] nums, int k) {
        return divideAndConquer(nums, 0, k - 1);
    }

    private static int max_sub3(int[] nums, int k) {
        int maxSum = 0;
        int thisSum = 0;
        for (int i=0; i<k; i++) {
            thisSum += nums[i];
            if (thisSum > maxSum) {
                maxSum = thisSum;
            }
            // 如果当前和为负数，则从下一个位置重新开始计算子列和
            if (thisSum < 0) {
                thisSum = 0;
            }
        }
        return maxSum;
    }

    private static int divideAndConquer(int[] list, int left, int right) {
        // 左右部分的最大子列和
        int maxLeftSum, maxRightSum;

        // 自中线从左、从右开始扫描的最大子列和
        int maxLeftBorderSum, maxRightBorderSum;
        int leftBorderSum, rightBorderSum;
        // 中间位置
        int center;

        // 递归终止条件
        if (left == right) {
            if (list[left] > 0) {
                return list[left];
            } else {
                return 0;
            }
        }

        // 下面是“分”的过程
        center = (left + right) / 2;
        maxLeftSum = divideAndConquer(list, left, center);
        maxRightSum = divideAndConquer(list, center + 1, right);

        // 下面是求跨分界线的最大子列和
        maxLeftBorderSum = 0;
        leftBorderSum = 0;
        for (int i = center; i >= left; i--) {
            leftBorderSum += list[i];
            if (leftBorderSum > maxLeftBorderSum) {
                maxLeftBorderSum = leftBorderSum;
            }
        }

        maxRightBorderSum = 0;
        rightBorderSum = 0;
        for (int i = center + 1; i <= right; i++) {
            rightBorderSum += list[i];
            if (rightBorderSum > maxRightBorderSum) {
                maxRightBorderSum = rightBorderSum;
            }
        }

        return max(maxLeftSum, maxRightSum, maxLeftBorderSum + maxRightBorderSum);

    }

    private static int max(int num1, int num2, int num3) {
        if (num1 > num2 && num1 > num3) {
            return num1;
        }
        if (num2 > num1 && num2 > num3) {
            return num2;
        }
        return num3;
    }



}
