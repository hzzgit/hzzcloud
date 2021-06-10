<?xml version="1.0" encoding="UTF-8"?>
<!DOCTYPE mapper
        PUBLIC "-//mybatis.org//DTD Mapper 3.0//EN"
        "http://mybatis.org/dtd/mybatis-3-mapper.dtd">

<!--${tableconment}mapper -->
<mapper namespace="${packageName}.mapper.T${entityName}Mapper">

    <!--查询${tableconment}列表 -->
    <select id="list"  resultType="${packageName}.common.vo.${sentityName}.${entityName}TableVO">
             select
        <#if parkquanxian??  &&parkquanxian=true>
                t2.park_name,    <!--  车场名称   -->
                t2.operator_id,  <!--  运营商主键   -->
                t2.operator_name,<!--  运营商名称  -->
                t2.city_id,      <!--  城市id  -->
                t2.city_name,    <!--  城市名称  -->
        </#if>
        <#list tableColumnList as tablecolumn>
            <#if  tablecolumn_index= tableColumnList?size-1>
                t1.${tablecolumn.columnnameold}
            <#else >
                t1.${tablecolumn.columnnameold},<!--  ${tablecolumn.columncomment}   -->
            </#if>
        </#list>
             from ${tablename} as t1
        <#if parkquanxian??  &&parkquanxian=true>
             join t_park t2 ON t1.park_guid = t2.park_guid
        </#if>
             where 1=1
        <#list tableColumnList as tablecolumn>
            <#if tablecolumn.columnname !="park_guid">
            <!--  ${tablecolumn.columncomment}   -->
            <#if tablecolumn.datatype=="int" || tablecolumn.datatype=="bigint" >
                <if test="${tablecolumn.columnname} != null ">
                    and t1.${tablecolumn.columnnameold}=${r"#"}{${tablecolumn.columnname}}
                </if>
            </#if>
            <#if tablecolumn.datatype=="varchar" || tablecolumn.datatype=="char">
                <if test="${tablecolumn.columnname} != null  and ${tablecolumn.columnname} != ''">
                    and t1.${tablecolumn.columnnameold}=${r"#"}{${tablecolumn.columnname}}
                    <!-- and t1.${tablecolumn.columnnameold} like '%${r"$"}{${tablecolumn.columnname}}%'-->
                </if>
            </#if>
            <#if tablecolumn.datatype=="datetime" || tablecolumn.datatype=="date">
                <if test="start${tablecolumn.columnname} != null and start${tablecolumn.columnname} != ''">
                    and t1.${tablecolumn.columnnameold} >= ${r"#"}{start${tablecolumn.columnname} }
                </if>
                <if test="end${tablecolumn.columnname} != null and end${tablecolumn.columnname} != ''">
                    and <![CDATA[ t1.${tablecolumn.columnnameold} < ${r"#"}{end${tablecolumn.columnname} }]]>
                </if>
            </#if>
            </#if>
        </#list>
        <#if parkquanxian?? && parkquanxian=true>
                <if test="operatorId != null  and operatorId != ''">
                    and t2.operator_id = ${r"#"}{operatorId}
                </if>
                <include refid="${packageName}.mapper.TParkMapper.ParkGuidsWHERE">
                    <property name="alias" value="t2."/>
                </include>
        </#if>
        <#if ordercol??>
            order by t1.${ordercol} desc
        </#if>

    </select>


</mapper>