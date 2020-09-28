package com.hzz.hzzcloud.freemarker;

import com.hzz.hzzcloud.freemarker.FreeMarkConfig.FreeMarkExcuter;
import com.hzz.hzzcloud.freemarker.emun.TemplateEnum;


/**
 * freeMark模板自动生成文件工具
 */
public class FreeMarkMain {



    public static void main(String[] args) {
        FreeMarkExcuter freeMarkExcuter=new FreeMarkExcuter();

       // freeMarkExcuter.readTable("subiaodb", "talkchannel",false,false,true);
      //  freeMarkExcuter.readTable("gps_hisdata", "gpsinfo_20200701",false,false, TemplateEnum.TEMPLATE_PATH1,false);
        freeMarkExcuter.readTable("subiaodb", "maparea",
                false,false, TemplateEnum.TEMPLATE_PATH1,false);

    }

}
