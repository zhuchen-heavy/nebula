package com.nebula.client.linkedlist;


/**
 * <p>
 * 1：单链表反转。
 * 2：链表中环的检测
 * 3：两个有序链表合并。
 * 4：删除链表倒数第n个节点。
 * 5：求链表的中间节点
 * </p>
 * @author: zhu.chen
 * @date: 2020-01-05
 */
public class LinkedListAlgo {

    // 单链表反转
    public static Node reverse(Node list) {
        Node curr = list;
        Node pre = null;
        while (curr != null) {
            Node next = curr.next;
            curr.next = pre;
            pre = curr;
            curr = next;
        }
        return pre;
    }

    // 环检测
    public static boolean checkCircle(Node list) {
        if (list == null) {
            return false;
        }
        Node fast = list.next;
        Node slow = list;

        // 链表的长度大于2
        if (fast != null && fast.next != null) {
            fast = fast.next.next;
            slow = slow.next;
            if (slow == fast) {
                return true;
            }
        }
        return false;
    }


    public static void main(String[] args) {
        Node next = new Node(4, null);
        Node curr = new Node(3, next);
        //System.out.println(curr.toString());
        System.out.println(reverse(curr).toString());

    }



    private static class Node {

        private int data;

        private Node next;

        public Node(int data, Node next) {
            this.data = data;
            this.next = next;
        }

        public int getData() {
            return data;
        }

        @Override
        public String toString() {
            return "Node{" +
                    "data=" + data +
                    ", next=" + next +
                    '}';
        }
    }

}
