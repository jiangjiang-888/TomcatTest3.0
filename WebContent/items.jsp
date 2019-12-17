<%@ page language="java" contentType="text/html; charset=utf-8"
	pageEncoding="utf-8"%>
<%@ page import="main.ListItemServlet"%>
<%@ page import="main.ItemDeleteServlet"%>
<%@ page import="main.ItemModifyServlet"%>
<%@ page import="main.UserBean"%>
<%@ page import="main.RegisterDao"%>
<%@ page import="java.sql.*"%>
<%@ page import="java.io.*,java.util.*,java.sql.*"%>
<%@ page import="javax.servlet.http.*,javax.servlet.*"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/sql" prefix="sql"%>
<!DOCTYPE html>
<html>
<head>

<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>商品管理</title>
<style type="text/css">
ul {
	list-style-type: none;
	margin: 0;
	padding: 0;
	overflow: hidden;
	margin-left: 5%;
	margin-right: 5%;
}

li {
	float: left
}

li a:link, a:visited {
	display: block;
	width: 80px;
	font-weight: bold;
	color: #FFFFFF;
	background-color: #bebebe;
	text-align: center;
	padding: 4px;
	text-decoration: none;
	text-transform: uppercase;
	border-top-left-radius: 7px;
}

li a:hover, a:active {
	border-top-left-radius: 7px;
	background-color: #cc0000;
}

li input {
	border: none;
}

.inputbg {
	background-color: #bebebe;
	padding: 4px;
}

body {
	background: #eff3f5;
}

/*表格样式*/
table {
	width: 90%;
	background: #ccc;
	margin-left: 5%;
	border-collapse: collapse;
	/*border-collapse:collapse合并内外边距(去除表格单元格默认的2个像素内外边距*/
}

th, td {
	height: 25px;
	line-height: 25px;
	text-align: center;
	border: 1px solid #ccc;
}

th {
	background: #eee;
	font-weight: normal;
}

tr {
	background: #fff;
}

tr:hover {
	background: #cc0;
}

td a {
	color: #06f;
	text-decoration: none;
}

td a:hover {
	color: #06f;
	text-decoration: underline;
}

td a:link {
	color: #06f;
}

td a:visited {
	color: #06f;
}

label {
	color: white;
}

.wrap {

	width: 100%;
}

#left {
	width: 10%;
	margin-left: 10%;
	float: left;

}

#right {
	padding-top: 20px;
	background: white;
	margin-left: 20%;
	height:600px;
}

.div a:link {
	color: #D56F2B;
	text-decoration: none;
}

.div a:visited {
	color: #D56F2B;
	text-decoration: none;
}

.div a:hover {
	color: #D56F2B;
	text-decoration: underline;
}
</style>

</head>
<body>

	<%
		request.setCharacterEncoding("UTF-8");
		
	%>


<body>
	<div  style="background-color:#D56F2B;height:45px; line-height:45px; color:white; text-align:right;padding-right:300px;">
		<%
		UserBean manager = (UserBean) request.getSession().getAttribute("manager");
		if(manager==null) 
		{
			request.getRequestDispatcher("/managerLoginIn.jsp").forward(request,response);
			
		}
		else
		{
			manager=RegisterDao.GetManager(manager);
			out.print("你好,管理員:"+manager.getUserName());
			out.print("<a href=ListItemServlet?act=loginout> 注销 </a>");
			out.print("  邮箱:"+manager.getUserEmail());
		}
	%>
	</div>
	<div class="wrap">

		<aside id="left">

			<div class="div"
				style="background-color: white; color: #D56F2B; height: 100%; line-height: 50px; border-top-left-radius: 7px; text-align: center;">
				<b> <a href="ListItemServlet2">商品表</a>
				</b>
			</div>

			<div class="div"
				style="background-color: white; color: #D56F2B; height: 100%; line-height: 50px; border-top-left-radius: 7px; text-align: center;">
				<b> <a href="ListSaleItemServet">销量表 </a></b>
			</div>
			<div class="div"
				style="background-color: white; color: #D56F2B; height: 100%; line-height: 50px; border-top-left-radius: 7px; text-align: center;">
				<b> <a href="ListSaleItemServlet2">销售情况 </a></b>
			</div>

		</aside>
		<section id="right">


			<div>

				<ul>
					<li><a href="ListItemServlet2">全部商品</a></li>
					<li><a href="ListItemServlet2?itemName=颜料">颜料</a></li>
					<li><a href="ListItemServlet2?itemName=画本">画本</a></li>
					<li><a href="ListItemServlet2?itemName=记事本">记事本</a></li>
					<li><a href="ListItemServlet2?itemName=笔">笔</a></li>
					<li><a href="ListItemServlet2?itemName=带">涂改带</a></li>
					<li><a href="ListItemServlet2?itemName=尺">尺子</a></li>
					<li><a href="addItem.jsp">添加</a></li>
					<li>
						<form class="inputbg" action="ListItemServlet2">
							<input type="text" name="itemName" id="itemName"
								placeholder="搜索……"></input> <input type="submit" value="确定">
						</form>
					</li>
				</ul>
			</div>
			<div style="overflow-x: auto; overflow-y: auto; height: 550px; width: 100% ;">

			<table>

				<tr>
					<th>编号</th>
					<th>名称</th>
					<th>价格</th>
					<th>库存</th>
					<th>操作</th>
				</tr>
				<c:forEach items="${items}" var="s" varStatus="vs">

					<tr>
						<td>${s.itemId }</td>
						<td>${s.itemName }</td>
						<td>${s.itemPrice }</td>
						<td>${s.itemStock }</td>

						<td><a
							href="modifyItem.jsp?itemid=${s.itemId }&itemname=${s.itemName }&itemprice=${s.itemPrice }&itemstock=${s.itemStock }">修改</a>
							<a href="ItemDeleteServlet?itemid=${s.itemId }">删除</a></td>
					</tr>

				</c:forEach>


			</table>
			</div>

		</section>
	</div>




</body>
</html>