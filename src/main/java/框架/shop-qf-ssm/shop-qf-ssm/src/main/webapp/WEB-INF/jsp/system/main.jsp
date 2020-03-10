<%@ page import="com.qf.pojo.SsmUser" %>
<%@ page language="java" contentType="text/html; charset=utf-8"
		 pageEncoding="utf-8"%>
<%
	String path = request.getContextPath();
%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
	<meta http-equiv="Content-Type" content="text/html; charset=utf-8">
	<!-- 引入样式文件和动态控制 -->
	<link href="<%=path %>/css/bootstrap.min.css" rel="stylesheet">
	<script src="<%=path %>/js/jquery-3.2.0.min.js"></script>
	<script src="<%=path %>/js/bootstrap.min.js"></script>
	<!--主要样式控制-->
	<link href="<%=path %>/css/main.css" rel="stylesheet">

	<title>雷雷有点小帅</title>

</head>
<body>
<!--顶部导航栏部分-->
<nav class="navbar navbar-inverse">
	<div class="container-fluid">
		<div class="navbar-header">
			<a class="navbar-brand" title="logoTitle" href="https://xuanandjava.oss-cn-shanghai.aliyuncs.com/123.jpg">logo</a>
		</div>
		<div class="collapse navbar-collapse">
			<ul class="nav navbar-nav navbar-right">
				<li role="presentation">
					<a href="#">当前用户：<span class="badge">${userInfo}</span></a>
				</li>
				<%--<li>--%>
					<%--<a href="../user/loginOut">--%>
						<%--<span class="glyphicon glyphicon-lock"></span>退出登录</a>--%>
				<%--</li>--%>
			</ul>
		</div>
	</div>
</nav>

<!-- 中间主体内容部分 -->
<div class="pageContainer">
	<!-- 左侧导航栏-->
	<div class="pageSidebar">
		<ul class="nav nav-stacked nav-pills">
			<li role="presentation">
				<a href="selectAll" target="mainFrame" >用户管理</a>
			</li>
			<li role="presentation">
				<a href="../../../shop_qf_ssm_war/liao_tian.html" target="mainFrame" >用户管理</a>
			</li>
			<%--<li role="presentation">--%>
				<%--<a href="../shop/shopAll" target="mainFrame">商户管理</a>--%>
			<%--</li>--%>
			<%--<li role="presentation">--%>
				<%--<a href="nav3.html" target="mainFrame">商品管理</a>--%>
			<%--</li>--%>
			<%--<li role="presentation">--%>
				<%--<a href="../pic/picAll" target="mainFrame">图片管理</a>--%>
			<%--</li>--%>
			<%--<!-- 开始 -->--%>
			<%--<li class="dropdown">--%>
				<%--<a class="dropdown-toggle" data-toggle="dropdown" href="nav4.html" target="mainFrame">--%>
					<%--个人设置<span class="caret"></span>--%>
				<%--</a>--%>
				<%--<ul class="dropdown-menu">--%>
					<%--<li>--%>
						<%--<a href="nav1.html" target="mainFrame">修改密码</a>--%>
					<%--</li>--%>
					<%--<li>--%>
						<%--<a href="nav2.html" target="mainFrame">退出系统</a>--%>
					<%--</li>--%>
					<%--<li>--%>
						<%--<a href="nav3.html" target="mainFrame">查看个人信息</a>--%>
					<%--</li>--%>
				<%--</ul>--%>
			<%--</li>--%>

		<%--</ul>--%>
	</div>

	<!-- 左侧导航和正文内容的分隔线 -->
	<div class="splitter"></div>
	<!-- 正文内容部分 -->
	<div class="pageContent">
		<iframe src="<%=path %>/system/welcome.jsp" id="mainFrame" name="mainFrame"
				frameborder="0" width="100%"  height="100%" frameBorder="0">
		</iframe>
	</div>

</div>
<!-- 底部页脚部分 -->
<div class="footer">
	<p class="text-center">
		220 &copy; 轩轩轩轩轩轩轩
	</p>
</div>

<script type="text/javascript">
	$(".nav li").click(function() {
		$(".active").removeClass('active');
		$(this).addClass("active");
	});


</script>
</body>
</html>
