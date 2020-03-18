<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>

	<head>
		<meta charset="utf-8" />
		<title>添加信息</title>
		<style type="text/css">
			table {
				width: 300px;
				border-collapse: collapse;
			}
			
			h3 {
				text-align: center;
			}
			
			div {
				margin: 0 auto;
				width: 300px;
			}
		</style>
		<script type="text/javascript" src="js/jquery-1.12.2.min.js"></script>
	</head>

	<body>
		<div>
			<h3>添加员工信息</h3>
			<form action="/staff/insertByPojo" method="post" onsubmit="return false;" >
				<table border="1">
					<tr>
						<th>姓名</th>
						<td><input type="text" name="name" /></td>
					</tr>
					<tr>
						<th>性别</th>
						<td>
							<input type="radio" name="sex" value="男" />男
							<input type="radio" name="sex" value="女" />女
						</td>
					</tr>
					<tr>
						<th>年龄</th>
						<td><input type="text" name="age" /></td>
					</tr>
					<tr>
						<th>手机</th>
						<td><input type="text" name="phone" /></td>
					</tr>
					<tr>
						<td colspan="2" align="center">
							<input type="submit" value="添加" />&nbsp;
							<input type="reset" value="重置" /></td>
					</tr>
				</table>
			</form>
		</div>

		<script type="text/javascript">
			// 涉及到表单时，如果使用ajax，需要感觉提交事件，发送ajax请求，同时需要注意：阻止默认的提交的操作
			$("form").submit(function(){
				$.ajax({
					type:"post",
					url:"/staff/insertByPojo",
					data:$("form").serialize(),
					dataType:"json",
					success:function(data){
							window.location.href = "list.jsp";
					}
				})
			})
		</script>
	</body>

</html>