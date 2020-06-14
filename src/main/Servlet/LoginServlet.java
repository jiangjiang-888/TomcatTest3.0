package main.Servlet;

import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import main.Utils.ActionUtils;
import main.Utils.IpUtils;
import main.Bean.UserBean;
import main.Dao.RegisterDao;
@WebServlet("/LoginServlet")
public class LoginServlet extends HttpServlet {
       
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		this.doPost(request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		IpUtils ipUtils =new IpUtils();
		String ip=ipUtils.getIpAddr(request);
		SimpleDateFormat dateFormat = new SimpleDateFormat(" yyyy-MM-dd HH:mm:ss");
		String currentDate =   dateFormat.format(new Date());
//		System.out.println(ip);

		response.setHeader("Content-type", "text/html;charset=utf-8");  //乱码问题
		response.setCharacterEncoding("utf-8");
		
		String userName=request.getParameter("userName");
		String userPassWord=request.getParameter("userPassWord");
		String act=request.getParameter("act");
//		System.out.println("LoginServlet:"+userName+userPassWord);
		
		UserBean userBean=new UserBean();
		userBean.setUserName(userName);
		userBean.setUserPwd(userPassWord);
		userBean.setIp(ip);
		userBean.setLoginIn(currentDate);
//		System.out.println("登陆对象："+act);
		request.getSession().setAttribute("type",act);
		
		if(act!=null&&(act.equals("manager")))
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
//				System.out.println("登陆成功！ this");
				userBean=RegisterDao.GetUser(userBean);
				userBean.setUserType(act);
				request.getSession().setAttribute("type","manager");
				request.getSession().setAttribute("manager", userBean);
				ActionUtils.WriteDownActions("manager",userBean.getUserName(),userBean.getIp(),"登录");
				response.sendRedirect("ListItemServlet2");
			}
			return;
			
			
		}
		if(act!=null&&act.equals("saler"))
		{
			if(!userBean.validate())//未输入账号名或密码
			{
				request.setAttribute("userBean", userBean);
				request.getRequestDispatcher("/SalerLoginIn.jsp").forward(request, response);
				return;
			}
			userBean=RegisterDao.SalerLogin(userBean);
			if(userBean.getItemClass()==null)
			{
				request.setAttribute("DBMes", "账号或密码错误");
				request.setAttribute("userBean", userBean);
				request.getRequestDispatcher("/SalerLoginIn.jsp").forward(request, response);
				return;
			}
			else {
//				System.out.println("登陆成功！ this");
				userBean.setUserType(act);
				request.getSession().setAttribute("saler", userBean);
				request.getSession().setAttribute("type","saler");
				ActionUtils.WriteDownActions("saler",userBean.getUserName(),userBean.getIp(),"登录");
				response.sendRedirect("ListItemServlet2");
			}
			return;


		}

		if(act!=null&&act.equals("user")){
			if(!userBean.validate())//未输入账号名或密码
			{
				request.setAttribute("userBean", userBean);
				request.getRequestDispatcher("/LoginIn.jsp").forward(request, response);
				return;
			}
			int b=RegisterDao.Login(userBean);
//			System.out.println("logindao:"+b);

			if(b==0)
			{
				request.setAttribute("DBMes", "账号或密码错误");
				request.setAttribute("userBean", userBean);
				request.getRequestDispatcher("/LoginIn.jsp").forward(request,response);
			}

			else if(b==1){
//				System.out.println("登陆成功！ this");
				userBean=RegisterDao.GetUser(userBean);
				userBean.setUserType(act);
				request.getSession().setAttribute("userBean", userBean);
				ActionUtils.WriteDownActions("user",userBean.getUserName(),userBean.getIp(),"登录");
				response.sendRedirect("ListItemServlet");
			}

		}

		
		
	}

}
