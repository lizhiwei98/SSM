<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>图书管理</title>

	<%-- 静态包含 base标签、css样式、jQuery文件 --%>
	<%@ include file="/WEB-INF/jsp/pages/common/head.jsp"%>
<script type="text/javascript">
   $(function () {

	   //给删除的a标签添加一个提示信息
   	$("a.deleteClass").click(function () {

   return confirm("你确定要删除该"+$(this).parent().parent().find("td:first").text()+"嘛")
	})


	 $("#jumpbtid").click(function () {

	 	var id=$("#pn_input").val()
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

	<div id="header">
			<img class="logo_img" alt="" src="static/img/logo.gif" >
			<span class="wel_word">小黎商城系统</span>

		<%-- 静态包含 manager管理模块的菜单  --%>
		<%@include file="/WEB-INF/jsp/pages/common/manager_menu.jsp"%>


	</div>

	<div id="main">
		<table>
			<tr>
				<td>商品名</td>
				<td>简介</td>
				<td>价格</td>
				<td>销量</td>
				<td>库存</td>
				<td colspan="2">操作</td>
			</tr>
			<c:forEach items="${requestScope.page.goods	}" var="singlegoods">

				<tr>
					<td>${singlegoods.name}</td>
					<td>${singlegoods.intro}</td>
					<td>${singlegoods.price}</td>
					<td>${singlegoods.sales}</td>
					<td>${singlegoods.stock}</td>
					<td><a class="updateClass" href="goods/toupdate?id=${singlegoods.id}&pageid=${requestScope.page.pageid}">修改</a></td>
					<td><a  class="deleteClass" href="goods/deleteGoods?id=${singlegoods.id}&pageid=${requestScope.page.pageid}&pagesize=${requestScope.page.pagesize}&jilutotal=${requestScope.page.jilutotal}">删除</a></td>
				</tr>
			</c:forEach>

			<tr>
				<td></td>
				<td></td>
				<td></td>
				<td></td>
				<td></td>
				<td></td>
				<!--传入pagetotal，以便添加完跳转到最后-->
				<td><a href="goods/toadd?pageid=${requestScope.page.pageid}&pagesize=${requestScope.page.pagesize}&jilutotal=${requestScope.page.jilutotal}">添加商品</a></td>
			</tr>
		</table>

		<div id="page_nav">
			<a href="${requestScope.page.url}&pageid=1">首页</a>

			<c:if test="${requestScope.page.pageid>2}"> <!--大于首页才显示-->
				<a href="${requestScope.page.url}&pageid=${requestScope.page.pageid-2}">${requestScope.page.pageid-2}</a>
			</c:if>

			<c:if test="${requestScope.page.pageid>1}"> <!--大于首页才显示-->
			<a href="${requestScope.page.url}&pageid=${requestScope.page.pageid-1}">${requestScope.page.pageid-1}</a>
			</c:if>

			【${requestScope.page.pageid}】

			<c:if test="${requestScope.page.pageid<requestScope.page.pagetotal}"> <!--小于最后一页才显示-->
				<a href="${requestScope.page.url}&pageid=${requestScope.page.pageid+1}">${requestScope.page.pageid+1}</a>
			</c:if>

			<c:if test="${requestScope.page.pageid<requestScope.page.pagetotal-1}"> <!--小于最后一页才显示-->
			<a href="${requestScope.page.url}&pageid=${requestScope.page.pageid+2}">${requestScope.page.pageid+2}</a>
			</c:if>

			<a href="${requestScope.page.url}&pageid=${requestScope.page.pagetotal}">末页</a>
		<!--
		共   页，  条记录
			到第<input value="4" name="pn" id="pn_input"/>页
         -->
			共${requestScope.page.pagetotal}页，${requestScope.page.jilutotal}条记录 到第<input value="${requestScope.page.pageid}" name="pn" id="pn_input"/>页
			<input type="button" id="jumpbtid" value="确定">

		</div>

	</div>


	<%--静态包含页脚内容--%>
	<%@include file="/WEB-INF/jsp/pages/common/footer.jsp"%>


</body>
</html>
