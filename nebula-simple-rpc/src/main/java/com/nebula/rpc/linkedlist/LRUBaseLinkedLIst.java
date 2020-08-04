package com.nebula.structure.linkedlist;

import java.util.Scanner;

/**
 * <p>
 * 基于单链表来实现LRU缓存
 * </p>
 * @author: zhu.chen
 * @date: 2020-01-05
 */
public class LRUBaseLinkedLIst<T> {

    /**
     * 默认容量
     */
    private final static Integer DEFAULT_CAPACITY = 10;

    /**
     * 链表头结点
     */
    private SNode<T> headNode;

    /**
     * 链表长度
     */
    private Integer leight;

    /**
     * 链表容量 ---》用于扩容使用
     */
    private Integer capacity;

    public LRUBaseLinkedLIst() {
        this.headNode = new SNode<>();
        this.leight = 0;
        this.capacity = DEFAULT_CAPACITY;
    }

    public LRUBaseLinkedLIst(Integer capacity) {
        this.headNode = new SNode<>();
        this.leight = 0;
        this.capacity = capacity;
    }

    public void add(T data) {
        SNode preNode = findPreNode(data);
        if (preNode != null) {
            deleteElemOptim(preNode);
            insetElemAtBegain(data);
        } else {
            if (leight >= this.capacity) {
                deleteElemAtEnd();
            }
            insetElemAtBegain(data);
        }

    }

    private void printAll(){
        SNode node = headNode.getNext();
        while (node != null) {
            System.out.print(node.getElement() + ",");
            node = node.getNext();
        }
        System.out.println();
    }

    /**
     * 删除尾节点
     */
    private void deleteElemAtEnd() {
        SNode ptr = headNode;
        if (ptr.getNext() == null) {
            return;
        }
        // 倒数第二个结点
        while (ptr.getNext().getNext() !=  null) {
            ptr = ptr.getNext();
        }
        SNode temp = ptr.getNext();
        ptr.setNext(null);
        temp = null;
        leight--;
    }

    /**
     * 链表头部插入节点
     *
     * @param data
     */
    private void insetElemAtBegain(T data) {
        SNode next = headNode.getNext();
        headNode.setNext(new SNode(data, next));
        leight++;
    }

    /**
     * 删除preNode结点下一个元素
     *
     * @param preNode
     */
    private void deleteElemOptim(SNode preNode) {
        SNode temp = preNode.getNext();
        preNode.setNext(temp.getNext());
        temp = null;
        leight--;
    }

    /**
     * 获取查找到元素的前一个结点
     * @param data
     * @return
     */
    public SNode findPreNode(T data) {
        SNode node = headNode;
        while (node.getNext() != null) {
            if (data.equals(node.getNext().getElement())) {
                return node;
            }
            node = node.getNext();
        }
        // 尾节点
        return null;
    }

    /**
     * 定义一个单链表节点。
     * 单连接节点存储的是：当前节点数据+下一个节点的指针。
     */
    private class SNode<T> {

        private T element; // 当前数据

        private SNode next; // 下一个节点的指针

        public SNode() {
        }

        public SNode(T element) {
            this.element = element;
        }

        public SNode(T element, SNode next) {
            this.element = element;
            this.next = next;
        }

        public T getElement() {
            return element;
        }

        public void setElement(T element) {
            this.element = element;
        }

        public SNode getNext() {
            return next;
        }

        public void setNext(SNode next) {
            this.next = next;
        }
    }

    public static void main(String[] args) {
        LRUBaseLinkedLIst lruBaseLinkedLIst = new LRUBaseLinkedLIst();
        Scanner scanner = new Scanner(System.in);
        while (true) {
            lruBaseLinkedLIst.add(scanner.nextInt());
            lruBaseLinkedLIst.printAll();
        }
    }


}
