# SpringMVC

## 一.springMVC概述：

Spring Web MVC是一种基于Java的实现了Web MVC设计模式的请求驱动类型的轻量级Web框架，即使用了MVC架构模式的思想，将web层进行职责解耦，基于请求驱动指的就是使用请求-响应模型，框架的目的就是帮助我们简化开发，Spring Web MVC也是要简化我们日常Web开发的。

### 2.**springMVC的强大之处**

1.Spring MVC 实现了即用的 MVC 的核心概念。它为控制器和处理程序提供了大量与此模式相关的功能。并且当向 MVC 添加反转控制（Inversion of Control，IoC）时，它使应用程序高度解耦，提供了通过简单的配置更改即可动态更改组件的灵活性。Spring MVC 为您提供了完全控制应用程序的各个方面的力量。

2.Spring 的 Web MVC 模块是围绕 DispatcherServlet 而设计的。DispatcherServlet 给处理程序分派请求，执行视图解析，并且处理语言环境和主题解析，此外还为上传文件提供支持。

3.DispatcherServlet 通过使用处理程序映射来决定哪一个处理程序应当处理传入的请求。处理程序映射只是用于标识使用哪一个处理程序来处理特定 URL 模式的映射。处理程序是只有一种方法 ModelAndView handleRequest(request,response) 的控制器接口的实现。Spring 还有一些可用的高级处理程序实现；其中一个重要的高级处理程序实现是 SimpleFormController，它提供了将命令对象绑定到表单、对其执行验证等功能。

### 3.**springMVC优势**

1、清晰的角色划分：前端控制器（DispatcherServlet）、请求到处理器映射（HandlerMapping）、处理器适配器（HandlerAdapter）、视图解析器（ViewResolver）、处理器或页面控制器（Controller）、验证器（ Validator）、命令对象（Command  请求参数绑定到的对象就叫命令对象）、表单对象（Form Object 提供给表单展示和提交到的对象就叫表单对象）。

2、分工明确，而且扩展点相当灵活，可以很容易扩展，虽然几乎不需要；

3、由于命令对象就是一个POJO，无需继承框架特定API，可以使用命令对象直接作为业务对象；

4、和Spring 其他框架无缝集成，是其它Web框架所不具备的；

5、可适配，通过HandlerAdapter可以支持任意的类作为处理器；

6、可定制性，HandlerMapping、ViewResolver等能够非常简单的定制；

7、功能强大的数据验证、格式化、绑定机制；

8、利用Spring提供的Mock对象能够非常简单的进行Web层单元测试；

9、本地化、主题的解析的支持，使我们更容易进行国际化和主题的切换。

10、强大的JSP标签库，使JSP编写更容易。

………………还有比如RESTful风格的支持、简单的文件上传、约定大于配置的契约式编程支持、基于注解的零配置支持等等。

### 4.**首先让我们了解下 MVC（Model-View-Controller）三元组的概念：**

Model（模型）：数据模型，提供要展示的数据，因此包含数据和行为，可以认为是领域模型或 JavaBean 组件（包含数据和行为），不过现在一般都分离开来：Value Object（数据） 和 服务层（行为）。也就是模型提供了模型数据查询和模型数据的状态更新等功能，包括数据和业务。

领域模型

javaBean组件等价于 域模型层 + 业务逻辑层 + 持久层

View(视图):负责进行模型的展示，一般就是我们见到的用户界面，客户想看到的东西。

Controller(控制器):接收用户请求，委托给模型进行处理（状态改变），处理完毕后把返回的模型数据返回给视图，

由视图负责展示。 也就是说控制器做了个调度员的工作，

### 5.运行流程

核心架构的具体流程步骤如下：

1、  首先用户发送请求——>DispatcherServlet，前端控制器收到请求后自己不进行处理，而是委托给其他的解析器进行处理，作为统一访问点，进行全局的流程控制；

2、DispatcherServlet——>HandlerMapping,HandlerMapping将会把请求映射为HandlerExecutionChain对象（包含一个Handler处理器（页面控制器）对象、多个HandlerInterceptor拦截器）对象，通过这种策略模式，很容易添加新的映射策略；

3、  DispatcherServlet——>HandlerAdapter，HandlerAdapter将会把处理器包装为适配器，从而支持多种类型的处理器，即适配器设计模式的应用，从而很容易支持很多类型的处理器；

4、  HandlerAdapter——>处理器功能处理方法的调用，HandlerAdapter将会根据适配的结果调用真正的处理器的功能处理方法，完成功能处理；并返回一个ModelAndView对象（包含模型数据、逻辑视图名）；

5、  ModelAndView的逻辑视图名——> ViewResolver， ViewResolver将把逻辑视图名解析为具体的View，通过这种策略模式，很容易更换其他视图技术；

6、  View——>渲染，View会根据传进来的Model模型数据进行渲染，此处的Model实际是一个Map数据结构，因此很容易支持其他视图技术；

7、返回控制权给DispatcherServlet，由DispatcherServlet返回响应给用户，到此一个流程结束。

![1558425749882](C:\Users\54110\AppData\Roaming\Typora\typora-user-images\1558425749882.png)

## 二.基于SpringMVC 的WEB应用!

### 1.导入JAR包

```
<dependencies>
  <dependency>
    <groupId>junit</groupId>
    <artifactId>junit</artifactId>
    <version>3.8.1</version>
    <scope>test</scope>
  </dependency>
  <dependency>
    <groupId>javax.servlet</groupId>
    <artifactId>javax.servlet-api</artifactId>
    <version>3.1.0</version>
    <scope>provided</scope>
  </dependency>
  <!-- 添加Spring包 -->
  <dependency>
    <groupId>org.springframework</groupId>
    <artifactId>spring-webmvc</artifactId>
    <version>4.3.6.RELEASE</version>
  </dependency>

  <dependency>
    <groupId>org.springframework</groupId>
    <artifactId>spring-context</artifactId>
    <version>4.3.6.RELEASE</version>
  </dependency>
  <dependency>
    <groupId>org.springframework</groupId>
    <artifactId>spring-context-support</artifactId>
    <version>4.3.6.RELEASE</version>
  </dependency>
  <dependency>
    <groupId>org.springframework</groupId>
    <artifactId>spring-web</artifactId>
    <version>4.3.6.RELEASE</version>
  </dependency>
  <dependency>
    <groupId>org.springframework</groupId>
    <artifactId>spring-jdbc</artifactId>
    <version>4.3.6.RELEASE</version>
  </dependency>

  <!-- 为了方便进行单元测试，添加spring-test包 -->
  <dependency>
    <groupId>org.springframework</groupId>
    <artifactId>spring-test</artifactId>
    <version>4.3.6.RELEASE</version>
  </dependency>
  <dependency>
    <groupId>org.springframework</groupId>
    <artifactId>spring-aspects</artifactId>
    <version>4.3.6.RELEASE</version>
  </dependency>
  <dependency>
    <groupId>org.springframework</groupId>
    <artifactId>spring-expression</artifactId>
    <version>4.3.6.RELEASE</version>
  </dependency>
  <dependency>
    <groupId>org.springframework</groupId>
    <artifactId>spring-orm</artifactId>
    <version>4.3.6.RELEASE</version>
  </dependency>
  <dependency>
    <groupId>org.aspectj</groupId>
    <artifactId>aspectjweaver</artifactId>
    <version>1.8.5</version>
  </dependency>
  <!-- jstl -->
  <dependency>
    <groupId>javax.servlet</groupId>
    <artifactId>jstl</artifactId>
    <version>1.2</version>
  </dependency>
</dependencies>
```

### 2.配置web.xml

```
<!DOCTYPE web-app PUBLIC
 "-//Sun Microsystems, Inc.//DTD Web Application 2.3//EN"
 "http://java.sun.com/dtd/web-app_2_3.dtd" >

<web-app>
  <display-name>Archetype Created Web Application</display-name>

  <servlet>
    <servlet-name>spring</servlet-name>
    <servlet-class>org.springframework.web.servlet.DispatcherServlet</servlet-class>

    <!--SpringMVC配置文件的名字  <servlet-name>-servlet.xml
        默认位置:src / resources
      如果放在了 src/resources(maven)
            contextConfigLocation:classpath:文件名即可!
            Web-INF/xx.xml
            contextConfigLocation:/WEB-INF/xx.xml
    -->
    <init-param>
      <param-name>contextConfigLocation</param-name>
      <param-value>classpath*:springmvc-servlet.xml</param-value>
    </init-param>

    <load-on-startup>1</load-on-startup>
  </servlet>
  <!-- 访问DispatcherServlet对应的路径 -->
  <servlet-mapping>
    <servlet-name>spring</servlet-name>
    <url-pattern>/</url-pattern> <!--/不过滤jsp防止死循环-->
  </servlet-mapping>
</web-app>
```

**/\* 强迫所有的请求及响应都经过该servlet**

**/ 将使你配置的servlet成为默认的servlet。/不拦截jsp资源! **

### 3.springmvc的配置文件

```
<?xml version="1.0" encoding="UTF-8"?>
<beans xmlns="http://www.springframework.org/schema/beans"
       xmlns:xsi="http://www.w3.org/2001/XMLSchema-instance"
       xmlns:context="http://www.springframework.org/schema/context"
       xsi:schemaLocation="http://www.springframework.org/schema/beans
              http://www.springframework.org/schema/beans/spring-beans.xsd
            http://www.springframework.org/schema/context
            http://www.springframework.org/schema/context/spring-context.xsd">



    <!-- 启动包扫描功能，以便注册带有@Controllers、@service、@repository、@Component等注解的类成为spring的bean -->
    <context:component-scan base-package="com.qf.controller"/>
    <context:annotation-config />
        <!-- 对模型视图名称的解析，在请求时模型视图名称添加前后缀 -->
    <bean class="org.springframework.web.servlet.view.InternalResourceViewResolver">
        <property name="viewClass" value="org.springframework.web.servlet.view.JstlView"/>
        <property name="prefix" value="/WEB-INF/jsp/"/>    <!-- 前缀 -->
        <property name="suffix" value=".jsp"/>    <!-- 后缀 -->
    </bean>

</beans>
```

### 4.创建controller

```
package com.qf.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * Created by 54110 on 2019-05-11.
 */
@Controller
public class HellowController {
    @RequestMapping("/hellow")
    public String hellow(){
        System.out.println("你好");
        return "hellow";
    }
}
```

@Controller 来源于@Component标示为控制层，用于加在类上。

@RequestMapping("/helloworld")  该方法对应的uri;

控制器类的方法返回字符串类型非常常见,返回字符串,代表根据返回的字符串找到对应的视图!

根据springmvc配置文件中视图解析器(InternalResourceViewResolver) 配置的视图文件的前缀和后缀!

helloworld()方法返回  "helloworld" 会找到 WEB-INF/jsp/helloworld.jsp文件!

######1.4 测试Spring MVC 

发布项目,通过浏览器,访问 当前项目对应地址+ /helloworld即可!

1.2 设置@RequestMapping method属性

> 可以指定方法对应的请求方式!如果客户端请求的方式和方法设置的方式不同,请求不成功!

 @RequestMapping(value = "/helloworld" , method =RequestMethod.GET)

 @RequestMapping(value = "/helloworld" , method =RequestMethod.POST)

**注意: 如果不指定method,可以接收任何类型的请求!如果指定但是访问类型不对会出现405错误!**

### 3.常用的注解

#### 1. @RequestParam表单参数处理

##### 1.1 获取表单参数

- 创建一个登陆表单

```html
<html>
<head>
    <title>Title</title>
</head>
<body>
    <!--action指定controller中对应的方法路径即可!-->
    <form action="/xx/login" method="POST">
        <label for="username">用户名:<input type="text" id="username" name="username" /></label>
        <label for="password">密码:<input type="text" id="password" name="password" /></label>
        <input type="submit" value="登陆">
    </form>
</body>

</html>
```

- 获取参数的控制器

```java
 @RequestMapping("/xx")
 @Controller
 public class HelloWorld {

      @RequestMapping(value = "/helloworld",method = RequestMethod.GET)
      public String hellworld(){
          System.out.println("helloworld");
          return "helloworld";
      }
  	//接收form表单
      @RequestMapping(value = "/login",method = RequestMethod.POST)
      public  String login(String username,String password){
          System.out.println("username = " + username);
          System.out.println("password = " + password);
          return "helloworld";
      }
  }
```



**获取参数,只需要在对应的方法中添加参数即可,如果参数名与请求传参的name值相同即可直接赋值,注意:对应类型很重要,如果是普通的输入框,使用字符串即可,如果是多选框,可以使用List类型的参数接值!**

**如果参数名和name值相同,无需使用@RequestParam注解!**

**注意: 将基本类型转化成包装类型!!**



##### 1.2 方法的参数名与传参的name值不同

  2.1是指name的值和方法参数名相同,开发中,也会碰到请求参数name的值与方法的参数名不同,我们还需要将指定的name对应参数传给方法的指定参数,这时,就不需要使用@RequestParam注解!



```java
//此案例,我们修改了input标签的name值,使得与login方法不同,所以我们需要使用@RequestParam(value = //"name") String username将其指定到 username参数上!

<label for="username">用户名:<input type="text" id="username" name="name" /></label>  //name改为 name

 @RequestMapping(value = "/login",method = RequestMethod.POST)
 public  String login(@RequestParam(value = "name") String username, String password){
        System.out.println("username = " + username);
        System.out.println("password = " + password);
        return "helloworld";
    }  

```



##### 1.3 方法参数设置默认值

  经过2.1和2.2的学习,不管name值和方法参数是否相同,我们都能讲想要的请求参数赋给对应的方法参数上。

但是，有一种特殊情况，如果客户端没有在请求传参，那么我们将得到null,我们不希望得到null,希望得到一个默认值,这个时候,我们还需要使用@RequestParam的defaultValue属性进行对应的设置。



```java
@RequestMapping("/list")
public  String list(@RequestParam(defaultValue = "1") Integer currentPage , @RequestParam(defaultValue = "10") Integer pageSize){
	//设置默认值,如果不传递使用参数的默认值
     System.out.println("currentPage = " + currentPage);
     System.out.println("pageSize = " + pageSize);
     return  "list";
 }
```



#### 2. @PathVariable获取路径参数

我们可以通过此注解,获取路径部分的数据!

例如: http://localhost:8080/user/list/1 

获取路径/list/后面1的数据!

```java
@RequestMapping("/user/list/{id}")

public  String getData(@PathVariable(value = "id") Integer id){

        System.out.println("id = " + id);

        return "list" ;
}

```



代码解释: 将路径中想要获取部分使用 ` {标注名} `标注,在方法对应赋值的参数添加@PathVariable注解即可!value值为标注名!!!



#### 3 . @CookieValue

@CookieValue注解可以获取请求中的cookie!!

 @RequestMapping("/cookie")

```
public String testCookie(@CookieValue("JSESSIONID")String cookie)
{
	System.out.println("cookie:"+cookie);
	return "result";
}
```

#####5. @RequestHeader

@RequestHeader注解可以获取请求头中的数据!!

 @RequestMapping("/header")

```
public String testHeader(@CookieValue("JSESSIONID")String cookie,
		@RequestHeader("User-Agent")String header)
{
	System.out.println("cookie:"+cookie);
	System.out.println("header:"+header);
	return "result";
}
```

### 4.支持的返回参值

- String

  - 情况1: 查找到指定的视图

    return "user/show";

  - 情况2: 转发或者重定向

    return "redirect: path";

    return "forword:path";

- ModelAndView

  ​    返回数据视图模型对象!

  ​    ModelAndView mv = new ModelAndView();

  ​    mv.setViewName("查找视图路径");

  ​    mv.addObject("key","object type data");

- Object

  ​    配合@ResponseBody返回Json数据类型!

- void

   可以返回其他类型的数据!通常将方法定义成void!

  配合方法传参得到Response对象,通过Response.getWriter().writer("数据");	



### 5.其他操作

#### 5.1转发: forward

转发语法:

```java
@RequestMapping("/user")
@Controller
public class UserContoller {
	
    @RequestMapping("/index")
	public String index(Integer size){
    	System.out.println("index method 被调用!"+size);
		return "forward:/user/result";
	}
    
    @RequestMapping("/result")
    public String result(){
    	return "result";
    }
	
}
```

#### 5.2重定向:redirect

重定向语法:

```java
@RequestMapping("/user")
@Controller
public class UserContoller {
	
    @RequestMapping("/index")
	public String index(Integer size){
    	System.out.println("index method 被调用!"+size);
		return "redirect:/user/result";
	}
    
    @RequestMapping("/result")
    public String result(){
    	
    	return "result";
    }
}
```



#### 5.3. 解决参数乱码问题

 Spring MVC中 GET方式不会乱码!

   在web.xml配置文件中添加spring自带的Filter设置编码格式

```xml
  <filter>
    <filter-name>characterEncodingFilter</filter-name>
    <filter-class>org.springframework.web.filter.CharacterEncodingFilter</filter-class>
    <init-param>
      <param-name>encoding</param-name>
      <param-value>UTF-8</param-value>
    </init-param>
    <init-param>
      <param-name>forceEncoding</param-name>
      <param-value>true</param-value>
    </init-param>
  </filter>

  <filter-mapping>
    <filter-name>characterEncodingFilter</filter-name>
    <url-pattern>/*</url-pattern>
  </filter-mapping>
```

### 三.实战练习-向controller传递对象类型数据

> 通过form表单向指定的controller的方法传递对象!

##### 1. 创建Pojo对象

Address.java

```java
package com.itqf.springmvc.pojo;

public class Address {

	private String province;
	private String city;
	//toString,getter,setter
}	
```

User.java

```java
public class User {
	
	private String username;
	private String password;
	private Integer  age;
	private Address  address;
	//toString,getter,setter
}  
```



##### 2. 创建控制器类

```java
@RequestMapping("/user")
@Controller
public class UserContoller {
	//跳转到 WEB-INF/user/form.jsp
	@RequestMapping("/form")
	public String from(){
		return "user/form";
	}
    //form表单提交数据到此处!获取在转发到success.jsp
	@RequestMapping("/add")
	public String add(User user){
		System.out.println(user);
		return "success";
	}
}  
```



##### 3. 创建form.jsp

文件位置: /WEB-INF/user/form.jsp

```java
    <form action="user/userEntity" method="post">
       用户名:<input type="text" value="${user.name}" id="name" name="name" /></label><br/>
        密码:<input type="password" id="password" name="password" /></label><br/>
       年龄:<input type="text" value="${user.age }" name="age" /></label><br/>
      省份:<input type="text" value="${user.address.province }" name="address.province" /></label><br/>
        城市:<input type="text" value="${user.address.city }" name="address.city" /></label><br/>
        特产名称:<input type="text" name="address.sp.pname" /></label><br/>
        特产价格:<input type="text" name="address.sp.price" /></label><br/>
        <input type="submit"  value="提交" />
    </form>
```



**注意:name的特殊写法,这里可以直接将表单数据转成User对象,但是User对象内部包含 Address的对象,所以,这里可以调用第一层属性,再点一层属性,如果多层依次类推!**

#####4. 创建success.jsp 

 文件位置: /WEB-INF/success.jsp 

#####5. 测试 

​     访问跳转路径即可!!!

### 四：文件上传下载

##### 1. 引入jar包!

  commons-fileupload.jar 

  commons-io.jar

  maven项目pom.xml

```xml
<!-- https://mvnrepository.com/artifact/commons-fileupload/commons-fileupload -->
<dependency>
    <groupId>commons-fileupload</groupId>
    <artifactId>commons-fileupload</artifactId>
    <version>1.3.1</version>
</dependency>

<!-- https://mvnrepository.com/artifact/commons-io/commons-io -->
<dependency>
    <groupId>commons-io</groupId>
    <artifactId>commons-io</artifactId>
    <version>2.4</version>
</dependency>

```

##### 2. 配置MultipartResolver

配置

```xml
 <!--multipartResolver配置-->
 <bean id="multipartResolver"
       class="org.springframework.web.multipart.commons.CommonsMultipartResolver"
       p:defaultEncoding="UTF-8"
       p:maxUploadSize="5242880"
       p:uploadTempDir="file:/d:/temp"
 />
```



##### 3. 编写控制器和文件上传表单

- 编写文件上传表单  upload.jsp

```jsp
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Title</title>
</head>
<body>
  <center>
      <form method="post" enctype="multipart/form-data" action="/user/upload">
            <label for="name">文件名称<input type="text" id="name" name="name" /></label>
            <input type="file" name="file" />
            <button>提交</button>
      </form>
  </center>
</body>
</html>
```



- 编写控制器代码

```java
 @RequestMapping("/toUpload")
 public String toUpload(){
    //跳转到上传页面
    return  "user/upload";
 }

 @RequestMapping("/upload")
 public  String saveFile(@RequestParam("name") String name , @RequestParam("file")MultipartFile file) throws IOException {
    //接收表单提交的数据,包含文件
    System.out.println("name = " + name);
    if (!file.isEmpty())
     {
       file.transferTo(new File("G:/temp/"+file.getOriginalFilename()));
     }
   return "success";
 }
```



​    SpringMVC会将上传文件绑定到MultipartFile对象上. MultipartFile提供了获取长传文件内容,文件名等方法,通过transferTo()方法还可将文件存储到磁盘中,具体方法如下:



  

|           方法名称           |                    方法解释                    |
| :--------------------------: | :--------------------------------------------: |
|      byte [] getBytes()      |                  获取文件数据                  |
|   String getContentType()    | 获取文件MIMETYPE类型,如image/jpeg,text/plain等 |
| InputStream getInputStream() |                 获取文件输入流                 |
|       String getName()       |        获取表单中文件组件的名称 name值!        |
| String getOriginalFilename() |               获取文件上传的原名               |
|        long getSize()        |         获取文件的字节大小,单位为byte          |
|      boolean isEmpty()       |                是否有长传的文件                |
|  void transferTo(File dest)  |       可以将上传的文件保存到指定的文件中       |

### 五：restful类型

REST:即Representational State Transfer , (资源)表现层状态转化,是目前最流行的一种互联网软件架构。它结构清晰、符合标注、易于理解、方便扩展，所以越来越多的网站采用！

具体说，就是HTTP协议里面,四个表示操作方式的动词:

GET POST PUT DELETE

它们分别代表着四种基本操作:

- GET用来获取资源
- POST用来创建新资源
- PUT用来更新资源
- DELETE用来删除资源

示例:

- /order/1 HTTP GET :得到id = 1 的 order
- /order/1 HTTP DELETE: 删除 id=1 的order
- /order/1 HTTP  PUT : 更新id = 1的 order
- /order  HTTP POST : 新增 order

#### 1. Spring中实现RESTful风格

HiddenHttpMethodFilter:浏览器form表单只支持GET和POST,不支持DELETE和PUT请求,Spring添加了一个过滤器,可以将这些请求转换为标准的http方法,支持GET,POST,DELETE,PUT请求!

#### 2.具体实现

##### 2.1 web.xml添加HiddenHttpMethodFilter配置

```xml
<filter>
    <filter-name>HiddenHttpMethodFilter</filter-name>
    <filter-class>org.springframework.web.filter.HiddenHttpMethodFilter</filter-class>
 </filter>
 <filter-mapping>
    <filter-name>HiddenHttpMethodFilter</filter-name>
    <url-pattern>/*</url-pattern>
 </filter-mapping>
```



##### 2.2 实现查,改,删 框架!

```java
@Controller
@RequestMapping("/order")
public class OrderController
{
    @RequestMapping(value = "/list",method = RequestMethod.GET)
    public  String list(){
        //获取用户列表
        return "order/list";
    }

    @RequestMapping(value = "/{id}",method = RequestMethod.DELETE)
    public  String delete(@PathVariable("id") Integer id){
        //执行删除操作
        System.out.println("id = " + id);
        return "redirect:/order/list";
    }


    @RequestMapping(value = "/{id}",method = RequestMethod.PUT)
    public  String update(@PathVariable("id") Integer id){
        //执行更新操作
        System.out.println("id = " + id);
        return "redirect:/order/list";
    }

}

```



Jsp代码:

```jsp
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Title</title>
</head>
<body>
     <h1>获取用户列表</h1>
     <a href="javascript:void(0)" onclick="deleteById()">删除</a>
     <form action="/order/1" method="post" id="deleteForm">
           <input type="hidden" name="_method" value="DELETE" />
     </form>
        

     <a href="javascript:void(0)" onclick="updateById()">修改</a>
     <form action="/order/1" method="post" id="updateForm">

         <input type="hidden" name="_method" value="PUT" />

     </form>

     <script>
          function updateById() {
              var form = document.getElementById("updateForm");
              form.submit();
          }

         function deleteById() {
            //TODO 删除
             var form = document.getElementById("deleteForm");
             form.submit();
         }

     </script>

</body>
</html>

```



  **需要注意**: 由于doFilterInternal方法只对method为post的表单进行过滤，所以在页面中必须如下设置：

```jsp
  <form action="..." method="post">  
         <input type="hidden" name="_method" value="put" />  
  </form>  

```

代表post请求,但是HiddenHttpMethodFilter将把本次请求转化成标准的put请求方式! name="_method"固定写法!



测试: 查看方法可以调通即可!

### 六.异常处理

#### 2. 异常处理方案 

##### 2.1 DefaultHandlerExceptionResolver 

​       Spring MVC默认装配了DefaultHandlerExceptionResolver,它会将Spring MVC框架的异常转换为相应的相应状态码!

**异常和相应状态码对应表**

|                异常类型                 |            响应状态码             |
| :-------------------------------------: | :-------------------------------: |
|     ConversionNotSupportedException     |      500(Web服务器内部错误)       |
|   HttpMediaTypeNotAcceptableException   | 406(无和请求accept匹配的MIME类型) |
|   HttpMediaTypeNotSupportedException    |        415(不支持MIME类型)        |
|     HttpMessageNotReadableException     |                400                |
|     HttpMessageNotWritableException     |                500                |
| HttpRequestMethodNotSupportedException  |                405                |
| MissingServletRequestParameterException |                400                |



在web.xml响应状态码配置一个对应页面

```xml
<error-page>
   <error>404</error>
   <location>/404.html</location>
</error-page>
```



**注意:** 静态资源注意会被DispatcherServlet拦截!

##### 2.2 AnnotationMethodHandlerExceptionResolver

  Spring MVC 默认注册了 AnnotationMethodHandlerExceptionResolver,它允许通过@ExceptionHandler注解指定处理特定异常的方法!

```java
 @ExceptionHandler
 public  String  handleException(RuntimeException re, HttpServletRequest request)
 {
    return "forward:/user/error";
 }
```

通过@ExceptionHandler指定了当前类的一个错误处理方法!如果当前类中出现异常,会触发错误处理方法!

但是@ExceptionHandler的异常处理方法只能对同一处理类中的其他处理方法进行异常响应处理!!



##### 2.3 全局异常处理

```java
@ControllerAdvice
public class MyExecptionHandler {

    @ExceptionHandler(Exception.class)
    public ModelAndView handleException(Exception ex)
    {
        System.out.println("全局异常:ex = " + ex);
        ModelAndView modelAndView = new ModelAndView();

        modelAndView.setViewName("error");
        modelAndView.addObject("exception",ex);
        return  modelAndView;
    }

}
```

此处可以捕捉全局异常,但是不要忘了在spring配置的时候扫描该类!

### 七.ResponseBody 和 @RequestBody 的作用

#### 7.1 ResponseBody

@ResponseBody是作用在方法上的，@ResponseBody 表示该方法的返回结果直接写入 HTTP response body 中，一般在异步获取数据时使用【也就是AJAX】，在使用 @RequestMapping后，返回值通常解析为跳转路径，但是加上 @ResponseBody 后返回结果不会被解析为跳转路径，而是直接写入 HTTP response body 中。 比如异步获取 json 数据，加上 @ResponseBody 后，会直接返回 json 数据。@RequestBody 将 HTTP 请求正文插入方法中，使用适合的 HttpMessageConverter 将请求体写入某个对象。

#### 7.2RequestBody

@RequestBody是作用在形参列表上，用于将前台发送过来固定格式的数据【xml 格式或者 json等】封装为对应的 JavaBean 对象，封装时使用到的一个对象是系统默认配置的 HttpMessageConverter进行解析，然后封装到形参上。