package com.xuan.transcation.service;

import com.xuan.transcation.dao.AccountDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.TransactionStatus;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.transaction.support.TransactionCallbackWithoutResult;
import org.springframework.transaction.support.TransactionTemplate;

/**
 * @Author: 轩轩
 * @Date: 2020/2/26 16:16
 * @description:
 *  @Transactional注解可以直接用于接口定义和接口方法,类定义和类的public方法上.
 * 但Spring建议在业务实现类上使用@Transactional注解,当然也可以添加到业务接口上,
 * 但是这样会留下一些容易被忽视的隐患,因为注解不能被继承,所以业务接口中标注
 * 的@Transactional注解不会被业务类实现继承.方法出添加注解会覆盖类定义处的注解,
 */
@Service("accountServiceAnnotateImpl")
@Transactional
public class AccountServiceAnnotateImpl implements AccountService {
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
     * @Transactional（
     *            readOnly ： 是否只读
     *            isolation = Isolation.READ_COMMITTED 隔离级别  = 不可重复读
     *  ）
     */
    @Transactional(readOnly = false , isolation = Isolation.READ_COMMITTED)
    public void TransferAccountNotError(int payId, int receiveidId, double money) {
                accountDao.decreaseMoney(payId,money);
                accountDao.increaseMoney(receiveidId,money);
    }
}
