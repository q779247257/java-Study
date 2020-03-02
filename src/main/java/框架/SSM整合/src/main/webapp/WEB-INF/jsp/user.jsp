<%--
  Created by IntelliJ IDEA.
  User: ASUS
  Date: 2020/3/2
  Time: 17:55
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" isELIgnored="false" isErrorPage="true" %>
<%
    String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort() +request.getContextPath()+"/";
%>
<html>
<head>
    <title>Title</title>
</head>
<body>
<p>${users}</p>
</body>
</html>
