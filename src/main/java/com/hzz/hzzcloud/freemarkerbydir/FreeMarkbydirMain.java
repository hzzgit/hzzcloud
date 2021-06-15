package com.hzz.hzzcloud.freemarkerbydir;

/**
 * freeMark模板自动生成文件工具,这边是生成常规的增删改查-导出模板
 */
public class FreeMarkbydirMain {


    public static void main(String[] args) {
        FreeMarkbydirExcuter freeMarkbydirExcuter=new FreeMarkbydirExcuter();
        freeMarkbydirExcuter.readTable("sync", "t_contract_ext_park",
                "com.cwgj.service.data",true);
//        freeMarkbydirExcuter.readTable("subiaodb", "config_table_filed",
//                false,false);
    }

}
