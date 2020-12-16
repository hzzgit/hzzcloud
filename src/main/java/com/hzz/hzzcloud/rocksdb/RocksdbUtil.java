package com.hzz.hzzcloud.rocksdb;

import lombok.extern.slf4j.Slf4j;
import net.fxft.gateway.util.KryoUtil;
import org.rocksdb.RocksDB;
import org.rocksdb.RocksDBException;

/**
 * @author ：hzz
 * @description：TODO
 * @date ：2020/12/14 17:38
 */
@Slf4j
public class RocksdbUtil {

    private RocksDB rocksDB;

    public RocksdbUtil(RocksDB rocksDB){
        this.rocksDB=rocksDB;
    }

    public void put(String key,Object value){
        try {
            rocksDB.put(key.getBytes(), KryoUtil.object2clsbyte(value));
        } catch (RocksDBException e) {
            log.error("添加rocksDB:key值数据异常",e);
        }
    }

    public Object get(String key){
        Object object=null;
        try {
            byte[] bytes = new byte[0];
            bytes = rocksDB.get(key.getBytes());
             object = KryoUtil.clsbyte2object(bytes);
        } catch (RocksDBException e) {
            log.error("获取rocksDB:key值数据异常",e);
        }
        return object;
    }
}
