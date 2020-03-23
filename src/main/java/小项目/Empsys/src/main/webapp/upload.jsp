<%--
  Created by IntelliJ IDEA.
  User: 轩轩
  Date: 2020/3/23
  Time: 14:02
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
<form action="/file/import" method="post" enctype="multipart/form-data">
    <input type="file" name="excelFile" />
    <input type="submit" value="导入" />
</form>
</body>
</html>
