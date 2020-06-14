package main.Servlet;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import main.Dao.ItemsDao;
import main.Bean.UserBean;
import main.Utils.ActionUtils;


@WebServlet("/ItemDeleteServlet")
public class ItemDeleteServlet extends HttpServlet {
    private ItemsDao itemdao;
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		this.doPost(request, response);
	}

	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		String type=(String)request.getSession().getAttribute("type");
		if(type!=null&&type.equals("saler"))
		{
			UserBean saler=(UserBean)request.getSession().getAttribute("saler" );
			if(saler==null)
			{
				request.getRequestDispatcher("/SalerLoginIn.jsp").forward(request,response);
			}
			String itemId=request.getParameter("itemid");
//			System.out.println(itemId);
			itemdao.delete(itemId);
			ActionUtils.WriteDownActions("saler",saler.getUserName(),saler.getIp(),"删除商品");
			response.sendRedirect("ListItemServlet2?type=saler");
		}
		else
		{
			UserBean manager = (UserBean) request.getSession().getAttribute("manager");
			if(manager==null)
			{
				request.getRequestDispatcher("/managerLoginIn.jsp").forward(request,response);

			}
			String itemId=request.getParameter("itemid");
//			System.out.println(itemId);
			itemdao.delete(itemId);
			ActionUtils.WriteDownActions("manager",manager.getUserName(),manager.getIp(),"删除商品");
			if(request.getSession().getAttribute("itemName2")!=null)
				response.sendRedirect("ListItemServlet2?type=manager&itemName="+request.getSession().getAttribute("itemName2"));
			else
				response.sendRedirect("ListItemServlet2?type=manager");

		}


		
	}

}
