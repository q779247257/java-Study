<%--
  Created by IntelliJ IDEA.
  User: renr
  Date: 2019/6/3
  Time: 10:01
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Title</title>
</head>
<body>

<form action="convert2.do">
    name<input type="text" name="name" /><br />
    age<input type="text" name="age" /><br />
    birth<input type="text" name="birth" /><br />
    <input type="submit" value="param" /><br />

</form>
<hr />
<form action="validate3.do">
    name<input type="text" name="name" /><br />
    age<input type="text" name="age" /><br />
    birth<input type="text" name="birth" /><br />
    <input type="submit" value="validate" /><br />

</form>

</body>
</html>
