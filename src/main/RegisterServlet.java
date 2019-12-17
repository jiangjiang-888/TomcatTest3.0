package main;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.ServletResponse;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import main.RegisterDao;
import main.UserBean;

@WebServlet("/RegisterServlet")
public class RegisterServlet extends HttpServlet {
       

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		this.doPost(request, response);
	}

	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		response.setHeader("Content-type", "text/html;charset=utf-8");
		response.setCharacterEncoding("utf-8");
		String act=request.getParameter("act");
		if(act!=null&&act.equals("modify"))
		{
			String addr=request.getParameter("userAddr");
			addr=new String(addr.getBytes("ISO-8859-1"),"UTF-8");
			String email=request.getParameter("userEmail");
			String phone=request.getParameter("userPhone");
			
			RegisterFormBean form=new RegisterFormBean();
			form.setUserAddr(addr);
			form.setUserEmail(email);
			form.setUserPhone(phone);
			if(!form.validateForModify())
			{
				request.setAttribute("formBean", form);
				request.getRequestDispatcher("/userInformation.jsp").forward(request, response);
				return;
			}
			UserBean user=(UserBean) request.getSession().getAttribute("userBean");
			user.setUserAddr(addr);
			user.setUserEmail(email);
			user.setUserPhone(phone);
			
			
			if(RegisterDao.ModifyUser(user)==1)
			{
				request.getRequestDispatcher("/userInformation.jsp").forward(request, response);
				return;
			}
			
			
		}
		
		String userName=request.getParameter("userName");
		System.out.print(userName);
		String userPassWord=request.getParameter("userPassWord");
		String userPassWord2=request.getParameter("userPassWord2");
		String userEmail=request.getParameter("userEmail");
		String userSex=request.getParameter("userSex");
		if(userSex!=null)
		userSex=new String(userSex.getBytes("ISO-8859-1"),"UTF-8");
		//userSex=new String(userSex.getBytes("ISO-8859-1"),"UTF-8" );
		System.out.print("userSex"+userSex);
		String userPhone=request.getParameter("userPhone");
		String userAddr=request.getParameter("userAddr");
		if(userAddr!=null||!userAddr.equals("null"))
		userAddr=new String(userAddr.getBytes("ISO-8859-1"),"UTF-8" );
		RegisterFormBean formBean=new RegisterFormBean();
		formBean.setUserName(userName);
		formBean.setUserPwd(userPassWord);
		formBean.setUserPwd2(userPassWord2);
		formBean.setUserEmail(userEmail);
		formBean.setUserSex(userSex);
		formBean.setUserPhone(userPhone);
		formBean.setUserAddr(userAddr);
		if(!formBean.validate())//信息格式不符合要求
		{
		
			request.setAttribute("formBean", formBean);
			request.getRequestDispatcher("/register.jsp").forward(request, response);
			return;
		}
		
		UserBean userBean=new UserBean();
		userBean.setUserName(userName);
		userBean.setUserPwd(userPassWord);
		userBean.setUserPhone(userPhone);
		userBean.setUserAddr(userAddr);
		userBean.setUserSex(userSex);
		userBean.setUserEmail(userEmail);
		int b=RegisterDao.Register(userBean);
		if(b!=1)
		{
			request.setAttribute("DBMes", "你注册的用户已存在");
			request.setAttribute("fromBean", formBean);
			request.getRequestDispatcher("/register.jsp").forward(request,response);
			return;
		}
		if(b==1)
		{
			System.out.print("注册成功");
			request.getSession().setAttribute("userBean", userBean);
			response.setHeader("refresh", "3;url=loginIn.jsp");
		}
		System.out.print("b"+b);
		

		
	}

}
