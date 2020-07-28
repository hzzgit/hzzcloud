package com.hzz.hzzcloud.设计模式.原型模式;

/**
 * @author ：hzz
 * @description：TODO
 * @date ：2020/7/28 16:55
 */
public class Prototype implements Cloneable {

//   深复制  /* 写入当前对象的二进制流 */
//    ByteArrayOutputStream bos = new ByteArrayOutputStream();
//    ObjectOuoos = new ObjectOutputStream(bos);
//        oos.writeObject(this);
//
//    /* 读出二进制流产生的新对象 */
//    ByteArrayInputStream bis = new ByteArrayInputStream(bos.toByteArray());
//    ObjectInputStream ois = new ObjectInputStream(bis);
//        return ois.readObject();

    public Object clone1() throws CloneNotSupportedException {
        Prototype proto = (Prototype) super.clone();
        return proto;
    }
}
