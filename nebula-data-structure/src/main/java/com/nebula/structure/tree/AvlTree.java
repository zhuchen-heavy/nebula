package com.nebula.structure.tree;

/**
 * <p>
 * avl树的简单实现
 * </p>
 * @author: zhu.chen
 * @date: 2020-01-03
 */
public class AvlTree<E extends Comparable<E>>{

    private static final int MAX_HEIGHT_DIFFERENCE = 1;

    private AVLNode root;

    public AvlTree() {
        this.root = null;
    }

    public int height() {
        return height(root);
    }

    private int height(AVLNode<E> t) {
        return t == null ? -1 : t.height;
    }

    public AVLNode<E> insert(E x, AVLNode<E> t) {
        if (t == null) {
            return new AVLNode<E>(x);
        }
        //先比较 是插左边还是插右边
        int compareResult = x.compareTo(t.element);
        if (compareResult < 0) {//插到左子树上
            t.left = insert(x, t.left);
            if (height(t.left) - height(t.right) == 2) {
                if (x.compareTo(t.left.element) < 0) {}
            }

        }
        return null;
    }

    private AVLNode<E> rightRotate(AVLNode<E> t) {
        AVLNode newTree = t.left;
        t.left = newTree.right;
        return null;
    }


    private static class AVLNode<E>  {
        E element;
        AVLNode<E> left;
        AVLNode<E> right;
        // 树的高度
        int height;

        public AVLNode(E element) {
            this(element, null, null);
        }

        public AVLNode(E element, AVLNode<E> left, AVLNode<E> right) {
            this.element = element;
            this.left = left;
            this.right = right;
        }
    }



}












