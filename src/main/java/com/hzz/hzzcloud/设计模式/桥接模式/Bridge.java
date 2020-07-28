package com.hzz.hzzcloud.设计模式.桥接模式;

/**
 * @author ：hzz
 * @description：TODO
 * @date ：2020/7/28 18:02
 */
public abstract class Bridge {
    private Sourceable source;

    public void method(){
        source.method();
    }

    public Sourceable getSource() {
        return source;
    }

    public void setSource(Sourceable source) {
        this.source = source;
    }
}