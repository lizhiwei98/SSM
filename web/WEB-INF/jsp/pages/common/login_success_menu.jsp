<%--
  Created by IntelliJ IDEA.
  User: Administrator
  Date: 2020/2/5
  Time: 10:21
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>

<div>
    <span>欢迎<span class="um_span">${sessionScope.username}</span>光临小黎商城</span>
    <a href="order/orderList">我的订单</a>
    <a href="Car/carList?username=${sessionScope.username}">购物车</a>
    <a href="user/logout">注销</a>
    <a href="index.jsp">返回</a>
</div>
