package com.hzz.hzzcloud.freemarker;
import com.hzz.hzzcloud.freemarker.FreeMarkConfig.FreeMarkExcuter;
import com.hzz.hzzcloud.freemarker.emun.TemplateEnum;


/**
 * freeMark模板自动生成文件工具
 */
public class FreeMarkMain {



    public static void main(String[] args) {
        FreeMarkExcuter freeMarkExcuter=new FreeMarkExcuter();

       // freeMarkExcuter.readTable("subiaodb", "exlreadrecord",false,false,true);
        freeMarkExcuter.readTable("subiaodb", "alarmgranter",false,false, TemplateEnum.TEMPLATE_PATH1,false);
        freeMarkExcuter.readTable("subiaodb", "alarmgranteruserlim",false,false, TemplateEnum.TEMPLATE_PATH1,false);
        freeMarkExcuter.readTable("subiaodb", "alarmgranterbyvehicle",false,false, TemplateEnum.TEMPLATE_PATH1,false);
        freeMarkExcuter.readTable("subiaodb", "alarmgranterbydep",false,false, TemplateEnum.TEMPLATE_PATH1,false);
        freeMarkExcuter.readTable("subiaodb", "alarmgrantertopic",false,false, TemplateEnum.TEMPLATE_PATH1,false);
    }

}
