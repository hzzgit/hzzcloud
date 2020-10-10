package com.hzz.hzzcloud.freemarker;

import com.hzz.hzzcloud.freemarker.FreeMarkConfig.FreeMarkAutoByTreeExcuter;
import com.hzz.hzzcloud.freemarker.FreeMarkConfig.FreeMarkExcuter;
import com.hzz.hzzcloud.freemarker.emun.TemplateEnum;


/**
 * freeMark模板自动生成文件工具,这边是生成常规的增删改查-导出模板
 */
public class FreeMarkMain {



    public static void main(String[] args) {
        FreeMarkExcuter freeMarkExcuter=new FreeMarkExcuter();
        FreeMarkAutoByTreeExcuter freeMarkAutoByTreeExcuter=new FreeMarkAutoByTreeExcuter();//自动构建规则列表和机构，车辆，用户授权代码

       // freeMarkExcuter.readTable("subiaodb", "talkchannel",false,false,true);
      //  freeMarkExcuter.readTable("gps_hisdata", "gpsinfo_20200701",false,false, TemplateEnum.TEMPLATE_PATH1,false);
        freeMarkExcuter.readTable("subiaodb", "tablename",
                false,false, TemplateEnum.TEMPLATE_PATHNOCOMMON,false);

          freeMarkAutoByTreeExcuter.readTable("subiaodb", "tablename",false,false);

    }

}
