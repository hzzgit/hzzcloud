package com.hzz.hzzcloud.学习.二维数组;

/**
 * @author ：hzz
 * @description：TODO
 * @date ：2021/2/4 10:07
 */
public class 旋转数组的最小数字 {

    public static void main(String[] args) {
        int[] a=new int[]{3,4,5,1,2};
        System.out.println(minArray(a));
    }

    public static int minArray(int[] numbers) {
        int i = 0, j = numbers.length - 1;
        while (i < j) {  //固定左指针i，不断缩小j
            int m = (i + j) / 2;
            if (numbers[m] > numbers[j]) {
                i = m + 1;
            } else if (numbers[m] < numbers[j]) {
                j = m;
            } else {
                j--;
            }
        }
        return numbers[i];
    }
}
