import dao.UserDetailMapper;
import dao.UserLoginMapper;
import dao.UserMapper;
import domain.User;
import domain.UserDetail;
import domain.UserLogin;
import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;
import org.junit.Test;
import java.io.IOException;
import java.io.InputStream;
import java.util.Date;
import java.util.HashMap;
import java.util.List;

public class client {
    //mybatis的原始用法
    @Test
     public void testMabtis() throws IOException {
        //1、加载myabtis配置文件 读取myabtis配置文件
        InputStream resourceAsStream = Resources.getResourceAsStream("SqlMapConfig.xml");

        //2、使用sqlSessionFactoryBuild来创建一个sqlSessionFactory
        SqlSessionFactory sqlSessionFactory = new SqlSessionFactoryBuilder().build(resourceAsStream);

        //3、获取到sql session  进行调取api
        SqlSession sqlSession = sqlSessionFactory.openSession();

        //4、根据mapper文件的路径进行调取api
        User user = sqlSession.selectOne("UserTest.selectByid", 1);
        System.out.println("info:"+user);
     }

     //使用接口调用mapper映射文件中的sql
     @Test
     public void testImplMybatis() throws IOException {
         //1、加载myabtis配置文件 读取myabtis配置文件
         InputStream resourceAsStream = Resources.getResourceAsStream("SqlMapConfig.xml");

         //2、使用sqlSessionFactoryBuild来创建一个sqlSessionFactory
         SqlSessionFactory sqlSessionFactory = new SqlSessionFactoryBuilder().build(resourceAsStream);

         //3、获取到sql session  进行调取api
         SqlSession sqlSession = sqlSessionFactory.openSession();

         //4、根据反射获取接口的对象
         UserMapper userMapper = sqlSession.getMapper(UserMapper.class);
         User user = userMapper.selectByid2(1);
         System.out.println("info:"+user);
     }
     //查询全部数据
     @Test
     public void selectAll() throws IOException {
         //1、加载myabtis配置文件 读取myabtis配置文件
         InputStream resourceAsStream = Resources.getResourceAsStream("SqlMapConfig.xml");
         //2、使用sqlSessionFactoryBuild来创建一个sqlSessionFactory
         SqlSessionFactory sqlSessionFactory = new SqlSessionFactoryBuilder().build(resourceAsStream);
         //3、获取到sql session  进行调取api
         SqlSession sqlSession = sqlSessionFactory.openSession();
         //4、根据反射获取接口的对象
         UserMapper userMapper = sqlSession.getMapper(UserMapper.class);
         List<User> users = userMapper.selectAllUser();
         for (User u : users){
             System.out.println("info:"+u);
         }
     }
    //增加数据
    @Test
    public void addUser() throws IOException {
        //1、加载myabtis配置文件 读取myabtis配置文件
        InputStream resourceAsStream = Resources.getResourceAsStream("SqlMapConfig.xml");
        //2、使用sqlSessionFactoryBuild来创建一个sqlSessionFactory
        SqlSessionFactory sqlSessionFactory = new SqlSessionFactoryBuilder().build(resourceAsStream);
        //3、获取到sql session  进行调取api
        SqlSession sqlSession = sqlSessionFactory.openSession();
        //4、根据反射获取接口的对象
        UserMapper userMapper = sqlSession.getMapper(UserMapper.class);
        User user = new User();
        user.setAddress("宁他爹");
        user.setName("善");
        user.setSex("男");
        user.setBirthday(new Date());

        Integer isAdd= userMapper.insertByUser(user);
        sqlSession.commit();//提交数据库事务
        System.out.println("增加的结果为："+isAdd);
    }

    //根据id修改User数据
    @Test
    public void editByUserId() throws IOException {
        //1、加载myabtis配置文件 读取myabtis配置文件
        InputStream resourceAsStream = Resources.getResourceAsStream("SqlMapConfig.xml");
        //2、使用sqlSessionFactoryBuild来创建一个sqlSessionFactory
        SqlSessionFactory sqlSessionFactory = new SqlSessionFactoryBuilder().build(resourceAsStream);
        //3、获取到sql session  进行调取api
        SqlSession sqlSession = sqlSessionFactory.openSession();
        //4、根据反射获取接口的对象
        UserMapper userMapper = sqlSession.getMapper(UserMapper.class);

        System.out.println("修改之前的结果为："+userMapper.selectByid2(11));

        User user = new User();
        user.setAddress("宁他爹");
        user.setName("善");
        user.setSex("女");
        user.setBirthday(new Date());
        user.setId(11);
        userMapper.editByUserId(user);
        System.out.println("修改之后的结果为："+userMapper.selectByid2(11));
        sqlSession.commit();//提交数据库事务
    }

    //Map传参，无注解
    //注意：map的key要和sql中的占位符保持名字一致
    @Test
    public void selectOnMap() throws IOException {
        //1、加载myabtis配置文件 读取myabtis配置文件
        InputStream resourceAsStream = Resources.getResourceAsStream("SqlMapConfig.xml");
        //2、使用sqlSessionFactoryBuild来创建一个sqlSessionFactory
        SqlSessionFactory sqlSessionFactory = new SqlSessionFactoryBuilder().build(resourceAsStream);
        //3、获取到sql session  进行调取api
        SqlSession sqlSession = sqlSessionFactory.openSession();
        //4、根据反射获取接口的对象
        UserMapper userMapper = sqlSession.getMapper(UserMapper.class);
        HashMap<String, Object> map = new HashMap<String, Object>();
        map.put("offset",0);//从第几条开始
        map.put("pagesize",5);//查看几条
        List<User> users = userMapper.selectByMap(map);

        for (User s : users){
            System.out.println(s);
        }
    }

    /**
     * 测试（不通过延迟加载，使用多次查询出来）
     */
    @Test
    public void userTest001() throws IOException {
        //1、加载myabtis配置文件 读取myabtis配置文件
        InputStream resourceAsStream = Resources.getResourceAsStream("SqlMapConfig.xml");
        //2、使用sqlSessionFactoryBuild来创建一个sqlSessionFactory
        SqlSessionFactory sqlSessionFactory = new SqlSessionFactoryBuilder().build(resourceAsStream);
        //3、获取到sql session  进行调取api
        SqlSession sqlSession = sqlSessionFactory.openSession();
        //4、根据反射获取接口的对象
        UserDetailMapper userDetailMapper = sqlSession.getMapper(UserDetailMapper.class);
        UserLoginMapper userLoginMapper = sqlSession.getMapper(UserLoginMapper.class);

        //查询所有的userDetail
        List<UserDetail> userDetails = userDetailMapper.selectAll();

        for (UserDetail userDetail : userDetails){
            //拿到userId
            int userId = userDetail.getId();
            UserLogin userLogin = userLoginMapper.findByid(userId);
            System.out.println("info:"+userLogin);
        }
    }

    /**
     * 测试（使用延迟加载 + 嵌套对象）
     */

    @Test
    public void userTest002() throws IOException {
        //1、加载myabtis配置文件 读取myabtis配置文件
        InputStream resourceAsStream = Resources.getResourceAsStream("SqlMapConfig.xml");
        //2、使用sqlSessionFactoryBuild来创建一个sqlSessionFactory
        SqlSessionFactory sqlSessionFactory = new SqlSessionFactoryBuilder().build(resourceAsStream);
        //3、获取到sql session  进行调取api
        SqlSession sqlSession = sqlSessionFactory.openSession();
        //4、根据反射获取接口的对象
        UserDetailMapper userDetailMapper = sqlSession.getMapper(UserDetailMapper.class);
        List<UserDetail> userDetails = userDetailMapper.selectAll();
        System.out.println(userDetails.get(0).getId());
    }

    /**
     * 嵌套对象 2条sql语句
     * @throws IOException
     */
    @Test
    public void userTest003() throws IOException {
        //1、加载myabtis配置文件 读取myabtis配置文件
        InputStream resourceAsStream = Resources.getResourceAsStream("SqlMapConfig.xml");
        //2、使用sqlSessionFactoryBuild来创建一个sqlSessionFactory
        SqlSessionFactory sqlSessionFactory = new SqlSessionFactoryBuilder().build(resourceAsStream);
        //3、获取到sql session  进行调取api
        SqlSession sqlSession = sqlSessionFactory.openSession();
        //4、根据反射获取接口的对象
        UserDetailMapper userDetailMapper = sqlSession.getMapper(UserDetailMapper.class);
        UserDetail userDetail = userDetailMapper.selectById(1);
        System.out.println("info:"+userDetail);
    }

    /**
     * 嵌套对象 1条sql语句 无懒加载
     * @throws IOException
     */
    @Test
    public void userTest004() throws IOException {
        //1、加载myabtis配置文件 读取myabtis配置文件
        InputStream resourceAsStream = Resources.getResourceAsStream("SqlMapConfig.xml");
        //2、使用sqlSessionFactoryBuild来创建一个sqlSessionFactory
        SqlSessionFactory sqlSessionFactory = new SqlSessionFactoryBuilder().build(resourceAsStream);
        //3、获取到sql session  进行调取api
        SqlSession sqlSession = sqlSessionFactory.openSession();
        //4、根据反射获取接口的对象
        UserDetailMapper userDetailMapper = sqlSession.getMapper(UserDetailMapper.class);
        UserDetail userDetail = userDetailMapper.selectByIdOne(2);
        System.out.println("info:"+userDetail);
    }

    /**
     * 嵌套集合
     * @throws IOException
     */
    @Test
    public void userTest005() throws IOException {
        //1、加载myabtis配置文件 读取myabtis配置文件
        InputStream resourceAsStream = Resources.getResourceAsStream("SqlMapConfig.xml");
        //2、使用sqlSessionFactoryBuild来创建一个sqlSessionFactory
        SqlSessionFactory sqlSessionFactory = new SqlSessionFactoryBuilder().build(resourceAsStream);
        //3、获取到sql session  进行调取api
        SqlSession sqlSession = sqlSessionFactory.openSession();
        //4、根据反射获取接口的对象
        UserDetailMapper userDetailMapper = sqlSession.getMapper(UserDetailMapper.class);
        UserDetail userDetail = userDetailMapper.selectByIdList(3);
        System.out.println("info:"+userDetail.getOrdersList());
    }

    //测试Myabtis动态If标签
    @Test
    public void userTestIf001() throws IOException {
        //1、加载myabtis配置文件 读取myabtis配置文件
        InputStream resourceAsStream = Resources.getResourceAsStream("SqlMapConfig.xml");
        //2、使用sqlSessionFactoryBuild来创建一个sqlSessionFactory
        SqlSessionFactory sqlSessionFactory = new SqlSessionFactoryBuilder().build(resourceAsStream);
        //3、获取到sql session  进行调取api
        SqlSession sqlSession = sqlSessionFactory.openSession();
        //4、根据反射获取接口的对象
        UserMapper userMapper = sqlSession.getMapper(UserMapper.class);

        //SQL: SELECT * from user where 1=1 and name = ?
        List<User> resultNotNull = userMapper.selectByUserName("贝哥");
        System.out.println("传参的时候返回:"+resultNotNull);

        //SQL: SELECT * from user where 1=1
        List<User> userNull = userMapper.selectByUserName(null);
        System.out.println("传null的时候返回:"+userNull);
    }

    /**
     * 测试Mybatis动态choose标签
     * choose (when,otherwize) ,相当于java 语言中的 switch ,与 jstl 中的choose 很类似
     * 只要when中的条件有一个成立，则其他的不成立
     */
    @Test
    public void userTestChoose() throws IOException {
        //1、加载myabtis配置文件 读取myabtis配置文件
        InputStream resourceAsStream = Resources.getResourceAsStream("SqlMapConfig.xml");
        //2、使用sqlSessionFactoryBuild来创建一个sqlSessionFactory
        SqlSessionFactory sqlSessionFactory = new SqlSessionFactoryBuilder().build(resourceAsStream);
        //3、获取到sql session  进行调取api
        SqlSession sqlSession = sqlSessionFactory.openSession();
        //4、根据反射获取接口的对象
        UserMapper userMapper = sqlSession.getMapper(UserMapper.class);
        User user = new User();

        //SQL: SELECT * from user where 1=1 and address= "宁他爹"
        //在所有的when标签不符合之后，最终会执行 otherwise 标签
        List<User> userNull = userMapper.selectByUserNameAndChoose(null);
        System.out.println("传null的时候返回:"+userNull);

        user.setSex("男");
        //SQL: SELECT * from user where 1=1 and sex = ?
        List<User> userAddSex = userMapper.selectByUserNameAndChoose(user);
        System.out.println("传加入性别的的时候返回:"+userAddSex);

        user.setName("邢轩轩");
        //如果不设置为null则不执行sql语句 直接返回和userAddSex一样的内容
        //所以，在 Mybtis中 对于 Choose 标签是 只走一个when的 相当于java中的switch 选择标签
        user.setSex(null);
        //SQL: SELECT * from user where 1=1 and name = ?
        List<User> userAddSexAndName = userMapper.selectByUserNameAndChoose(user);

        System.out.println("传加入性别和名字的时候返回:"+userAddSexAndName);
    }

    /**
     *
     * 测试Mybatis动态Where标签
     * where 标签会知道如果它包含的标签中有返回值的话，就会插入一个 where。
     * 此外，如果标签返回的内容是以AND 或 OR 开头的，则它会剔除掉
     * @throws IOException
     */
    @Test
    public void userTestWhere() throws IOException {
        //1、加载myabtis配置文件 读取myabtis配置文件
        InputStream resourceAsStream = Resources.getResourceAsStream("SqlMapConfig.xml");
        //2、使用sqlSessionFactoryBuild来创建一个sqlSessionFactory
        SqlSessionFactory sqlSessionFactory = new SqlSessionFactoryBuilder().build(resourceAsStream);
        //3、获取到sql session  进行调取api
        SqlSession sqlSession = sqlSessionFactory.openSession();
        //4、根据反射获取接口的对象
        UserMapper userMapper = sqlSession.getMapper(UserMapper.class);
        User user = new User();
//       SQL:  select * from user
        List<User> userNull = userMapper.selectByUserNameWhere(null);
        System.out.println("传null的时候返回:"+userNull);

//      SQL: SELECT * from user where 1=1 and name = ?
        user.setName("邢轩轩");
        List<User> userAddSexAndName = userMapper.selectByUserNameAndChoose(user);
        System.out.println("传名字的时候返回:"+userAddSexAndName);
    }

    /**
     * set标签元素主要是用在更新操作的时候，它的主要功能和 where 标签元素其实是差不多的，
     * 主要是在包含的语句前输出一个 set ， 然后如果包含的语句是以逗号结束的话，将会把该逗号忽略，
     * 如果 set 包含的内容为空的话则会报错，有了 setu元素可以动态的更新那些修改了的字段
     */
    @Test
    public void userTestSet() throws IOException {
        //1、加载myabtis配置文件 读取myabtis配置文件
        InputStream resourceAsStream = Resources.getResourceAsStream("SqlMapConfig.xml");
        //2、使用sqlSessionFactoryBuild来创建一个sqlSessionFactory
        SqlSessionFactory sqlSessionFactory = new SqlSessionFactoryBuilder().build(resourceAsStream);
        //3、获取到sql session  进行调取api
        SqlSession sqlSession = sqlSessionFactory.openSession();
        //4、根据反射获取接口的对象
        UserMapper userMapper = sqlSession.getMapper(UserMapper.class);

        User user = new User();
        user.setId(1);
        user.setSex("男");
//        SQL: UPDATE user SET sex = ? where id = ?
        userMapper.updateUserNameAndSxUseSet(user);
        List<User> user1 = userMapper.selectByUserName("贝哥");
        System.out.println("修改第一次的信息为:"+user1);


        user.setSex("女");
        user.setName("吕俊风");
//        SQL： UPDATE user SET name = ?, sex = ? where id = ?
        userMapper.updateUserNameAndSxUseSet(user);
        System.out.println("修改第2次的信息为:"+userMapper.selectByUserName("吕俊风"));

        sqlSession.commit();//提交数据库事务

    }

}
