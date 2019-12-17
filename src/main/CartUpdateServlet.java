package main;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import main.CartDao;


@WebServlet("/CartUpdateServlet")
public class CartUpdateServlet extends HttpServlet {



	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		doPost(request,response);
	}


	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		UserBean user = (UserBean) request.getSession().getAttribute("userBean");
		if(user==null) 
		{
			request.getRequestDispatcher("loginIn.jsp").forward(request, response);
			
		}
		String action=request.getParameter("action");
		String userid=(String) request.getParameter("username");
		String itemId=(String)request.getParameter("itemid");
	
		
		if(action.equals("reduce"))
		{
			CartDao.reduce(itemId, userid);
			
		}
		if(action.equals("add"))
		{
			CartDao.add(itemId, userid);
		}
		if(action.equals("delete"))
		{
			CartDao.delete(itemId, userid);
		}
		
		
		response.sendRedirect("CartBeanListServlet?act=cart");
		
		
	
	}

}
