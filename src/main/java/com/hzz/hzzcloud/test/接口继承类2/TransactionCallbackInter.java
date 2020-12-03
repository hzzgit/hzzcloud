package com.hzz.hzzcloud.test.接口继承类2;

import com.hzz.hzzcloud.test.Student;
import org.apache.poi.ss.formula.functions.T;
import org.springframework.lang.Nullable;
import org.springframework.transaction.TransactionStatus;

@FunctionalInterface
public interface TransactionCallbackInter {
    @Nullable
    Student doInTransaction(Student var1);
}
