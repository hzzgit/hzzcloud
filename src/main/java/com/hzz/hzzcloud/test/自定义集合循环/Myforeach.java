package com.hzz.hzzcloud.test.自定义集合循环;

import java.util.Iterator;
import java.util.List;
import java.util.Spliterator;

/**
 * @author ：hzz
 * @description：TODO
 * @date ：2020/7/20 17:46
 */
public class Myforeach implements  Iterable<String>,Iterator<String> {
    @Override
    public Iterator iterator() {
        return null;
    }


    @Override
    public Spliterator spliterator() {
        return null;
    }

    @Override
    public boolean hasNext() {
        return false;
    }

    @Override
    public String next() {
        return null;
    }

    @Override
    public void remove() {

    }

    public static void main(String[] args) {
        List<String> datas=new MyList<>();
        datas.add("1");
        datas.add("11");
        datas.add("111");
        datas.add("1111");
        Iterator<String> iterator = datas.iterator();
        while (iterator.hasNext()){
            String next = iterator.next();
            System.out.println(next);
        }
    }
}
