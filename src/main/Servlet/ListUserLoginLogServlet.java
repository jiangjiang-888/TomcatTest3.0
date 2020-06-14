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

@WebServlet(name = "ListUserLoginLogServlet",urlPatterns = "/ListUserLoginLogServlet")
public class ListUserLoginLogServlet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        doGet(request,response);
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        UserBean manager=(UserBean)request.getSession().getAttribute("manager");
        if(manager==null)
        {
            request.getRequestDispatcher("/managerLoginIn.jsp").forward(request,response);
        }
        String type=request.getParameter("type");
        List<UserBean> list = ItemsDao.getUserLoginLogByType(type);
        request.setAttribute("list",list);
        request.getRequestDispatcher("/listLoginLog.jsp").forward(request,response);


    }
}
