package com.learning.algorithmproject.chapter2collections;

/**
 * 用链表表示多项式
 * @author Richard_yyf
 * @Date 2018/9/11
 * @Description
 */
public class PolyList {

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
        /** 系数 */
        public int coef;
        /** 指数 */
        public int expn;
        /** 下一个节点 */
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
     * @param polyList1
     * @param polyList2
     * @return
     */
    public static PolyList addPoly(PolyList polyList1, PolyList polyList2) {
        PolyNode node1 = polyList1.headNode.next;
        PolyNode node2 = polyList2.headNode.next;
        PolyList resultPolyList = new PolyList();

        while (node1 != null && node2 !=null) {
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
     * @param polyList1
     * @param polyList2
     * @return
     */
    public static PolyList multiplyPoly(PolyList polyList1, PolyList polyList2) {
        return null;
    }

    @Override
    public String toString(){
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
    public void print(){
        PolyNode node = headNode.next;
        StringBuilder sb = new StringBuilder();
        while (node!=null) {
            sb.append(node.coef).append(" ").append(node.expn).append(" ");
            node = node.next;
        }
        System.out.println(sb.substring(0, sb.length() - 1));
    }
}
