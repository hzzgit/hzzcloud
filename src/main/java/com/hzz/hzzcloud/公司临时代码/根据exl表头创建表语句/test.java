package com.hzz.hzzcloud.公司临时代码.根据exl表头创建表语句;

/**
 * @author ：hzz
 * @description：TODO
 * @date ：2020/12/16 10:01
 */
public class test {
    public static void main(String[] args) {
        String name="plateNo,车牌号\tcolor,颜色\tton,吨座位\ttransportcard,运输证号\tjilevel,技术等级\tjileveldate,等级评定日\taftercheckdate,下次综检日\tlastweidate,末次二维日\tafterweidate,下次维护日\ttransportdep,运输企业\tbeforecheckdate,上次综检日\tvehicletype,车辆类型\tyearcheckdate,年审日期\tyearcheckresult,年审结果\tstate,状态\tvehicleaddress,车籍地\tdeplicence,企业许可证\tvehiclecreatetime,创建日期\tvehicleowner,车主\ttransporttype,运输类型\tdrivercardregisterdate,行驶证登记日\tregion,行政区域\tenginenumber,发动机号\tvin,车架号\tfactoryplatemodel,厂牌型号\ttransportcardcreatedate,运输证发证日\tbusinessscope,经营范围\tdangercargocheck,危货审批\tdrivername,驾驶员姓名\tcertificatecode,从业资格证号\tsupercargo,押运员\tsupercargocertificatecode,押运员资格证号\tcargotype,承运货种\tSignlightnumber,标志灯编号\tSignlightinsdate,标志灯安装日期\tSignlightvaliditydate,标志灯有效期\tSignboardvaliditydate,标志牌有效期\tcarriersrisk,承运人责任险\tcarriersriskvaliditydate,承运人责任险有效期\tisusegps,使用GPS\tisusedriverrecorder,有行车记录仪\tisusegpsanddervercorder,带行车记录仪的GPS\tsafecheckdate,安检有效期\tViolationnum,违章次数\tpolicestate,交警状态\tSecondmaintenancecycle,二级维护周期\tSecondmaintenancemileage,二级维护间隔里程\tfueltype,燃料种类\tdriver,驾驶员\tdrivercard,驾驶员资格证\tdriversfzh,驾驶员身份证\tdrivercardfirstdate,行驶证首发日期\ttransportcardfirstdate,运输证首发日期\tfulltotalmass,满载总质量\tlength,长\twidth,宽\theight,高\tdepmobilephone,企业手机\tdeptelephone,企业电话\tcarbodycolor,车身颜色\tcapacitysource,运力来源\ttractionmass,准牵引总质量\ttwomaintaindep,二维维修企业\tAllinspection,综检单位\tpolicecartype,交警车型";

        String createsql="CREATE TABLE vehiclereport ( \n";

        String[] split = name.split("\t");
        for (String s : split) {
            String[] split1 = s.split(",");

                createsql+=split1[0]+" varchar(255)  DEFAULT NULL COMMENT '"+split1[1]+"', \n";


        }
        createsql +=") ENGINE=InnoDB COMMENT='企业车辆信息表维护表'";

        System.out.println(createsql);
    }
}
