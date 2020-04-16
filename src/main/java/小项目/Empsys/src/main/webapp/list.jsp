<%@page import="java.util.List"%>

<%@ taglib uri="http://java.sun.com/jsp/jstl/core"  prefix="c" %>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://shiro.apache.org/tags" prefix="shiro" %>
<!DOCTYPE html>
<html>

	<head>
		<meta charset="utf-8" />
		<title>所有员工信息</title>
		<script type="text/javascript" src="js/jquery-1.12.2.min.js"></script>
		<style type="text/css">
			table td {
				text-align: center;
			}
			/*合并表格的边框*/
			
			table {
				width: 700px;
				border-collapse: collapse;
			}
			
			h3 {
				text-align: center;
			}
			
			div {
				margin: 0 auto;
				width: 700px;
			}
		</style>
	</head>

	<body>
	<%-- 显示用户的用户名 --%>
	<shiro:principal></shiro:principal>
	<a href="logout.do">退出登录</a>
	<a href="javascript:testPerms()"	>测试shiro ajax超链接 </a>
		<div>
			<h3>所有员工信息</h3>
			<a href="/add.jsp">添加员工信息</a><br/>
			<table border="1">

				<tr>
					<th>序号</th>
					<th>姓名</th>
					<th>性别</th>
					<th>年龄</th>
					<th>手机</th>
					<th>操作</th>
				</tr>
				<tbody id="tid">
				<tr>
					<td>你好</td>
					<td>你好</td>
					<td>你好</td>
					<td>你好</td>
					<td>你好</td>
				</tr>
				</tbody>
			</table>
		</div>
	<script type="text/javascript">
		//页面加载完之后加载次函数
		$(function(){
			loadData(1);
		})

		function loadData(page){
			$.ajax({
				type:"get",
				url:"/staff/page.do?page="+page+"&size=5",
				dataType:"json",
				success:function(data){
					// 清空表格的数据
						$("#tid").empty();
						// 找到要遍历的数据
						var infos = data.pageList;
						// 循环遍历
						$(infos).each(function(index,element){
							// 组装html格式的字符串
							var html = "<tr>";
							html += "	<td>" + (index+1) + "</td>";
							html += "	<td>" + element.name + "</td>";
							html += "	<td>" + element.sex + "</td>";
							html += "	<td>" + element.age + "</td>";
							html += "	<td>" + element.phone + "</td>";
							// 针对超链接 ，要想发送ajax请求，可以点击超链接时，执行一个函数，在函数中发送ajax请求
							// href='javascript:delEmp()'表示，点击超链接，执行对应的js函数
							html += "	<td>" +
									"<shiro:hasPermission name="user:del"><a href='javascript:delEmp(" + this.id + ")'>删除</a> </shiro:hasPermission> " +
									"<a href='update.jsp?id=" + this.id + "'>修改</a></td>";
							html += "</tr>";
							// 根据html格式字符串创建dom对象，将对象添加到对应表格的最后
							$("#tid").append($(html));
						})
					var html = '<tr><td colspan="6">' +
							'<a href="javascript:loadData(1)">首页</a>&nbsp;' +
							'<a href="javascript:loadData(' + (data.currentPage - 1) + ')">上一页</a>&nbsp;' +
							'<a href="javascript:loadData(' + (data.currentPage + 1) + ')">下一页</a>&nbsp;' +
							'<a href="javascript:loadData(' + data.totalPage + ')">末页</a>' +
							'</td></tr>';
					$("#tid").append($(html));
				},
				error:function(){
					alert("ajax报错");
				}
			})
		}
	</script>
<%--删除数据时间--%>
		<script type="text/javascript">
			function delEmp(id){
				$.ajax({
					type:"get",
					url:"staff/deleteById?id=" + id,
					dataType:"json",
					success:function(data){
							//window.location.href = "list.jsp";
							// 因为是在同一页面，没有必要，重新定位到list.jsp
							// 可以直接发送ajax请求，获取所有数据，重新绑定
							loadData(1);
					}
				})
			}
			
			function testPerms() {
				$.ajax({
					type:"get",
					url:"test2.do",
					dataType:"text",
					success:function(data){
						alert(data);
					}
				})
			}
		</script>
	</body>

</html>