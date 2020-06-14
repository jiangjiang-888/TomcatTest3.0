package main.Servlet;

import main.Bean.ItemBean;
import main.Dao.ItemsDao;
import main.Bean.UserBean;
import main.Utils.ActionUtils;

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
		String type=(String) req.getSession().getAttribute("type");  //管理员还是销售员
		UserBean userBean=new UserBean();

		if(type!=null&&type.equals("saler")||req.getHeader("Referer").indexOf("itemmsforSaler")>0)
		{
			userBean=(UserBean) req.getSession().getAttribute("saler");
			if(userBean==null)	req.getRequestDispatcher("/SalerLoginIn.jsp").forward(req,resp);
//			System.out.println("list item servlet");
			String itemName =req.getParameter("itemName");
			List<ItemBean> list=null;
			if(itemName==null||itemName.equals("null")||itemName.equals(" "))
			{

//				System.out.println("部分查询："+itemName);
				list=dao.getListByClass(userBean.getItemClass());
			}
			else
			{
				list=dao.getListByName(itemName,userBean.getItemClass());
			}
			req.setAttribute("items",list);

			req.getRequestDispatcher("/itemsForSaler.jsp").forward(req, resp);

		}
		else if(type!=null&&type.equals("manager")){
			userBean = (UserBean) req.getSession().getAttribute("manager");
			if(userBean==null)			req.getRequestDispatcher("/managerLoginIn.jsp").forward(req,resp);
//			System.out.println("list item servlet");
			String itemName =req.getParameter("itemName");
			req.getSession().setAttribute("itemName2", itemName);

			List<ItemBean> list=null;
			if(itemName!=null&&!itemName.equals("null"))
			{
//			itemName=new String(itemName.getBytes("ISO-8859-1"),"utf-8");
//				System.out.println("部分查询："+itemName);
				list=dao.getListByClass(itemName);
			}
			else
			{
				list =dao.getListAll();
			}
			req.setAttribute("items",list);

			req.getRequestDispatcher("/items.jsp").forward(req, resp);


		}
		else
		{
			req.getRequestDispatcher("/managerLoginIn.jsp").forward(req,resp);
		}



	}
	
}
