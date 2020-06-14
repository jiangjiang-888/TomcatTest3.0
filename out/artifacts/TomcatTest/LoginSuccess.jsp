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
    <title>购物主页</title>
    <link href="style/publicstyle.css" rel="stylesheet"  type="text/css" >
    <style type="text/css">
        ul {
            list-style-type: none;
            margin: 0;
            padding: 0;
            overflow: hidden;
            margin-left: 5%;
            margin-right: 5%;
        }

        li {
            float: left
        }

        li a:link, a:visited {
            display: block;
            width: 80px;
            font-weight: bold;
            color: #FFFFFF;
            background-color: #E6E6E6;
            text-align: center;
            padding: 4px;
            text-decoration: none;
            text-transform: uppercase;
            border-top-left-radius: 7px;
        }

        li a:hover, a:active {
            border-top-left-radius: 7px;
            background-color: #cc0000;
        }

        li input {
            border: none;
        }

        .inputbg {
            background-color: #E6E6E6;
            padding: 4px;
        }

        body {
            background: #eff3f5;
        }

        label {
            color: white;
        }

        .wrap {
            margin: 0 auto;
            width: 100%;
            margin-top: 20px;
        }

        #left {
            width: 15%;
            float: left;
            margin-left: 5%;
        }

        #right {
            padding-top: 20px;
            background: white;
            margin-left: 20%;
            margin-right: 5%;

        }

        .cart {
            height: 60px;
            margin: 10px;
            padding: 10px;
            background-color: white;
            border-radius: 5px;
        }

        .CartItemName {
            color: #888888;
            font-size: 13px;
            height: 35px;
            width: 60%;
            float: left;
        }

        .CartItemPrice {
            color: #D56F2B;
            text-align: center;
            width: 30%;
            float: right;
            font-size: 10px;
            margin-top: 10px;
        }

        .CartItemNumber {
            color: #D56F2B;
            text-align: center;
            whith: 30%;
            height: 50%;
            float: right;
        }


        .div a:link {
            color: #D56F2B;
            text-decoration: none;
        }

        .div a:visited {
            color: #D56F2B;
            text-decoration: none;
        }

        .div a:hover {
            color: #D56F2B;
            text-decoration: underline;
        }


        .CartItemPrice a:link {
            color: #D56F2B;
            text-decoration: none;
        }

        .CartItemPrice a:visited {
            color: #D56F2B;
            text-decoration: none;
        }

        .CartItemPrice a:hover {
            color: #D56F2B;
            text-decoration: underline;
        }

        .hover a:link {
            color: white;
            text-decoration: none;
            margin-left: 15px;
        }

        .hover a:visited {
            color: white;
            text-decoration: none;
        }

        .hover a:hover {
            color: white;
            text-decoration: underline;
        }

    </style>

</head>
<body>
<div class="top">
    <%
        request.setCharacterEncoding("UTF-8");
    %>
    <%
        UserBean userBean = (UserBean) request.getSession().getAttribute("userBean");
        if (userBean != null) {
            out.print("你好，" + "<a href='userInformation.jsp'>" + userBean.getUserName() + "</a>" + " "
            );
            out.print("  <a href=ListItemServlet?act=loginout&type=user>   注销 </a>");
            out.print("  <a href='CartBeanListServlet?act=cart'>   我的购物车</a>");
            out.print("  <a href=CartBeanListServlet?act=orderForm>   我的订单 </a>");
            out.print("  <a href='ListPersonasServlet'>   用户足迹 </a>");

        } else {
            out.print("<a href='LoginIn.jsp'>请登录<a>");
        }
    %>
</div>
<div class="wrap">
    <aside id="left">
        <div class="div" style="background-color: white; color: #D56F2B; height: 50px; line-height: 50px; border-top-left-radius: 7px; text-align: center;">
            <b><a href='CartBeanListServlet?act=cart'>我的购物车</a></b>
        </div>

        <div style="height: 450px; overflow-y: scroll; margin-top: 10px; ">
            <c:forEach items="${cart}" var="s" varStatus="vs">

                <div class="cart">

                    <div class="CartItemName">${s.itemName}</div>
                    <div class="CartItemPrice">
                        ￥${s.itemPrice}*${s.buyNumber } <br> <br> <a
                            href="ListItemServlet?act=delete&itemId=${s.itemId}">删除</a>
                    </div>

                </div>


            </c:forEach>


        </div>


    </aside>
    <section id="right">


        <div>

            <ul>
                <%
                    if(userBean!=null)
                        out.print("<li><a href=\"ListItemServlet?itemName=recommendclass\">推荐</a></li>");
                %>
                <li><a href="ListItemServlet">全部商品</a></li>
                <li><a href="ListItemServlet?itemName=颜料">颜料</a></li>
                <li><a href="ListItemServlet?itemName=画本">画本</a></li>
                <li><a href="ListItemServlet?itemName=记事本">记事本</a></li>
                <li><a href="ListItemServlet?itemName=笔">笔</a></li>
                <li><a href="ListItemServlet?itemName=涂改带">涂改带</a></li>
                <li><a href="ListItemServlet?itemName=尺子">尺子</a></li>
                <li>
                    <form class="inputbg" action="ListItemServlet">
                        <input type="text" name="itemName" id="itemName"
                               placeholder="搜索……"></input> <input type="submit" value="确定">
                    </form>
                </li>


            </ul>


        </div>


        <div style="overflow-x: auto; overflow-y: auto; height: 550px; width: 100% ; background-color:#F7F7F7">

            <c:forEach items="${items}" var="s" varStatus="vs">

                <div style="width:20%; height:350px; float:left;padding:10px;margin:15px; background-color:white; border-radius:10px;">
                    <div style="width:100%; height:250px;">
                        <a href="ItemInformation.jsp?itemid=${s.itemId }&itemname=${s.itemName }&itemprice=${s.itemPrice }&itemstock=${s.itemStock }&itemclass=${s.itemClass}&filename=${s.filename}&itemIfo=${s.itemIfo}" target="_blank">
                            <img src=${s.filename } style="height:100%;width:100%;margin:0;" alt="图片加载失败">
                        </a>

                    </div>
                    <div style=" height :36px;margin-top:10px;line-height:18px;display: -webkit-box;-webkit-box-orient: vertical;-webkit-line-clamp: 2;overflow: hidden;">${s.itemName}</div>
                    <div class="div">
                        <label style="color:red;font-size:20px;"> ￥${s.itemPrice } </label>
                        <label style="color:gray;font-size:13px;"> 库存${s.itemStock }</label>
                        <a href="ItemAddCartServlet?itemid=${s.itemId }&number=1&itemName=<%=request.getSession().getAttribute("itemName")%>&itemstock=${s.itemStock }"><img
                                style="height:24px;float:right;" src="image/cart.jpg"></a>
                    </div>
                </div>
            </c:forEach>


        </div>
        <div style="background-color:white;height:20px "></div>
    </section>
</div>


</body>
</html>