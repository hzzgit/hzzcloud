package com.hzz.hzzcloud.高德地图api.行人规划.Vo;

import java.util.List;

/**
 * Auto-generated: 2020-07-03 9:52:28
 *
 * @author www.jsons.cn 
 * @website http://www.jsons.cn/json2java/ 
 */
public class Paths {

    private String distance;
    private String duration;
    private List<Steps> steps;
    public void setDistance(String distance) {
         this.distance = distance;
     }
     public String getDistance() {
         return distance;
     }

    public void setDuration(String duration) {
         this.duration = duration;
     }
     public String getDuration() {
         return duration;
     }

    public void setSteps(List<Steps> steps) {
         this.steps = steps;
     }
     public List<Steps> getSteps() {
         return steps;
     }

}