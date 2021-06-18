package com.hzz.hzzcloud.并行流式运算.flatMap;

import java.util.*;
import java.util.stream.Collectors;

/**
 * @author ：hzz
 * @description：TODO
 * @date ：2021/6/11 16:20
 */
public class flatMapTest {


    private static class Klass {
        private int field;

        public Klass(int field) {
            this.field = field;
        }

        @Override
        public String toString() {
            return "field=" + field;
        }
    }

    private static Klass[] getKlassList(Integer... id){
        Klass[] klasses=new Klass[id.length];
        for (int i = 0; i < id.length; i++) {
            klasses[i]=new Klass(id[i]);
        }
        return klasses;
    }

    private static class KlassGroup {
        private List<Klass> group = new ArrayList<>();

        public KlassGroup(Klass... objList) {
            for (Klass item : objList) {
                this.group.add(item);
            }
        }

        public List<Klass> getKlassList() {
            return group;
        }
    }

    public static void main(String[] args) {
        List<KlassGroup> groupList = Arrays.asList(
                new KlassGroup(new Klass(1), new Klass(2), new Klass(3)),
                new KlassGroup(new Klass(4), new Klass(5), new Klass(6)),
                new KlassGroup(new Klass(7), new Klass(8), new Klass(9)),
                new KlassGroup(new Klass(10))
        );

        List<Klass[]> klasses=new ArrayList<>();
        klasses.add(getKlassList(1,2,3));
        klasses.add(getKlassList(4,5,6));
        klasses.add(getKlassList(7,8,9,10));

        List<KlassGroup> collect3 = klasses
                .parallelStream()
                .map(KlassGroup::new)
                .distinct()
                .filter(p->p.getKlassList().size()>1)
                .filter(Objects::nonNull)
                .collect(Collectors.toList());




        //用map会导致出现这种
        List<List<Klass>> collect = groupList.parallelStream()
                .map(KlassGroup::getKlassList)
                .collect(Collectors.toList());

        List<Klass> collect4 = collect.stream()
                .map(List::iterator)
                .map(Iterator::next)
                .collect(Collectors.toList());

        collect.stream().map(List::iterator)
                .forEach(p->{
                    while (p.hasNext()){
                        System.out.println(p.next());
                    }
                });


        //使用flatMap，就可以快速将
        List<Klass> collect1 = groupList.parallelStream()
                .flatMap(klassGroup -> {
                 return    klassGroup.getKlassList().stream();
                })
                .collect(Collectors.toList());

        List<Klass> collect2 = groupList.parallelStream()
                .flatMap(klassGroup -> klassGroup.getKlassList().stream())
                .collect(Collectors.toList());




        System.out.println(1);
    }
}
