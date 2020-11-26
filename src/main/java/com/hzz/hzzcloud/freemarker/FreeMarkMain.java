package com.hzz.hzzcloud.freemarker;

import com.hzz.hzzcloud.freemarker.FreeMarkConfig.FreeMarkAutoByTreeExcuter;
import com.hzz.hzzcloud.freemarker.FreeMarkConfig.FreeMarkExcuter;


/**
 * freeMark模板自动生成文件工具,这边是生成常规的增删改查-导出模板
 */
public class FreeMarkMain {



    public static void main(String[] args) {
        FreeMarkExcuter freeMarkExcuter=new FreeMarkExcuter();
        FreeMarkAutoByTreeExcuter freeMarkAutoByTreeExcuter=new FreeMarkAutoByTreeExcuter();//自动构建规则列表和机构，车辆，用户授权代码

       // freeMarkExcuter.readTable("subiaodb", "talkchannel",false,false,true);
      //  freeMarkExcuter.readTable("gps_hisdata", "gpsinfo_20200701",false,false, TemplateEnum.TEMPLATE_PATH1,false);
//        freeMarkExcuter.readTable("subiaodb", "imggathercommand",
//                false,false, TemplateEnum.TEMPLATE_PATHNOCOMMON,false);

//        freeMarkExcuter.readTable("subiaodb", "keypoint_area",false,false, TemplateEnum.TEMPLATE_PATHNOCOMMON,false);
//        freeMarkExcuter.readTable("subiaodb", "keypoint_areapoint",false,false, TemplateEnum.TEMPLATE_PATHNOCOMMON,false);
//        freeMarkExcuter.readTable("subiaodb", "keypoint_orderbyarea",false,false, TemplateEnum.TEMPLATE_PATHNOCOMMON,false);
//        freeMarkExcuter.readTable("subiaodb", "keypoint_orderbysimno",false,false, TemplateEnum.TEMPLATE_PATHNOCOMMON,false);
//        freeMarkExcuter.readTable("subiaodb", "keypoint_ordermanage",false,false, TemplateEnum.TEMPLATE_PATHNOCOMMON,false);
       //freeMarkExcuter.readTable("subiaodb", "oldflowrateno",false,false, TemplateEnum.TEMPLATE_PATH1,false);

        freeMarkAutoByTreeExcuter.readTable("subiaodb","yrgpstranspond",false,false);

    }

}
