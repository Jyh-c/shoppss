<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <meta charset="utf-8">
    <meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
    <title>供应商列表</title>
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
        <li>进货管理</li>
        <li>供应商</li>
        <li>供应商列表</li>
    </ul>
</div>
<div class="row alert alert-info" style="margin:0px; padding:3px;">
    <form class="form-inline" action="${pageContext.request.contextPath}/supplier.do?action=queryBlurry" method="post">
        <div class="form-group">
            <label class="" for="activename">供应商名称：</label>
            <input type="text" name="keyword" class="form-control" id="activename" placeholder="请输入供应商名称">
        </div>
        <input type="submit" class="btn btn-danger" value="查询"/>
        &nbsp;&nbsp;
        <a class="btn btn-success" href="${pageContext.request.contextPath}/view/supplier/supplier_add.jsp">添加供应商</a>
    </form>
</div>
<div class="row" style="padding:15px; padding-top:0px; ">
    <table class="table  table-condensed table-striped">
        <tr>
            <th>供应商编号</th>
            <th>供应商名称</th>
            <th>联系人</th>
            <th>联系电话</th>
            <th>地址</th>
            <th>操作</th>
        </tr>
        <c:forEach items="${supplierList}" var="supplier">
            <tr>
                <td>${supplier.id}</td>
                <td>${supplier.supplier_name}</td>
                <td>${supplier.contacts_name}</td>
                <td>${supplier.tel}</td>
                <td>${supplier.address}</td>
                <td>
                    <a href="${pageContext.request.contextPath}/supplier.do?action=queryById&id=${supplier.id}">修改</a>
                    <a href="${pageContext.request.contextPath}/supplier.do?action=delete&id=${supplier.id}" onclick="confirm('是否确定删除?')">删除</a>
                </td>
            </tr>
        </c:forEach>
    </table>
</div>
</body>
</html>
