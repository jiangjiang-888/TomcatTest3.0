<%@ page language="java" contentType="text/html; charset=utf-8"
         pageEncoding="utf-8" %>
<%@ page import="main.Servlet.ListItemServlet" %>
<%@ page import="main.Servlet.ItemDeleteServlet" %>
<%@ page import="main.Servlet.ItemModifyServlet" %>
<%@ page import="main.Bean.UserBean" %>
<%@ page import="main.Dao.RegisterDao" %>
<%@ page import="java.sql.*" %>
<%@ page import="java.io.*,java.util.*" %>
<%@ page import="javax.servlet.http.*,javax.servlet.*" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/sql" prefix="sql" %>
<!DOCTYPE html>
<html>
<head>

    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
    <title>商品管理</title>
    <link href="style/showitemstyle.css" rel="stylesheet" type="text/css"/>

</head>
<body>

<%
    request.setCharacterEncoding("UTF-8");

%>

<div style="background-color:#D56F2B;height:45px; line-height:45px; color:white; text-align:right;padding-right:300px;">
    <%
        UserBean userBean = (UserBean) request.getSession().getAttribute("manager");

        if (userBean == null) {
            request.getRequestDispatcher("/managerLoginIn.jsp").forward(request, response);

        } else {
            userBean = RegisterDao.GetManager(userBean);
            out.print("你好,管理員:" + userBean.getUserName());
            out.print("<a href=ListItemServlet?act=loginout&type=manager> 注销 </a>");
            out.print("  邮箱:" + userBean.getUserEmail());
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
        <div style="overflow-x: auto; overflow-y: auto; height: 550px; width: 100% ;">

            <table>

                <tr>
                    <th>账号</th>
                    <th>密码</th>
                    <th>销售类别</th>
                    <th>邮箱</th>
                    <th>操作</th>
                </tr>
                <c:forEach items="${salers}" var="s" varStatus="vs">

                    <tr>

                        <td>${s.userName }</td>
                        <td>${s.userPwd }</td>
                        <td>
                                ${s.itemClass}
                        </td>
                        <td>${s.userEmail }</td>

                        <td><a href="addSaler.jsp?salerid=${s.userName}&ItemClass=${s.itemClass}&Saleremail=${s.userEmail}&action=modifySaler">修改</a>
                            <a href="SalerAddServlet?itemclass=${s.itemClass}&action=deleteSaler">删除</a></td>
                    </tr>

                </c:forEach>


            </table>
        </div>

    </section>
</div>


</body>
</html>