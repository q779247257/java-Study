package com.xuan.transcation.service;

import com.xuan.transcation.dao.AccountDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.TransactionStatus;
import org.springframework.transaction.support.TransactionCallbackWithoutResult;
import org.springframework.transaction.support.TransactionTemplate;

/**
 * @Author: 轩轩
 * @Date: 2020/2/26 15:25
 * @description:
 */
@Service("accountServiceAoontaionImpl")
public class AccountServiceAoontaionImpl implements AccountService  {

    @Autowired
    private AccountDao accountDao;
    @Autowired
    private TransactionTemplate template;
    /**
     * 转账 测试异常和事务
     * @param payId 发送的账户id
     * @param receiveidId 接受的账户id
     * @param money  数量
     */
    public void TransferAccount(int payId, int receiveidId, double money) {
        template.execute(new TransactionCallbackWithoutResult() {
            @Override
            protected void doInTransactionWithoutResult(TransactionStatus transactionStatus) {
                accountDao.decreaseMoney(payId,money);
                int i = 1/0;
                accountDao.increaseMoney(receiveidId,money);
            }
        });

    }

    /**
     * 转账无异常
     * @param payId 发送的账户id
     * @param receiveidId 接受的账户id
     * @param money  数量
     */
    public void TransferAccountNotError(int payId, int receiveidId, double money) {
        template.execute(new TransactionCallbackWithoutResult() {
            @Override
            protected void doInTransactionWithoutResult(TransactionStatus transactionStatus) {
                accountDao.decreaseMoney(payId,money);
                accountDao.increaseMoney(receiveidId,money);
            }
        });
    }
}
