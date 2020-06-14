<%@ page language="java" contentType="text/html; charset=utf-8"
         pageEncoding="utf-8" %>
<%@ page import="main.Servlet.ListItemServlet" %>
<%@ page import="main.Bean.UserBean" %>
<%@ page import="java.sql.*" %>
<%@ page import="java.io.*,java.util.*" %>
<%@ page import="javax.servlet.http.*,javax.servlet.*" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/sql" prefix="sql" %>

<!DOCTYPE html>
<html>
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
    <title>我的订单</title>
    <link href="style/cartstyle.css" rel="stylesheet" type="text/css"/>
</head>
<body>

<div class="hover"
     style="background-color:#D56F2B;height:38px; line-height:38px; color:white; text-align:right;padding-right:250px; font-size:15px;">
    <%
        UserBean userBean = (UserBean) request.getSession().getAttribute("userBean");
        if (userBean == null) {
            request.getRequestDispatcher("LoginIn.jsp").forward(request, response);
        } else {
            out.print("你好，" + "<a href='userInformation.jsp'>" + userBean.getUserName() + "</a>" + " "
            );
            out.print("  <a href=ListItemServlet?act=loginout>   注销 </a>");
            out.print("  <a href='ListItemServlet?itemName=recommendclass'>   购物主页 </a>");
            out.print("  <a href='CartBeanListServlet?act=cart'>   我的购物车</a>");
            out.print("  <a href='ListPersonasServlet'>   用户足迹 </a>");
        }

    %>

</div>
<div style="color:#D56F2B ;text-align:center; height:60px;margin: 0 auto;">
    <h2>我的订单</h2>
</div>


<div style="overflow-x: auto; overflow-y: auto; height: 550px; ">
    <c:forEach items="${orderForm}" var="s" varStatus="vs">
        <div class="cart">

            <div class="CartItemName">
                <nobr class="laber2">编&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;号</nobr>
                    ${s.itemId }
                <br>
                <nobr class="laber2">名&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;称</nobr>
                    ${s.itemName}
                <br>
                <nobr class="laber2">下单时间</nobr>
                    ${s.date}
            </div>

            <div class="CartItemPrice">￥${s.itemPrice }</div>

            <div class="CartItemNumber">

                    ${s.buyNumber }

            </div>
            <div class="CartItemPrice">￥${s.totalPrice }</div>


        </div>


    </c:forEach>


</div>
<div style="width:100% ; position:fixed ; bottom:0px; background-color:white;height:45px;">

</div>


</body>
</html>