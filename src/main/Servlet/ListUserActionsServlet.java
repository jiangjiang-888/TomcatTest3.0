package main.Servlet;

import main.Bean.UserBean;
import main.Dao.ItemsDao;

import javax.mail.FetchProfile;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

@WebServlet(name = "ListUserActionsServlet",urlPatterns = "/ListUserActionsServlet" )
public class ListUserActionsServlet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        UserBean manager=(UserBean)request.getSession().getAttribute("manager");
        if(manager==null)
        {
            request.getRequestDispatcher("/managerLoginIn.jsp").forward(request,response);

        }
        String type=request.getParameter("type");
        String userName=request.getParameter("userName");
        List<UserBean> list=null;
        if(type!=null&&!type.equals("null"))
        {
            list= ItemsDao.getUserActionsListByType(type);
        }
        if(userName!=null&&!userName.equals("null"))
        {
            list= ItemsDao.getUserActionsListByName(userName);
        }
        request.setAttribute("list",list);
        request.getRequestDispatcher("/managerShowActions.jsp").forward(request,response);

    }


    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doPost(request,response);
    }
}
