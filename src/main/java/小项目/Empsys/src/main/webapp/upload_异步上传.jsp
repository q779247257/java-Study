<%--
  Created by IntelliJ IDEA.
  User: renr
  Date: 2019/6/5
  Time: 16:54
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Title</title>
    <script type="text/javascript" src="js/jquery-1.12.2.min.js"></script>
    <!--  借助该js库，实现文件的异步上传 -->
    <script type="text/javascript" src="js/jquery.form.js"></script>

</head>
<body>

<form method="post" enctype="multipart/form-data" onsubmit="return false;">
    <input type="file" name="excelFile" />
    <input type="submit" value="导入" />

</form>

<script type="text/javascript">
    $("form").submit(function(){
        // ajaxSubmit 是jquery.form中提供的方法
        $("form").ajaxSubmit({
            type:"post",
            url:"import.do",
            data:$("form").serialize(),
            dataType:"json",
            success:function(data){
                if(data.code == 1){
                    alert("导入成功");
                }else{
                    alert(data.info);
                }
            }
        })
    })

</script>

</body>
</html>
