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
        <li>库存管理</li>
        <li>库存列表</li>
    </ul>
</div>
<div class="row alert alert-info" style="margin:0px; padding:3px;">
    <form class="form-inline" action="${pageContext.request.contextPath}/stock.do?action=queryBlurry" method="post">
        <div class="form-group">
            <label class="" for="activename">商品名称：</label>
            <input type="text" name="keyword" class="form-control" id="activename"  placeholder="请输入商品名称">
        </div>
        <input type="submit" class="btn btn-danger" value="查询"/>
    </form>
</div>
<div class="row" style="padding:15px; padding-top:0px; ">
    <table class="table  table-condensed table-striped">
        <tr>
            <th>商品条码</th>
            <th>品类</th>
            <th>商品名称</th>
            <th>库存</th>
            <th>供应商</th>
            <th>供应商联系人</th>
            <th>供应商电话</th>
        </tr>
        <c:forEach items="${stockList}" var="stock">
            <tr>
                <td>${stock.product_bar_code}</td>
                <td>${stock.category_name}</td>
                <td>${stock.product_name}</td>
                <td>${stock.stock_count}</td>
                <td>${stock.supplier_name}</td>
                <td>${stock.contacts_name}</td>
                <td>${stock.tel}</td>
            </tr>
        </c:forEach>
    </table>
</div>
</body>
</html>
