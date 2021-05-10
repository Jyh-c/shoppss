<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <meta charset="utf-8">
    <meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
    <title>模拟销售</title>
    <!-- 新 Bootstrap 核心 CSS 文件 -->
    <link rel="stylesheet" href="../../css/bootstrap.min.css">
    <!-- jQuery文件。务必在bootstrap.min.js 之前引入 -->
    <script src="../../js/jquery.min.js"></script>
    <!-- 最新的 Bootstrap 核心 JavaScript 文件 -->
    <script src="../../js/bootstrap.min.js"></script>
</head>
<body>
<div style="padding:0px; margin:0px;">
    <ul class="breadcrumb" style="  margin:0px; ">
        <li>系统管理</li>
        <li>用户管理</li>
        <li>用户列表</li>
    </ul>
</div>
<div class="row alert alert-info" style="margin:0px; padding:3px;">
    <form class="form-inline" action="${pageContext.request.contextPath}/sysuser.do?action=queryBlurry" method="post">
        <div class="form-group">
            <label class="" for="activename">用户姓名：</label>
            <input type="text" name="keyword" class="form-control" id="activename" placeholder="请输入用户姓名">
        </div>
        <input type="submit" class="btn btn-danger" value="查询"/>
        &nbsp;&nbsp;
        <a class="btn btn-success" href="${pageContext.request.contextPath}/view/user/user_add.jsp">添加用户</a>
    </form>
</div>
<div class="row" style="padding:15px; padding-top:0px; ">
    <table class="table  table-condensed table-striped">
        <tr>
            <th>用户编号</th>
            <th>用户姓名</th>
            <th>登陆账号</th>
            <th>电话</th>
            <th>微信号</th>
            <th>身份证号</th>
            <th>地址</th>
            <th>角色</th>
            <th>操作</th>
        </tr>
        <c:forEach items="${userList}" var="user">
            <tr>
                <td>${user.id}</td>
                <td>${user.user_name}</td>
                <td>${user.login_account}</td>
                <td>${user.tel}</td>
                <td>${user.wx_account}</td>
                <td>${user.id_no}</td>
                <td>${user.address}</td>
                <c:if test="${user.role_id == 1}">
                    <td>管理员</td>
                </c:if>
                <c:if test="${user.role_id == 2}">
                    <td>普通用户</td>
                </c:if>
                <td>
                    <a href="${pageContext.request.contextPath}/sysuser.do?action=queryById&id=${user.id}">修改</a>
                    <a href="${pageContext.request.contextPath}/sysuser.do?action=delete&id=${user.id}" onclick="confirm('是否确定删除?')">删除</a>
                </td>
            </tr>
        </c:forEach>
    </table>
</div>
</body>
</html>
