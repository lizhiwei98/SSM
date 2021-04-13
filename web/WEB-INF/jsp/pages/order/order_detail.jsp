<%--
  Created by IntelliJ IDEA.
  User: Administrator
  Date: 2020/11/9
  Time: 14:42
  To change this template use File | Settings | File Templates.
--%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Title</title>
    <%-- 静态包含 base标签、css样式、jQuery文件 --%>
    <%@ include file="/WEB-INF/jsp/pages/common/head.jsp"%>
</head>
<body>
<div id="header">
    <img class="logo_img" alt="" src="static/img/logo.gif" >
    <span class="wel_word">我的订单</span>

    <%--静态包含，登录 成功之后的菜单 --%>
    <%@ include file="/WEB-INF/jsp/pages/common/login_success_menu.jsp"%>


</div>
<div id="main">

    <table>
        <tr>
            <td>商品名称</td>
            <td>数量</td>
            <td>单价</td>
            <td>金额</td>

        </tr>

        <c:forEach items="${requestScope.details}" var="item"> <!--找了大半天，是因为没有加el表达式的jar包，也不报错，坑死我了-->
            <tr>
                <td>${item.goodsname}</td>
                <td>${item.count}</td>
                <td>${item.price}</td>
                <td>${item.totalPrice}</td>

            </tr>
        </c:forEach>

    </table>
    <div class="cart_info">
        <span class="cart_span">购物车中共有<span class="b_count">${requestScope.totalCount}</span>件商品</span>
        <span class="cart_span">总金额<span class="b_price">${requestScope.totalPrice}</span>元</span>
    </div>

</div>


<%--静态包含页脚内容--%>
<%@include file="/WEB-INF/jsp/pages/common/footer.jsp"%>

</body>
</html>
