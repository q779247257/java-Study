<%--
  Created by IntelliJ IDEA.
  User: 轩轩
  Date: 2020/3/10
  Time: 17:42
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" isELIgnored="false" isErrorPage="true" %>
<%
    /*获取工程路径*/
    String path = request.getContextPath();
    String basePath = request.getScheme() + "://" + request.getServerName() + ":" + request.getServerPort() + path + "/";
%>
<html>
<head>
    <title>Title</title>
</head>
<body>
<form action="convert1.do">
    name <input type="text" name="name" /> <br />
    age <input type="text" name="age" /> <br />
    birth <input type="text" name="birth" /> <br />
     <input type="submit" value="提交"/> <br />
</form>
</body>
</html>
