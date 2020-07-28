package com.hzz.hzzcloud.设计模式.建造者模式;

import com.hzz.hzzcloud.设计模式.工厂模式.animal;
import com.hzz.hzzcloud.设计模式.工厂模式.cat;
import com.hzz.hzzcloud.设计模式.工厂模式.dog;

import java.util.ArrayList;
import java.util.List;

/**
 * @author ：hzz
 * @description：TODO
 * @date ：2020/7/28 16:53
 */
public class Builder {

    private List<animal> list = new ArrayList<animal>();

    public void producecatanimal(int count){
        for(int i=0; i<count; i++){
            list.add(new cat());
        }
    }

    public void producedoganimal(int count){
        for(int i=0; i<count; i++){
            list.add(new dog());
        }
    }
}
