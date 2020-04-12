package com.xuan.realm;

import com.xuan.dao.ShiroDao;
import com.xuan.dao.ShiroUserDao;
import com.xuan.dao.StaffDao;
import com.xuan.entity.ShiroUser;
import com.xuan.entity.Staff;
import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.AuthenticationInfo;
import org.apache.shiro.authc.AuthenticationToken;
import org.apache.shiro.authc.SimpleAuthenticationInfo;
import org.apache.shiro.authz.AuthorizationInfo;
import org.apache.shiro.authz.SimpleAuthorizationInfo;
import org.apache.shiro.realm.AuthorizingRealm;
import org.apache.shiro.subject.PrincipalCollection;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.HashSet;
import java.util.List;

/**
 * @Author: 轩轩
 * @Date: 2020/4/12 21:25
 * @description: 自定义Realm类
 */
public class MyRealm extends AuthorizingRealm {
    @Autowired
    private ShiroUserDao shiroUserDao;
    @Autowired
    private StaffDao staffDao;

    /**
     * 获取授权信息
     */
    @Override
    protected AuthorizationInfo doGetAuthorizationInfo(PrincipalCollection principalCollection) {

        //获取登录后的合法用户名
        String name = (String) principalCollection.getPrimaryPrincipal();
        //根据用户名查到拥有的角色列表
        List<String> roles = staffDao.findRolesByName(name);
        //根据用户名查到拥有的权限列表
        List<String> perms = staffDao.findPermsByName(name);
        //创建授权信息对象
        SimpleAuthorizationInfo info = new SimpleAuthorizationInfo();
        //设置角色
        info.setRoles(new HashSet<>(roles));
        //设置权限
        info.setStringPermissions(new HashSet<>(perms));
        return info;
    }

    /**
     * 获取认证信息
     */
    @Override
        protected AuthenticationInfo doGetAuthenticationInfo(AuthenticationToken token) throws AuthenticationException {
        //从token中获取用户名
        String name = (String) token.getPrincipal();
        //从数据库查询用户数据
        ShiroUser shiroUser = shiroUserDao.queryByName(name);
        //创建认证信息对象 保存合法认证信息的对象
        SimpleAuthenticationInfo info = null;
        if (shiroUser != null){
            //第一个参数：用户名
            //第二个参数：用户名密码
            //第三个参数： realm名称
            info = new SimpleAuthenticationInfo(name, shiroUser.getPassword(), this.getName());
        }else {
            info = new SimpleAuthenticationInfo(null, null, this.getName());

        }
        return info;
    }
}
