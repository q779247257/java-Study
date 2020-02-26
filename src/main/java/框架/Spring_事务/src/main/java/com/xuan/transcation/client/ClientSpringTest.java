package com.xuan.transcation.client;

import com.xuan.transcation.service.AccountService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
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

    @Autowired
    private AccountService accountService;

    @Test
    public void  Test001(){
        accountService.TransferAccountNotError(2,3,1000D);
//        accountService.TransferAccount(2,3,1000D);
    }
}
