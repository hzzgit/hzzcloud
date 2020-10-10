package com.hzz.hzzcloud.test.反射注入一个类;

/**
 * @author ：hzz
 * @description：TODO
 * @date ：2020/9/30 17:10
 */
public class School {
    private Student student;
    private String SchoolName;

    public String getSchoolName() {
        return SchoolName;
    }

    public void setSchoolName(String schoolName) {
        SchoolName = schoolName;
    }

    public Student getStudent() {
        return student;
    }

    public void setStudent(Student student) {
        this.student = student;
    }
}
