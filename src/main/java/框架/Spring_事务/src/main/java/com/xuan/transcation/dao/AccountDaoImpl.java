package com.xuan.transcation.dao;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

/**
 * @Author: 轩轩
 * @Date: 2020/2/26 13:59
 * @description:
 */
@Repository//交给Spring容器
public class AccountDaoImpl implements AccountDao{

    @Autowired
    private JdbcTemplate jdbcTemplate;

    /**
     * 加钱方法
     */
    public void increaseMoney(Integer id, Double money) {
        jdbcTemplate.update("update ar_account set money = money + ? where id = ?",money,id);
    }
    /**
     * 减去钱方法
     */
    public void decreaseMoney(Integer id, Double money) {
        jdbcTemplate.update("update ar_account set money = money - ? where id = ?",money,id);
    }
}
