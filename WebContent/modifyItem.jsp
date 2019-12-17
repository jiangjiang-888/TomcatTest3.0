<%@ page language="java" contentType="text/html; charset=utf-8"
	pageEncoding="utf-8"%>
	<%@page import="main.UserBean" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="utf-8">
<title>修改商品信息</title>
<style>
input
{
width:250px;
height:20px;
border: 1px solid black;
border-radius:5px;
padding-left:10px;

}
</style>
</head>
<body style="background-color:#D56F2B ;">
<% UserBean manager = (UserBean) request.getSession().getAttribute("manager");
		if(manager==null) 
		{
			request.getRequestDispatcher("/managerLoginIn.jsp").forward(request,response);
			
}%>

<%
request.setCharacterEncoding("utf-8");

String itemId=request.getParameter("itemid");
String itemName=request.getParameter("itemname");
itemName=new String(itemName.getBytes("ISO-8859-1"),"UTF-8" );
String itemPrice=request.getParameter("itemprice");
String itemStock=request.getParameter("itemstock");

%>
<div style="width:60%; height:550px; border-radius:10px; margin:0 auto; background-color:white; 
text-align:center;line-height:50px;margin-top:50px;padding-top:30px; color:#D56F2B">
<h2>修改商品</h2>
	<form action="ItemModifyServlet" method="post">

		<label>商品编号: </label> <input  type="text"	 name="itemId" value="<%=itemId%>" > 
		<br />
		<label>商品名称: </label> <input  type="text"	 name="itemName" value="<%=itemName%>"> 
		<br />
		<label>商品价格: </label> <input  type="text"	 name="itemPrice" value="<%=itemPrice%>"> 
		<br />
		<label>商品库存: </label> <input  type="text"	 name="itemStock" value="<%=itemStock%>"> 
		<br />
		<input  type="submit" value="确认提交"  style="background-color:#D56F2B;color:white; height:30px;border:none;font-size:16px;width:150px;">
		
	</form>
</div>
</body>
</html>