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
<title>购物车</title>
<style type="text/css">
body {
	background: #eff3f5;
}

.cart {
	height: 60px;
	margin: 10px auto;
	padding: 10px;
	padding-left: 30px;
	background-color: white;
	border-radius: 5px;
	width: 60%
}

.CartItemName {
	color: #888888;
	font-size: 15px;
	height: 35px;
	width: 60%;
	float: left;
}

.CartItemPrice {
	color: #D56F2B;
	text-align: center;
	width: 10%;
	font-size: 15px;
	margin-top: 10px;
	float: left;
}

.CartItemNumber {
	color: #D56F2B;
	text-align: center;
	whith: 10%;
	font-size: 15px;
	float: left;
	margin-top: 10px;
}
.CartItemPrice a{
font-size:12px;


}
laber {
	line-height: 20px;
	text-align: center;
}


.hover a:link {
	color: white;
	text-decoration:none;
	margin-left:15px;
}
.hover a:visited {
	color:white;
	text-decoration:none;
}
.hover a:hover {
	color: white;
	text-decoration: underline;
}

</style>

</head>
<body>

<div class="hover" style="background-color:#D56F2B;height:38px; line-height:38px; color:white; text-align:right;padding-right:250px; font-size:15px;" >  
	<%
		UserBean userBean = (UserBean) request.getSession().getAttribute("userBean");
	if(userBean==null)
	{
		request.getRequestDispatcher("loginIn.jsp").forward(request, response);
	}
	else
	{
		
		out.print("你好，" + "<a href='userInformation.jsp'>" + userBean.getUserName() + "</a>" + " "
				);
		out.print("  <a href=ListItemServlet?act=loginout>   注销 </a>");
		out.print(" 	<a href='ListItemServlet'>购物主页</a>");
		out.print("  <a href=CartBeanListServlet?act=orderForm>   我的订单 </a>");

	}

	%>
	


</div>
<div  style="color:#D56F2B ;text-align:center; height:60px;margin: 0 auto;">
<h2>我的购物车</h2>
</div>

<div class="cart" style="color:#D56F2B ;">
<div style="height:45px;width:30px;float:left; margin-right:15px;"><img src="image/addr.jpg" width="30px" height="30px "></img></div>

联系电话：<%=userBean.getUserPhone()%> <br>
常用邮箱：<%=userBean.getUserEmail()%><br>
邮寄地址：<%=userBean.getUserAddr()%><br>

</div>


	<div style="overflow-x: auto; overflow-y: auto; height: 550px; ">
		<c:forEach items="${cart}" var="s" varStatus="vs">
			<div class="cart">

				<div class="CartItemName">${s.itemId }
					<br>${s.itemName}</div>

				<div class="CartItemPrice">￥${s.itemPrice }</div>

				<div class="CartItemNumber">
					<a	href="CartUpdateServlet?action=reduce&itemid=${s.itemId }&username=${s.userId}"><img
						src="image/reduce.jpg" width="15px" height="15px"></a>
					 ${s.buyNumber } 
					<a	href="CartUpdateServlet?action=add&itemid=${s.itemId }&username=${s.userId}"><img
						src="image/add.jpg" width="15px" height="15px"></a>
				</div>
				<div class="CartItemPrice">￥${s.totalPrice }</div>
				

				<div class="CartItemPrice">
					<a
						href="CartUpdateServlet?action=delete&itemid=${s.itemId }&username=${s.userId}">删除</a>
						
				</div>

			</div>


		</c:forEach>


	</div>
	<div style="width:100% ; position:fixed ; bottom:0px; background-color:white;height:45px;" >

	 <a href="SettleServlet"><button  style="width:140px;height:35px;text-align:center;background:#E87529;border:none;color:white;border-radius: 5px; margin-top:5px;margin-right:25%;float:right;">
	 <b>下单</b>
	 </button></a>
	 	<div style="float:right; text-align:center;height:45px; line-height:45px;margin-right:15px;color:#D56F2B;font-size:18px;">￥${totalprice}</div>
	</div> 

</body>
</html>