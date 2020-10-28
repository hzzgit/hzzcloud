package com.hzz.hzzcloud.学习.事务的隔离级别;

/**
 * @author ：hzz
 * @description：TODO
 * @date ：2020/8/11 15:54
 */
public class 事务的隔离级别 {

 /*   read uncommitted 可以读取未提交的数据，会导致脏读因为它未提交，但是却读取到了
    read committed 读取提交的数据。会出现重复读，原因是因为update的提交也会读取到，这种时候两次读取的内容不一致
    repeatable read(MySQL默认隔离级别) 可以重复读取，但有幻读 ，只读取insert并且提交的sql，但是在对于统计数量等，
    在MySQL中，其他事务新增的数据，看不到，不会产生幻读。采用多版本并发控制（MVCC）机制解决幻读问题。
    serializable 可读，不可写。像java中的锁，写数据必须等待另一个事务结束。其实就是直接锁表了*/
 public static void main(String[] args) {


     String sql="SELECT\n" +
             "\tg.simNo \n" +
             "FROM\n" +
             "\tgpsrealdata g\n" +
             "\tLEFT JOIN imggatherisexsitdevice b ON g.simNo = b.simNo \n" +
             "WHERE\n" +
             "\tg.updateDate > DATE_ADD( NOW( ), INTERVAL - 30 MINUTE ) \n" +
             "\tAND b.simNo IS NULL";
     System.out.println(sql);


 }
}
