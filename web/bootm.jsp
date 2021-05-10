<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<title>首页</title>
<!-- 新 Bootstrap 核心 CSS 文件 -->
<link rel="stylesheet" href="http://cdn.bootcss.com/bootstrap/3.2.0/css/bootstrap.min.css">
<!-- 可选的Bootstrap主题文件（一般不用引入） -->
<link rel="stylesheet" href="http://cdn.bootcss.com/bootstrap/3.2.0/css/bootstrap-theme.min.css">
<!-- jQuery文件。务必在bootstrap.min.js 之前引入 -->
<script src="http://cdn.bootcss.com/jquery/1.11.1/jquery.min.js"></script>
<!-- 最新的 Bootstrap 核心 JavaScript 文件 -->
<script src="http://cdn.bootcss.com/bootstrap/3.2.0/js/bootstrap.min.js"></script>
</head>

<body>

<div style="padding:0px; margin:0px;">
 <ul class="breadcrumb" style=" padding:0px; padding-left:20px;" >
  <li ><a href="#">首页</a></li>
  <li>工作台</li>
</ul>
</div>

<div class="row " style="padding:1px; margin:0px;"  >
	<div class="col-sm-8">
        <div class="panel panel-default" >
          <div class="panel-heading" style=" padding:3px;height:30px;"  >
            <span class="glyphicon glyphicon-refresh"></span>销售品类分布
          </div>
              <div class="panel-body">
               <img src="img/招生.png" width="100%" />
              </div>
        </div>
    </div>
 	<div class="col-sm-4">
            <div class="panel panel-default">
          <div class="panel-heading"  style=" padding:3px;height:30px;"  >
            <span class="glyphicon glyphicon-refresh"></span>今日销量Top5
          </div>
   <table class="table table-bordered">
       <tr>
           <th>商品</th>
           <th>销量</th>
           <th>金额</th>
       </tr>
       <tr>
           <td>百岁山500ml</td>
           <td>100</td>
           <td>300</th>
       </tr>
       <tr>
           <td>小布丁</td>
           <td>90</td>
           <td>45</th>
       </tr>
       <tr>
           <td>瓜子300g</td>
           <td>80</td>
           <td>400</th>
       </tr>
       <tr>
           <td>农夫山泉500ml</td>
           <td>60</td>
           <td>120</th>
       </tr>
       <tr>
           <td>巧乐滋</td>
           <td>50</td>
           <td>200</th>
       </tr>
	</table>  
        </div> </div>
	<div class="col-sm-6">
        <div class="panel panel-default" >
          <div class="panel-heading" style=" padding:3px;height:30px;"  >
            <span class="glyphicon glyphicon-refresh"></span>营业额分布图
          </div>
              <div class="panel-body">
               <img src="img/02.png" width="100%" />
              </div>
        </div>
    </div>
	<div class="col-sm-6">
        <div class="panel panel-default" >
          <div class="panel-heading" style=" padding:3px;height:30px;"  >
            <span class="glyphicon glyphicon-refresh"></span>订单数量分布图
          </div>
              <div class="panel-body">
               <img src="img/02.png" width="100%" />
              </div>
        </div>
    </div>
 	<div class="col-sm-4">
            <div class="panel panel-default">
          <div class="panel-heading"  style=" padding:3px;height:30px;"  >
            <span class="glyphicon glyphicon-refresh"></span>近期商品预警
          </div>
   <table class="table table-bordered" >
	<tr >
        <th>商品条码</th>
    	<th>商品名称</th>
        <th>保质期限</th>
    </tr>
    <tr>
        <td>629837912</td>
    	<td>绝味鸭脖250g</td>
        <td>2020-07-30</td>
    </tr>
	</table>  
        </div>
    </div>
 	<div class="col-sm-4">
            <div class="panel panel-default">
          <div class="panel-heading"  style=" padding:3px;height:30px;"  >
            <span class="glyphicon glyphicon-refresh"></span>库存预警
          </div>
   <table class="table table-bordered" >
	<tr >
        <th>商品条码</th>
        <th>商品名称</th>
        <th>库存数量</th>
    </tr>
    <tr>
        <td>629837912</td>
        <td>绝味鸭脖250g</td>
        <td>20</td>
    </tr>
	</table>  
        </div>
    </div>
    <div class="col-sm-4">
        <div class="panel panel-default">
            <div class="panel-heading"  style=" padding:3px;height:30px;"  >
                <span class="glyphicon glyphicon-refresh"></span>公告
            </div>
            <div class="panel-body">公告</div>
        </div>
    </div>
</div>



</body>
</html>
