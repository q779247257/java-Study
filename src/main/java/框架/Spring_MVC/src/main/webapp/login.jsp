<%--
  Created by IntelliJ IDEA.
  User: ASUS
  Date: 2020/2/27
  Time: 16:03
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Title</title>
</head>
<body>
<!--action指定controller中对应的方法路径即可!-->
<form action="/Spring_MVC_war/hellow/login" method="post">
    <label for="username">用户名:<input type="text" id="username" name="name" /></label>
    <label for="password">密码:<input type="text" id="password" name="password" /></label>
    <input type="submit" value="登陆">
</form>
</body>

</html>
