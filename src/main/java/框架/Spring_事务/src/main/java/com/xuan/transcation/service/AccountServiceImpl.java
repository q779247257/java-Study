package com.xuan.transcation.service;

import com.xuan.transcation.dao.AccountDao;
import org.aspectj.lang.annotation.Around;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.jdbc.datasource.DataSourceTransactionManager;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.naming.ldap.PagedResultsControl;

/**
 * @Author: 轩轩
 * @Date: 2020/2/26 14:04
 * @description:
 */
@Service("accountServiceImpl")
public class AccountServiceImpl implements AccountService {

    @Autowired
    private AccountDao accountDao;


    /**
     * 转账 测试异常和事务
     * @param payId 发送的账户id
     * @param receiveidId 接受的账户id
     * @param money  数量
     */
    public void TransferAccount(int payId, int receiveidId, double money) {
        accountDao.decreaseMoney(payId,money);
        int i = 1/0;
        accountDao.increaseMoney(receiveidId,money);
    }

    /**
     * 转账无异常
     * @param payId 发送的账户id
     * @param receiveidId 接受的账户id
     * @param money  数量
     */
    public void TransferAccountNotError(int payId, int receiveidId, double money) {
        accountDao.decreaseMoney(payId,money);
        accountDao.increaseMoney(receiveidId,money);
    }
}
