package main.Utils;

import main.Dao.RegisterDao;

import javax.mail.FetchProfile;
import javax.servlet.http.HttpServletRequest;
import java.io.UnsupportedEncodingException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class UserBrowseUtils {
    public static void getURLandTime(HttpServletRequest request,String ItemClass,String userName) throws ParseException, UnsupportedEncodingException {
        String oldItemName=(String)request.getSession().getAttribute("class");
        request.getSession().setAttribute("class", ItemClass);
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        String currentDate = dateFormat.format(new Date());
        if (oldItemName!= null && !oldItemName.equals("null")) {
//            oldURL = java.net.URLDecoder.decode(oldURL, "UTF-8");
            String oldTime = (String) request.getSession().getAttribute("actTime");
            if (oldTime != null && !oldTime.equals("null")) {
                try {
                    Date old = dateFormat.parse(oldTime);
                    Date new_ = dateFormat.parse(currentDate);
                    long diff = (new_.getTime() - old.getTime()) / 1000; //秒数
                    RegisterDao.BrowseLog(userName,oldItemName,oldTime,currentDate,String.valueOf(diff));
                } catch (Exception e) {
                }
            }
        }
//        更新来源URL与时间
        request.getSession().setAttribute("actTime", currentDate);

        return;
    }
}
