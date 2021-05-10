<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <meta charset="utf-8">
    <meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
    <title>销售报表</title>
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
        <li>销售管理</li>
        <li>销售报表</li>
    </ul>
</div>
<div class="row alert alert-info" style="margin:0px; padding:3px;">
    <form class="form-inline" action="/sale.do?action=queryBlurry" method="post">
        <div class="form-group">
            <label class="" for="activename">商品名称：</label>
            <input type="text" name="keyword" class="form-control" id="activename" placeholder="请输入商品名称">
        </div>
        <input type="submit" class="btn btn-danger" value="查询"/>
    </form>
</div>
<div class="row" style="padding:15px; padding-top:0px; ">
    <table class="table  table-condensed table-striped">
        <tr>
            <th>商品条码</th>
            <th>商品名称</th>
            <th>销售单价</th>
            <th>销售数量</th>
            <th>销售金额</th>
            <th>销售日期</th>
        </tr>
        <c:forEach items="${saleList}" var="sale">
            <tr>
                <td>${sale.product_bar_code}</td>
                <td>${sale.product_name}</td>
                <td>${sale.sale_price}</td>
                <td>${sale.sale_count}</td>
                <td>${sale.sale_amount}</td>
                <td>${sale.create_time}</td>
            </tr>
        </c:forEach>
    </table>
</div>
</body>
</html>
