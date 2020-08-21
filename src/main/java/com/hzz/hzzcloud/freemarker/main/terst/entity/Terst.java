package com.hzz.hzzcloud.freemarker.main.terst.entity;

import lombok.*;
import net.fxft.common.jdbc.*;
import java.util.*;

@Data
@DbTable(value="subiaodb.terst")
public class Terst implements java.io.Serializable  {

private static final long serialVersionUID = 1L;
    public static final String F_id = "id";
    public static final String F_id2 = "id2";


    /**  主键  */
        @DbId
    private Long  id;
    /**    */
    private Long  id2;

public static void main(String[] args) {
String name="{\n";
        name +="  \"id\":0, //主键\n";
        name +="  \"id2\":0 //\n";
name+="}";
System.out.println(name);

}

}