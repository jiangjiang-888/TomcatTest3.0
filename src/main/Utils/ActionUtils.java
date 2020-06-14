package main.Utils;

import main.Dao.RegisterDao;

import java.text.SimpleDateFormat;
import java.util.Date;

public class ActionUtils {

    public static void WriteDownActions(String type,String id,String ip,String action)
    {
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        String currentTime= dateFormat.format(new Date());
        String sql="insert into Actions values('"+type+"','"+id+"','"+currentTime+"','"+ip+"','"+action+"')";
        RegisterDao.Insert(sql);

    }
}
