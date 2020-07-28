package com.hzz.hzzcloud.设计模式.工厂模式.多个工厂方法模式;

import com.hzz.hzzcloud.设计模式.工厂模式.animal;
import com.hzz.hzzcloud.设计模式.工厂模式.bird;
import com.hzz.hzzcloud.设计模式.工厂模式.cat;
import com.hzz.hzzcloud.设计模式.工厂模式.dog;

/**
 * @author ：hzz
 * @description：TODO
 * @date ：2020/7/28 9:59
 */
public class animalfactory {
    public animal getcat(){
        return new cat();
    }
    public animal getdog(){
        return new dog();
    }
    public animal getbird(){
        return new bird();
    }
}
