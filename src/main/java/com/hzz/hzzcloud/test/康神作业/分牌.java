package com.hzz.hzzcloud.test.康神作业;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Random;

/**
 * @author ：hzz
 * @description：TODO
 * @date ：2020/8/13 17:19
 */
public class 分牌 {
    private List<Integer> all = new ArrayList<>();

    private List<Integer>[] wanjia = new List[3];

    private void 打乱集合() {
        System.out.print("打乱牌为:");
        List<Integer> allluan = new ArrayList<>();
        Random random = new Random();
        int length = all.size();
        for (int i = 0; i < length; i++) {
            Integer integer = all.get(random.nextInt(all.size()));
            allluan.add(integer);
            System.out.print(integer + ",");
            all.remove(integer);
        }
        all = allluan;
        System.out.println("");

    }

    public void 冒泡排序() {
        Integer[] integers = new Integer[]{22, 33, 1, 222, 333, 34, 45, 3, 44, 555, 6, 7, 8, 1, 2, 3};
        for (int i = 0; i < integers.length; i++) {
            for (int i1 = 0; i1 < integers.length; i1++) {
                if (integers[i] < integers[i1]) {
                    Integer a = integers[i1];
                    Integer b = integers[i];
                    integers[i] = a;
                    integers[i1] = b;
                }
            }
        }
        System.out.println(integers);
    }


    private void 玩家牌排序() {
        for (int i = 0; i < wanjia.length; i++) {
            List<Integer> integers=wanjia[i];
            integers = 集合排序(integers);
            wanjia[i]=integers;
        }
        System.out.println("排序后");
        打印玩家牌();
    }

    private List<Integer> 集合排序(List<Integer> integers1) {
        Integer[] integers = (Integer[]) integers1.toArray(new Integer[0]);

        for (int i = 0; i < integers.length; i++) {
            for (int i1 = 0; i1 < integers.length; i1++) {
                if (integers[i] < integers[i1]) {
                    Integer a = integers[i1];
                    Integer b = integers[i];
                    integers[i] = a;
                    integers[i1] = b;
                }
            }
        }
        integers1 = new ArrayList<>(Arrays.asList(integers));
        return integers1;
    }

    public 分牌() {
        System.out.print("初始牌为:");
        for (int i = 0; i < 52; i++) {
            all.add(i + 1);
            System.out.print(i + 1 + ",");
        }
        for (int i = 0; i < wanjia.length; i++) {
            wanjia[i] = new ArrayList<>();
        }
        System.out.println("");

    }

    public void ramdontouser() {
        int length = all.size();
        for (int i = 0; i < length; i++) {
            Integer integer = all.get(i);
            wanjia[i % 3].add(integer);
        }
        打印玩家牌();

    }

    private void 打印玩家牌() {
        for (int i = 0; i < wanjia.length; i++) {
            System.out.print("玩家" + i + 1 + "的牌为:");
            for (Integer integer : wanjia[i]) {
                System.out.print(integer + ",");
            }
            System.out.println("");
        }
    }

    public static void main(String[] args) {
        分牌 test = new 分牌();
        test.打乱集合();
        test.ramdontouser();
        test.玩家牌排序();
    }
}
