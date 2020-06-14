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
<%@ page import="main.Utils.ActionUtils" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/sql" prefix="sql" %>
<!DOCTYPE html>
<html>
<head>

    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
    <title>查看操作</title>
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
            <b><a href="ListUserActionsServlet?type=manager">查看用户操作</a></b>
        </div>
        <div class="div">
            <b><a href="ListUserLoginLogServlet?type=manager">查看用户操作</a></b>
        </div>

    </aside>
    <section id="right">
        <div>

            <ul>
                <li><a href="ListUserActionsServlet?type=manager">管理员操作</a></li>
                <li><a href="ListUserActionsServlet?type=saler">销售员操作</a></li>
                <li><a href="ListUserActionsServlet?type=user">用户操作</a></li>
                <li>
                    <form class="inputbg" action="ListUserActionsServlet">
                        <input type="text" name="userName" id="userName"
                               placeholder="搜索……">
                        <input type="submit" value="确定">
                    </form>
                </li>
            </ul>
        </div>
        <div style="overflow-x: auto; overflow-y: auto; height: 550px; width: 100% ;">

            <table>

                <tr>
                    <th>类型</th>
                    <th>账号</th>
                    <th>时间</th>
                    <th>IP</th>
                    <th>操作</th>
                </tr>
                <c:forEach items="${list}" var="s" varStatus="vs">
                    <tr>

                        <td>${s.userType }</td>
                        <td>${s.userName }</td>
                        <td>${s.beginTime }</td>
                        <td>${s.ip }</td>
                        <td>${s.action}</td>

                    </tr>

                </c:forEach>


            </table>
        </div>

    </section>
</div>

<%
    ActionUtils.WriteDownActions("manager",userBean.getUserName(),userBean.getIp(),"查看用户操作");
%>
</body>
</html>