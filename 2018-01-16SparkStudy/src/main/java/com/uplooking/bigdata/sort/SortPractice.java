package com.uplooking.bigdata.sort;

import java.util.Arrays;

/**
 * @ Title: 2018-01-16SparkStudy
 * @ Package:com.uplooking.bigdata.sort
 * @ description: 排序
 * @ This program is protected by copyright laws
 * @ author zhangjie
 * @ date 2018/1/18
 * @ version V1.0
 */
public class SortPractice {

    public static void main(String[] args) {

        int[] sortarr = new int[]{3, 9, 0, 7, -1, -5, 10, 12};
        System.out.println("没有使用排序之前的数组" + Arrays.toString(sortarr));
        //intsertSort(sortarr);
        //selectSort(sortarr);
        //bubbleSort(sortarr);
        quickSort(sortarr);

        System.out.println("使用排序之后的数组" + Arrays.toString(sortarr));

    }

    /**
     * 冒泡排序
     * 由大到小
     *
     * @param sortarr
     */
    public static void bubbleSort(int[] sortarr) {
        for (int i = 0; i < sortarr.length; i++) {
            for (int j = 0; j < sortarr.length - 1; j++) {
                if (sortarr[i] > sortarr[j]) {
                    sort(sortarr, i, j);
                }
            }
        }


    }


    /**
     * 选择排序
     * 由小到大
     *
     * @param sortarr
     */

    public static void selectSort(int[] sortarr) {
        for (int i = 0; i < sortarr.length - 1; i++) {
            int m = i;
            for (int j = i + 1; j < sortarr.length; j++) {
                if (sortarr[j] <= sortarr[m]) {
                    m = j;
                }
            }
            if (m != i) {
                sort(sortarr, i, m);
            }
        }
    }

    /**
     * 插入排序
     * 由大到小
     *
     * @param sortarr
     */
    public static void intsertSort(int[] sortarr) {
        for (int i = 1; i < sortarr.length; i++) {
            int ints = sortarr[i];
            int j = i;
            while (j > 0 && ints > sortarr[j - 1]) {
                sortarr[j] = sortarr[j - 1];
                j--;
            }
            sortarr[j] = ints;
        }

    }

    /**
     * 快速排序
     * 由小到大
     * @param sortarr
     */
    public static void quickSort(int[] sortarr) {
        quickSort(sortarr, 0, sortarr.length - 1);

    }

    public static void quickSort(int[] sortarr, int low, int higth) {
        int i, j, index;
        if (low > higth) {
            return;
        }
        i=low;
        j= higth;
        index = sortarr[low];
        while (i < j && sortarr[j] >= index)
        {
            j--;
        }
        while (i <j && sortarr[i]<=index)
        {
            i ++;
        }
        if (i< j)
        {
            sort(sortarr,i,j);
        }
           sortarr[low]=sortarr[i];
           sortarr[i]=index;
           quickSort(sortarr,low,i-1);
           quickSort(sortarr,i+1,higth);
    }
    private static void sort(int[] sortarr, int i, int j) {
        sortarr[i] = sortarr[i] ^ sortarr[j];
        sortarr[j] = sortarr[i] ^ sortarr[j];
        sortarr[i] = sortarr[i] ^ sortarr[j];
    }
}
