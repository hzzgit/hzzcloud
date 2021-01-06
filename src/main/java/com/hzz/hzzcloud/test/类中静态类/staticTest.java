package com.hzz.hzzcloud.test.类中静态类;

/**
 * @author ：hzz
 * @description：TODO
 * @date ：2021/1/6 11:54
 */
public class staticTest {

    public cla1 getcla1(){
        return  new cla1();
    }


    public static class  cla1{

        private String school;

        public cla1() {
        }

        public String getSchool() {
            return school;
        }

        public void setSchool(String school) {
            this.school = school;
        }
    }


    public static class  cla2{

        private String name;
        private int age;

        public cla2() {
        }

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }

        public int getAge() {
            return age;
        }

        public void setAge(int age) {
            this.age = age;
        }
    }
}
