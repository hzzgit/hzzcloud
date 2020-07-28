package com.hzz.hzzcloud.设计模式.工厂模式.抽象工厂模式;

import com.hzz.hzzcloud.设计模式.工厂模式.animal;
import com.hzz.hzzcloud.设计模式.工厂模式.cat;

/**
 * @author ：hzz
 * @description：TODO
 * @date ：2020/7/28 10:01
 */
public class catfactory implements  animalfactory {
    @Override
    public animal getanimal() {
       return  new cat();
    }
}
