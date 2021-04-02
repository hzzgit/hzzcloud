package nocommon.config_table_filed.entity;

import lombok.*;
import net.fxft.common.jdbc.*;
import java.util.*;

@Data
@DbTable(value="subiaodb.config_table_filed")
public class Config_table_filed implements java.io.Serializable  {

private static final long serialVersionUID = 1L;
    public static final String F_id = "id";
    public static final String F_mainid = "mainid";
    public static final String F_deleted = "deleted";
    public static final String F_colname = "colname";
    public static final String F_annotation = "annotation";
    public static final String F_createdate = "createdate";
    public static final String F_issearchfiled = "issearchfiled";
    public static final String F_searchtype = "searchtype";
    public static final String F_coltype = "coltype";
    public static final String F_isshow = "isshow";
    public static final String F_issort = "issort";
    public static final String F_sorttype = "sorttype";


    /**  主键  */
        @DbId
    private Long  id;
    /**  配置表主键  */
    private Long  mainid;
    /**  删除标志  */
    private Long  deleted;
    /**  字段名  */
    private String  colname;
    /**  字段显示名称  */
    private String  annotation;
    /**  创建时间  */
    private Date  createdate;
    /**  是否是查询条件字段,0=否，1为是  */
    private Long  issearchfiled;
    /**  查询方式,1 = ，2 左like 3 右like 4 全like 5 >= 6 <= 7>=且 <=(传参需要该字段开始条件加start结束条件加end开头) 8 是否下拉框，0是否1为是  */
    private Long  searchtype;
    /**  字段类型，1 字符串 2 数字 3 yyyy-mm-dd 时间 4 yyyy-mm-dd h24:mi:ss 时间  */
    private Long  coltype;
    /**  是否展示，0为否，1为是  */
    private Long  isshow;
    /**  是否排序字段  */
    private Long  issort;
    /**  排序方式，0升序，1降序  */
    private Long  sorttype;

public static void main(String[] args) {
String name="{\n";
    name +="  \"id\":0, //主键\n";
    name +="  \"mainid\":0, //配置表主键\n";
    name +="  \"deleted\":0, //删除标志\n";
    name +="  \"colname\":\"\", //字段名\n";
    name +="  \"annotation\":\"\", //字段显示名称\n";
    name +="  \"createdate\":\"2020-09-11 00:00:00\", //创建时间\n";
    name +="  \"issearchfiled\":0, //是否是查询条件字段,0=否，1为是\n";
    name +="  \"searchtype\":0, //查询方式,1 = ，2 左like 3 右like 4 全like 5 >= 6 <= 7>=且 <=(传参需要该字段开始条件加start结束条件加end开头) 8 是否下拉框，0是否1为是\n";
    name +="  \"coltype\":0, //字段类型，1 字符串 2 数字 3 yyyy-mm-dd 时间 4 yyyy-mm-dd h24:mi:ss 时间\n";
    name +="  \"isshow\":0, //是否展示，0为否，1为是\n";
    name +="  \"issort\":0, //是否排序字段\n";
    name +="  \"sorttype\":0 //排序方式，0升序，1降序\n";
name+="}";
System.out.println(name);

}

}