<%--
  Created by IntelliJ IDEA.
  User: 轩轩
  Date: 2020/4/10
  Time: 22:46
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

<form action="login.do" method="post">
    name <input type="text" name="username" /><br />
    password <input type="text" name="password" /> <br />
    <%-- 是否记住我 --%>
    <input type="checkbox" name="isRemember" value="1" /> 记住我 <br />
    <input type="submit" value="login" />
</form>

</body>
</html>
