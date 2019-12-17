package main;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet("/ListSaleItemServlet2")
public class ListSaleItemServlet2 extends HttpServlet {
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
		
		List<ItemBean> list=null;
		list =dao.getSaleListAll2();
		request.setAttribute("items",list);
		request.getRequestDispatcher("/salestate.jsp").forward(request, response);
		
	}

}
