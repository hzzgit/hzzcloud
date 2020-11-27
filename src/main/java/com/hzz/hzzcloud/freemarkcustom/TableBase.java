package com.hzz.hzzcloud.freemarkcustom;

import java.util.ArrayList;
import java.util.List;

/**
 * @author ：hzz
 * @description：TODO
 * @date ：2020/11/27 11:39
 */

public class TableBase {
    private String packpath;

    private List<ColmunVo> coluvos=new ArrayList<>();

    public String getPackpath() {
        return packpath;
    }

    public void setPackpath(String packpath) {
        this.packpath = packpath;
    }

    public List<ColmunVo> getColuvos() {
        return coluvos;
    }

    public void setColuvos(List<ColmunVo> coluvos) {
        this.coluvos = coluvos;
    }
}
