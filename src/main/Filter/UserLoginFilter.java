package main.Filter;

import main.Bean.UserBean;
import main.Dao.ItemsDao;

import javax.servlet.*;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import java.io.IOException;
import java.util.Map;
import java.util.Set;

@WebFilter(filterName = "UserLoginFilter",
urlPatterns = "/LoginServlet")
public class UserLoginFilter implements Filter {
    public void destroy() {
        System.out.println("Filter销毁");
    }

    public void doFilter(ServletRequest req, ServletResponse resp, FilterChain chain) throws ServletException, IOException {
//        System.out.println("Filter执行aaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaa");
        chain.doFilter(req, resp);
//        System.out.println("Filter执行bbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbb");
        //计算
        HttpServletRequest request=(HttpServletRequest)req;
        String type=(String)request.getSession().getAttribute("type");
        if(type!=null&&"user".equals(type))//计算用户最喜欢浏览的类别，保存到session
        {
            UserBean user=(UserBean)request.getSession().getAttribute("userBean");
//            System.out.println(user.getUserName());
            if(user!=null) {
                String RecommendClass = ItemsDao.getBrowseItemClass(user.getUserName());
                request.setAttribute("recommendclass", RecommendClass);
            }
        }
        if("saler".equals(type))
        {

        }
        if("manager".equals(type))
        {

        }
//        System.out.println("Filter执行bbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbb");


    }

    public void init(FilterConfig config) throws ServletException {
//        System.out.println("Filter创建");

    }

}
