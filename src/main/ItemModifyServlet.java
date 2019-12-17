package main;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import main.ItemBean;
import main.ItemsDao;

@WebServlet("/ItemModifyServlet")
public class ItemModifyServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
 

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		doPost(request,response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		UserBean manager = (UserBean) request.getSession().getAttribute("manager");
		if(manager==null) 
		{
			request.getRequestDispatcher("/managerLoginIn.jsp").forward(request,response);
			
		}
		String itemId=request.getParameter("itemId");
		String itemName=request.getParameter("itemName");
		itemName=new String(itemName.getBytes("ISO-8859-1"),"UTF-8" );
		double itemPrice=Double.parseDouble(request.getParameter("itemPrice"));
		int itemStock=Integer.parseInt(request.getParameter("itemStock"));
		System.out.println(itemId+" "+itemName+" "+itemPrice+" "+itemStock);
		ItemBean itemBean=new ItemBean();
		itemBean.setItemId(itemId);
		itemBean.setItemName(itemName);
		itemBean.setItemPrice(itemPrice);
		itemBean.setItemStock(itemStock);
		
		ItemsDao dao=new ItemsDao();
		dao.update(itemBean);
		response.sendRedirect("ListItemServlet2?itemName="+request.getSession().getAttribute("itemName2"));
		
		
		
		
	}

}
