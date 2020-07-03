package com.hzz.hzzcloud.高德地图api.行人规划.Vo;
import java.util.List;

/**
 * Auto-generated: 2020-07-03 9:52:28
 *
 * @author www.jsons.cn 
 * @website http://www.jsons.cn/json2java/ 
 */
public class Route {

    private String origin;
    private String destination;
    private List<Paths> paths;
    public void setOrigin(String origin) {
         this.origin = origin;
     }
     public String getOrigin() {
         return origin;
     }

    public void setDestination(String destination) {
         this.destination = destination;
     }
     public String getDestination() {
         return destination;
     }

    public void setPaths(List<Paths> paths) {
         this.paths = paths;
     }
     public List<Paths> getPaths() {
         return paths;
     }

}