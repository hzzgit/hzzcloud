package com.hzz.hzzcloud.aop;

/**
 * @author ：hzz
 * @description：TODO
 * @date ：2020/11/3 14:06
 */
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.*;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;

/**
 * Created by wuwf on 17/4/27.
 */
@Component
@Aspect
@Order(1)
public class UserAccessAspect {

    @Pointcut(value = "@annotation(com.hzz.hzzcloud.aop.UserAccess)")
    public void access() {

    }

    @Before("access()")
    public void deBefore(JoinPoint joinPoint) throws Throwable {
        System.out.println("second before");
    }

    @Around("@annotation(userAccess)")
    public Object around(ProceedingJoinPoint pjp, UserAccess userAccess) {
        //获取注解里的值
        System.out.println("second around:" + userAccess.desc());
        try {
            return pjp.proceed();
        } catch (Throwable throwable) {
            throwable.printStackTrace();
            return null;
        }
    }
}
