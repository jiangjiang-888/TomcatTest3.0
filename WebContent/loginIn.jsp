<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ page import="java.io.*,java.util.*"%>
<%@ page import="main.LoginServlet"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="utf-8">
<title>登陆</title>

<style type="text/css">
.inputForUser {
	border: 1px solid black;
	border-radius: 5px;
	height: 22px;
	font-size: 15px;
	width: 220px;
	padding-left: 5px;
	margin: 5px;
}

.fromForUser {
	margin: 0 auto;
	width: 420px;
	height: 500px;
	background-color: white;
	border-radius:15px;
}

.clickbutton {
	height: 35px;
	background-color: #ee7700;
	border: none;
	color: white;
	font-size: 18px;
	width: 70%;
	border-radius:10px;
}

.divForForm {
	text-align: center;
}

label {
	float: left;
	width: 120px;
	height: 32px;
	text-align: right;
	color: #D56F2B;
	font-size: 15px;
}

h2 {
	display: inline-block;
	color: #D56F2B;
	margin: 0 auto;
}

body {
	background-image: url(image/bg.jpeg);
}
 a:link {
	color: #D56F2B;
	text-decoration:none;
	margin-left:15px;
}
 a:visited {
	color:#D56F2B;
	text-decoration:none;
}
 a:hover {
	color: #D56F2B;
	text-decoration: underline;
}
</style>

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
			<form action="LoginServlet" method="Post">
				<laber>账号: </laber>
				<input type="text" class="inputForUser" name="userName"
					value="${userBean.userName}"> <br />
				<laber>密码: </laber>
				<input type="password" class="inputForUser" name="userPassWord" /> <br />
				<br /> <input type="submit" class="clickbutton" value="提交" />
			</form>
			<br>
			<a href="register.jsp">注册</a>
			
			<a href="managerLoginIn.jsp">管理员登录</a>

		</div>
	</div>

</body>
</html>