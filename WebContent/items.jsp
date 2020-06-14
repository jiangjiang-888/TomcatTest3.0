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
//        UserBean userBean = (UserBean) request.getSession().getAttribute("saler");

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
        <div>

            <ul>
                <li><a href="ListItemServlet2?type=manager">全部商品</a></li>
                <li><a href="ListItemServlet2?type=manager&itemName=颜料">颜料</a></li>
                <li><a href="ListItemServlet2?type=manager&itemName=画本">画本</a></li>
                <li><a href="ListItemServlet2?type=manager&itemName=记事本">记事本</a></li>
                <li><a href="ListItemServlet2?type=manager&itemName=笔">笔</a></li>
                <li><a href="ListItemServlet2?type=manager&itemName=涂改带">涂改带</a></li>
                <li><a href="ListItemServlet2?type=manager&itemName=尺子">尺子</a></li>
                <li><a href="addItem.jsp">添加</a></li>
                <li>
                    <form class="inputbg" action="ListItemServlet2?type=manager">
                        <input type="text" name="itemName" id="itemName"
                               placeholder="搜索……">
                        <input type="submit" value="确定">
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
                    <th>库存</th>
                    <th>类别</th>
                    <th>操作</th>
                </tr>
                <c:forEach items="${items}" var="s" varStatus="vs">

                    <tr>

                        <td>${s.itemId }</td>
                        <td>
                          ${s.itemName }
                        </td>
                        <td>${s.itemPrice }</td>
                        <td>${s.itemStock }</td>
                        <td>${s.itemClass}</td>

                        <td><a
                                href="modifyItem.jsp?itemid=${s.itemId }&itemname=${s.itemName }&itemprice=${s.itemPrice }&itemstock=${s.itemStock }">修改</a>
                            <a href="ItemDeleteServlet?itemid=${s.itemId }">删除</a></td>
                    </tr>

                </c:forEach>


            </table>
        </div>

    </section>
</div>

<%
    ActionUtils.WriteDownActions("manager",userBean.getUserName(),userBean.getIp(),"查看商品");
%>
</body>
</html>