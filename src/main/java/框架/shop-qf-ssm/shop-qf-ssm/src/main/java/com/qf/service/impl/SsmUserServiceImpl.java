package com.qf.service.impl;

import com.qf.dao.SsmUserDao;
import com.qf.pojo.SsmUser;
import com.qf.service.SsmUserService;
import com.qf.utils.Md5Util;
import com.qf.utils.UserParamUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import java.util.List;

/**
 * @Author: 轩轩
 * @Date: 2020/3/6 15:58
 * @description:
 */
@Service
public class SsmUserServiceImpl implements SsmUserService {

    @Autowired private SsmUserDao userDao;


    /**
     * 登录验证
     * @param username 账号
     * @param password 密码
     */
    @Override
    public Integer login(String username, String password) {
        //验证账号和密码不为空
        if (StringUtils.isEmpty(username) || StringUtils.isEmpty(password)) return 3;

        //对密码进行加密
        String encryption = Md5Util.encryption(password);
        SsmUser findUser = new SsmUser();
        //根据账户查询账户是否存在
        findUser.setUsername(username);
        SsmUser userByName =  userDao.findsingleByUser(findUser);
        //账户不存在
        if (userByName == null){ return 4; }

        //设置密码再次查询
        findUser.setPassword(encryption);
        SsmUser userAddPassword =  userDao.findsingleByUser(findUser);

        //密码错误
        if (userAddPassword == null){
            return 5;
        }  else {
            return 0;
        }
    }

    /**
     * 根据账号查询 用户
     * @param username 账号
     * @return 用户pojo
     */
    @Override
    public SsmUser selectByName(String username) {
        SsmUser findUser = new SsmUser();
        findUser.setUsername(username);
        return userDao.findsingleByUser(findUser);
    }

    /**
     * 查询所有
     * @return
     */
    @Override
    public List<SsmUser> selectAll() {
        return userDao.selectAll();
    }

    @Override
    public Integer innert(SsmUser user) {
        SsmUser defaultParam = new UserParamUtil().getDefaultParam(user);
        System.out.println("user 对比："+ (defaultParam == user) );
        return userDao.insertUser(defaultParam);
    }

    @Override
    public SsmUser selectById(Integer id) {
        SsmUser user = new SsmUser();
        user.setId(id);
        return userDao.findsingleByUser(user);
    }

    @Override
    public Integer updateUserById(SsmUser user) {
        return userDao.updateUserById(user);
    }


}
