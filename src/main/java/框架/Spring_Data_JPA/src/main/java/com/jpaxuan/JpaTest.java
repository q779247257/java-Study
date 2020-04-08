package com.jpaxuan;

import com.jpaxuan.dao.Jpa_UserDao;
import com.jpaxuan.entity.Jap_User;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

/**
 * @创建人： xuanxuan
 * @创建时间： 2020/4/6
 * @描述：
 * 查询
 *  count：统计
 *  exists系列方法：数据库中是否存在
 *  find系列方法：立即加载
 *  getOne：延迟加载，返回的是一个动态代理对象
 */

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = "classpath:spring-jpa-config.xml")
public class JpaTest {
    @Autowired
    private Jpa_UserDao userDao;

    /**
     * 新增数据  如果id存在则更新  如果id不存在则新增，表自动创建
     */
    @Test
    public void testSave(){
        Jap_User user = new Jap_User();
        user.setId(4);
        user.setName("轩");
        user.setAge(22);
        Jap_User userResult = userDao.save(user);

        System.out.println(userResult);
    }
    /**
     * 根据id删除数据
     */
    @Test
    public void testDelete(){
        //检测id为2的用户是否存在
        boolean b = userDao.existsById(4);
        //如果存在则删除
        if (b){
            userDao.deleteById(4);
        }
        System.out.println("存在的结果为："+b);
    }




}
