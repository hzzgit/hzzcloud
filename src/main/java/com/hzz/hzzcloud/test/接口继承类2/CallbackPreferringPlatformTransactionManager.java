package com.hzz.hzzcloud.test.接口继承类2;

import com.hzz.hzzcloud.test.Student;
import org.apache.poi.ss.formula.functions.T;
import org.springframework.lang.Nullable;
import org.springframework.transaction.TransactionDefinition;
import org.springframework.transaction.TransactionException;

/**
 * @author ：hzz
 * @description：TODO
 * @date ：2020/12/2 16:24
 */
public interface CallbackPreferringPlatformTransactionManager {

   Student execute(String var1, TransactionCallbackInter var2)  ;
}
