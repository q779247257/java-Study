<%--
  Created by IntelliJ IDEA.
  User: ASUS
  Date: 2020/3/2
  Time: 11:54
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ page isELIgnored="false"%>
<%@ page isErrorPage="true"%>
<%
    /*获取工程路径*/
    String path = request.getContextPath();
    String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>

<html>
<head>
    <title>Title</title>
</head>
<body>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Title</title>
</head>
<body>
    <form method="post" action="result/insert">
        <label for="name">resultPost<input type="text" id="name" name="name" /></label>
        <button type="submit">提交</button>
    </form>
    <br/>
    <form method="post" action="result/update">
        <input type="hidden" name="_method" value="PUT" />
        <label for="name">result_Put<input type="text" id="name1" name="name" /></label>
        <button type="submit">提交</button>
    </form>

    <form method="post" action="result/delete/1">
        <input type="hidden" name="_method" value="delete" />
        <label for="name">result_Delete<input type="text" id="name12" name="name" /></label>
        <button>提交</button>
    </form>

</body>
</html>
</body>
</html>
