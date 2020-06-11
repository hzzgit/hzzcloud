package com.hzz.hzzcloud.freemarker.Vo;

import java.util.List;


public class TableVo {

    private String pricolname;//主键名称
    private String tableschema;//所在数据库
    private String tablename;//表名
    private String packpath;//class所在package位置
    private String classname;//自定义的class名称

    private PackageVo packageVo;//各个类所在的package位置
    private  Boolean veanddepquanxian;//是否生成车辆机构权限
    private  Boolean depquanxian;//是否生成机构权限,如果上面的有,这个不生效
    private String entityName;//大写的类名
    private String tableconment;//表注释

    private String ordercol;//排序字段

    private List<TableColumn> tableColumnList;//字段内容

    public String getClassname() {
        return classname;
    }

    public void setClassname(String classname) {
        this.classname = classname;
    }

    public String getOrdercol() {
        return ordercol;
    }

    public void setOrdercol(String ordercol) {
        this.ordercol = ordercol;
    }

    public PackageVo getPackageVo() {
        return packageVo;
    }

    public void setPackageVo(PackageVo packageVo) {
        this.packageVo = packageVo;
    }

    public String getPricolname() {
        return pricolname;
    }

    public void setPricolname(String pricolname) {
        this.pricolname = pricolname;
    }

    public String getTableconment() {
        return tableconment;
    }

    public void setTableconment(String tableconment) {
        this.tableconment = tableconment;
    }

    public String getEntityName() {
        return entityName;
    }

    public void setEntityName(String entityName) {
        this.entityName = entityName;
    }

    public String getTableschema() {
        return tableschema;
    }

    public void setTableschema(String tableschema) {
        this.tableschema = tableschema;
    }

    public String getTablename() {
        return tablename;
    }

    public void setTablename(String tablename) {
        this.tablename = tablename;
    }

    public String getPackpath() {
        return packpath;
    }

    public void setPackpath(String packpath) {
        this.packpath = packpath;
    }

    public Boolean getVeanddepquanxian() {
        return veanddepquanxian;
    }

    public void setVeanddepquanxian(Boolean veanddepquanxian) {
        this.veanddepquanxian = veanddepquanxian;
        depquanxian=null;
    }

    public Boolean getDepquanxian() {
        return depquanxian;
    }

    public void setDepquanxian(Boolean depquanxian) {
        if(veanddepquanxian==null) {
            this.depquanxian = depquanxian;
        }
    }

    public List<TableColumn> getTableColumnList() {
        return tableColumnList;
    }

    public void setTableColumnList(List<TableColumn> tableColumnList) {
        this.tableColumnList = tableColumnList;
    }
}
