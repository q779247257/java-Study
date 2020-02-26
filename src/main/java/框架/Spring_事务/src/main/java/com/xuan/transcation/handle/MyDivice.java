package com.xuan.transcation.handle;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.datasource.DataSourceTransactionManager;
import org.springframework.stereotype.Component;
import org.springframework.transaction.TransactionDefinition;
import org.springframework.transaction.TransactionStatus;
import org.springframework.transaction.support.DefaultTransactionDefinition;

/**
 * @Author: 轩轩
 * @Date: 2020/2/26 14:39
 * @description:
 */
@Component
@Aspect
public class MyDivice {
    //JDBC事务管理器
    @Autowired private DataSourceTransactionManager transactional;

    @Pointcut("execution(* com.xuan.transcation.service.AccountServiceImpl.* (..))")
    public void ptl(){ }

    @Around("ptl()")
    public Object around(ProceedingJoinPoint point) throws Throwable {
        DefaultTransactionDefinition def = new DefaultTransactionDefinition();
        def.setPropagationBehavior(TransactionDefinition.PROPAGATION_REQUIRED);
        TransactionStatus status = transactional.getTransaction(def);
        Object proceed=null;
        try {
            System.out.println("转账开始之前");
            point.proceed();
            transactional.commit(status);//无异常后  提交事物
            System.out.println("转账结束");
        }catch (Exception e){
            transactional.rollback(status);//rollback事务回滚
            System.out.println("异常通知：转账异常，事务回滚");
        }finally {
            System.out.println("最终通知");
        }
        return point;
    }

}
