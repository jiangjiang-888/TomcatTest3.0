package main;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;


@WebServlet(urlPatterns = "/ListItemServlet"  ,loadOnStartup = 1 )
public class ListItemServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private ItemsDao dao=new ItemsDao();

	public void service (HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		
		
		
		System.out.println("list item servlet");
		String itemName =req.getParameter("itemName");
		String act=null;
		act =req.getParameter("act");
		System.out.println("action:"+act);
		UserBean user= (UserBean) req.getSession().getAttribute("userBean");
		System.out.println("部分查询："+itemName);
		req.getSession().setAttribute("itemName", itemName);
		List<ItemBean> list=null;
		if(itemName!=null)
		{
			itemName=new String(itemName.getBytes("ISO-8859-1"),"utf-8");
			list=dao.getListByName(itemName);
		}
		else
		{
			list =dao.getListAll();
		}
		
		if(act!=null)
		{
			if(act.contentEquals("delete"))
			{
			String itemId=req.getParameter("itemId");
			CartDao.delete(itemId, user.getUserName());
			}
			if(act.contentEquals("loginout"))
			{
				req.getSession().setAttribute("userBean", null);
				req.setAttribute("items",list);
				req.getRequestDispatcher("/LoginSuccess.jsp").forward(req, resp);
				return;
			}
		}
	
		
		List<CartBean>listCart=CartDao.getListAll( user);
		
		req.setAttribute("items",list);
		req.setAttribute("cart", listCart);
		
		
		req.getRequestDispatcher("/LoginSuccess.jsp").forward(req, resp);
	}
	
}
