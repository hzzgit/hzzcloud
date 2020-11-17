package com.hzz.hzzcloud.test.读取亿万数据文件并且排序;

/**
 * @author ：hzz
 * @description：TODO
 * @date ：2020/11/16 16:10
 */
public class KeyWordsCount {

    private static KeyWordsCount kc;

    private int count = 0;
    private KeyWordsCount(){

    }

    public static synchronized KeyWordsCount getCountObject(){
        if(kc == null){
            kc = new KeyWordsCount();
        }
        return kc;
    }

    public synchronized void  addCount(int count){
        System.out.println("增加次数："+count);
        this.count += count;
    }

    public int getCount() {
        return count;
    }

    public void setCount(int count) {
        this.count = count;
    }

}