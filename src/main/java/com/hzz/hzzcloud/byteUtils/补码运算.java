package com.hzz.hzzcloud.byteUtils;

import java.util.ArrayList;

/**
 * @author ：hzz
 * @description：TODO
 * @date ：2020/7/20 16:00
 */
public class 补码运算 {
    public static void main(String[] args) {
        int data=252;
        byte d= (byte) data;
        String string = Integer.toBinaryString(data);
        if(string.length()==8){
            if (string.indexOf("1")==0) {
                String hex=string.substring(1,string.length());
                String fanhex="";
                for (int i = 0; i <hex.length() ; i++) {
                       if(hex.charAt(i)=='1'){
                           fanhex+="0";
                       }else{
                           fanhex+="1";
                       }
                }

                int fanb=Integer.parseInt(fanhex,2);
                byte b= (byte) -(fanb+1);
                int a=Integer.parseInt(hex,2);
                data=-a;
            }
        }
        ArrayList<Object> objects = new ArrayList<>();
        objects.add(1);
        ArrayList<Object> objects2 = new ArrayList<>(objects);
        ArrayList<Object> objects3 = objects2;

        String []arr=new String[]{"1","2","3","4"};
        String []arr2=new String[3];
        System.arraycopy(arr,0,arr2,0,3);

        objects3.add(2);
        System.out.println(objects==objects2);
        System.out.println(objects3==objects2);
        System.out.println(data);
    }
}
