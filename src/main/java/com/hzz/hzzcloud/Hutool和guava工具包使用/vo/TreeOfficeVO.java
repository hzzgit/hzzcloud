package com.hzz.hzzcloud.Hutool和guava工具包使用.vo;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @author ：hzz
 * @description：TODO
 * @date ：2021/6/11 15:22
 */
@Builder(toBuilder = true)
@Data
@NoArgsConstructor
@AllArgsConstructor
public class TreeOfficeVO {

    private Long officeId;

    /**
     * 父级编号
     */

    private Long parentId;

    /**
     * 名称
     */
    private String name;

    public Long getOfficeId() {
        return officeId;
    }

    public void setOfficeId(Long officeId) {
        this.officeId = officeId;
    }

    public Long getParentId() {
        return parentId;
    }

    public void setParentId(Long parentId) {
        this.parentId = parentId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
