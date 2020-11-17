package com.hzz.hzzcloud.test.读取亿万数据文件并且排序.一行一行读取排序;

import com.hzz.hzzcloud.test.读取亿万数据文件并且排序.SoutDto;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import java.util.concurrent.ConcurrentLinkedQueue;

/**
 * @author ：hzz
 * @description：TODO
 * @date ：2020/11/16 17:52
 */
public class ReadThread implements Runnable {

    public boolean isread=true;

//    要处理的数量
    private int processnum=100;

    public ReadThread(int processnum) {
        this.processnum = processnum;
    }

    private ConcurrentLinkedQueue<SoutDto> queue=new ConcurrentLinkedQueue();

    public  List<SoutDto> list = new ArrayList<>();

    public void addqueue(SoutDto soutDto){
            queue.add(soutDto);
    }

    private   List all(){
        return  list;
    }


    private   void add(SoutDto soutDto) {
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
            if(list.size()>processnum){
                list.remove(list.size()-1);
            }

        }else{
            if(list.size()<processnum){
                list.add(soutDto);
            }
        }
        long e = System.currentTimeMillis(); //获取结束时间
        long l = e - s;

        if(l>100){
            System.out.println("用时"+l);
        }


    }

    @Override
    public void run() {
        while (isread){
            SoutDto poll = queue.poll();
            while (poll!=null){
                    add(poll);
                poll = queue.poll();
            }
            try {
                Thread.sleep(100);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }
}
