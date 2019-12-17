package main;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet("/ListSaleItemServet")
public class ListSaleItemServet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private ItemsDao dao=new ItemsDao();

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doPost(request,response);
	}


	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		UserBean manager = (UserBean) request.getSession().getAttribute("manager");
		if(manager==null) 
		{
			request.getRequestDispatcher("/managerLoginIn.jsp").forward(request,response);
			
		}
		System.out.println("list item servlet");
		String itemName =request.getParameter("itemName");
		request.getSession().setAttribute("itemName3", itemName);
		
		List<ItemBean> list=null;
		if(itemName!=null&&!itemName.equals("null"))
		{
			itemName=new String(itemName.getBytes("ISO-8859-1"),"utf-8");
			System.out.println("部分查询："+itemName);
			list=dao.getSaleListByName(itemName);
		}
		else
		{
			list =dao.getSaleListAll();
		}
		
		request.setAttribute("items",list);
		
	
		request.getRequestDispatcher("/sellorder.jsp").forward(request, response);
		
	}

}
