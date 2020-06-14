package main.Servlet;

import main.Bean.CartBean;
import main.Bean.ItemBean;
import main.Bean.UserBean;
import main.Dao.CartDao;
import main.Dao.ItemsDao;
import main.Dao.RegisterDao;
import main.Utils.ActionUtils;
import main.Utils.UserBrowseUtils;

import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;


@WebServlet(urlPatterns = "/ListItemServlet"  ,loadOnStartup = 1 )
public class ListItemServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private ItemsDao dao=new ItemsDao();

	public void service (HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		req.setCharacterEncoding("utf-8");
		String itemName =req.getParameter("itemName");  //查询类别
		String RecommendClass=req.getParameter("recommendclass");
		String type=(String)req.getSession().getAttribute("type");
		String act=null;
		act =req.getParameter("act");
//		System.out.println("action:"+act);
		UserBean user=null;
		if(type!=null&&type.equals("manager"))
		{
			user= (UserBean) req.getSession().getAttribute("manager");
		}
		else if(type!=null&&type.equals("saler"))
		{
			user= (UserBean) req.getSession().getAttribute("saler");
		}
		else{
			user= (UserBean) req.getSession().getAttribute("userBean");
			if(user!=null&&!"recommendclass".equals(itemName))
			{
				try {

					UserBrowseUtils.getURLandTime(req,itemName,user.getUserName());
				} catch (ParseException e) {
					e.printStackTrace();
				}
			}
		}
		req.getSession().setAttribute("itemName", itemName);
		List<ItemBean> list=null;


		if("recommendclass".equals(itemName)&&user!=null)
		{
			itemName=ItemsDao.getBrowseItemClass(user.getUserName());
//			System.out.println("推荐商品："+itemName);
			list=dao.getListByClass(itemName);
		}
		else if(RecommendClass!=null&&!RecommendClass.equals("null"))
		{
//			System.out.println("查找推荐商品");
			list=dao.getListByClass(RecommendClass);
		}
		else if(itemName==null||itemName.equals("null"))
		{
			list =dao.getListAll();
		}
		else {
//			System.out.println("部分查询："+itemName);
//			itemName=new String(itemName.getBytes("ISO-8859-1"),"utf-8");
			list=dao.getListByClass(itemName);
		}

		if(act!=null)
		{
			if(act.equals("delete")&&user!=null)
			{
				String itemId=req.getParameter("itemId");
				CartDao.delete(itemId, user.getUserName());
				ActionUtils.WriteDownActions("user",user.getUserName(),user.getIp(),"修改购物车");
			}
			if(act.equals("loginout")&&user!=null)
			{

				SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
				String currentDate =   dateFormat.format(new Date());
//				System.out.println(currentDate);
				user.setLoginOut(currentDate);
//				System.out.println("type:"+type);
				if(type==null||type.equals("user")||type.equals("null")){
					RegisterDao.UserIp(user);
					ActionUtils.WriteDownActions("user",user.getUserName(),user.getIp(),"用户退出登录");
					req.getSession().removeAttribute("userBean");
					req.getSession().removeAttribute("type");
					user=null;
				}
				else if(type.equals("manager")){
					RegisterDao.ManagerIp(user);
					ActionUtils.WriteDownActions("manager",user.getUserName(),user.getIp(),"管理员退出登录");
					req.getSession().removeAttribute("manager");
					req.getSession().removeAttribute("type");
					user=null;
				}
				else if(type.equals("saler")){
					RegisterDao.SalerIp(user);
					ActionUtils.WriteDownActions("saler",user.getUserName(),user.getIp(),"销售员退出登录");
					req.getSession().removeAttribute("saler");
					req.getSession().removeAttribute("type");
					user=null;
				}

			}
		}

		if(user!=null)
		{
			List<CartBean>listCart=CartDao.getListAll(user);
			req.setAttribute("cart", listCart);
			ActionUtils.WriteDownActions("user",user.getUserName(),user.getIp(),"浏览主页");
		}
		req.setAttribute("items",list);
		req.getRequestDispatcher("/LoginSuccess.jsp").forward(req, resp);
	}
	
}
