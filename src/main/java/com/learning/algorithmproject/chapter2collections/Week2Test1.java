package com.learning.algorithmproject.chapter2collections;

import java.util.Scanner;

/**
 * @author Richard_yyf
 * @Date 2018/8/29
 * @Description 设计函数分别求两个一元多项式的乘积与和。
 * <p>
 * 输入格式:
 * 输入分2行，每行分别先给出多项式非零项的个数，再以指数递降方式输入一个多项式非零项系数和指数（绝对值均为不超过1000的整数）。数字间以空格分隔。
 * <p>
 * 输出格式:
 * 输出分2行，分别以指数递降方式输出乘积多项式以及和多项式非零项的系数和指数。数字间以空格分隔，但结尾不能有多余空格。零多项式应输出[0] [0]。
 * <p>
 * 输入样例:
 * 4 3 4 -5 2  6 1  -2 0
 * 3 5 20  -7 4  3 1
 * <p>
 * 输出样例:
 * 15 24 -25 22 30 21 -10 20 -21 8 35 6 -33 5 14 4 -15 3 18 2 -6 1
 * 5 20 -4 4 -5 2 9 1 -2 0
 */
public class Week2Test1 {

    public static void main(String[] args) {
        int k;
        // 系数
        int coef;
        // 指数
        int expn;
        Scanner in = new Scanner(System.in);
        //输入序列的长度
        k = in.nextInt();
        //输入序列
        PolyList polyList1 = new PolyList();
        polyList1.theSize = k;
        for (int i = 0; i < k; i++) {
            coef = in.nextInt();
            expn = in.nextInt();
            polyList1.add(coef, expn);
        }

        k = in.nextInt();
        //输入序列
        PolyList polyList2 = new PolyList();
        polyList2.theSize = k;
        for (int i = 0; i < k; i++) {
            coef = in.nextInt();
            expn = in.nextInt();
            polyList2.add(coef, expn);
        }
        // 多项式相乘
        PolyList.multiplyPoly(polyList1, polyList2).print();
        // 多项式相加
        PolyList.addPoly(polyList1, polyList2).print();
    }
}

class PolyList {

    public PolyNode headNode;

    public PolyNode current;

    public int theSize;

    public PolyList() {
        super();
        doClear();
    }

    public void doClear() {
        this.headNode = new PolyNode();
        current = headNode;
    }

    public void add(int coef, int expn) {
        PolyNode node = new PolyNode(coef, expn);
        current.next = node;
        current = node;
    }

    public class PolyNode {
        /**
         * 系数
         */
        public int coef;
        /**
         * 指数
         */
        public int expn;
        /**
         * 下一个节点
         */
        public PolyNode next;

        public PolyNode() {

        }

        public PolyNode(int coef, int expn) {
            this.coef = coef;
            this.expn = expn;
        }
    }

    /**
     * 多项式相加
     *
     * @param polyList1
     * @param polyList2
     * @return
     */
    public static PolyList addPoly(PolyList polyList1, PolyList polyList2) {
        PolyNode node1 = polyList1.headNode.next;
        PolyNode node2 = polyList2.headNode.next;
        PolyList resultPolyList = new PolyList();

        while (node1 != null && node2 != null) {
            int expn1 = node1.expn;
            int expn2 = node2.expn;
            int coef1 = node1.coef;
            int coef2 = node2.coef;

            if (expn1 == expn2) {
                // 指数相同，系数相加
                if (coef1 + coef2 != 0) {
                    resultPolyList.add(coef1 + coef2, expn1);
                }
                node1 = node1.next;
                node2 = node2.next;
            } else if (expn1 < expn2) {
                // node2 先放进结果多项式的链表中
                resultPolyList.add(coef2, expn2);
                node2 = node2.next;
            } else {
                // node1 指数项较大
                resultPolyList.add(coef1, expn1);
                node1 = node1.next;
            }
        }
        // 多项式一已经结束了
        while (node2 != null) {
            resultPolyList.add(node2.coef, node2.expn);
            node2 = node2.next;
        }
        // 多项式二已经结束了
        while (node1 != null) {
            resultPolyList.add(node1.coef, node1.expn);
            node1 = node1.next;
        }
        return resultPolyList;
    }

    /**
     * 多项式相乘
     *
     * @param polyList1 多项式1
     * @param polyList2 多项式2
     * @return 结果多项式
     */
    public static PolyList multiplyPoly(PolyList polyList1, PolyList polyList2) {
        PolyNode node1 = polyList1.headNode.next;
        PolyList resultPolyList = new PolyList();
        while (node1 != null) {
            resultPolyList = addPoly(resultPolyList, multiplyPoly(node1, polyList2));
            node1 = node1.next;
        }
        return resultPolyList;
    }

    private static PolyList multiplyPoly(PolyNode mNode, PolyList polyList) {
        PolyNode node = polyList.headNode.next;
        PolyList resultPolyList = new PolyList();
        while (node != null) {
            resultPolyList.add(node.coef * mNode.coef, node.expn + mNode.expn);
            node = node.next;
        }
        return resultPolyList;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        PolyNode node = headNode.next;
        while (node != null) {
            sb.append(node.coef).append("x^").append(node.expn);
            sb.append(" + ");
            node = node.next;
        }
        return sb.substring(0, sb.length() - 2);
    }

    /**
     * 根据题目要求打印
     */
    public void print() {
        PolyNode node = headNode.next;
        StringBuilder sb = new StringBuilder();
        if (node == null) {
            // 零多项式
            sb.append(0).append(" ").append(0);
        } else {
            while (node != null) {
                sb.append(node.coef).append(" ").append(node.expn).append(" ");
                node = node.next;
            }
        }
        System.out.println(sb.substring(0, sb.length() - 1));
    }
}
