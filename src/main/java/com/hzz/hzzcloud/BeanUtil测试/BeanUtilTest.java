package com.hzz.hzzcloud.BeanUtil测试;

import com.hzz.hzzcloud.test.Student;
import org.springframework.beans.BeanUtils;

/**
 * @author ：hzz
 * @description：TODO
 * @date ：2020/10/30 14:11
 */
public class BeanUtilTest {

    public static void main(String[] args) {
        Student student = BeanUtils.instantiateClass(Student.class);
        System.out.println(1);
    }
}
