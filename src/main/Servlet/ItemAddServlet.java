package main.Servlet;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import main.Bean.ItemBean;
import main.Dao.ItemsDao;
import main.Bean.UserBean;
import main.Utils.ActionUtils;

@WebServlet("/ItemAddServlet")
public class ItemAddServlet extends HttpServlet {
	  

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
	
	this.doPost(request,response);
	
	
	}
	@SuppressWarnings("static-access")
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		response.setHeader("Content-type", "text/html;charset=utf-8");
		response.setCharacterEncoding("utf-8");
		UserBean manager = (UserBean) request.getSession().getAttribute("manager");
		if(manager==null) 
		{
			request.getRequestDispatcher("/managerLoginIn.jsp").forward(request,response);
			
		}
		String itemId=request.getParameter("itemId");
		String itemName=request.getParameter("itemName");
		Double itemPrice=Double.parseDouble(request.getParameter("itemPrice"));
		int itemStock=Integer.parseInt(request.getParameter("itemStock"));
		
		ItemsDao dao=new ItemsDao();
		
		ItemBean itemBean=new ItemBean();
		
		itemBean.setItemId(itemId);
		itemBean.setItemName(itemName);
		itemBean.setItemPrice(itemPrice);
		itemBean.setItemStock(itemStock);

		
		int b=dao.check(itemId);
		
//		System.out.print("b:"+b);
		
		if(b==0)//数据库id不存在，插入
		{
			if((dao.insert(itemBean))==1)
//			System.out.println("插入成功！");

			response.sendRedirect("ListItemServlet2");

			
		}
		else 
		{
			request.setAttribute("DBMes", "该商品编号已存在");
			request.getSession().setAttribute("itemBean",itemBean);
			request.getRequestDispatcher("/addItems.jsp").forward(request, response);
			
		}
		
		
		
		
	}

}
