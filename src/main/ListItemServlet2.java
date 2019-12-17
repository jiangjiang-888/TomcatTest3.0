package main;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;


@WebServlet(urlPatterns = "/ListItemServlet2"  )
public class ListItemServlet2 extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private ItemsDao dao=new ItemsDao();
	
	
	public void service (HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		UserBean manager = (UserBean) req.getSession().getAttribute("manager");
		if(manager==null) 
		{
			req.getRequestDispatcher("/managerLoginIn.jsp").forward(req,resp);
			
		}
		
		System.out.println("list item servlet");
		String itemName =req.getParameter("itemName");
		req.getSession().setAttribute("itemName2", itemName);
		
		System.out.println("部分查询："+itemName);
		List<ItemBean> list=null;
		if(itemName!=null&&!itemName.equals("null"))
		{
			itemName=new String(itemName.getBytes("ISO-8859-1"),"utf-8");
			list=dao.getListByName(itemName);
		}
		else
		{
			list =dao.getListAll();
		}
		
		req.setAttribute("items",list);
		
	
		req.getRequestDispatcher("/items.jsp").forward(req, resp);
	}
	
}
