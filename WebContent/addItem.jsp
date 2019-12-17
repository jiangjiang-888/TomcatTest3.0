<%@ page language="java" contentType="text/html; charset=utf-8"
	pageEncoding="utf-8"%>
	<%@ page import="main.UserBean"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="utf-8">
<title>添加商品</title>
<style>
input
{
width:250px;
height:25px;
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
			//request.getRequestDispatcher("/managerLoginIn.jsp").forward(request,response);
			
		}%>
<div style="width:60%; height:550px; border-radius:10px; margin:0 auto; background-color:white; 
text-align:center;line-height:50px;margin-top:50px;padding-top:30px; color:#D56F2B">
	<h2>添加商品</h2>

	<form method="post" action="UploadServlet" enctype="multipart/form-data">
	<label>商品编号: </label> <input  type="text"	 name="itemId" > 
		<br />
		<label>商品名称: </label> <input  type="text"	 name="itemName" > 
		<br />
		<label>商品价格: </label> <input  type="text"	 name="itemPrice" > 
		<br />
		<label>商品库存: </label> <input  type="text"	 name="itemStock" > 
		<br />
              选择一个图片: <input type="file" name="uploadFile" />
    		<br/><br/>
    <input type="submit" value="确定" style="background-color:#D56F2B;color:white; height:30px;border:none;font-size:16px;width:150px;"/>
</form>
	
</div>

</body>
</html>