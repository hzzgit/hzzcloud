package com.hzz.hzzcloud.freemarker;
import com.hzz.hzzcloud.freemarker.FreeMarkConfig.FreeMarkExcuter;


public class FreeMarkMain {



    public static void main(String[] args) {
        FreeMarkExcuter freeMarkExcuter=new FreeMarkExcuter();
        freeMarkExcuter.readTable("subiaodb", "talkchannel",false,false,true);
    }

}
