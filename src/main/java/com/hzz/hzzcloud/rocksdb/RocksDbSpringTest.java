//package com.hzz.hzzcloud.rocksdb;
//
//import com.hzz.hzzcloud.test.Student;
//import net.fxft.gateway.util.KryoUtil;
//import org.rocksdb.RocksDB;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.stereotype.Service;
//
//import javax.annotation.PostConstruct;
//
///**
// * @author ：hzz
// * @description：TODO
// * @date ：2020/12/14 17:37
// */
//@Service
//public class RocksDbSpringTest {
//
//    @Autowired
//    private RocksdbUtil rocksdbUtil;
//
//
//    @PostConstruct
//    private void init() throws  Exception{
//        Student student=new Student();
//        student.setName("测试");
//        rocksdbUtil.put("test", (student));
//        Student o = (Student) rocksdbUtil.get("test");
//        System.out.println(1);
//    }
//}
