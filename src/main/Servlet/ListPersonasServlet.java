package main.Servlet;
import main.Bean.UserBean;
import main.Dao.ItemsDao;
import main.Dao.RegisterDao;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Map;
@WebServlet("/ListPersonasServlet")
public class ListPersonasServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        UserBean user =(UserBean)request.getSession().getAttribute("userBean");
        if(user==null)request.getRequestDispatcher("/LoginIn.jsp").forward(request,response);

        String userName=user.getUserName();
        Map info= RegisterDao.UserPersona(userName);
        Map BrowseMap= ItemsDao.getItemClassAndBrowseTime(userName);
        Map BuyMap=ItemsDao.getBuyTableAndSpent(userName);
        request.setAttribute("info",info);
        request.setAttribute("browseMap",BrowseMap);
        request.setAttribute("buyMap",BuyMap);
        request.getRequestDispatcher("/userPersonas.jsp").forward(request,response);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp)
            throws ServletException, IOException {

        doGet(req, resp);
    }

}