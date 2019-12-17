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
<title>我的订单</title>
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
	width: 60%;
	
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
.laber2
{
color: #D56F2B;
	font-size: 13px;

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
		out.print("  <a href='ListItemServlet'>   购物主页 </a>");
		out.print("  <a href='CartBeanListServlet?act=cart'>   我的购物车</a>");
	}

	%>

	

</div>
<div  style="color:#D56F2B ;text-align:center; height:60px;margin: 0 auto;">
<h2>我的订单</h2>
</div>


	<div style="overflow-x: auto; overflow-y: auto; height: 550px; ">
		<c:forEach items="${orderForm}" var="s" varStatus="vs">
			<div class="cart">

				<div class="CartItemName"><nobr class="laber2">编&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;号</nobr> ${s.itemId }
					<br><nobr class="laber2">名&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;称</nobr>  ${s.itemName}
					<br><nobr class="laber2">下单时间</nobr>  ${s.date}
					</div>

				<div class="CartItemPrice">￥${s.itemPrice }</div>

				<div class="CartItemNumber">
					
					 ${s.buyNumber } 
					
				</div>
				<div class="CartItemPrice">￥${s.totalPrice }</div>
				

				

			</div>


		</c:forEach>


	</div>
	<div style="width:100% ; position:fixed ; bottom:0px; background-color:white;height:45px;" >

	</div> 




</body>
</html>