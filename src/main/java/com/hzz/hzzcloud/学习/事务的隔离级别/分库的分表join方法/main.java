package com.hzz.hzzcloud.学习.事务的隔离级别.分库的分表join方法;

import java.util.concurrent.locks.ReentrantLock;

/**
 * @author ：hzz
 * @description：TODO
 * @date ：2020/10/28 11:23
 */
public class main {

    public static void main(String[] args) {

        String ip="127.0.0.1";//假如有两个数据库
        String ip2="127.0.0.2";

        /*第一个数据库有两张分表表*/
        String Student="student1001";
        String Student2="student1010";

        /*第二个数据库有两张分表表*/
        String school="school1002";
        String school2="school1011";

        //他们之间要join查询，有一张分表创建配置表，分别在两个数据库，加一张表配置若库1需要用到库2的表，也就是构建远程表
        //只有一个服务在连接多个数据库进行分表创建,每个库都有相同的配置表和分表创建记录表
        //这时候如果库1需要用到库2 的表，需要在配置表进行配置
        //还要有一张数据库管理配置表，当扫描到每个库的远程表配置表，就开始去扫描不同数据库下是否有相对应的表，如果有则进行查询分表记录表，并进行创建分表连接


        //如果是分库情况下的事务，相对比较麻烦，需要有一个服务作为最终事务的提交者，
        // 这个服务负责接收事务的执行提交，也就是kafka通知，最终汇总两个库的事务，要么一起提交，要么一起回滚


        ReentrantLock reentrantLock=new ReentrantLock();
        boolean b = reentrantLock.tryLock();



    }
}
