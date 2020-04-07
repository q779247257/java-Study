package com.qfedu.menu;

import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;

import java.io.IOException;
import java.io.Reader;
import java.util.List;

public class Demo {
    private static SqlSessionFactory sessionFactory = null;
    static{
        // 读取mybatis的主配置文件
        Reader reader = null;
        try {
            reader = Resources.getResourceAsReader("mybatis.xml");
        } catch (IOException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        // 获取SqlSessionFactory 对象
        sessionFactory = new SqlSessionFactoryBuilder().build(reader);
    }



    public static void findMenus(){
        SqlSession session = sessionFactory.openSession();
        MenuDao mapper = session.getMapper(MenuDao.class);
        List<VMenuInfo> list = mapper.findMenu("zhangsan");
        list.forEach(item ->{
            System.out.println(item);
            item.getCmenuList().forEach(value ->{
                System.out.println(value);
            });
        });
        session.commit();
        session.close();
    }

    public static void main(String[] args) {
        Demo.findMenus();
    }



}
