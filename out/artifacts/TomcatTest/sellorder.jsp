<%--
  Created by IntelliJ IDEA.
  User: 江江江
  Date: 2020/6/3
  Time: 15:56
  To change this template use File | Settings | File Templates.
--%>
<%@ page language="java" contentType="text/html; charset=utf-8"
         pageEncoding="utf-8" %>
<%@ page import="java.sql.*" %>
<%@ page import="java.io.*,java.util.*" %>
<%@ page import="javax.servlet.http.*,javax.servlet.*" %>
<%@ page import="main.*" %>
<%@ page import="static java.lang.Double.*" %>
<%@ page import="main.Dao.RegisterDao" %>
<%@ page import="main.Bean.UserBean" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/sql" prefix="sql" %>
<!DOCTYPE html>
<html>
<head>

    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
    <title>销量清单</title>
    <link href="style/showitemstyle.css" rel="stylesheet" type="text/css"/>
    <link href="style/publicstyle.css" rel="stylesheet" type="text/css"/>


</head>
<body>

    <%
		request.setCharacterEncoding("UTF-8");

	%>


<body>
<div style="background-color:#D56F2B;height:45px; line-height:45px; color:white; text-align:right;padding-right:300px;">
    <%
        UserBean manager = (UserBean) request.getSession().getAttribute("manager");
        if (manager == null) {
            request.getRequestDispatcher("/managerLoginIn.jsp").forward(request, response);

        } else {
            manager = RegisterDao.GetManager(manager);
            out.print("你好,管理員:" + manager.getUserName());
            out.print("<a href=ListItemServlet?act=loginout&type=manager> 注销 </a>");
            out.print("  邮箱:" + manager.getUserEmail());
        }
    %>
</div>
<div class="wrap">

    <aside id="left">


        <div class="div">
            <b> <a href="ListItemServlet2?type=manager">商品表</a>
            </b>
        </div>
        <div class="div">
            <b> <a href="ListSaleItemServet?action=sellorder">销量表 </a></b>
        </div>
        <div class="div">
            <b> <a href="ListSaleItemServet?action=salestate">销售情况 </a></b>
        </div>
        <div class="div">
            <b> <a href="addSaler.jsp?action=addSaler">添加销售员 </a></b>
        </div>
        <div class="div">
            <b><a href="SalerListServlet">销售员列表</a></b>
        </div>
        <div class="div">
            <b><a href="ListSaleItemServet?action=salersituation">销售员业绩</a></b>
        </div>
        <div class="div">
            <b><a href="ListUserActionsServlet?type=manager">查看操作日志</a></b>
        </div>
        <div class="div">
            <b><a href="ListUserLoginLogServlet?type=manager">查看登录日志</a></b>
        </div>
    </aside>
    <section id="right">


        <div>

            <ul>
                <li><a href="ListSaleItemServet">全部商品</a></li>
                <li><a href="ListSaleItemServet?itemName=颜料">颜料</a></li>
                <li><a href="ListSaleItemServet?itemName=画本">画本</a></li>
                <li><a href="ListSaleItemServet?itemName=记事本">记事本</a></li>
                <li><a href="ListSaleItemServet?itemName=笔">笔</a></li>
                <li><a href="ListSaleItemServet?itemName=涂改带">涂改带</a></li>
                <li><a href="ListSaleItemServet?itemName=尺子">尺子</a></li>

                <li>
                    <form class="inputbg" action="ListSaleItemServet">
                        <input type="text" name="itemName" id="itemName"
                               placeholder="搜索……"> <input type="submit" value="确定">
                    </form>
                </li>


            </ul>


        </div>
        <div style="overflow-x: auto; overflow-y: auto; height: 550px; width: 100% ;">


            <table>

                <tr>
                    <th>编号</th>
                    <th>名称</th>
                    <th>价格</th>
                    <th>销量</th>
                    <th>总价</th>

                </tr>

                <c:forEach items="${items}" var="s" varStatus="vs">

                    <tr>
                        <td>${s.itemId }</td>
                        <td>${s.itemName }</td>
                        <td>${s.itemPrice }</td>
                        <td>${s.itemSale }</td>
                        <td>${s.totalprice}</td>


                    </tr>

                </c:forEach>


            </table>
        </div>
    </section>
</div>


<div class="bottom">
    <label style="text-align:center;font-size:25px;color:#E87529;margin-top:5px;margin-right:5%;float:right;" >
        合计：￥<%out.print(request.getAttribute("totalprice"));%>
    </label>
<%--    <div class="bottom-totalprice" >合计：￥<%out.print(request.getAttribute("totalprice"));%></div>--%>
</div>

</body>
</html>