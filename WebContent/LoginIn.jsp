<%@ page language="java" contentType="text/html; charset=UTF-8"
		 pageEncoding="UTF-8"%>
<%@ page import="java.io.*,java.util.*"%>
<%@ page import="main.Servlet.LoginServlet"%>
<!DOCTYPE html>
<html>
<head>
	<meta charset="utf-8">
	<title>登陆</title>
	<link href="style/loginstyle.css" rel="stylesheet" type="text/css" />
</head>
<body>
<%String wrong=(String)request.getAttribute("DBMes");
	if(wrong!=null)out.print("<script language=javascript>alert('"+wrong+"')</script>"); %>
<div class="divForForm">
	<div class="fromForUser">
		<br />
		<h2>登录</h2>
		<br />
		<br />
		<form action="LoginServlet?act=user" method="Post">
			<label>账号: </label>
			<input type="text" class="inputForUser" name="userName"
				   value="${userBean.userName}"> <br />
			<label>密码: </label>
			<input type="password" class="inputForUser" name="userPassWord" /> <br />
			<br /> <input type="submit" class="clickbutton" value="提交" />
		</form>
		<br>
		<a href="register.jsp">注册</a>
		<a href="SalerLoginIn.jsp">销售员登录</a>
		<a href="managerLoginIn.jsp">管理员登录</a>
	</div>
</div>

</body>
</html>