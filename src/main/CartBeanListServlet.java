package main;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import main.CartDao;

@WebServlet("/CartBeanListServlet")
public class CartBeanListServlet extends HttpServlet {

    
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		doPost(request,response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		
		UserBean user=(UserBean)request.getSession().getAttribute("userBean");
		if(user==null)
		{
			request.getRequestDispatcher("loginIn.jsp").forward(request, response);
			return;
		}
		String act=request.getParameter("act");
		System.out.println("act:"+act);
		if(act!=null &&act.equals("cart"))
		{
			List<CartBean> list=new ArrayList<CartBean>();
			list=CartDao.getListAll(user);
			double totalPrice=CartDao.getListTotalPrice(user);
			
			System.out.println("totalPrice:"+totalPrice);
			request.setAttribute("cart", list);
			request.setAttribute("totalprice",totalPrice);
			request.getRequestDispatcher("/cart.jsp").forward(request, response);
		}
		if(act!=null &&act.equals("orderForm"))
		{
			System.out.println("获取我的订单……");
			List<CartBean> list=new ArrayList<CartBean>();
			list=CartDao.getListBuyOrder(user);
			request.setAttribute("orderForm", list);
			request.getRequestDispatcher("/orderForm.jsp").forward(request, response);
		}

		
	}

}
