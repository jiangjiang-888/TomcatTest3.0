<%--
  Created by IntelliJ IDEA.
  User: 江江江
  Date: 2020/5/30
  Time: 15:18
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ page import="java.io.*,java.util.*"%>
<%@ page import="main.Servlet.SalerAddServlet"%>
<%@ page import="main.Bean.UserBean"%>
<html>
<head>
        <%
    UserBean manager = (UserBean) request.getSession().getAttribute("manager");
    if (manager == null) {
        request.getRequestDispatcher("/managerLoginIn.jsp").forward(request,response);

    }
    String action=request.getParameter("action");
    String salerid=request.getParameter("salerid");
    String itemClass=request.getParameter("ItemClass");
    String saleremail=request.getParameter("Saleremail");


%>
    <title>
        <%
            if(action!=null&&action.equals("addSaler"))
                out.print("添加销售员");
            if(action!=null&&action.equals("modifySaler"))
                out.print("重置销售员");
        %>
    </title>
    <link href="style/loginstyle.css" rel="stylesheet" type="text/css"/>

<body>

<div class="divForForm">
    <div class="fromForUser">
        <br/>
        <h2>
            <%
            if(action!=null&&action.equals("addSaler"))
                out.print("添加销售员");
            if(action!=null&&action.equals("modifySaler"))
                out.print("重置销售员");
        %>
        </h2>
        <br/>
        <br/>
        <form method="Post" action="SalerAddServlet?action=<%=action%>">
            <label>销售员账号：</label><input type="text" class="inputForUser" name="name" value=
                <%
                    if(salerid!=null)out.print("\""+salerid+"\"");
                    else out.print("\"\"");
                %>
                required
                                       <% if(action!=null&&action.equals("modifySaler"))
                                           out.print("readonly=\"readonly\"");

                                       %>

        />
            <br>
            <label>密码：</label><input type="password" class="inputForUser" name="pwd" required="required"/>
            <br>
            <label>邮箱：</label><input type="text" class="inputForUser" name="email" id="email" value=
            <%
                    if(saleremail!=null)out.print("\""+saleremail+"\"");
                    else out.print("\"\"");
                %>
                required="required" />
            <br>
            <span id="notice" class="notice"></span>
            <br>
            <label>销售种类：</label><select name="Itemclass" class="inputForUser"  value=
                <%
                    if(itemClass!=null)out.print("\""+itemClass+"\"" );
                    else out.print("\"\"");
                %>


            required="required">
                <option value =""> </option>
                <option value ="颜料">颜料</option>
                <option value ="画本">画本</option>
                <option value="记事本">记事本</option>
                <option value="笔">笔</option>
                <option value="涂改带">涂改带</option>
                <option value="尺子">尺子</option>
            </select>

            <span class="notice">注意：每一种销售种类只能指定一名销售员。</span>

<%
    if(request.getHeader("Referer")!=null&&request.getHeader("Referer").indexOf("SalerAddServlet")>0)
    {
        out.print("<span class=\"notice\" style=\" color:red;fontsize:25px;\">该销售类型已指定销售员或该销售员已存在！！！！！</span>");
    }
%>
            <br>

            <br>
            <br>
            <input type="submit" class="clickbutton" value="提交">

        </form>
    </div>
</div>

<script>
    let re = /^\w+@[a-zA-Z0-9]{2,10}(?:\.[a-z]{2,4}){1,3}$/;

    //绑定文本输入时触发的事件
    email.oninput = function() {

        let textBox = this.value;

        //判断检测这个值是否正确，
        if (re.test(textBox)) { //如果验证正确执行以下代码
            notice.innerHTML = '邮箱验证成功';
            notice.style.color = 'plum';
        } else { //验证不成功，执行以下代码
            notice.innerHTML = '邮箱验证失败，请重新输入';
            notice.style.color = 'green';
        }
    }
</script>


</body>
</html>
