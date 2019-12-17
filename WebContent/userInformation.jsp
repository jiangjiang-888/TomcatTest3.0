<%@ page language="java" contentType="text/html; charset=utf-8"
	pageEncoding="utf-8"%>
<%@ page import="main.ListItemServlet"%>
<%@ page import="main.UserBean"%>
<%@ page import="java.sql.*"%>
<%@ page import="java.io.*,java.util.*,java.sql.*"%>
<%@ page import="javax.servlet.http.*,javax.servlet.*"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/sql" prefix="sql"%>

<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>用户个人信息</title>

<style type="text/css">

body
{
background-color:#F2F2F2;

}
.wrap {
	margin: 0 auto;
	width: 100%;
}

#left {
	width: 10%;
	float: left;
	margin-left: 15%;
	background-color: white;
	height: 550px;
}

#right {
	
	background: #D56F2B;
	margin-left: 25%;
	margin-right: 15%;
	background-color:white;
	height: 550px;
}

label {
	width: 110px;
	height: 32px;
	text-align: right;
	color: #D56F2B;
	font-size: 18px;
	margin-left: 120px;
}

span {
	
	width: 120px;
	font-size: 18px;
	color: gray;
	text-align: left;
}

input {
	border: 1px solid #D56F2B;
	border-radius: 5px;
	height: 25px;
	font-size: 18px;
	width: 220px;
	padding-left: 5px;
	margin: 5px;
	text-color:#D56F2B;
}

.clickbutton {
	height: 30px;
	background-color: #D56F2B;
	border: none;
	color: white;
	font-size: 18px;
	width:150px;
	margin-left:120px;
	
}
a:link {
	color: white;
	text-decoration:none;
	margin-left:15px;
}
a:visited {
	color:white;
	text-decoration:none;
}
a:hover {
	color: white;
	text-decoration: underline;
}
</style>
</head>
<body>
<div style="background-color: #D56F2B;height:45px; line-height:35px; color:white; text-align:right;padding-right:300px; margin-bottom:20px;"> 
			<%
				UserBean user = (UserBean) request.getSession().getAttribute("userBean");
				if (user == null)
					request.getRequestDispatcher("loginIn.jsp").forward(request, response);
				else
				{
					out.print("  <a href=ListItemServlet?act=loginout>   注销 </a>");
					out.print("  <a href='ListItemServlet'>   购物主页 </a>");
					out.print("  <a href='CartBeanListServlet?act=cart'>   我的购物车</a>");
					out.print("  <a href=CartBeanListServlet?act=orderForm>   我的订单 </a>");
				}
			%>
</div>
	<div class="wrap">
		<aside id="left">
		<img src="image/userimage.jpg" style="width:70%;margin-left:30%; margin-top:50px;"/>
		</aside>
		<section id="right">


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