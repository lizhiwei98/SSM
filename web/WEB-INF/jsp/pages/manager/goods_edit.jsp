<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>编辑图书</title>

	<%-- 静态包含 base标签、css样式、jQuery文件 --%>
	<%@ include file="/WEB-INF/jsp/pages/common/head.jsp"%>


	<style type="text/css">
	h1 {
		text-align: center;
		margin-top: 200px;
	}

	h1 a {
		color:red;
	}

	input {
		text-align: center;
	}
</style>
</head>
<body>
		<div id="header">
			<img class="logo_img" alt="" src="static/img/logo.gif" >
			<span class="wel_word">编辑商品</span>

			<%-- 静态包含 manager管理模块的菜单  --%>
			<%@include file="/WEB-INF/jsp/pages/common/manager_menu.jsp"%>


		</div>

		<div id="main">
			<form action="goods/${empty requestScope.goods ?"addGoods" :"updateGoods"}" method="post">
			<!--插入不会传入book参数，修改需要获取被修改book的数据-->
				<input type="hidden" name="id" value="${requestScope.goods.id }" />
				<input type="hidden" name="pageid" value="${requestScope.pageid}" />
				<input type="hidden" name="pagesize" value="${requestScope.pagesize}" />
				<input type="hidden" name="jilutotal" value="${requestScope.jilutotal}" />
				<table>
					<tr>
						<td>名称</td>
						<td>简介</td>
						<td>价格</td>
						<td>销量</td>
						<td>库存</td>
						<td>图片路径</td>
						<td colspan="2">操作</td>
					</tr>
					<tr>
						<td><input name="name" type="text" value="${requestScope.goods.name}"/></td>
						<td><input name="intro" type="text" value="${requestScope.goods.intro}"/></td>
						<td><input name="price" type="text" value="${requestScope.goods.price}"/></td>
						<td><input name="sales" type="text" value="${requestScope.goods.sales}"/></td>
						<td><input name="stock" type="text" value="${requestScope.goods.stock}"/></td>
						<td><input name="imgPath" type="text" value="${requestScope.goods.imgPath}"/></td>
						<td><input type="submit" value="提交"/></td>
					</tr>
				</table>
			</form>


		</div>


		<%--静态包含页脚内容--%>
		<%@include file="/WEB-INF/jsp/pages/common/footer.jsp"%>


</body>
</html>
