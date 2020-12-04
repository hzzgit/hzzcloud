package com.hzz.hzzcloud.test.自旋锁的使用.ABA问题;

import java.util.concurrent.atomic.AtomicStampedReference;

/**
 * @author ：hzz
 * @description：TODO
 * @date ：2020/12/3 17:23
 */
public class AtomicStampedReferencetest {
    public static void main(String[] args) {
        AtomicStampedReference atomicStampedReference=new AtomicStampedReference("ce1",1);

        int stamp = atomicStampedReference.getStamp();
        //想要对这个的值进行修改，必须知道之前的值以及版本号，才能更新成功否则是返回一个错误的
        boolean b = atomicStampedReference.compareAndSet("ce1", "ce2", 1, 2);

        System.out.println(b);
    }
}
