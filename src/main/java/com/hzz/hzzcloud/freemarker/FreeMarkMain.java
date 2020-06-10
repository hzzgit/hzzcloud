package com.hzz.hzzcloud.freemarker;
import com.hzz.hzzcloud.freemarker.FreeMarkConfig.FreeMarkExcuter;


/**
 * freeMark模板自动生成文件工具
 */
public class FreeMarkMain {



    public static void main(String[] args) {
        FreeMarkExcuter freeMarkExcuter=new FreeMarkExcuter();

       // freeMarkExcuter.readTable("subiaodb", "exlreadrecord",false,false,true);
        freeMarkExcuter.readTable("subiaodb", "maparea",false,false,true);
    }

}
