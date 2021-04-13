<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>小黎商城首页</title>

	<%-- 静态包含 base标签、css样式、jQuery文件 --%>
	<%@ include file="/WEB-INF/jsp/pages/common/head.jsp"%>

	<script type="text/javascript">
		$(function () {

			//var basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";

			// 给加入购物车按钮绑定单击事件
			$("button.addToCart").click(function () {
				/**
				 * 在事件响应的function函数 中，有一个this对象，这个this对象，是当前正在响应事件的dom对象
				 * @type {jQuery}
				 */
				var goodsId = $(this).attr("goodsId");
				//var basePath = $(this).attr("basePath"); //在按钮传url就行
				//console.log(basePath);
				//console.log("xxxxxxxxxxxxxx")
				//var TbasePath =basePath.substr(0,basePath.length-3)
				$.getJSON("${requestScope.page.path}"+"/Car/addItem","goodsId="+goodsId,function (data) {
					//会得到 http://localhost:8080/SSM-1%7D/Car/addItem?goodsId=29
					//%7D是 } 字符
				 $("#totalCount").text("您的购物车中有"+data.totalCount+"件商品")
				 $("#lastname").text(data.lastName)
			 })
			});

			//给删除的a标签添加一个提示信息
			$("a.deleteClass").click(function () {

				return confirm("你确定要删除该"+$(this).parent().parent().find("td:first").text()+"嘛")
			})


			$("#jumpbtid").click(function () {

				var id=$("#pn_input").val()
				console.log(id);
				if(id>0||id<${requestScope.page.pagetotal+1}){
					//javascript中的location的href属性就是当前地址栏的地址
					location.href="${pageContext.getAttribute("bathPath")}${requestScope.page.url}&pageid="+id;
				}else{
					alert("请输入正确的页码")
				}
			})

		})


	</script>

</head>
<body>
<%--body里面的地址由base标签确定了，直接用相对地址即可--%>
	<div id="header">
			<img class="logo_img" alt="" src="static/img/logo.gif" >
			<span class="wel_word">小黎商城</span>
			<div>
				<!--判断有没有登陆，登录了则不要显示这两个功能--->
				<c:if test="${empty sessionScope.username}">
					<a href="user/tologin">登录</a> |
					<a href="user/toregist">注册</a> &nbsp;
<%--					head.jsp中basePath获取到了http://localhost:8080/SSM-1/--%>
				</c:if>

				<!--
				判断有没有登陆，登录了则需要显示这两个功能,
				实现了真正的登录功能和注销功能，
				即使用session保存用户登录信息，借此来判断当前用户是否已经登录，
				即判断session域中有没有保存相关的数据。
				--->
				&nbsp;<c:if test="${not empty sessionScope.username}">
				<span>欢迎<span class="um_span">${sessionScope.username}</span>光临小黎商城</span>
				<a href="order/orderList">我的订单</a>
				<a href="Car/carList?username=${sessionScope.username}">购物车</a>
				&nbsp;<c:if test="${sessionScope.username=='admin'}">
				<a href="goods/tomanager">后台管理</a><!--WEB-INF/jsp/pages/manager/manager.jsp不能直接访问WEB-INF-->
			      </c:if>
				<a href="user/logout">注销</a>&nbsp;&nbsp;

			</c:if>
			</div>
	</div>

	<div id="main">x`
		<div id="book">
			<div class="book_cond">
				<form action="goods/listByPrice" method="get">

					价格：<input id="min" type="text" name="min" value="${param.min}"> 元 -
						<input id="max" type="text" name="max" value="${param.max}"> 元
						<input type="submit" value="查询" />
				</form>
			</div>
			<div style="text-align: center">
				<span id="totalCount"></span>
				<div>
					您刚刚将<span id="lastname" style="color: red">${sessionScope.lastName}</span>加入到了购物车中
				</div>
			</div>

			<c:forEach items="${requestScope.page.goods}" var="goods">


			<div class="b_list">
				<div class="img_div">
					<img class="book_img" alt="" src="${goods.imgPath}" />
				</div>
				<div class="book_info">
					<div class="book_name">
						<span class="sp1">商品名:</span>
						<span class="sp2">${goods.name}</span>
					</div>
					<div class="book_author">
						<span class="sp1">简介:</span>
						<span class="sp2">${goods.intro}</span>
					</div>
					<div class="book_price">
						<span class="sp1">价格:</span>
						<span class="sp2">${goods.price}</span>
					</div>
					<div class="book_sales">
						<span class="sp1">销量:</span>
						<span class="sp2">${goods.sales}</span>
					</div>
					<div class="book_amount">
						<span class="sp1">库存:</span>
						<span class="sp2">${goods.stock}</span>
					</div>
					<c:if test="${not empty sessionScope.username}">
					<div class="goods_add">
						<button class="addToCart" goodsId="${goods.id}" basePath="${requestScope.page.path}">加入购物车</button>
<%--						basePath的结果是 / 项目名 }  多了一个}       /ssm}    --%>
					</div>
					</c:if>
				</div>
			</div>

			</c:forEach>

		</div>
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
