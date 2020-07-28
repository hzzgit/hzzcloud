package com.hzz.hzzcloud.设计模式.工厂模式.普通工厂模式;

import com.hzz.hzzcloud.设计模式.工厂模式.animal;
import com.hzz.hzzcloud.设计模式.工厂模式.bird;
import com.hzz.hzzcloud.设计模式.工厂模式.cat;
import com.hzz.hzzcloud.设计模式.工厂模式.dog;

/**
 * @author ：hzz
 * @description：TODO
 * @date ：2020/7/28 9:56
 */
public class animalfactory {
    public animal getanimal(int type){
        if(type==1){
            return  new cat();
        }else if(type==2){
            return  new dog();
        }else{
            return new bird();
        }
    }
}
