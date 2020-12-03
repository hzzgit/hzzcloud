package com.hzz.hzzcloud.test.接口继承类;

import com.hzz.hzzcloud.test.Student;
import com.hzz.hzzcloud.test.接口继承类2.CallbackPreferringPlatformTransactionManager;
import com.hzz.hzzcloud.test.接口继承类2.TransactionCallbackInter;
import net.fxft.common.jdbc.convert.IResultConvert;

/**
 * @author ：hzz
 * @description：TODO
 * @date ：2020/12/2 16:31
 */
public class 接口继承类test {

    public static void main(String[] args) {
        CallbackPreferringPlatformTransactionManager callbackPreferringPlatformTransactionManager=new CallbackPreferringPlatformTransactionManager() {
            @Override
            public Student execute(String var1, TransactionCallbackInter var2) {
                Student student=new Student();
                student.setName(var1);
                var2.doInTransaction(student);
                return student;
            }
        };


        TransactionCallbackInter tiResultConvert = (student) -> {
            student.setName(student.getName() + "2次写入");
            return student;
        };

        //函数式接口,可以快速返回参数
        Student 测试 = callbackPreferringPlatformTransactionManager.execute("测试", tiResultConvert);

        System.out.println(测试.getName());
    }
}
