
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>

	<head>
		<meta charset="utf-8" />
		<title>修改信息</title>
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
			<h3>修改员工信息</h3>
			
			<form action="/staff/updateById" method="post" accept-charset="UTF-8">
				<input type="hidden" name="id" value="" />
				<table border="1">
					<tr>
						<th>姓名</th>
						<td><input type="text" name="name" value="" /></td>
					</tr>
					<tr>
						<th>性别</th>
						
							<td>
								<input id="mIn" type="radio" name="sex" value="男"  />男
								<input id="fIn" type="radio" name="sex" value="女"  />女
							</td>
					
						
					</tr>
					<tr>
						<th>年龄</th>
						<td><input type="text" name="age" value="" /></td>
					</tr>
					<tr>
						<th>手机</th>
						<td><input type="text" name="phone" value="" /></td>
					</tr>
					<tr>
						<td colspan="2" align="center">
							<input type="submit" value="修改" />&nbsp;
							<input type="reset" value="重置" /></td>
					</tr>
				</table>
			</form>
		</div>

		<script type="text/javascript">
			$(function(){
				$.ajax({
					type:"get",
					url:"/staff/selectById?id=${param.id}",
					dataType:"json",
					success:function(data){
							var info = data;
							$("input[name='id']").val(info.id);
							$("input[name='name']").val(info.name);
							$("input[name='age']").val(info.age);
							$("input[name='phone']").val(info.phone);
							if(info.sex == '男'){
								$("#mIn").attr("checked", "checked");
							}else{
								$("#fIn").attr("checked", "checked");
							}
					}
				})
			})

		</script>
	</body>

</html>