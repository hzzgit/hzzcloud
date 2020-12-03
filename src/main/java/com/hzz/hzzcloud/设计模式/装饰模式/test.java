package com.hzz.hzzcloud.设计模式.装饰模式;

import com.hzz.hzzcloud.test.Student;
import com.hzz.hzzcloud.test.接口继承类2.TransactionCallbackInter;
import org.springframework.transaction.TransactionDefinition;
import org.springframework.transaction.interceptor.DefaultTransactionAttribute;

import java.util.function.Supplier;

/**
 * @author ：hzz
 * @description：TODO
 * @date ：2020/12/2 15:04
 */
public class test {
    public static void main(String[] args) {
        Source source=new Source();
        Sourceable sourceable=new Decorator(source);
        sourceable.method();


            String name="例子";
        String getstr = test.getstr(() -> {
            return name+"你逗我";
        });
        System.out.println(getstr);

        TransactionCallbackInter transactionCallbackInter=(stu) -> { System.out.println(stu.getName());
            stu.setName("ccc");
            return stu;
        };

        Student student=new Student();
        student.setName("测试");
        Student student1 = transactionCallbackInter.doInTransaction(student);
        System.out.println(student1.getName());

    }

    public static  String getstr(Supplier<String> res){
        return res.get()+res.get();
    }





}
