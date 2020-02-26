package com.xuan.transcation.service;

/**
 * @Author: 轩轩
 * @Date: 2020/2/26 14:02
 * @description:
 */
public interface AccountService {

    /**
     * 转账
     * @param payId 发送的账户id
     * @param receiveidId 接受的账户id
     * @param money  数量
     */
    void TransferAccount(int payId , int receiveidId , double money);
     void TransferAccountNotError (int payId , int receiveidId , double money);
}
