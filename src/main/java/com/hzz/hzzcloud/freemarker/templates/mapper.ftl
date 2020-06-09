<?xml version="1.0" encoding="UTF-8"?>
<!DOCTYPE mapper PUBLIC "-//mybatis.org//DTD Mapper 3.0//EN"
        "http://mybatis.org/dtd/mybatis-3-mapper.dtd">
<!--${tableconment}mapper -->
<mapper namespace="${tablename}mapper">

    <!--查询${tableconment}列表 -->
    <select id="selectlist" parameterType="java.util.HashMap"
            resultType="java.util.HashMap">
        select
        <#list tableColumnList as tablecolumn>
            <#if  tablecolumn_index= tableColumnList?size-1>
                <!--  ${tablecolumn.columncomment}   -->
                a.${tablecolumn.columnname}
            <#else >
                <!--  ${tablecolumn.columncomment}   -->
                a.${tablecolumn.columnname},
            </#if>
        </#list>
        from
        ${tableschema}.${tablename} as a
        <#if veanddepquanxian?? && veanddepquanxian=true>
            left join vehicle v on a.vehicleId =v.vehicleId and v.deleted=false
        </#if>

        <#if depquanxian??  &&depquanxian=true>
            left join department d on a.depId =d.depId and d.deleted=false
        </#if>
        where 1=1
        <#list tableColumnList as tablecolumn>
            <#if tablecolumn.datatype=="int" && tablecolumn.columnname !="deleted">
                <if test="${tablecolumn.columnname} != null ">
                    and a.${tablecolumn.columnname}=${r"#"}{${tablecolumn.columnname}}
                </if>
            </#if>
            <#if  tablecolumn.columnname =="deleted">
                and a.deleted=false
            </#if>
            <#if tablecolumn.datatype=="varchar">
                <if test="${tablecolumn.columnname} != null ">
                    and a.${tablecolumn.columnname} like '%${r"$"}{${tablecolumn.columnname}}%'
                </if>
            </#if>
            <#if tablecolumn.datatype=="datetime">
                <if test="start${tablecolumn.columnname} != null ">
                    and a.${tablecolumn.columnname} >= ${r"#"}{start${tablecolumn.columnname} }
                </if>
                <if test="end${tablecolumn.columnname} != null ">
                    and <![CDATA[ a.${tablecolumn.columnname} < ${r"#"}{end${tablecolumn.columnname} }]]>
                </if>
            </#if>
        </#list>

        <#if depquanxian?? &&depquanxian=true>
            <#noparse>
                <if test="depIdList != null">
                    and d.depId in
                    <foreach
                            collection="depIdList" index="index" item="item"
                            open="(" separator="," close=")">
                        #{item}
                    </foreach>
                </if>
            </#noparse>
        </#if>


        <#if veanddepquanxian?? && veanddepquanxian=true>
            <#noparse>
                <if test="depIdList != null and vehicleIdList == null">
                    and v.depId in
                    <foreach
                            collection="depIdList" index="index" item="item"
                            open="(" separator="," close=")">
                        #{item}
                    </foreach>
                </if>
                <if test="vehicleIdList != null and depIdList == null">
                    and v.vehicleId in
                    <foreach
                            collection="vehicleIdList" index="index" item="item"
                            open="(" separator="," close=")">
                        #{item}
                    </foreach>
                </if>

                <if test="vehicleIdList != null and depIdList != null">
                    and (v.depId in
                    <foreach
                            collection="depIdList" index="index" item="item"
                            open="(" separator="," close=")">
                        #{item}
                    </foreach>
                    or v.vehicleId in
                    <foreach
                            collection="vehicleIdList" index="index" item="item"
                            open="(" separator="," close=")">
                        #{item}
                    </foreach>
                    )
                </if>
            </#noparse>
        </#if>
        <#if ordercol??>
            order by a.${ordercol} desc
        </#if>

    </select>


</mapper>