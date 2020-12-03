package com.hzz.hzzcloud.OpenFeign.vo;

import lombok.Data;
import lombok.EqualsAndHashCode;

import java.util.List;

@Data
@EqualsAndHashCode(callSuper=false)
public class GetDepTreeVO {

    private boolean updateNode;
    private List<Long> ids;
    private String platenoOrsimno;

    /*用来进行模糊搜索的机构名*/
    private String searchDepName;

    private int pageNo = 1;
    private int pageSize = 50;
    //车辆状态：0、全部 ，1、在线，2、离线，3、全部情况下摄像头设备，4、在线情况下摄像头设备，5离线情况下摄像头设备
    private int state=0;

    //展示关注车辆：0、不关注，1、关注
    private int follow=0;

    //客户需求，第一次进来请求，如果0，那么就不过滤，如果是大于0，那么就根据传过来的数量进行第一次如果超过阀值，那么就根据这个阀值进行返回所有的车辆

    private int ischecknum=0;
}
