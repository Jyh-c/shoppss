<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <meta charset="utf-8">
    <meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
    <title>进货记录列表</title>
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
        <li>进货记录</li>
        <li>记录列表</li>
    </ul>
</div>
<div class="row alert alert-info" style="margin:0px; padding:3px;">
    <form class="form-inline" action="${pageContext.request.contextPath}/purchase.do?action=queryBlurry" method="post">
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
            <th>进货批号</th>
            <th>供应商</th>
            <th>商品条码</th>
            <th>品类</th>
            <th>商品名称</th>
            <th>进货单价(元)</th>
            <th>进货数量</th>
            <th>进货金额</th>
            <th>保质期限</th>
        </tr>
        <c:forEach items="${purchaseList}" var="purchase">
            <tr>
                <td>${purchase.id}</td>
                <td>${purchase.supplier_name}</td>
                <td>${purchase.bar_code}</td>
                <td>${purchase.category_name}</td>
                <td>${purchase.product_name}</td>
                <td>${purchase.purchase_price}</td>
                <td>${purchase.count}</td>
                <td>${purchase.amount}</td>
                <td>${purchase.exp_date}</td>
            </tr>
        </c:forEach>

    </table>
</div>
</body>
</html>
