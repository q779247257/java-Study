<%--
  Created by IntelliJ IDEA.
  User: ASUS
  Date: 2020/2/27
  Time: 15:58
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%--
isELIgnored="false”
用来解析ModelAndVidew对象中的参数，否则解析不出来
如果设定为真，那么JSP中的表达式被当成字符串处理
--%>
<%@ page isELIgnored="false"%>
<html>
<head>
    <title>Title</title>
</head>
<body>
    <h1> 欢迎进入 MVC</h1>
    <%--modelAndView 测试--%>
    <label for="username">用户名:<input type="text" value="${username}" id="username" name="name" /></label>
    用户姓名:<input type="text" value="${pojo.username}"  name="name" />
    手机号:<input type="text" value="${pojo.phone}"  name="name" />
    性别:<input type="text" value="${pojo.sex}"  name="name" />
</body>
</html>
