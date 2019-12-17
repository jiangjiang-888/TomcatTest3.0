package main;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import main.UserBean;
import main.CartDao;
@WebServlet("/ItemAddCartServlet")
public class ItemAddCartServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
    
    public ItemAddCartServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
	
	   doPost(request,response);
	}

	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		response.setHeader("Content-type", "text/html;charset=utf-8");  //乱码问题
		response.setCharacterEncoding("utf-8");
	
		String itemId=request.getParameter("itemid");
		UserBean userBean=(UserBean)request.getSession().getAttribute("userBean");
		int number=Integer.parseInt(request.getParameter("number"));
		String itemName=(String)request.getSession().getAttribute("itemName");
		
		if(userBean==null)
		{

			request.getRequestDispatcher("loginIn.jsp").forward(request, response);
			return;
		}
		CartBean cartBean=new CartBean();
		cartBean.setItemId(itemId);
		cartBean.setUserId(userBean.getUserName());
		cartBean.setBuyNumber(number);
		
		System.out.println(itemId+userBean.getUserName()+number);
		CartDao .insertToCart(cartBean);
		if(itemName==null)
		{
			response.sendRedirect("ListItemServlet");
		}
		else
		{
			response.sendRedirect("ListItemServlet?itemName="+itemName);
		}

		
		
		
		
		
	}

}
