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
<title>用户个人信息</title>
<link href="style/informationstyle.css" rel="stylesheet" type="text/css">
	<link href="style/publicstyle.css" rel="stylesheet" type="text/css">

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
					out.print("  <a href=CartBeanListServlet?act=orderForm>   我的订单 </a>");
					try {
						UserBrowseUtils.getURLandTime(request,null,user.getUserName());
						ActionUtils.WriteDownActions("user",user.getUserName(),user.getIp(),"查看个人信息");

					} catch (ParseException e) {
						e.printStackTrace();
					}
				}
			%>
</div>
	<div class="wrap">
		<aside class="left">
		<img src="image/userimage.jpg" class="user-image"/>
		</aside>
		<section class="right">


			<div style="height:50px;"></div>
			<form action="RegisterServlet?act=modify" method="post">


				<label>账号: </label> <input class="inputForUser" type="text"
					name="userName" value="<%=user.getUserName()%>"
					style="border: none;"> <br> <br> <label>性别:</label>

				<%
					if (user.getUserSex().equals("m"))
						out.print("男");
					else if(user.getUserSex().equals("w"))
						out.print("女");
					else
						out.print(user.getUserSex());
				%>

				<span>${formBean.errors.userSex}</span> <br> <br> <label>邮箱:</label>
				<input class="inputForUser" type="text" name="userEmail"
					value="<%=user.getUserEmail()%>" /> <span>${formBean.errors.userEmail}</span>


				<br> <br> <label>联系电话:</label> <input class="inputForUser"
					type="text" name="userPhone" value="<%=user.getUserPhone()%>" /><span>${formBean.errors.userPhone}</span>
				<br> <br> <label>邮寄地址:</label> <input class="inputForUser"
					type="text" name="userAddr" value="<%=user.getUserAddr()%>" /> <span>${formBean.errors.userAddr}</span><br />
				<br /> <br /> <input class="clickbutton" type="submit"  value="修改" />
			</form>

		</section>
	</div>



</body>
</html>