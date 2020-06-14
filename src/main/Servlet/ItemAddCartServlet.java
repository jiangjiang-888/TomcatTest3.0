package main.Servlet;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import main.Bean.CartBean;
import main.Bean.UserBean;
import main.Dao.CartDao;
import main.Utils.ActionUtils;

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

		response.setCharacterEncoding("UTF-8");
		response.setHeader("Content-type", "text/html;charset=utf-8");
		String from=request.getParameter("from");
		String itemId=request.getParameter("itemid");
		UserBean userBean=(UserBean)request.getSession().getAttribute("userBean");
		int number=Integer.parseInt(request.getParameter("number"));
			int stock = Integer.parseInt(request.getParameter("itemstock"));
			if (number > stock) number = stock;

		String itemName=(String)request.getSession().getAttribute("itemName");
		if(userBean==null)
		{

			request.getRequestDispatcher("LoginIn.jsp").forward(request, response);
			return;
		}
		CartBean cartBean=new CartBean();
		cartBean.setItemId(itemId);
		cartBean.setUserId(userBean.getUserName());
		cartBean.setBuyNumber(number);
		CartDao .insertToCart(cartBean,stock);
		ActionUtils.WriteDownActions("user",userBean.getUserName(),userBean.getIp(),"加入购物车");
		if(from!=null&&from.equals("Ifo"))
		{

			request.getHeader("Referer");// 获得上一个页面的地址 如:a.jsp
			response.sendRedirect(request.getHeader("Referer"));

		}
		else {
			if (itemName == null) {
				response.sendRedirect("ListItemServlet");
			} else {


				request.getRequestDispatcher("ListItemServlet?itemName="+itemName).forward(request,response);

			}
		}

		
		
		
		
		
	}

}
