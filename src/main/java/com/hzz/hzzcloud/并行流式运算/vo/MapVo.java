package com.hzz.hzzcloud.并行流式运算.vo;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @author ：hzz
 * @description：TODO
 * @date ：2021/6/11 14:14
 */
@Builder(toBuilder = true)
@AllArgsConstructor
@NoArgsConstructor
@Data
public class MapVo {
    private Integer id;

    private String name;

    private Boolean validFlag;

    public Boolean getValidFlag() {
        return validFlag;
    }

    public void setValidFlag(Boolean validFlag) {
        this.validFlag = validFlag;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    @Override
    public String toString() {
        return "MapVo{" +
                "id=" + id +
                ", name='" + name + '\'' +
                '}';
    }
}
