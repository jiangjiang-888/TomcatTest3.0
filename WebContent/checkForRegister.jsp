<%@ page language="java" contentType="text/html; charset=utf-8"
    pageEncoding="utf-8"%>
<%@ page  import="main.Bean.RegisterFormBean"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="utf-8">
<title>检查</title>
</head>
<body>
	<%
	request.setCharacterEncoding("utf-8");
	String userName=request.getParameter("userName");
	String userPassWord=request.getParameter("userPassWord");
	String userPassWord2=request.getParameter("userPassWord2");
	String userEmail=request.getParameter("userEmail");
	String userSex=request.getParameter("userSex");
	String userPhone=request.getParameter("userPhone");
	String userAddr=request.getParameter("userAddr");
	
	
	RegisterFormBean bean =new   RegisterFormBean();
	bean.setUserName(userName);
	bean.setUserPwd(userPassWord);
	bean.setUserPwd2(userPassWord2);
	bean.setUserEmail(userEmail);
	bean.setUserPhone(userPhone);
	bean.setUserAddr(userAddr);
	bean.setUserSex(userSex);
	
	out.print(userName);
	
	boolean check=bean.validate();
	if(check)
	{
		out.print("信息通过！！");
	}else
	{
		out.print("信息不符合要求！");
	}
	
	%>
</body>
</html>