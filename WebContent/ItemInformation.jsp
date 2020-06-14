<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%--
  Created by IntelliJ IDEA.
  User: 江江江
  Date: 2020/5/30
  Time: 18:09
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@page import="main.Bean.UserBean" %>
<%@ page import="main.Dao.RegisterDao" %>
<%@ page import="main.Utils.ActionUtils" %>
<%@ page import="main.Utils.UserBrowseUtils" %>
<%@ page import="java.text.ParseException" %>
<html>
<head>
    <title>产品详情</title>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
    <link href="style/informationstyle.css" rel="stylesheet" type="text/css">
    <link href="style/publicstyle.css" rel="stylesheet" type="text/css">

    <script>
        function add(){
            if (parseInt(document.getElementById("buynumber").value)<parseInt(document.getElementById("stock").innerText))
                document.getElementById("buynumber").value=parseInt(document.getElementById("buynumber").value)+1;
            else
            {
                alert("不能超过库存哦>_<");
            }

        }

        function reduce(){
            if (parseInt(document.getElementById("buynumber").value)>1)
                document.getElementById("buynumber").value=parseInt(document.getElementById("buynumber").value)-1;
            else
            {
                alert("不能再少了啦>_<");
            }
        }
        function check(){
            if (parseInt(document.getElementById("buynumber").value)>parseInt(document.getElementById("stock").innerText))
            {
                document.getElementById("buynumber").value=parseInt(document.getElementById("stock").innerText);
                alert("不能超过库存哦>_<");
            }

            if(parseInt(document.getElementById("buynumber").value)<1) {
                alert("不能再少了啦>_<");
                document.getElementById("buynumber").value = 1;
            }
        }
    </script>
</head>
<body>


<%
    request.setCharacterEncoding("utf-8");
    String itemId = request.getParameter("itemid");
    String itemName = request.getParameter("itemname");
//    itemName = new String(itemName.getBytes("ISO-8859-1"), "UTF-8");
    String itemPrice = request.getParameter("itemprice");
    String itemStock = request.getParameter("itemstock");
    String filename = request.getParameter("filename");
    String itemIfo = request.getParameter("itemIfo");
    String itemclass=request.getParameter("itemclass");
//    itemIfo=new String(itemIfo.getBytes("ISO-8859-1"),"UTF-8");

%>


<div class="top">
    <%--    头部导航栏--%>

    <%
        request.setCharacterEncoding("UTF-8");
    %>
    <%
        UserBean userBean = (UserBean) request.getSession().getAttribute("userBean");
        if (userBean != null) {
            out.print("你好，" + "<a href='userInformation.jsp'>" + userBean.getUserName() + "</a>" + " "
            );
            out.print("  <a href=ListItemServlet?act=loginout&type=user>   注销 </a>");
            out.print("  <a href='ListItemServlet?itemName=recommendclass'>   购物主页 </a>");
            out.print("  <a href='CartBeanListServlet?act=cart'>   我的购物车</a>");
            out.print("  <a href=CartBeanListServlet?act=orderForm>   我的订单 </a>");
            try {
                UserBrowseUtils.getURLandTime(request,itemclass,userBean.getUserName());
            } catch (ParseException e) {
                e.printStackTrace();
            }
            ActionUtils.WriteDownActions("user",userBean.getUserName(),userBean.getIp(),"查看商品详情");

        } else {
            out.print("<a href='LoginIn.jsp'>请登录<a>");
        }

    %>

</div>
<aside class="left-item">
    <img src=<%=filename%> class="user-image"/>
</aside>
<section class="right-item">

    <div class="item-Name">
        <%=itemName%>
    </div>
    <br>
    <div> <span>种类：</span><%=itemclass%>
    </div>
    <br>
    <div> <span>价格：</span><%=itemPrice%>
    </div>
    <br>
    <div> <span>库存：</span><span id="stock"><%=itemStock%></span>
    </div>
    <form action="ItemAddCartServlet"

    <%if(userBean==null)
    {
        out.print("target='_blank'");
    }%>>
        <input id="itemid" name="itemid" value="<%=itemId%>" style="visibility: hidden"/>
        <div>
            <span>购买数量</span>
            <img src="image/reduce.jpg" width="15px" height="15px" onclick="reduce()">
            <input id="buynumber" style="width:50px;" name="number" value="1"  onblur="check()"/>
            <img src="image/add.jpg" width="15px" height="15px" onclick="add()">
        </div>
        <br>
        <div><span>详情：</span><%=itemIfo%>
        </div>
        <br><br>
        <input type="submit" class="clickbutton" onclick="alert('已加入购物车')" value="加入购物车"/>
        <input name="from" id="from" value="Ifo" style="visibility: hidden"/>
        <input id="itemstock" name="itemstock" value="<%=itemStock%>" style="visibility: hidden"/>
    </form>


</section>


</body>
</html>
