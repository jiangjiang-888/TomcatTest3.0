package main.Servlet;

import main.Bean.ItemBean;
import main.Dao.ItemsDao;
import main.Bean.UserBean;
import main.Utils.ActionUtils;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet("/ListSaleItemServet")
public class ListSaleItemServet extends HttpServlet {
    private static final long serialVersionUID = 1L;
    private ItemsDao dao = new ItemsDao();

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doPost(request, response);
    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        String type = (String) request.getSession().getAttribute("type");
        String action = request.getParameter("action");
        if ("manager".equals(type)) {
            UserBean manager = (UserBean) request.getSession().getAttribute("manager");
            if (manager == null) {
                request.getRequestDispatcher("/managerLoginIn.jsp").forward(request, response);

            }
            if (action != null && action.equals("sellorder")) {
//                System.out.println("list item servlet");
                String itemName = request.getParameter("itemName");
                request.getSession().setAttribute("itemName3", itemName);
                List<ItemBean> list = null;
                double totalprice = 0;
                if (itemName != null && !itemName.equals("null")) {
//			itemName=new String(itemName.getBytes("ISO-8859-1"),"utf-8");
//                    System.out.println("部分查询：" + itemName);
                    list = dao.getSaleListByName(itemName);
                    for (ItemBean x : list) {
                        totalprice += x.getTotalprice();
                    }
                } else {
                    list = dao.getSaleListAll();
                    for (ItemBean x : list) {
                        totalprice += x.getTotalprice();
                    }

                }
//                System.out.println("totalprice:" + totalprice);
                request.setAttribute("items", list);
                request.setAttribute("totalprice", totalprice);
                ActionUtils.WriteDownActions("manager",manager.getUserName(),manager.getIp(),"查看销售表");
                request.getRequestDispatcher("/sellorder.jsp").forward(request, response);
            }
            if (action != null && action.equals("salestate")) {
                List<ItemBean> list = null;
                list = dao.getSaleListAll2();
                request.setAttribute("items", list);
                ActionUtils.WriteDownActions("manager",manager.getUserName(),manager.getIp(),"查看销售状态");
                request.getRequestDispatcher("/salestate.jsp").forward(request, response);
            }
            if (action != null && action.equals("salersituation")) {
                List<UserBean> list = null;
                list = ItemsDao.getSalerSituation();
                request.setAttribute("situation", list);
                ActionUtils.WriteDownActions("manager",manager.getUserName(),manager.getIp(),"查看销售员业绩");
                request.getRequestDispatcher("/SalerSituation.jsp").forward(request, response);
            } else {
                request.getRequestDispatcher("ListItemServlet2").forward(request, response);
            }

        } else if ("saler".equals(type)) {
			UserBean saler = (UserBean) request.getSession().getAttribute("saler");
			if (saler == null) {
				request.getRequestDispatcher("/SalerLoginIn.jsp").forward(request, response);
			}
			if ("sellorder".equals(action))
			{
				String itemName = request.getParameter("itemName");
				List<ItemBean> list = null;
				double totalprice = 0;
				if (itemName != null && !itemName.equals("null")) {
//			itemName=new String(itemName.getBytes("ISO-8859-1"),"utf-8");
//					System.out.println("部分查询a：" + itemName);
					list = dao.getSaleListByClassAndName(saler.getItemClass(), itemName);
					for (ItemBean x : list) {
						totalprice += x.getTotalprice();
					}
				}
				else {
					list = dao.getSaleListByName(saler.getItemClass());
					for (ItemBean x : list) {
						totalprice += x.getTotalprice();
					}

				}
//				System.out.println("totalprice:" + totalprice);
				request.setAttribute("items", list);
				request.setAttribute("totalprice", totalprice);
                ActionUtils.WriteDownActions("saler",saler.getUserName(),saler.getIp(),"查看销售表");
				request.getRequestDispatcher("/salersellorder.jsp").forward(request, response);
			} else if ("salestate".equals(action)) {
                List<ItemBean> list = null;
                list = dao.getSaleListByClass(saler.getItemClass());
                request.setAttribute("items", list);
                ActionUtils.WriteDownActions("saler",saler.getUserName(),saler.getIp(),"查看销售状态");
                request.getRequestDispatcher("/salersalestate.jsp").forward(request, response);

			}
		}

    }

}
