package com.hzz.hzzcloud.设计模式.工厂模式.抽象工厂模式;

import com.hzz.hzzcloud.设计模式.工厂模式.animal;
import com.hzz.hzzcloud.设计模式.工厂模式.bird;
import lombok.extern.slf4j.Slf4j;

/**
 * @author ：hzz
 * @description：TODO
 * @date ：2020/7/28 10:01
 */
@Slf4j
public class birdfactory implements animalfactory {
    @Override
    public animal getanimal() {
        return new bird();
    }
}
