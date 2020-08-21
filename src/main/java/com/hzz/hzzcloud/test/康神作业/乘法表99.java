package com.hzz.hzzcloud.test.康神作业;

/**
 * @author ：hzz
 * @description：TODO
 * @date ：2020/7/27 16:27
 */
public class 乘法表99 {
    public static void main(String[] args) {
        int j=1;
        for (int i = 1; j <=9 ; i++) {

            System.out.print(i+"*"+j+"="+i*j+",");
            if(i==j){
                i=0;
                j++;
                System.out.println();
            }

        }
    }
}
