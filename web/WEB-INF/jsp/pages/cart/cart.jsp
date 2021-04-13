<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>购物车</title>

	<%-- 静态包含 base标签、css样式、jQuery文件 --%>
	<%@ include file="/WEB-INF/jsp/pages/common/head.jsp"%>


</head>
<body>
	<div id="header">
			<img class="logo_img" alt="" src="static/img/logo.gif" >
			<span class="wel_word">购物车</span>

		<%--静态包含，登录 成功之后的菜单 --%>
		<%@ include file="/WEB-INF/jsp/pages/common/login_success_menu.jsp"%>
		<script type="text/javascript">
			$(function () {
				// 给 【删除】绑定单击事件
				$("a.deleteItem").click(function () {
					return confirm("你确定要删除【" + $(this).parent().parent().find("td:first").text() +"】吗?")
				});
				// 给清空购物车绑定单击事件
				$("#clearCart").click(function () {
					return confirm("你确定要清空购物车吗?");
				})
				// 给输入框绑定 onchange内容发生改变事件
				$(".updateCount").change(function () {
					// 获取商品名称
					var name = $(this).parent().parent().find("td:first").text();
					var id = $(this).attr('goodsId');
					// 获取商品数量
					var count = this.value;
					if ( confirm("你确定要将【" + name + "】商品修改数量为：" + count + " 吗?") ) {
						//发起请求。给服务器保存修改
						location.href = "${pageContext.getAttribute("bathPath")}${requestScope.page.url}"+"?count="+count+"&goodsId="+id;
					} else {
						// defaultValue属性是表单项Dom对象的属性。它表示默认的value属性值。
						this.value = this.defaultValue;
					}
				});

			});
		</script>

	</div>

	<div id="main">

		<table>
			<tr>
				<td>商品名称</td>
				<td>数量</td>
				<td>单价</td>
				<td>金额</td>
				<td>操作</td>
			</tr>
			<c:if test="${empty requestScope.page.goods}">
				<%--如果购物车空的情况--%>
				<tr>
					<td colspan="5"><a href="index.jsp">亲，当前购物车为空！快跟小伙伴们去浏览商品吧！！！</a> </td>
				</tr>
			</c:if>
			<c:if test="${not empty requestScope.page.goods}">
				<%--如果购物车非空的情况--%>
				<c:forEach items="${requestScope.page.goods}" var="singleItem"><!--此处的goods代表Page中的每个项，可能是商品，可能是购物车项-->
					<tr>
						<td>${singleItem.goodsname}</td>
						<td>
							<input class="updateCount" style="width: 80px;"
								   goodsId="${singleItem.goodsid}"
								   type="text" value="${singleItem.count}">
						</td>
						<td>${singleItem.price}</td>
						<td>${singleItem.totalPrice}</td>
						<td><a class="deleteItem" href="Car/deleteOne?goodsId=${singleItem.goodsid}">删除</a></td>
					</tr>
				</c:forEach>
			</c:if>
		</table>
		<%--如果购物车非空才输出页面的内容--%>
		<c:if test="${not empty requestScope.page.goods}">
			<div class="cart_info">
				<span class="cart_span">购物车中共有<span class="b_count">${requestScope.totalCount}</span>件商品</span>
				<span class="cart_span">总金额<span class="b_price">${requestScope.totalPrice}</span>元</span>
				<span class="cart_span"><a id="clearCart" href="Car/deleteAll">清空购物车</a></span>
				<span class="cart_span"><a href="order/addOrder">去结账</a></span>
			</div>
		</c:if>

	</div>

	<div id="page_nav">
		<a href="${requestScope.page.url}pageid=1">首页</a>

		<c:if test="${requestScope.page.pageid>2}"> <!--大于首页才显示-->
			<a href="${requestScope.page.url}pageid=${requestScope.page.pageid-2}">${requestScope.page.pageid-2}</a>
		</c:if>

		<c:if test="${requestScope.page.pageid>1}"> <!--大于首页才显示-->
			<a href="${requestScope.page.url}pageid=${requestScope.page.pageid-1}">${requestScope.page.pageid-1}</a>
		</c:if>

		【${requestScope.page.pageid}】

		<c:if test="${requestScope.page.pageid<requestScope.page.pagetotal}"> <!--小于最后一页才显示-->
			<a href="${requestScope.page.url}pageid=${requestScope.page.pageid+1}">${requestScope.page.pageid+1}</a>
		</c:if>

		<c:if test="${requestScope.page.pageid<requestScope.page.pagetotal-1}"> <!--小于最后一页才显示-->
			<a href="${requestScope.page.url}pageid=${requestScope.page.pageid+2}">${requestScope.page.pageid+2}</a>
		</c:if>

		<a href="${requestScope.page.url}pageid=${requestScope.page.pagetotal}">末页</a>
		<!--
        共   页，  条记录
            到第<input value="4" name="pn" id="pn_input"/>页
         -->
		共${requestScope.page.pagetotal}页，${requestScope.page.jilutotal}条记录 到第<input value="${requestScope.page.pageid}" name="pn" id="pn_input"/>页
		<input type="button" id="jumpbtid" value="确定">
	</div>

	<%--静态包含页脚内容--%>
	<%@include file="/WEB-INF/jsp/pages/common/footer.jsp"%>


</body>
</html>
