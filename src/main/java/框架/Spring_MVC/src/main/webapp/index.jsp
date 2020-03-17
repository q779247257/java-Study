<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<body>
<h2>Hello World!</h2>

<form action="/date.do" method="get">
    生日 <input type="text" name="date" value="2018-09-08 18:25:06" />   <br />
    年龄<input type="text" name="age" value="17" />   <br />
    名字<input type="text" name="name" value="轩轩" />   <br />
    <input type="submit" value="提交按钮" />
</form>

<form action="/date2.do" method="post">
    data2 ,@InitBinder 方法转换  <input type="text" name="date" value="2018-09-08 18:25:06" />   <br />
    <input type="submit"  value="提交按钮" />
</form>
</body>
</html>
