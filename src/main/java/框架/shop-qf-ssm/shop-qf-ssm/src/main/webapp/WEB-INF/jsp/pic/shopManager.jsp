<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%
    /*获取工程路径*/
    String path = request.getContextPath();
    String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>
<html>
<head>
    <script src="https://cdn.bootcss.com/jquery/2.1.1/jquery.min.js"></script>
    <!-- 新 Bootstrap 核心 CSS 文件 -->
    <link href="https://cdn.bootcss.com/bootstrap/3.3.7/css/bootstrap.min.css" rel="stylesheet">
    <!-- 最新的 Bootstrap 核心 JavaScript 文件 -->
    <script src="https://cdn.bootcss.com/bootstrap/3.3.7/js/bootstrap.min.js"></script>
    <script type="text/javascript">
        $(function () {
            msg=${msg}
                    console.log(msg)
            if(msg!=null &&msg!=''){
                alert(${msg});
            }
        });
        //查询事件
        function findData(){
            $('#currentPage').val('1');
            document.forms[0].submit();
        }
        //清空查询条件
        function clearP(){
            $("#name").val("");
            $("#loginName").val("");
        }

        //删除用户
        function del(id) {
            if(confirm("确定要删除该用户吗？")){
                $.ajax({
                    url:"<%=basePath%>user/del",
                    type:"post",
                    dataType:"json",
                    data:{id:id},
                    success:function () {
                        alert("删除成功！");
                        $("#myform").submit();
                    },
                    error:function () {//不知道为什么有时会走error
                        alert("删除成功！");
                        $("#myform").submit();
                    }
                });
            }
        }
/*        //编辑用户 先获取当前ID用户信息
        function edit(id,status) {
            $.ajax({
                url:"<%=basePath%>pic/updateStatus",
                type:"post",
                dataType:"json",
                data:{id:id,status:status},
                success:function (data) {
                    console.log(data)
                    location =location;
                },
                error:function (data) {
                    console.log(data)
                    alert("获取用户数据失败！");
                }
            });
        }*/
    </script>
</head>
<body>
<div class="table-responsive">
    <form class="form-inline" role="form" action="<%=basePath%>pic/picAll" method="post" id="myform">
        <div style="margin-left: 80px;">
            状态：<input type="text" name="status" id="name"  class="form-control" style="width: 120px;">
            <input type="button" onclick="findData();" class="btn btn-info" value="查询"/>
            <input type="button" onclick="clearP();" class="btn btn-info" value="清空"/>
            <input type="button" data-toggle="modal" data-target="#addModal" class="btn btn-info" value="新增"/>
        </div>
        <hr style="margin-top: 10px;"/>
        <div style="margin-left: 20px;">
            <table class="table table-hover">
                <thead>
                <tr style="text-align: center">
                    <th style="text-align: center">序号</th>
                    <th style="text-align: center">图片</th>
                    <th style="text-align: center">状态</th>
                    <th style="text-align: center">操作</th>
                </tr>
                </thead>
                <tbody>
                <c:if test="${!empty shopPicList }">
                    <c:forEach items="${shopPicList}" var="shop" varStatus="status">
                        <tr style="text-align: center">
                            <td>${shop.id}</td>
                            <td><img width=" 50px" height="50px" src="${shop.path}"></td>
                            <td>
                                <c:if test="${shop.status ==1}">禁用</c:if>
                                <c:if test="${shop.status ==0}">正常</c:if>
                            </td>
                            <td>
                                <c:if test="${shop.status==1}">
                                    <%--<button type="button" class="btn btn-success" onclick="edit('','0')">解禁</button>--%>
                                    <a href="../pic/updateStatus/${shop.id }/0">解禁</a>
                                </c:if>
                                <c:if test="${shop.status==0}">
                                  <%--  <button type="button" class="btn btn-success" onclick="edit('${shop.id }','1')">禁用</button>--%>
                                    <a href="../pic/updateStatus/${shop.id }/1">禁用</a>
                                </c:if>

                            </td>
                        </tr>
                    </c:forEach>
                </c:if>
                </tbody>
            </table>
            <div >
                <jsp:include page="../../../common/page.jsp"/>
            </div>
        </div>
    </form>
</div>
</body>
</html>
