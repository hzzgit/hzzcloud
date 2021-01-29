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
                a.${tablecolumn.columnname?lower_case}
            <#else >
                <!--  ${tablecolumn.columncomment}   -->
                a.${tablecolumn.columnname?lower_case},
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
            <#if tablecolumn.datatype=="int" && tablecolumn.columnname?lower_case !="deleted">
                <if test="${tablecolumn.columnname?lower_case} != null ">
                    and a.${tablecolumn.columnname?lower_case}=${r"#"}{${tablecolumn.columnname?lower_case}}
                </if>
            </#if>
            <#if  tablecolumn.columnname?lower_case =="deleted">
                and a.deleted=false
            </#if>
            <#if tablecolumn.datatype=="varchar">
                <if test="${tablecolumn.columnname?lower_case} != null ">
                    and a.${tablecolumn.columnname?lower_case}=${r"#"}{${tablecolumn.columnname?lower_case}}
                    <!-- and a.${tablecolumn.columnname?lower_case} like '%${r"$"}{${tablecolumn.columnname?lower_case}}%'-->
                </if>
            </#if>
            <#if tablecolumn.datatype=="datetime">
                <if test="start${tablecolumn.columnname?lower_case} != null ">
                    and a.${tablecolumn.columnname?lower_case} >= ${r"#"}{start${tablecolumn.columnname?lower_case} }
                </if>
                <if test="end${tablecolumn.columnname?lower_case} != null ">
                    and <![CDATA[ a.${tablecolumn.columnname?lower_case} < ${r"#"}
                    {end${tablecolumn.columnname?lower_case} }]]>
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
        union
        select
        <#list tableColumnList as tablecolumn>
            <#if  tablecolumn_index= tableColumnList?size-1>
                <!--  ${tablecolumn.columncomment}   -->
                a.${tablecolumn.columnname?lower_case}
            <#else >
                <!--  ${tablecolumn.columncomment}   -->
                a.${tablecolumn.columnname?lower_case},
            </#if>
        </#list>
        from
        ${tableschema}.${tablename} as a,
        ${tableschema}.${tablename}userlim as u

        <#if veanddepquanxian?? && veanddepquanxian=true>
            left join vehicle v on a.vehicleId =v.vehicleId and v.deleted=false
        </#if>

        <#if depquanxian??  &&depquanxian=true>
            left join department d on a.depId =d.depId and d.deleted=false
        </#if>
        where 1=1
        and a.${pricolname} =u.mainid

        <#noparse>
            <if test="userid !=null ">
                and u.userId = #{userid}
            </if>
        </#noparse>
        <#list tableColumnList as tablecolumn>
            <#if tablecolumn.datatype=="int" && tablecolumn.columnname?lower_case !="deleted">
                <if test="${tablecolumn.columnname?lower_case} != null ">
                    and a.${tablecolumn.columnname?lower_case}=${r"#"}{${tablecolumn.columnname?lower_case}}
                </if>
            </#if>
            <#if  tablecolumn.columnname?lower_case =="deleted">
                and a.deleted=false
            </#if>
            <#if tablecolumn.datatype=="varchar">
                <if test="${tablecolumn.columnname?lower_case} != null ">
                    and a.${tablecolumn.columnname?lower_case}=${r"#"}{${tablecolumn.columnname?lower_case}}
                    <!-- and a.${tablecolumn.columnname?lower_case} like '%${r"$"}{${tablecolumn.columnname?lower_case}}%'-->
                </if>
            </#if>
            <#if tablecolumn.datatype=="datetime">
                <if test="start${tablecolumn.columnname?lower_case} != null ">
                    and a.${tablecolumn.columnname?lower_case} >= ${r"#"}{start${tablecolumn.columnname?lower_case} }
                </if>
                <if test="end${tablecolumn.columnname?lower_case} != null ">
                    and <![CDATA[ a.${tablecolumn.columnname?lower_case} < ${r"#"}
                    {end${tablecolumn.columnname?lower_case} }]]>
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


    </select>

</mapper>