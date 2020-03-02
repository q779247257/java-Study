package com.ssm.service;

import com.ssm.dao.SsmUserMapper;
import com.ssm.pojo.SsmUser;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @Author: 轩轩
 * @Date: 2020/3/2 18:06
 * @description:
 */
@Service
public class SsmUserServiceImpl implements SsmUserService {
    @Autowired
    private SsmUserMapper ssmUserMapper;
    @Override
    public List<SsmUser> selectAll() {
        List<SsmUser> users = ssmUserMapper.selectAll();
        System.out.println("users:"+users);
        return users;
    }
}
