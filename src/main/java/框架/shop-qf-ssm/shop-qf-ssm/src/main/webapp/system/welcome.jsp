<%--
  Created by IntelliJ IDEA.
  User: 54110
  Date: 2019-05-26
  Time: 14:23
  To change this template use File | Settings | File Templates.
--%>
<%@page import="java.text.SimpleDateFormat"%>
<%@page import="java.util.*"%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>首页</title>
</head>
<body>

<%
    Date d = new Date();
    SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
    String now = df.format(d);
%>
    <center><h1>欢迎您！登录轩轩聊天室。管理首页，<br>当前时间为<%=now %></h1></center>
</body>
</html>
