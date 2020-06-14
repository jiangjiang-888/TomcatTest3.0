package main.Servlet;

import main.Bean.UserBean;
import main.Dao.ItemsDao;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

@WebServlet("/ListUserBrowseTime")
public class ListUserBrowseTime extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp)
            throws ServletException, IOException {
        UserBean saler=(UserBean) req.getSession().getAttribute("saler");
        if(saler==null)
        {
            req.getRequestDispatcher("/SalerLoginIn.jsp").forward(req,resp);
        }
        List<UserBean> list= ItemsDao.getUserBrowseList(saler.getItemClass());
        req.setAttribute("list",list);
        req.getRequestDispatcher("/salerUserBrowseState.jsp").forward(req,resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp)
            throws ServletException, IOException {

        doGet(req, resp);
    }

}