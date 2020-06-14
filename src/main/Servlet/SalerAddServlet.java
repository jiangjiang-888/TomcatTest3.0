package main.Servlet;


import main.Dao.RegisterDao;
import main.Bean.UserBean;
import main.Utils.ActionUtils;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
@WebServlet("/SalerAddServlet")
public class SalerAddServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp)
            throws ServletException, IOException {
        UserBean manager=(UserBean)req.getSession().getAttribute("manager");
        if(manager==null)req.getRequestDispatcher("/managerLoginIn.jsp").forward(req,resp);
        RegisterDao registerDao=new RegisterDao();
        resp.setHeader("Content-type", "text/html;charset=utf-8");  //乱码问题
        resp.setCharacterEncoding("utf-8");
        String action=req.getParameter("action");
//        System.out.println(action);

        if(action!=null&&action.equals("addSaler"))
        {

            UserBean userBean=new UserBean();
            userBean.setUserName(new String(req.getParameter("name").getBytes("ISO-8859-1"),"UTF-8"));
            userBean.setUserPwd(req.getParameter("pwd"));
            String Itemclass=req.getParameter("Itemclass");
            Itemclass=new String(Itemclass.getBytes("ISO-8859-1"),"UTF-8");
            userBean.setItemClass(Itemclass);
            userBean.setUserEmail(req.getParameter("email"));
            int flag=registerDao.AddSaler(userBean);
            if(flag==1)
            {
                //转到新页面
                ActionUtils.WriteDownActions("manager",manager.getUserName(),manager.getIp(),"添加销售员");
                req.getRequestDispatcher("/SalerListServlet").forward(req, resp);
            }
            else if(flag==-1)  //该销售类型已有该销售员
            {
                req.setAttribute("DBMS","该销售类型已有销售员");
                req.setAttribute("saler",userBean.getUserName());
                req.getRequestDispatcher("/addSaler.jsp?salerid="+userBean.getUserName()+"&ItemClass="+userBean.getClass()+"&Saleremail="+userBean.getUserEmail()+"&action=addSaler").forward(req,resp);
            }
//            System.out.println(flag);

//            String Salerid=req.getParameter("name");
//            String Salerpwd=req.getParameter("pwd");
//            String Saleremail=req.getParameter("email");
//            System.out.println(action+Salerid+Salerpwd+ Itemclass+Saleremail);
        }
        if(action!=null&&action.equals("modifySaler"))
        {

            UserBean userBean=new UserBean();
            userBean.setUserName(new String(req.getParameter("name").getBytes("ISO-8859-1"),"UTF-8"));
            userBean.setUserPwd(req.getParameter("pwd"));
            String Itemclass=req.getParameter("Itemclass");
            Itemclass=new String(Itemclass.getBytes("ISO-8859-1"),"UTF-8");
            userBean.setItemClass(Itemclass);
            userBean.setUserEmail(req.getParameter("email"));
            int flag=registerDao.ModifySaler(userBean);
            if(flag==1)
            {
                ActionUtils.WriteDownActions("manager",manager.getUserName(),manager.getIp(),"修改销售员");
                //转到新页面
                req.getRequestDispatcher("/SalerListServlet").forward(req, resp);
            }
            else if(flag==-1)
            {
                req.setAttribute("DBMS","该销售类型已有销售员");
                req.setAttribute("saler",userBean.getUserName());
                req.getRequestDispatcher("/addSaler.jsp?salerid="+userBean.getUserName()+"&ItemClass="+userBean.getClass()+"&Saleremail="+userBean.getUserEmail()+"&action=modifySaler").forward(req,resp);
            }
        }
        if(action!=null&&action.equals("deleteSaler"))
        {

            UserBean userBean=new UserBean();
//            userBean.setItemClass(new String(req.getParameter("itemclass").getBytes("ISO-8859-1"),"UTF-8"));
            userBean.setItemClass(req.getParameter("itemclass"));
//            System.out.println(userBean.getItemClass());
            RegisterDao.DeleteSaler(userBean);
            ActionUtils.WriteDownActions("manager",manager.getUserName(),manager.getIp(),"删除销售员");
            req.getRequestDispatcher("/SalerListServlet").forward(req, resp);

        }
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp)
            throws ServletException, IOException {

        doGet(req, resp);
    }

}