package com.hzz.hzzcloud.学习.二维数组;

/**
 * @author ：hzz
 * @description：如果是
 * @date ：2021/2/3 17:35
 */
public class 二维数据的查找 {
    public static void main(String[] args) {
        int [][] a=new int[][]{
                {1,4,7,11,15},
                {2,5,8,12,19},
                {3,6,9,16,22},
                {10,13,14,17,24},
                {18,21,23,26,30}
        };

        int co=10;
        boolean isnum = isnum(a, co);
        boolean isnum2 = findNumberIn2DArray(a, co);
        System.out.println(isnum);
        System.out.println(isnum2);
    }

    public static boolean isnum( int [][] a,int co){
        boolean arg=false;
        int num=0;
        for (int i = 0; i < a.length; i++) {
            if(a[i][0]>co){
                break;
            }
            if(a[i][a[i].length-1]<co){
               continue;
            }
            for (int j = 0; j <a[i].length ; j++) {
                num=num+1;
                int i1 = a[i][j];
                if(i1 ==co){
                    arg= true;
                    break;
                }else if(i1>co){
                    break;
                }
            }
            if(arg){
                break;
            }

        }
        System.out.println("循环"+num+"次数");
        return arg;
    }


    public static boolean findNumberIn2DArray(int[][] matrix, int target) {
        if(matrix.length == 0) return false;
        boolean arg=false;
        int num=0;
        int i = 0;
        int j = matrix[0].length - 1;  //从右上角为起始点，往下增大，往左减小
        while(i <= matrix.length - 1 && j >= 0){ //注意条件是与，当横纵坐标都未超出边界时一直循环
            num++;
            if(matrix[i][j] > target) j--;
            else if(matrix[i][j] < target) i++;
            else {
                arg= true;
                break;
            }
        }
        System.out.println("循环"+num+"次");
        return arg;
    }
}
