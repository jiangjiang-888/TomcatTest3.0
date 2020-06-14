<%@ page language="java" contentType="text/html; charset=utf-8"
	pageEncoding="utf-8"%>
<%@ page import="main.Servlet.ListItemServlet"%>
<%@ page import="main.Servlet.ItemDeleteServlet"%>
<%@ page import="main.Servlet.ItemModifyServlet"%>
<%@ page import="main.Bean.UserBean"%>
<%@ page import="main.Dao.RegisterDao"%>
<%@ page import="java.sql.*"%>
<%@ page import="java.io.*,java.util.*"%>
<%@ page import="javax.servlet.http.*,javax.servlet.*"%>
<%@ page import="main.Utils.ActionUtils" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/sql" prefix="sql"%>
<!DOCTYPE html>
<html>
<head>

<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<meta http-equiv="refresh" content="5">
<title>用户浏览情况</title>
<link href="style/statestyle.css" rel="stylesheet" type="text/css" >

</head>
<body>

	<%
		request.setCharacterEncoding("UTF-8");
	%>


<body>
	<div  style="background-color:#D56F2B;height:45px; line-height:45px; color:white; text-align:right;padding-right:300px;">
		<%
		UserBean saler = (UserBean) request.getSession().getAttribute("saler");
		if(saler==null)
		{
			request.getRequestDispatcher("/SalerLoginIn.jsp").forward(request,response);
		}
		else
		{
			out.print("你好,销售員:"+saler.getUserName());
			out.print("<a href=ListItemServlet?act=loginout> 注销 </a>");
			out.print("  邮箱:"+saler.getUserEmail());
		}
			ActionUtils.WriteDownActions("saler",saler.getUserName(),saler.getIp(),"查看用户浏览情况");
	%>
	</div>
	<div class="wrap">

		<aside id="left">

			<div class="div">
				<b> <a href="ListItemServlet2">商品表</a>
				</b>
			</div>

			<div class="div">
				<b> <a href="ListSaleItemServet?action=sellorder">销量表 </a></b>
			</div>
			<div class="div">
				<b> <a href="ListSaleItemServet?action=salestate">销售情况 </a></b>
			</div>
			<div class="div">
				<b> <a href="ListUserBrowseTime">用户浏览情况 </a></b>
			</div>

		</aside>

		<section id="right">
			<div style="overflow-x: auto; overflow-y: auto; height: 550px; width: 100% ;">
			<c:forEach items="${list}" var="s" varStatus="vs">
			<div class="item">


				用户<label>${s.userName}</label>在<label>${s.beginTime}</label>到<label>${s.endTime}</label>期间浏览了商品类别为<label>${s.itemClass}</label>的商品<label>${s.browseTime }</label>秒！
			
			</div>
			</c:forEach>
			</div>


		</section>
	</div>




</body>
</html>