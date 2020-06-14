<%@ page language="java" contentType="text/html; charset=utf-8"
	pageEncoding="utf-8"%>
<%@ page import="main.Servlet.ListItemServlet"%>
<%@ page import="main.Bean.UserBean"%>
<%@ page import="java.sql.*"%>
<%@ page import="java.io.*,java.util.*"%>
<%@ page import="javax.servlet.http.*,javax.servlet.*"%>
<%@ page import="main.Utils.UserBrowseUtils" %>
<%@ page import="java.text.ParseException" %>
<%@ page import="main.Utils.ActionUtils" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/sql" prefix="sql"%>

<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>我的足迹</title>
<link href="style/informationstyle.css" rel="stylesheet" type="text/css">
	<link href="style/publicstyle.css" rel="stylesheet" type="text/css">
<style>


	table caption{
		color:  rgb(177, 108, 39);

	}
	table thead, table tr {
		border-top-width: 1px;
		border-top-style: solid;
		border-top-color: rgb(230, 203, 147);
	}
	table {
		float: left;
		margin-top: 10%;
		margin-left: 5%;
		/*border-bottom-width: 1px;*/
		border-width: 1px;
		border-bottom-style: solid;
		border-bottom-color: rgb(230, 203, 147);
	}

	table td, table th {
		padding: 5px 10px;
		font-size: 12px;
		font-family: Verdana;
		color: rgb(177, 108, 39);
	}

	table tr:nth-child(even) {
		background: rgb(230, 203, 147)
	}
	table tr:nth-child(odd) {
		background: #FFF
	}
</style>
</head>
<body>
<div class="top">
			<%
				UserBean user = (UserBean) request.getSession().getAttribute("userBean");
				if (user == null)
					request.getRequestDispatcher("LoginIn.jsp").forward(request, response);
				else
				{
					out.print("  <a href=ListItemServlet?act=loginout&type=user>   注销 </a>");
					out.print("  <a href='ListItemServlet?itemName=recommendclass'>   购物主页 </a>");
					out.print("  <a href='CartBeanListServlet?act=cart'>   我的购物车</a>");
					out.print("  <a href='CartBeanListServlet?act=orderForm'>   我的订单 </a>");
					out.print("  <a href='ListPersonasServlet'>   用户足迹 </a>");
					try {
						UserBrowseUtils.getURLandTime(request,null,user.getUserName());
						ActionUtils.WriteDownActions("user",user.getUserName(),user.getIp(),"查看个人足迹");

					} catch (ParseException e) {
						e.printStackTrace();
					}

				}
			%>
</div>
	<div class="wrap">
		<aside class="left">
			<div style="height:50px;"></div>

		</aside>
		<section class="right">



			<table >
				<caption><b>我的信息</b></caption>
				<thead>
				<tr>
					<th>分析内容</th>
					<th>结果</th>
				</tr>
				</thead>

				<c:forEach items="${info}" var="s" varStatus="vs">
					<c:forEach items="${s.getKey()}" var="k" >
						<c:forEach items="${s.getValue()}" var="v">
							<tr>
								<td>${k}</td>
								<td>${v}</td>
							</tr>
						</c:forEach>
					</c:forEach>
				</c:forEach>
			</table>

			<table >
				<caption><b>我的浏览记录</b></caption>
				<thead>
				<tr>
					<th>浏览类别</th>
					<th>浏览时间</th>
				</tr>
				</thead>

				<c:forEach items="${browseMap}" var="s" varStatus="vs">
					<c:forEach items="${s.getKey()}" var="k" >
						<c:forEach items="${s.getValue()}" var="v">
							<tr>
								<td>${k}</td>
								<td>${v}</td>
							</tr>
						</c:forEach>
					</c:forEach>
				</c:forEach>
			</table>


			<table  >
				<caption><b>我的购买记录</b></caption>
				<thead>
				<tr>
					<th>购买类别</th>
					<th>购买总价</th>
				</tr>
				</thead>

				<c:forEach items="${buyMap}" var="s" varStatus="vs">
					<c:forEach items="${s.getKey()}" var="k" >
						<c:forEach items="${s.getValue()}" var="v">
							<tr>
								<td>${k}</td>
								<td>${v}</td>
							</tr>
						</c:forEach>
					</c:forEach>
				</c:forEach>
			</table>
			<div style="height:50px;"></div>
		</section>
	</div>



</body>
</html>