package com.qf.utils;

import com.qf.pojo.SsmUser;
import org.springframework.util.StringUtils;

import java.util.Date;

/**
 * @Author: 轩轩
 * @Date: 2020/3/9 14:57
 * @description:
 */
public class UserParamUtil{

    /**
     *  设置用户的头像和用户的状态
     * @param user      * pojo实体
     * @return 设置默认了的头像和状态的用户pojo
     */
    public SsmUser getDefaultParam(SsmUser user){
        Date date = new Date();
        //如果头像为null，则设置一个默认的头像
        if (StringUtils.isEmpty(user.getUserHeadPortraitUrl())) user.setUserHeadPortraitUrl("https://xuanandjava.oss-cn-shanghai.aliyuncs.com/123.jpg");
        //设置状态
        if (StringUtils.isEmpty(user.getUserState())) user.setUserState(0);
        //加密密码
        String newPassword = Md5Util.encryption(user.getPassword());
        user.setPassword(newPassword);

        user.setUpdatTime(date);//设置更新时间
        user.setCreateTime(date);//设置新增时间
        return user;
    }
}
