package com.hzz.hzzcloud.test;

/**
 * @author ：hzz
 * @description：TODO
 * @date ：2020/9/28 10:26
 */
public  class Final修饰符 {
    private final  int a1;

    public Final修饰符(int a1) {
        this.a1 = a1;
    }

    private static final class AnnotationCacheEntry {

        private final int name;

        private AnnotationCacheEntry(int name) {
            this.name = name;
        }

    }

    public static void main(String[] args) {
        String name ="video;http://fxft.oss-cn-hangzhou.aliyuncs.com/gd/video/02_40_4071_0_091ef22136f749b4a35517e7f7b6622f.mp4,img;http://fxft.oss-cn-hangzhou.aliyuncs.com/gd/video/02_40_4071_0_091ef22136f749b4a35517e7f7b6622f.jpg";

        String[] split = name.split(",");
        for (String s : split) {
            String[] split1 = s.split(";");
            for (String s1 : split1) {
                System.out.println(s1);
            }
        }
    }
}
