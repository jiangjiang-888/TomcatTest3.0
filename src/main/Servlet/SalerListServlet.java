package main.Servlet;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

import main.Dao.ItemsDao;
import main.Bean.UserBean;
import main.Utils.ActionUtils;

@WebServlet("/SalerListServlet")
public class SalerListServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp)
            throws ServletException, IOException {
        ItemsDao itemsDao = new ItemsDao();
        List<UserBean> list = itemsDao.getListSaler();
        UserBean manager = (UserBean) req.getSession().getAttribute("manager");
        if (manager == null) {
            req.getRequestDispatcher("/managerLoginIn.jsp").forward(req, resp);

        }
//        System.out.println("list item servlet");
        req.setAttribute("salers",list);
        ActionUtils.WriteDownActions("manager",manager.getUserName(),manager.getIp(),"查看销售员");
        req.getRequestDispatcher("/ListSaler.jsp").forward(req, resp);


    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp)
            throws ServletException, IOException {

        doGet(req, resp);
    }

}