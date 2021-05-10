<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <meta charset="utf-8">
    <meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
    <title>商品详情</title>
    <!-- 新 Bootstrap 核心 CSS 文件 -->
    <link rel="stylesheet" href="../../css/bootstrap.min.css">
    <!-- jQuery文件。务必在bootstrap.min.js 之前引入 -->
    <script src="../../js/jquery.min.js"></script>
    <!-- 最新的 Bootstrap 核心 JavaScript 文件 -->
    <script src="../../js/bootstrap.min.js"></script>
</head>

<body>
<div style="padding:0px; margin:0px;">
    <ul class="breadcrumb" style="  margin:0px; " >
        <li><a href="#">商品中心</a></li>
        <li>商品管理</li>
        <li>商品详情</li>
    </ul>
</div>
<form action="${pageContext.request.contextPath}/product.do?action=update&id=${product.id}" class="form-horizontal" method="post">
    <h5 class="page-header alert-info" style="padding:10px; margin:0px; margin-bottom:5px;">基本信息</h5>
    <!--开始 -->
    <div class="row">
        <div class="col-sm-5">
            <div class="form-group">
                <label class="col-sm-3 control-label">商品条码</label>
                <div class="col-sm-9">
                    <input type="text" name="bar_code" class="form-control input-sm" value="${product.bar_code}"/>
                </div>
            </div>
        </div>
        <div class="col-sm-5">
            <div class="form-group">
                <label class="col-sm-3 control-label">商品名称</label>
                <div class="col-sm-9">
                    <input type="text" name="product_name" class="form-control input-sm" value="${product.product_name}"/>
                </div>
            </div>
        </div>
    </div>
    <!--结束 -->
    <!--开始 -->
    <div class="row">
        <div class="col-sm-5">
            <div class="form-group">
                <label class="col-sm-3 control-label">销售单价</label>
                <div class="col-sm-9">
                    <input type="text" name="sale_price" class="form-control input-sm" value="${product.sale_price}"/>
                </div>
            </div>
        </div>
        <div class="col-sm-5">
            <div class="form-group">
                <label class="col-sm-3 control-label">供应商</label>
                <div class="col-sm-6">
                    <select name="supplier_id" class="form-control input-sm">
                        <c:forEach items="${supplierList}" var="supplier">
                                <option value="${supplier.id}">${supplier.supplier_name}</option>
                        </c:forEach>
                    </select>
                </div>
            </div>
        </div>
    </div>
    <!--结束 -->
    <!--开始 -->
    <div class="row">
        <div class="col-sm-5">
            <div class="form-group">
                <label class="col-sm-3 control-label">品类</label>
                <div class="col-sm-6">
                    <select name="category_id" class="form-control input-sm" >
                        <c:forEach items="${categoryList}" var="category">
                            <option value="${category.id}">${category.category_name}</option>
                        </c:forEach>
                    </select>
                </div>
            </div>
        </div>
        <div class="col-sm-5">
            <div class="form-group">
            </div>
        </div>
    </div>
    <!--结束 -->
    <div class="row">
        <div class="col-sm-3 col-sm-offset-4">
            <input  type="submit" class="btn btn-success" value="提交"/>
            <a class="btn btn-warning" href="${pageContext.request.contextPath}/view/product/product_list.jsp">返回上一级</a>
        </div>
    </div>
</form>
</body>
</html>
