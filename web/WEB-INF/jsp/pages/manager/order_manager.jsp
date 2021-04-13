<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>订单管理</title>

	<%-- 静态包含 base标签、css样式、jQuery文件 --%>
	<%@ include file="/WEB-INF/jsp/pages/common/head.jsp"%>


</head>
<body>

	<div id="header">
			<img class="logo_img" alt="" src="static/img/logo.gif" >
			<span class="wel_word">订单管理系统</span>

		<%-- 静态包含 manager管理模块的菜单  --%>
		<%@include file="/WEB-INF/jsp/pages/common/manager_menu.jsp"%>

	</div>

	<div id="main">
		<table>
			<tr>
				<td>日期</td>
				<td>金额</td>
				<td>详情</td>
				<td>发货</td>

			</tr>

			<c:forEach items="${requestScope.AdminOrders}" var="order">

				<tr>
					<td>${order.date}</td>
					<td>${order.price}</td>
					<td><a href="order/orderDetail?orderid=${order.orderid}&totalPrice=${order.price}">查看详情</a></td>
					<c:if test="${order.yes == 1}">
						<%--<td>已发货</td>--%>
						<td><a id="cancle" href="order/updateStatus?orderid=${order.orderid}&yes=1">取消发货</a></td>
					</c:if>
					<c:if test="${order.yes == 0}">
					<td><a id="fahuo" href="order/updateStatus?orderid=${order.orderid}&yes=0">点击发货</a></td>

					</c:if>
				</tr>
			</c:forEach>

		</table>
	</div>


	<%--静态包含页脚内容--%>
	<%@include file="/WEB-INF/jsp/pages/common/footer.jsp"%>


</body>
</html>
