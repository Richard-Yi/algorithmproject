package com.learning.algorithmproject.chapter1Base;

import java.util.Scanner;

/**
 * @author Richard_yyf
 * @Date 2018/8/28
 * @Description Output Specification:
 * For each test case, output in one line the largest sum, together with the first and the last numbers of the maximum subsequence.
 * The numbers must be separated by one space, but there must be no extra space at the end of a line.
 * In case that the maximum subsequence is not unique, output the one with the smallest indices i and j (as shown by the sample case).
 * If all the K numbers are negative, then its maximum sum is defined to be 0, and you are supposed to output the first and the last numbers of the whole sequence.
 * <p>
 * Sample Input:
 * 10
 * -10 1 2 3 4 -5 -23 3 7 -21
 * Sample Output:
 * 10 1 4
 */
public class Week1Test2 {

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
            System.out.println(nums[i] + " ");
        }

        max_sub(nums, k);
    }

    /**
     * 1. 如果都是负数，则输出0,0,k
     * 2. 如果最大子列和的子列不唯一，取下标小的
     *
     * @param nums
     * @param k
     */
    private static void max_sub(int[] nums, int k) {
        int maxSum = nums[0];
        int thisSum = 0;
        int left = nums[0], right = nums[k-1];
        int thisLeft = nums[0], thisRight;
        for (int i = 0; i < k; i++) {
            thisSum += nums[i];
            thisRight = nums[i];
            // thisSum > 0 是针对全是负数的情况
            if (thisSum > maxSum && thisSum >= 0) {
                maxSum = thisSum;
                right = thisRight;
                left = thisLeft;
            }
            if (thisSum < 0) {
                if (i != k - 1) {
                    // i=k-1的情况下，会数组越界，所以thisLeft当做最后一位就好
                    thisLeft = nums[i + 1];
                }
                thisSum = 0;
            }
        }
        // 排除全是负数的情况
        if (maxSum < 0) {
            maxSum = 0;
        }
        System.out.println(maxSum + " " + left + " " + right);
    }
}
