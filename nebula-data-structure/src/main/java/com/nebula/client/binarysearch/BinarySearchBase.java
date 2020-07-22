package com.nebula.client.binarysearch;

/**
 * <p>
 * 二分查找的实现
 * 分治的思想
 * </p>
 * @author: zhu.chen
 * @date: 2020-01-08
 */
public class BinarySearchBase {

    /**
     *
     * @param a 数组
     * @param n 数组的大小
     * @param value 要查询的值
     * @return 输出数据在数组中的下标
     */
    public static int bserach(int[] a, int n, int value) {
        int low = 0;
        int high = n - 1;

        while (low <= high) {
            int mid = low + ((high - low) >> 1);
            if (a[mid] == value) {
                return mid;
            } else if (a[mid] < value) {
                low = mid + 1;
            } else {
                low = mid - 1;
            }
        }
        return -1;
    }


    public static void main(String[] args) {
        int[] a = new int[]{3,6,4,9,7,6};
        System.out.println(bserach(a, a.length, 7));
    }

}
