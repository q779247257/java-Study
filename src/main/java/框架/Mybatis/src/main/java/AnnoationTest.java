import dao.UserAnnoationMapper;
import dao.UserMapper;
import domain.User;
import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import java.io.IOException;
import java.io.InputStream;
import java.util.List;

/**
 * @Author: 轩轩
 * @Date: 2020/2/21 16:58
 * @description: Myabtis注解用法的测试用例
 *
 *
 * @Insert： 实现新增
 * @Update： 实现更新
 * @Delete： 实现删除
 * @Select： 实现查询
 * @Result： 实现结果集的封装
 * @Results: 可以于 @Result 一起使用，封装多个结果集
 * @ResultMap： 实现引用 @Results 定义的封装
 * @One： 实现 一对一 结果集封装
 * @Many： 实现一对多结果集封装
 * @SelectProvider： 实现动态SQL 映射
 * @CacheNamespace： 实现注解二级缓存的使用
 */
public class AnnoationTest {
    private UserAnnoationMapper userAnnoationMapper;
    private SqlSession sqlSession;

    @Before
    public void before() throws IOException {
        //1、加载myabtis配置文件 读取myabtis配置文件
        InputStream resourceAsStream = Resources.getResourceAsStream("SqlMapConfig.xml");
        //2、使用sqlSessionFactoryBuild来创建一个sqlSessionFactory
        SqlSessionFactory sqlSessionFactory = new SqlSessionFactoryBuilder().build(resourceAsStream);
        //3、获取到sql session  进行调取api
        sqlSession = sqlSessionFactory.openSession();
        //4、根据反射获取接口的对象
        userAnnoationMapper  = sqlSession.getMapper(UserAnnoationMapper.class);
    }

//    测试注解查询
    @Test
    public void TestFindAll(){
        List<User> all = userAnnoationMapper.findAlls();
        System.out.println("info:"+all);
    }

    @After
    public void after(){
        sqlSession.commit();
        sqlSession.close();
    }
}
