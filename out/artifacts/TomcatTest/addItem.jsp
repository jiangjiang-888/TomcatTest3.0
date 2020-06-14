<%@ page language="java" contentType="text/html; charset=utf-8"
         pageEncoding="utf-8" %>
<%@ page import="main.Bean.UserBean" %>
<%@ page import="main.Utils.ActionUtils" %>
<!DOCTYPE html>
<html>
<head>
    <meta charset="utf-8">
    <title>添加商品</title>
	<link href="style/modifystyle.css" rel="stylesheet" type="text/css">

</head>
<body>
<%
    String type=(String)request.getSession().getAttribute("type");
    if("manager".equals(type))
    {
        UserBean manager = (UserBean) request.getSession().getAttribute("manager");
        if (manager == null) {
            request.getRequestDispatcher("/managerLoginIn.jsp").forward(request,response);

        }
    }
    else if("saler".equals(type))
    {
        UserBean saler=(UserBean)request.getSession().getAttribute("saler");
        if(saler==null)
        {

            request.getRequestDispatcher("/SalerLoginIn.jsp").forward(request,response);
        }
    }



%>
<div >
    <h2>添加商品</h2>

    <form method="post" action="UploadServlet" enctype="multipart/form-data">
        <label>商品编号: </label> <input type="text" name="itemId" value="${itemBean.itemId}">
        <br/>
        <label>商品名称: </label> <input type="text" name="itemName" value="${itemBean.itemName}">
        <br/>
        <label>商品价格: </label> <input type="text" name="itemPrice"  value="${itemBean.itemPrice}">
        <br/>
        <label>商品库存: </label> <input type="text" name="itemStock" value="${itemBean.itemStock}">
		<br/>
		<label>商品类别: </label>
        <select name="itemClass" class="inputForUser"  value="${itemBean.itemClass}"  required="required">
            <option value ="颜料">颜料</option>
            <option value ="画本">画本</option>
            <option value="记事本">记事本</option>
            <option value="笔">笔</option>
            <option value="涂改带">涂改带</option>
            <option value="尺子">尺子</option>
        </select>
        <br/>
        <label>商品介绍: </label> <input type="text" name="itemIfo"  value="${itemBean.itemIfo}">
        <br/>
        选择一个图片: <input type="file" name="uploadFile"  value="${itemBean.filename}"/>
        <br/><br/>
        <input type="submit" value="确定" class="submit"/>
    </form>
        <span>
            <%
                String wrong=request.getParameter("message");
                if(wrong!=null)
                    out.print(wrong);
            %>
        </span>
</div>

</body>
</html>