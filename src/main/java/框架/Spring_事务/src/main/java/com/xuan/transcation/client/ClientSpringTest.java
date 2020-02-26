package com.xuan.transcation.client;

import com.xuan.transcation.service.AccountService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

/**
 * @Author: 轩轩
 * @Date: 2020/2/26 14:06
 * @description:
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration("classpath:applicationContext.xml")
public class ClientSpringTest {

    @Autowired//（AOP实现事务回滚）
    @Qualifier("accountServiceImpl")
    private AccountService accountService;


    @Autowired//XML 实现事务回滚
    @Qualifier("accountServiceAoontaionImpl")
    private AccountService accountServiceAoon;

    @Test//（AOP实现事务回滚）
    public void  Test001() {
        accountService.TransferAccount(2, 3, 1000D);
    }

    @Test//XML 实现事务回滚
    public void  Test002() {
        accountServiceAoon.TransferAccount(2, 3, 1000D);
    }
}
