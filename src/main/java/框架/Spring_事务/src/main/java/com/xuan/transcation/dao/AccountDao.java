package com.xuan.transcation.dao;

/**
 * @Author: 轩轩
 * @Date: 2020/2/26 13:58
 * @description:
 */
public interface AccountDao {
    /**
     * 加钱方法
     * @param id
     * @param money
     */
    void increaseMoney(Integer id , Double money);

    /**
     * 减钱方法
     * @param id
     * @param money
     */
    void decreaseMoney(Integer id , Double money);
}
