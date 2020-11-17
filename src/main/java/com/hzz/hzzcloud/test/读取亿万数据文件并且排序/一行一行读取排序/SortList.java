package com.hzz.hzzcloud.test.读取亿万数据文件并且排序.一行一行读取排序;

import com.hzz.hzzcloud.test.读取亿万数据文件并且排序.SoutDto;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

/**
 * @author ：hzz
 * @description：用于多线程排序
 * @date ：2020/11/16 16:48
 */
public class SortList {
    private static List<SoutDto> list = new ArrayList<>();

    public static  List all(){
        return  list;
    }
    public static SoutDto get(String text) {
        String[] split = text.split(",");
        if(split.length>5){
            String s = text.split(",")[5];
            int co = Integer.parseInt(s);
            SoutDto soutDto = new SoutDto();
            soutDto.setText(text);
            soutDto.setSortcol(co);
            return soutDto;
        }
        else{
            return null;
        }

    }

    public static synchronized void add(SoutDto soutDto) {
        Integer weizhi = null;
          long s = System.currentTimeMillis();   //获取开始时间

        for (int i = 0; i < list.size(); i++) {
            if (list.get(i).getSortcol() > soutDto.getSortcol()) {
                weizhi = i;
                break;
            }
        }

        if (weizhi != null) {
            list.add(soutDto);
            Collections.sort(list, new Comparator<SoutDto>() {
                @Override
                public int compare(SoutDto o1, SoutDto o2) {
                    return o1.getSortcol()-o2.getSortcol();
                }
            });
            if(list.size()>20000){
                list.remove(list.size()-1);
            }

        }else{
            if(list.size()<20000){
                list.add(soutDto);
            }
        }
        long e = System.currentTimeMillis(); //获取结束时间
        long l = e - s;

            if(l>100){
                System.out.println("用时"+l);
            }


    }
}
