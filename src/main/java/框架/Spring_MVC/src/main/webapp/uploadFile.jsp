<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Title</title>
</head>
<body>
<center>
    <form method="post" enctype="multipart/form-data" action="/Spring_MVC_war/file/upload">
        <label for="name">文件名称<input type="text" id="name" name="name" /></label>
        <input type="file" name="file" />
        <button>提交</button>
    </form>
</center>
</body>
</html>