



####一、MyBatis动态SQL

#####1、动态SQL简介

​	MyBatis 的强大特性之一便是它的动态 SQL。如果你有使用 JDBC 或其他类似框架的经验，你就能体会到根据不同条件拼接 SQL 语句有多么痛苦。拼接的时候要确保不能忘了必要的空格，还要注意省掉列名列表最后的逗号。利用动态 SQL 这一特性可以彻底摆脱这种痛苦。

​	通常使用动态 SQL 不可能是独立的一部分,MyBatis 当然使用一种强大的动态 SQL 语言来改进这种情形,这种语言可以被用在任意的 SQL 映射语句中。

​	动态 SQL 元素和使用 JSTL 或其他类似基于 XML 的文本处理器相似。在 MyBatis 之前的版本中,有很多的元素需要来了解。MyBatis 3 大大提升了它们,现在用不到原先一半的元素就可以了。MyBatis 采用功能强大的基于 OGNL 的表达式来消除其他元素。

​	mybatis 的动态sql语句是基于OGNL表达式的。可以方便的在 sql 语句中实现某些逻辑. 总体说来mybatis 动态SQL 语句主要有以下几类:

- if 语句 (简单的条件判断)
- choose (when,otherwize) ,相当于java 语言中的 switch ,与 jstl 中的choose 很类似.
- trim (对包含的内容加上 prefix,或者 suffix 等，前缀，后缀)
- where (主要是用来简化sql语句中where条件判断的，能智能的处理 and or ,不必担心多余导致语法错误)
- set (主要用于更新时)
- foreach (在实现 mybatis in 语句查询时特别有用)

#####2、进行判断

######2.1、if元素

​	动态 SQL 通常要做的事情是有条件地包含 where 子句的一部分。比如:

```xml
<select id="findActiveBlogWithTitleLike"
     resultType="Blog">
  SELECT * FROM BLOG 
  WHERE state = ‘ACTIVE’ 
  <if test="title != null">
    AND title like #{title}
  </if>
</select>
```

​	这条语句提供了一个可选的文本查找类型的功能。如果没有传入“title”，那么所有处于“ACTIVE”状态的BLOG都会返回；反之若传入了“title”，那么就会把模糊查找“title”内容的BLOG结果返回(就这个例子而言，细心的读者会发现其中的参数值是可以包含一些掩码或通配符的)。

​	如果想可选地通过“title”和“author”两个条件搜索该怎么办呢？首先，改变语句的名称让它更具实际意义；然后只要加入另一个条件即可。

```xml
<select id="findActiveBlogLike"
     resultType="Blog">
  SELECT * FROM BLOG WHERE state = ‘ACTIVE’ 
  <if test="title != null">
    AND title like #{title}
  </if>
  <if test="author != null and author.name != null">
    AND author_name like #{author.name}
  </if>
</select>
```

​	Mybatis if 标签可用在许多类型的 [SQL ](http://www.qf.com/sql/)语句中，我们以查询为例。首先看一个很普通的查询：

```xml
<!-- 查询用户列表，like用户名称 -->  
<select id="getUserListLikeName" parameterType="User" resultMap="userResultMap">  
    SELECT * from user u   
WHERE u.username LIKE CONCAT(CONCAT('%', #{username}),'%')  
</select>  
```

​	但是当 username 或 sex 为 null 时，此语句很可能报错或查询结果为空。此时我们使用 if 动态 sql 语句先进行判断，如果值为 null 或等于空字符串，我们就不进行此条件的判断，增加灵活性。

​	参数为实体类：User。将实体类中所有的属性均进行判断，如果不为空则执行判断条件。

```xml
<!-- 添加 if(判断参数) - 将实体类 User 不为空的属性作为 where 条件 -->  
<select id="getUserList" resultMap="resultMap_User" parameterType="com.qf.pojo.User">  
    SELECT u.username,  
           u.password,  
           u.sex,  
           u.birthday,  
           u.photo,  
           u.score,  
           u.sign
      FROM user u   
     WHERE  
    <if test="username !=null ">  
        u.username LIKE CONCAT(CONCAT('%', #{username, jdbcType=VARCHAR}),'%')  
    </if>  
    <if test="sex!= null and sex != '' ">  
        AND u.sex = #{Sex, jdbcType=INTEGER}  
    </if>  
    <if test="birthday != null ">  
        AND u.birthday = #{birthday, jdbcType=DATE}  
    </if>  

    <if test="userId != null and userId != '' ">  
        AND id.user_id = #{userId, jdbcType=VARCHAR}  
    </if>   
</select> 
```

​	使用时比较灵活，创建新的一个这样的实体类，我们需要限制那个条件，只需要附上相应的值就会 where 这个条件，相反不去赋值就可以不在 where 中判断。

```java
public void select_by_if() {  
    User user = new User();  
    user.setUsername("");  
    user.setSex(1);  
    user.setBirthday(DateUtil.parse("1990-08-18"));  
    List<User> userList = this.dynamicSqlMapper.getUserList_if(user);  
    for (user u : userList) {  
        System.out.println(u.toString());  
    }  
} 
```

我们再看看一下另一个示例，先来看看下面的代码：

```xml
<select id="dynamicIfTest" parameterType="Blog" resultType="Blog">
        select * from t_blog where 1 = 1
        <if test="title != null">
            and title = #{title}
        </if>
        <if test="content != null">
            and content = #{content}
        </if>
        <if test="owner != null">
            and owner = #{owner}
        </if>
    </select>
```

​	这条语句的意思非常简单，如果提供了 title 参数，那么就要满足 title=#{title}，同样如果提供了 Content 和 Owner 的时候，它们也需要满足相应的条件，之后就是返回满足这些条件的所有 Blog，这是非常有用的一个功能，以往我们使用其他类型框架或者直接使用 JDBC的时候， 如果我们要达到同样的选择效果的时候，我们就需要拼 SQL 语句，这是极其麻烦的，比起来，上述的动态SQL就比较简单了。

######2.2、choose元素

​	有时候我们并不想应用所有的条件，而只是想从多个选项中选择一个。而使用if标签时，只要test中的表达式为 true，就会执行 if 标签中的条件。MyBatis 提供了 choose 元素。if标签是与(and)的关系，而 choose 是或(or)的关系。

​	choose标签是按顺序判断其内部when标签中的test条件出否成立，如果有一个成立，则 choose 结束。当 choose 中所有 when 的条件都不满则时，则执行 otherwise 中的sql。类似于Java 的 switch 语句，choose 为 switch，when 为 case，otherwise 则为 default。

​	例如下面例子，同样把所有可以限制的条件都写上，方面使用。choose会从上到下选择一个when标签的test为true的sql执行。安全考虑，我们使用where将choose包起来，放置关键字多于错误。

```xml
<!--  choose(判断参数) - 按顺序将实体类 User 第一个不为空的属性作为：where条件 -->  
<select id="getUserList_choose" resultMap="resultMap_user" parameterType="com.qf.pojo.User">  
    SELECT *  
      FROM User u   
    <where>  
        <choose>  
            <when test="username !=null ">  
                u.username LIKE CONCAT(CONCAT('%', #{username, jdbcType=VARCHAR}),'%')  
            </when >  
            <when test="sex != null and sex != '' ">  
                AND u.sex = #{sex, jdbcType=INTEGER}  
            </when >  
            <when test="birthday != null ">  
                AND u.birthday = #{birthday, jdbcType=DATE}  
            </when >  
            <otherwise>  
            </otherwise>  
        </choose>  
    </where>    
</select>  
```

​	choose (when,otherwize) ,相当于java 语言中的 switch ,与 jstl 中 的 choose 很类似。

```xml
<select id="dynamicChooseTest" parameterType="Blog" resultType="Blog">
        select * from t_blog where 1 = 1 
        <choose>
            <when test="title != null">
                and title = #{title}
            </when>
            <when test="content != null">
                and content = #{content}
            </when>
            <otherwise>
                and owner = "owner1"
            </otherwise>
        </choose>
    </select>
```

​	when元素表示当 when 中的条件满足的时候就输出其中的内容，跟 JAVA 中的 switch 效果差不多的是按照条件的顺序，当 when 中有条件满足的时候，就会跳出 choose，即所有的 when 和 otherwise 条件中，只有一个会输出，当所有的我很条件都不满足的时候就输出 otherwise 中的内容。所以上述语句的意思非常简单， 当 title!=null 的时候就输出 and titlte = #{title}，不再往下判断条件，当title为空且 content!=null 的时候就输出 and content = #{content}，当所有条件都不满足的时候就输出 otherwise 中的内容。

#####3、拼关键字

######3.1、where元素

​	当 where 中的条件使用的 if 标签较多时，这样的组合可能会导致错误。当 java 代码按如下方法调用时：

```java
@Test  
public void select_test_where() {  
    User user = new User();  
    user.setUsername(null);  
    user.setSex(1);  
    List<User> userList = this.dynamicSqlMapper.getUsertList_where(user);  
    for (User u : userList ) {  
        System.out.println(u.toString());  
    }  
}  
```

​	如果上面例子，参数 username 为 null，将不会进行列 username 的判断，则会直接导“WHERE AND”关键字多余的错误 SQL。

​	这时可以使用 where 动态语句来解决。“where”标签会知道如果它包含的标签中有返回值的话，它就插入一个‘where’。此外，如果标签返回的内容是以 AND 或OR 开头的，则它会剔除掉。

​	上面例子修改为：

```xml
<select id="getUserList_whereIf" resultMap="resultMap_User" parameterType="com.qf.pojo.User">  
    SELECT u.user_id,  
           u.username,  
           u.sex,  
           u.birthday 
      FROM User u
    <where>  
        <if test="username !=null ">  
            u.username LIKE CONCAT(CONCAT('%', #{username, jdbcType=VARCHAR}),'%')  
        </if>  
        <if test="sex != null and sex != '' ">  
            AND u.sex = #{sex, jdbcType=INTEGER}  
        </if>  
        <if test="birthday != null ">  
            AND u.birthday = #{birthday, jdbcType=DATE}  
        </if> 
    </where>    
</select>  
```

​	where 主要是用来简化 sql 语句中 where 条件判断，自动地处理 AND/OR 条件。

```xml
<select id="dynamicWhereTest" parameterType="Blog" resultType="Blog">
        select * from t_blog 
        <where>
            <if test="title != null">
                title = #{title}
            </if>
            <if test="content != null">
                and content = #{content}
            </if>
            <if test="owner != null">
                and owner = #{owner}
            </if>
        </where>
    </select>

```

​	where 元素的作用是会在写入 where 元素的地方输出一个 where，另外一个好处是你不需要考虑 where 元素里面的条件输出是什么样子的，MyBatis 会智能的帮处理，如果所有的条件都不满足那么 MyBatis 就会查出所有的记录，如果输出后是 and 开头的，MyBatis 会把第一个and忽略，当然如果是 or 开头的，MyBatis 也会把它忽略；此外，在 where 元素中你不需要考虑空格的问题，MyBatis 会智能的帮你加上。像上述例子中，如果 title=null， 而 content != null，那么输出的整个语句会是 select * from t_blog where content = #{content}，而不是 select * from t_blog where and content = #{content}，因为 MyBatis 会自动地把首个 and / or 给忽略。

######3.2、set元素

​	当 update 语句中没有使用 if 标签时，如果有一个参数为 null，都会导致错误。

​	当在 update 语句中使用if标签时，如果前面的if没有执行，则或导致逗号多余错误。使用set标签可以将动态的配置 SET 关键字，并剔除追加到条件末尾的任何不相关的逗号。使用 if+set 标签修改后，如果某项为 null 则不进行更新，而是保持数据库原值。如下示例：

```xml
<!--  if/set(判断参数) - 将实体 User类不为空的属性更新 -->  
<update id="updateUser_if_set" parameterType="com.pojo.User">  
    UPDATE user  
    <set>  
        <if test="username!= null and username != '' ">  
            username = #{username},  
        </if>  
        <if test="sex!= null and sex!= '' ">  
           sex = #{sex},  
        </if>  
        <if test="birthday != null ">  
            birthday = #{birthday},  
        </if>  
    </set>  
    WHERE user_id = #{userid};      
</update>  
```

​	再看看下面的一个示例：

```xml
<update id="dynamicSetTest" parameterType="Blog">
        update t_blog
        <set>
            <if test="title != null">
                title = #{title},
            </if>
            <if test="content != null">
                content = #{content},
            </if>
            <if test="owner != null">
                owner = #{owner}
            </if>
        </set>
        where id = #{id}
    </update>
```

​	set 标签元素主要是用在更新操作的时候，它的主要功能和 where 标签元素其实是差不多的，主要是在包含的语句前输出一个 set，然后如果包含的语句是以逗号结束的话将会把该逗号忽略，如果 set 包含的内容为空的话则会出错。有了 set 元素就可以动态的更新那些修改了的字段。

######3.3、trim元素

​	trim 是更灵活用来去处多余关键字的标签，它可以用来实现 where 和 set 的效果。

```xml
<!-- 使用 if/trim 代替 where(判断参数) - 将 User 类不为空的属性作为 where 条件 -->  
<select id="getUsertList_if_trim" resultMap="resultMap_User">  
    SELECT * 
      FROM user u
    <trim prefix="WHERE" prefixOverrides="AND|OR">  
        <if test="username !=null ">  
            u.username LIKE CONCAT(CONCAT('%', #{username, jdbcType=VARCHAR}),'%')  
        </if>  
        <if test="sex != null and sex != '' ">  
            AND u.sex = #{sex, jdbcType=INTEGER}  
        </if>  
        <if test="birthday != null ">  
            AND u.birthday = #{birthday, jdbcType=DATE}  
        </if>
    </trim>     
</select>  
```

​	trim 代替 set

```xml
<!-- if/trim代替set(判断参数) - 将 User 类不为空的属性更新 -->  
<update id="updateUser_if_trim" parameterType="com.qf.pojo.User">  
    UPDATE user  
    <trim prefix="SET" suffixOverrides=",">  
        <if test="username != null and username != '' ">  
            username = #{username},  
        </if>  
        <if test="sex != null and sex != '' ">  
            sex = #{sex},  
        </if>  
        <if test="birthday != null ">  
            birthday = #{birthday},  
        </if>  
         
    </trim>  
    WHERE user_id = #{user_id}  
</update>  
```

trim (对包含的内容加上 prefix,或者 suffix 等，前缀，后缀)

```xml
<select id="dynamicTrimTest" parameterType="Blog" resultType="Blog">
        select * from t_blog 
        <trim prefix="where" prefixOverrides="and |or">
            <if test="title != null">
                title = #{title}
            </if>
            <if test="content != null">
                and content = #{content}
            </if>
            <if test="owner != null">
                or owner = #{owner}
            </if>
        </trim>
    </select>
```

​	trim 元素的主要功能是可以在自己包含的内容前加上某些前缀，也可以在其后加上某些后缀，与之对应的属性是 prefix 和 suffix；可以把包含内容的首部某些内容覆盖，即忽略，也可以把尾部的某些内容覆盖，对应的属性是 prefixOverrides 和 suffixOverrides；正因为 trim 有这样的功能，所以我们也可以非常简单的利用 trim 来代替 where 元素的功能。

#####4、进行循环

######4.1、foreach元素

​	foreach的主要用在构建in条件中，它可以在SQL语句中进行迭代一个集合。
​	foreach元素的属性主要有 item，index，collection，open，separator，close。

- ​    item表示集合中每一个元素进行迭代时的别名，

- ​    index指 定一个名字，用于表示在迭代过程中，每次迭代到的位置，

- ​    open表示该语句以什么开始，

- ​    separator表示在每次进行迭代之间以什么符号作为分隔 符，

- ​    close表示以什么结束。

  在使用foreach的时候最关键的也是最容易出错的就是collection属性，该属性是必须指定的，但是在不同情况 下，该属性的值是不一样的，主要有一下3种情况：
  ​    1. 如果传入的是单参数且参数类型是一个List的时候，collection属性值为list
  ​    2. 如果传入的是单参数且参数类型是一个array数组的时候，collection的属性值为array

  ​    3.如果传入的参数是多个的时候，我们就需要把它们封装成一个Map了，当然单参数也可以封装成map，实际上如果你在传入参数的时候，在breast里面也是会把它封装成一个Map的，map的key就是参数名，所以这个时候collection属性值就是传入的List或array对象在自己封装的map里面的key 

  下面分别来看看上述三种情况的示例代码：

######4.1.1、单参数List的类型

```xml
<select id="dynamicForeachTest" resultType="Blog">
           select * from t_blog where id in
       <foreach collection="list" index="index" item="item" open="(" separator="," close=")">
                #{item}       
       </foreach>    
    </select>
```

​	上述collection的值为list，对应的Mapper是这样的
​		public List dynamicForeachTest(List ids);
​	测试代码：

```java
  @Test
      public void dynamicForeachTest() {
          SqlSession session = Util.getSqlSessionFactory().openSession();      
          BlogMapper blogMapper = session.getMapper(BlogMapper.class);
           List ids = new ArrayList();
           ids.add(1);
           ids.add(3);
            ids.add(6);
          List blogs = blogMapper.dynamicForeachTest(ids);
          for (Blog blog : blogs)
              System.out.println(blog);
          session.close();
      }
```

######4.1.2、单参数array数组的类型

```xml
 <select id="dynamicForeach2Test" resultType="Blog">
     select * from t_blog where id in
     <foreach collection="array" index="index" item="item" open="(" separator="," close=")">
          #{item}
     </foreach>
 </select>    
```

​	上述collection为array，对应的Mapper代码：

​		public List dynamicForeach2Test(int[] ids);

​	对应的测试代码：

```java
  @Test
  public void dynamicForeach2Test() {
          SqlSession session = Util.getSqlSessionFactory().openSession();
          BlogMapper blogMapper = session.getMapper(BlogMapper.class);
          int[] ids = new int[] {1,3,6,9};
          List blogs = blogMapper.dynamicForeach2Test(ids);
          for (Blog blog : blogs)
          System.out.println(blog);    
          session.close();
 }
```

######4.1.3、自己把参数封装成Map的类型

```xml
 <select id="dynamicForeach3Test" resultType="Blog">
         select * from t_blog where title like "%"#{title}"%" and id in
          <foreach collection="ids" index="index" item="item" open="(" separator="," close=")">
               #{item}
          </foreach>
 </select>
```

 

​	上述collection的值为ids，是传入的参数Map的key，对应的Mapper代码：
​		public List dynamicForeach3Test(Map params);
​	对应测试代码：

```java
@Test
    public void dynamicForeach3Test() {
        SqlSession session = Util.getSqlSessionFactory().openSession();
         BlogMapper blogMapper = session.getMapper(BlogMapper.class);
          final List ids = new ArrayList();
          ids.add(1);
          ids.add(2);
          ids.add(3);
          ids.add(6);
         ids.add(7);
         ids.add(9);
        Map params = new HashMap();
         params.put("ids", ids);
         params.put("title", "中国");
        List blogs = blogMapper.dynamicForeach3Test(params);
         for (Blog blog : blogs)
             System.out.println(blog);
         session.close();
     }
```

#### 