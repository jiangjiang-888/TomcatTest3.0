package main.Listener;

import main.Bean.UserBean;
import main.Dao.RegisterDao;
import main.Utils.ActionUtils;

import javax.servlet.*;
import javax.servlet.annotation.WebListener;
import javax.servlet.http.*;
import java.text.SimpleDateFormat;
import java.util.Date;

@WebListener()
public class UserSessionDestroyedListener implements ServletRequestListener,
        HttpSessionListener, HttpSessionAttributeListener {
    private String type;
    private String username;
    private String userip;
    private String logout;
    private String loginin;



    public UserSessionDestroyedListener() {
        System.out.println("listener初始化");
    }


    public void sessionCreated(HttpSessionEvent se) {
        System.out.println("session创建");  //计算用户画像


    }
    public void sessionDestroyed(HttpSessionEvent se) {
        System.out.println("session销毁");
        if(this.type!=null&&this.userip!=null&this.username!=null)
        {
            SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
            String currentDate = dateFormat.format(new Date());
            UserBean user=new UserBean();
            user.setUserName(username);
            user.setLoginOut(currentDate);
            user.setLoginIn(loginin);
            user.setIp(userip);
            if("saler".equals(type))
            {
                RegisterDao.SalerIp(user);
            }
            else if("manager".equals(type))
            {
                RegisterDao.ManagerIp(user);
            }
            else if("user".equals(type))
            {
                RegisterDao.UserIp(user);
            }
            ActionUtils.WriteDownActions(this.type,this.username,this.userip,type+"退出登录");
            this.type=null;
            this.userip=null;
            this.username=null;

        }

    }

    @Override
    public void requestDestroyed(ServletRequestEvent sre) {
    }

    @Override
    public void requestInitialized(ServletRequestEvent sre) {

    }


    public void attributeAdded(HttpSessionBindingEvent sbe) {
      /* This method is called when an attribute
         is added to a session.
      */
        HttpSession session=sbe.getSession();
        String type=(String)session.getAttribute("type");
        UserBean user=null;
        if("user".equals(type))
        {
            user=(UserBean)session.getAttribute("userBean");
        }
        else {
            user = (UserBean) session.getAttribute(type);
        }
        if(type!=null&&user!=null)
        {
            this.type=type;
            this.userip=user.getIp();
            this.username=user.getUserName();
            this.loginin=user.getLoginIn();
            System.out.println("session用户登录La3333"+type+username+userip);
        }

    }

    public void attributeRemoved(HttpSessionBindingEvent sbe) {

    }

    public void attributeReplaced(HttpSessionBindingEvent sbe) {

    }


}
