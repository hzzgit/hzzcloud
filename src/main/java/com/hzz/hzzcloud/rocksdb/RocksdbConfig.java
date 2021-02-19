//package com.hzz.hzzcloud.rocksdb;
//
//import lombok.extern.slf4j.Slf4j;
//import org.rocksdb.Options;
//import org.rocksdb.RocksDB;
//import org.springframework.context.annotation.Bean;
//import org.springframework.context.annotation.Configuration;
//
//import java.io.File;
//import java.nio.file.Files;
//import java.nio.file.Paths;
//
///**
// * @author ：hzz
// * @description：TODO
// * @date ：2020/12/14 17:33
// */
//@Configuration
//@Slf4j
//public class RocksdbConfig {
//
//
//    @Bean
//    public RocksdbUtil getRocksDB() {
//        RocksDB rocksDB =null;
//        try {
//            String dbPath = System.getProperty("user.dir") + File.separator + "rockdb";
//            RocksDB.loadLibrary();
//            Options options = new Options();
//            options.setCreateIfMissing(true);
//            // 文件不存在，则先创建文件
//            if (!Files.isSymbolicLink(Paths.get(dbPath))) {
//                Files.createDirectories(Paths.get(dbPath));
//            }
//            rocksDB= RocksDB.open(options, dbPath);
//        } catch (Exception e) {
//          log.error("创建rocksdb异常",e);
//        }
//        RocksdbUtil rocksdbUtil =new RocksdbUtil(rocksDB);
//        return  rocksdbUtil;
//    }
//
//}
