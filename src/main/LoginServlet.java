package main;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import main.UserBean;
import main.RegisterDao;
@WebServlet("/LoginServlet")
public class LoginServlet extends HttpServlet {
       
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		this.doPost(request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		response.setHeader("Content-type", "text/html;charset=utf-8");  //乱码问题
		response.setCharacterEncoding("utf-8");
		
		String userName=request.getParameter("userName");
		String userPassWord=request.getParameter("userPassWord");
		String act=request.getParameter("act");
		System.out.println("LoginServlet:"+userName+userPassWord);
		
		UserBean userBean=new UserBean();
		userBean.setUserName(userName);
		userBean.setUserPwd(userPassWord);


		
		if(act!=null&&act.equals("manager"))
		{
			if(!userBean.validate())//未输入账号名或密码
			{
				request.setAttribute("userBean", userBean);
				request.getRequestDispatcher("/managerLoginIn.jsp").forward(request, response);
				return;
			}
			int b=RegisterDao.ManagerLogin(userBean);
			if(b==0)
			{
				request.setAttribute("DBMes", "账号或密码错误");
		 		request.setAttribute("userBean", userBean);
				request.getRequestDispatcher("/managerLoginIn.jsp").forward(request,response);
			}
			
			else if(b==1){
				System.out.println("登陆成功！ this");
				userBean=RegisterDao.GetUser(userBean);
				request.getSession().setAttribute("manager", userBean);
				response.sendRedirect("ListItemServlet2");


				//response.setHeader("refresh", "0;url=ListItemServlet.jsp");
			}
			return;
			
			
		}

		if(!userBean.validate())//未输入账号名或密码
		{
			request.setAttribute("userBean", userBean);
			request.getRequestDispatcher("/loginIn.jsp").forward(request, response);
			return;
		}
		int b=RegisterDao.Login(userBean);
		System.out.println("logindao:"+b);
		
		if(b==0)
		{
			request.setAttribute("DBMes", "账号或密码错误");
	 		request.setAttribute("userBean", userBean);
			request.getRequestDispatcher("/loginIn.jsp").forward(request,response);
		}
		
		else if(b==1){
			System.out.println("登陆成功！ this");
			userBean=RegisterDao.GetUser(userBean);
			request.getSession().setAttribute("userBean", userBean);
			response.sendRedirect("ListItemServlet");


			//response.setHeader("refresh", "0;url=ListItemServlet.jsp");
		}
		else if(b==-1)
		{
			//程序异常
		}
		
		
	}

}
