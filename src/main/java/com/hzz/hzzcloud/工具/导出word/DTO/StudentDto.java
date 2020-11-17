package com.hzz.hzzcloud.工具.导出word.DTO;

import lombok.Data;

/**
 * @author ：hzz
 * @description：TODO
 * @date ：2020/11/11 10:55
 */
@Data
public class StudentDto {
    private int id;
    private String name;
    private String sex;
    private int age;

    public StudentDto() {
        id=1;
        name="test";
        sex="男";
        age=15;
    }
}
