package com.hzz.hzzcloud.设计模式.工厂模式.静态工厂模式;

import com.hzz.hzzcloud.设计模式.工厂模式.animal;
import com.hzz.hzzcloud.设计模式.工厂模式.bird;
import com.hzz.hzzcloud.设计模式.工厂模式.cat;
import com.hzz.hzzcloud.设计模式.工厂模式.dog;

/**
 * @author ：hzz
 * @description：TODO
 * @date ：2020/7/28 9:58
 */
public class animalfactory {
    public  static animal  getcat(){
        return  new cat();
    }

    public  static animal  getdog(){
        return  new dog();
    }

    public  static animal  getbird(){
        return  new bird();
    }
}
