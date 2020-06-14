package main.Servlet;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import main.Dao.CartDao;
import main.Utils.ActionUtils;
import main.Utils.SendEmail;
import main.Bean.UserBean;

@WebServlet("/SettleServlet")
public class SettleServlet extends HttpServlet {

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		doPost(request,response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
	
		UserBean user =(UserBean)request.getSession().getAttribute("userBean");
		if(user==null)
		{
			request.getRequestDispatcher("LoginIn.jsp").forward(request, response);
		}
		String act=request.getParameter("act");
		
		if(act!=null&&act.equals("settleaccounts"));//点击结算按钮
		{
			CartDao.insertToBuyTable(user);
			ActionUtils.WriteDownActions("user",user.getUserName(),user.getIp(),"下单");
			SendEmail.sendEmails(user.getUserName(),user.getUserEmail());

		}
		response.sendRedirect("CartBeanListServlet?act=orderForm");
	}

}
