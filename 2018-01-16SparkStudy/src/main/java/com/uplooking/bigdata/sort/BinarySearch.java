package com.uplooking.bigdata.sort;

import java.util.Arrays;

/**
 * @ Title: 2018-01-16SparkStudy
 * @ Package:com.uplooking.bigdata.sort
 * @ description: (二分查找)
 *  在一个有序的集合上面查找某一个元素是否存在，存在返回对应的索引位置，
 *  如果不存在返回-1，或者返回应该存在的位置(负数)
 * @ This program is protected by copyright laws
 * @ author zhangjie
 * @ date 2018/1/22
 * @ version V1.0
 */
public class BinarySearch {

    public static void main(String[] args) {
        //定义一个有序的集合
        int[] arr = {1, 2, 3, 4, 5, 7, 8, 10, 20};
        int key = 6;//要查找的元素
        int low = 0;//开始位置
        int high = arr.length - 1;
        //int result = binarySearch(arr, key,low,high);//递归查找
        int result= nobinarySearch(arr, key,low,high);
        System.out.println("在集合"+ Arrays.toString(arr)+"中的位置为第"+ result+"位");
    }

    /**
     *递归查找
     * @param arr
     * @param key
     * @return
     */
    private static int binarySearch(int[] arr, int key ,int low ,int high) {
     //结束位置
        int mid=(low+high) / 2;//中间的位置
        while (low <= high){
            if (key < arr[mid]){
                //说明在左边
                high=mid-1;
                return binarySearch(arr,key,low,high);

            }else if (key > arr[mid]){
                //说明在右边
                low=mid+1;
                return binarySearch(arr,key,low,high);
            }else {
                //找到位置所在
                 return mid;
            }

        }
        return -(mid+1);
    }

    private static int nobinarySearch(int[] arr,int key ,int low,int high){
        while (low <=high){
            int mid=(low+high) / 2;
            if (key <arr[mid]){
                high=mid-1;

            }else if (key > arr[mid]){
               low=mid+1;
            }else {
                return mid;
            }
        }
        return -(high+1);
    }



}
