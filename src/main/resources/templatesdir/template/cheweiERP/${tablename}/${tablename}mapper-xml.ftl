<?xml version="1.0" encoding="UTF-8"?>
<!DOCTYPE mapper
        PUBLIC "-//mybatis.org//DTD Mapper 3.0//EN"
        "http://mybatis.org/dtd/mybatis-3-mapper.dtd">

<!--${tableconment}mapper -->
<mapper namespace="${packageName}.mapper.T${entityName}Mapper">

    <!--查询${tableconment}列表 -->
    <select id="list"  resultType="${packageName}.common.vo.${sentityName}.${entityName}VO">
        select
        <#if parkquanxian??  &&parkquanxian=true>
        <!--  车场名称   -->
        t2.park_name,
        t2.operator_id,
        t2.operator_name,
        t2.city_id,
        t2.city_name,
        </#if>


        <#list tableColumnList as tablecolumn>
            <#if  tablecolumn_index= tableColumnList?size-1>
                <!--  ${tablecolumn.columncomment}   -->
                t1.${tablecolumn.columnname}
            <#else >
                <!--  ${tablecolumn.columncomment}   -->
                t1.${tablecolumn.columnname},
            </#if>
        </#list>

        from
        ${tableschema}.${tablename} as t1

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
                <if test="start${tablecolumn.columnname} != null and ${tablecolumn.columnname} != ''">
                    and t1.${tablecolumn.columnnameold} >= ${r"#"}{start${tablecolumn.columnname} }
                </if>
                <if test="end${tablecolumn.columnname} != null ">
                    and <![CDATA[ t1.${tablecolumn.columnnameold} < ${r"#"}{end${tablecolumn.columnname} }]]>
                </if>
            </#if>
            </#if>
        </#list>

        <#if parkquanxian?? && parkquanxian=true>
                <include refid="${packageName}.mapper.TParkMapper.ParkGuidsWHERE">
                    <property name="alias" value="t2."/>
                </include>
        </#if>
        <#if ordercol??>
            order by t1.${ordercol} desc
        </#if>

    </select>


</mapper>