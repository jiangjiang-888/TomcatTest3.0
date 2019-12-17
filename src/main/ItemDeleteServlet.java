package main;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import main.ItemsDao;


@WebServlet("/ItemDeleteServlet")
public class ItemDeleteServlet extends HttpServlet {
    private ItemsDao itemdao;
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		this.doPost(request, response);
	}

	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
	
		UserBean manager = (UserBean) request.getSession().getAttribute("manager");
		if(manager==null) 
		{
			request.getRequestDispatcher("/managerLoginIn.jsp").forward(request,response);
			
		}
		String itemId=request.getParameter("itemid");
		System.out.println(itemId);
		itemdao.delete(itemId);
		if(request.getSession().getAttribute("itemName2")!=null)
			response.sendRedirect("ListItemServlet2?itemName="+request.getSession().getAttribute("itemName2"));
		else
			response.sendRedirect("ListItemServlet2?");
			

		
	}

}
