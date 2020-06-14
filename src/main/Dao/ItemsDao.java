package main.Dao;

import com.sun.deploy.util.SyncAccess;
import main.Bean.ItemBean;
import main.Bean.UserBean;

import javax.jws.soap.SOAPBinding;
import javax.swing.plaf.nimbus.State;
import java.sql.*;
import java.util.*;


public class ItemsDao {

    static String URL = "jdbc:mysql://101.37.13.59:3306/websites?useUnicode=true&characterEncoding=utf8&zeroDateTimeBehavior=convertToNull";
    static String USERNAME = "root";
    static String PWD = "root123!";
    static Connection connection = null;
    static PreparedStatement pstmt = null;
    static Statement stmt=null;
    static ResultSet res = null;


    public static int insert(ItemBean itemBean) {

        try {
            String sql = "insert into Items(itemid,itemname,itemprice,itemstock,filename,Itemclass,ItemIfo) values (?,?,?,?,?,?,?)";
            Class.forName("com.mysql.jdbc.Driver");// 加载具体驱动类
            connection = DriverManager.getConnection(URL, USERNAME, PWD);// 建立连接
            pstmt = connection.prepareStatement(sql);
            pstmt.setString(1, itemBean.getItemId());
            pstmt.setString(2, itemBean.getItemName());
            pstmt.setDouble(3, itemBean.getItemPrice());
            pstmt.setInt(4, itemBean.getItemStock());
            pstmt.setString(5, itemBean.getFilename());
            pstmt.setString(6, itemBean.getItemClass());
            pstmt.setString(7, itemBean.getItemIfo());
            pstmt.executeUpdate();
            return 1;
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
            return -1;

        } catch (SQLException e) {
            e.printStackTrace();
            return -1;
        } catch (Exception e) {
            e.printStackTrace();
            return -1;
        } finally {

            try {
                if (pstmt != null)
                    pstmt.close();
                if (connection != null)
                    connection.close();
            } catch (SQLException e) {
                e.printStackTrace();
                return -1;
            }
        }

    }

    public static void delete(String itemId) {
        try {
            String sql = "delete from Items where itemid='" + itemId + "'";
            Class.forName("com.mysql.jdbc.Driver");// 加载具体驱动类
            connection = DriverManager.getConnection(URL, USERNAME, PWD);// 建立连接
            pstmt = connection.prepareStatement(sql);
            pstmt.executeUpdate();
            sql = "delete from Cart where itemid='" + itemId + "'";
            pstmt = connection.prepareStatement(sql);
            pstmt.executeUpdate();

        } catch (ClassNotFoundException e) {
            e.printStackTrace();
            return;

        } catch (SQLException e) {
            e.printStackTrace();
            return;
        } catch (Exception e) {
            e.printStackTrace();
            return;
        } finally {

            try {
                if (pstmt != null)
                    pstmt.close();
                if (connection != null)
                    connection.close();
            } catch (SQLException e) {
                e.printStackTrace();
                return;
            }
        }

    }

    public ItemBean getById(String itemId) {
        try {
            String sql = "select from Items where itemid='?'";
            Class.forName("com.mysql.jdbc.Driver");// 加载具体驱动类
            connection = DriverManager.getConnection(URL, USERNAME, PWD);// 建立连接
            pstmt = connection.prepareStatement(sql);
            pstmt.setString(1, itemId);
            res = pstmt.executeQuery();

            if (res.next()) {

                ItemBean item = new ItemBean();
                item.setItemId(res.getString("itemid"));
                item.setItemName(res.getString("itemname"));
                item.setItemPrice(res.getDouble("itemprice"));
                item.setItemStock(res.getInt("itemstock"));
                item.setFilename(res.getString("filename"));
                return item;

            }
            return null;

        } catch (ClassNotFoundException e) {
            e.printStackTrace();
            return null;

        } catch (SQLException e) {
            e.printStackTrace();
            return null;
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        } finally {

            try {
                if (pstmt != null)
                    pstmt.close();
                if (connection != null)
                    connection.close();
            } catch (SQLException e) {
                e.printStackTrace();
                return null;
            }
        }

    }

    public List<UserBean> getListSaler() {
        List<UserBean> list = new ArrayList<>();
        try {
            String sql = "select * from Saler ";
            Class.forName("com.mysql.jdbc.Driver");// 加载具体驱动类
            connection = DriverManager.getConnection(URL, USERNAME, PWD);// 建立连接
            pstmt = connection.prepareStatement(sql);
            res = pstmt.executeQuery(sql);
            while (res.next()) {
                UserBean saler = new UserBean();
                saler.setUserName(res.getString("Salerid"));
                saler.setUserPwd(res.getString("Salerpwd"));
                saler.setUserEmail(res.getString("Saleremail"));
                saler.setItemClass(res.getString("ItemClass"));
                list.add((saler));
            }
            return list;
        } catch (ClassNotFoundException | SQLException e) {
            e.printStackTrace();
            return null;
        } finally {
            try {
                if (pstmt != null)
                    pstmt.close();
                if (connection != null)
                    connection.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }

    public List<ItemBean> getListByClass(String itemName) {
        List<ItemBean> list = new ArrayList<>();
        try {
            String sql = "select * from Items where ItemClass like '%" + itemName + "%' union select * from Items where  ItemName like '%" + itemName + "%'";
            Class.forName("com.mysql.jdbc.Driver");// 加载具体驱动类
            connection = DriverManager.getConnection(URL, USERNAME, PWD);// 建立连接
            pstmt = connection.prepareStatement(sql);
            res = pstmt.executeQuery(sql);
            while (res.next()) {
                ItemBean item = new ItemBean();
                item.setItemId(res.getString("itemid"));
                item.setItemName(res.getString("itemname"));
                item.setItemPrice(res.getDouble("itemprice"));
                item.setItemStock(res.getInt("itemstock"));
                item.setFilename(res.getString("filename"));
                item.setItemClass(res.getString("Itemclass"));
                item.setItemIfo(res.getString("ItemIfo"));
                list.add(item);

            }
            return list;

        } catch (ClassNotFoundException | SQLException e) {
            e.printStackTrace();
            return null;

        } finally {

            try {
                if (pstmt != null)
                    pstmt.close();
                if (connection != null)
                    connection.close();
            } catch (SQLException e) {
                e.printStackTrace();

            }
        }

    }

    public List<ItemBean> getListByName(String itemName, String itemClass) {
        List<ItemBean> list = new ArrayList<>();
        try {
            String sql = "select * from Items where ItemClass ='" + itemClass + "' and  ItemName like '%" + itemName + "%'";
            Class.forName("com.mysql.jdbc.Driver");// 加载具体驱动类
            connection = DriverManager.getConnection(URL, USERNAME, PWD);// 建立连接
            pstmt = connection.prepareStatement(sql);
            res = pstmt.executeQuery(sql);

            while (res.next()) {
                ItemBean item = new ItemBean();
                item.setItemId(res.getString("itemid"));
                item.setItemName(res.getString("itemname"));
                item.setItemPrice(res.getDouble("itemprice"));
                item.setItemStock(res.getInt("itemstock"));
                item.setFilename(res.getString("filename"));
                item.setItemClass(res.getString("Itemclass"));
                item.setItemIfo(res.getString("ItemIfo"));
                list.add(item);

            }
            return list;

        } catch (ClassNotFoundException | SQLException e) {
            e.printStackTrace();
            return null;
        } finally {

            try {
                if (pstmt != null)
                    pstmt.close();
                if (connection != null)
                    connection.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }

    }

    public List<ItemBean> getSaleListByClassAndName(String ItemClass, String itemName) {
        List<ItemBean> list = new ArrayList<>();
        try {
            String sql = "select Items.itemid,Items.itemname,Items.itemprice,sum(Saletable.salenumber)from Saletable left join Items on Saletable.itemid=Items.itemid where Items.ItemClass = '" + ItemClass + "' and Items.itemName like '%" + itemName + "%' group by itemid ";
            Class.forName("com.mysql.jdbc.Driver");// 加载具体驱动类
            connection = DriverManager.getConnection(URL, USERNAME, PWD);// 建立连接
            pstmt = connection.prepareStatement(sql);
            res = pstmt.executeQuery(sql);

            while (res.next()) {
                ItemBean item = new ItemBean();
                item.setItemId(res.getString("itemid"));
                item.setItemName(res.getString("itemname"));
                item.setItemPrice(res.getDouble("itemprice"));
                item.setItemSale(res.getInt("sum(Saletable.salenumber)"));

                item.setTotalprice(item.getItemSale() * item.getItemPrice());
                list.add(item);

            }
            return list;

        } catch (ClassNotFoundException e) {
            e.printStackTrace();
            return null;

        } catch (SQLException e) {
            e.printStackTrace();
            return null;
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        } finally {

            try {
                if (pstmt != null)
                    pstmt.close();
                if (connection != null)
                    connection.close();
            } catch (SQLException e) {
                e.printStackTrace();
                return null;
            }
        }
    }

    public List<ItemBean> getSaleListByName(String itemName) {
        List<ItemBean> list = new ArrayList<>();
        try {
            String sql = "select Items.itemid,Items.itemname,Items.itemprice,sum(Saletable.salenumber)from Saletable left join Items on Saletable.itemid=Items.itemid where Items.ItemClass like '%" + itemName + "%'   group by itemid union select Items.itemid,Items.itemname,Items.itemprice,sum(Saletable.salenumber)from Saletable left join Items on Saletable.itemid=Items.itemid where Items.ItemName like '%" + itemName + "%'   group by itemid";
            Class.forName("com.mysql.jdbc.Driver");// 加载具体驱动类
            connection = DriverManager.getConnection(URL, USERNAME, PWD);// 建立连接
            pstmt = connection.prepareStatement(sql);

            res = pstmt.executeQuery(sql);

            while (res.next()) {
                ItemBean item = new ItemBean();
                item.setItemId(res.getString("itemid"));
                item.setItemName(res.getString("itemname"));
                item.setItemPrice(res.getDouble("itemprice"));
                item.setItemSale(res.getInt("sum(Saletable.salenumber)"));

                item.setTotalprice(item.getItemSale() * item.getItemPrice());

                list.add(item);

            }
            return list;

        } catch (ClassNotFoundException | SQLException e) {
            e.printStackTrace();
            return null;

        } finally {

            try {
                if (pstmt != null)
                    pstmt.close();
                if (connection != null)
                    connection.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }

    }

    public static String  getBrowseItemClass(String UserName) {
        //返回用户浏览最多的浏览类别
		try {
			String sql = "SELECT a.Userid ,a.URL ,sum(a.Length) from UserBrowseLog  as a INNER JOIN " +
                    "(SELECT * from UserBrowseLog  where Userid='"+UserName+"'  ORDER BY BeginTime Desc  Limit 0, 30 )as b on a.BeginTime=b.BeginTime " +
                    "GROUP BY a.URL ORDER BY sum(a.Length) Desc ";

			Class.forName("com.mysql.jdbc.Driver");// 加载具体驱动类
			connection = DriverManager.getConnection(URL, USERNAME, PWD);// 建立连接
            Statement stmt=connection.createStatement();
            stmt.execute("SET sql_mode ='STRICT_TRANS_TABLES,NO_ZERO_IN_DATE,NO_ZERO_DATE,ERROR_FOR_DIVISION_BY_ZERO,NO_AUTO_CREATE_USER,NO_ENGINE_SUBSTITUTION';");
			pstmt = connection.prepareStatement(sql);
			res = pstmt.executeQuery(sql);
//			map.put("最经常浏览的商品类别","时间");
			if (res.next()) {
				return res.getString("a.URL");
			}
			return  null;
//
//
//            sql="select Itemclass,sum(itemprice*buynumber) FROM Buytable LEFT JOIN Items on Items.itemid=Buytable.itemid  " +
//                    "where userid='"+UserName+"' GROUP BY Itemclass ORDER BY sum(itemprice*buynumber) DESC";
//            System.out.println(sql);
//            map.put("购买商品类别","总价");
//            pstmt = connection.prepareStatement(sql);
//            res = pstmt.executeQuery(sql);
//            while (res.next()) {
//                map.put(res.getString("Itemclass"),res.getString("sum(itemprice*buynumber)"));
//            }


		} catch (ClassNotFoundException | SQLException e) {
			e.printStackTrace();
			return null;
		}
		finally{
            try {
                if (pstmt != null)
                    pstmt.close();
                if (connection != null)
                    connection.close();
            } catch (SQLException e) {
                e.printStackTrace();
                return null;
            }
        }
	}


	public  static Map getItemClassAndBrowseTime(String userName)
    {
        //返回近30条浏览记录的浏览类别和浏览时间
        Map<String ,String> map=new LinkedHashMap<>();
        try
        {
            String sql =
                    "SELECT a.Userid ,a.URL ,sum(a.Length) from UserBrowseLog  as a INNER JOIN " +
                    "(SELECT * from UserBrowseLog  where Userid='"+userName+"'  ORDER BY BeginTime Desc  Limit 0, 30 )as b on a.BeginTime=b.BeginTime " +
                    "GROUP BY a.URL ORDER BY sum(a.Length) Desc ";

            Class.forName("com.mysql.jdbc.Driver");// 加载具体驱动类
            connection = DriverManager.getConnection(URL, USERNAME, PWD);// 建立连接
            stmt = connection.createStatement();
            stmt.execute("SET sql_mode ='STRICT_TRANS_TABLES,NO_ZERO_IN_DATE,NO_ZERO_DATE,ERROR_FOR_DIVISION_BY_ZERO,NO_AUTO_CREATE_USER,NO_ENGINE_SUBSTITUTION'");
            res = stmt.executeQuery(sql);
//			map.put("最经常浏览的商品类别","时间");
            while (res.next()) {
                map.put(res.getString("a.URL"),res.getString("sum(a.Length)"));
            }
            return  map;
        } catch (ClassNotFoundException | SQLException e) {
            e.printStackTrace();
            return null;
        }
        finally {
            try {
                if (stmt != null)
                    stmt.close();
                if (connection != null)
                    connection.close();
            } catch (SQLException e) {
                e.printStackTrace();

            }
        }

    }

    public  static Map getBuyTableAndSpent(String userName) {
        //返回购买商品类别和总价
        Map<String, String> map = new LinkedHashMap<>();
        try {
            String sql = "select Itemclass,sum(itemprice*buynumber) FROM Buytable LEFT JOIN Items on Items.itemid=Buytable.itemid  " +
                    "where userid='" + userName + "' GROUP BY Itemclass ORDER BY sum(itemprice*buynumber) DESC";

            Class.forName("com.mysql.jdbc.Driver");// 加载具体驱动类
            connection = DriverManager.getConnection(URL, USERNAME, PWD);// 建立连接
            pstmt = connection.prepareStatement(sql);
            res = pstmt.executeQuery(sql);
//			map.put("最经常浏览的商品类别","时间");
            while (res.next()) {
                map.put(res.getString("Itemclass"), res.getString("sum(itemprice*buynumber)"));
            }
            return map;
        } catch (ClassNotFoundException | SQLException e) {
            e.printStackTrace();
            return null;
        } finally {
            try {
                if (pstmt != null)
                    pstmt.close();
                if (connection != null)
                    connection.close();
            } catch (SQLException e) {
                e.printStackTrace();

            }
        }

    }
    public static List<UserBean> getUserActionsListByType(String type)
    {
        List<UserBean> list =new ArrayList<>();
        try {
            String sql = "SELECT * from Actions where type='"+type+"' order by Time Desc Limit 0,100";
            System.out.print(sql);
            Class.forName("com.mysql.jdbc.Driver");// 加载具体驱动类
            connection = DriverManager.getConnection(URL, USERNAME, PWD);// 建立连接
            pstmt = connection.prepareStatement(sql);
            res = pstmt.executeQuery(sql);
            while (res.next()){
                UserBean user=new UserBean();
                user.setBeginTime(res.getString("Time"));
                user.setUserType(res.getString("Type"));
                user.setAction(res.getString("Action"));
                user.setIp(res.getString("Ip"));
                user.setUserName(res.getString("Id"));
//                System.out.println(user.getUserName()+user.getUserType()+user.getIp()+user.getBeginTime()+user.getAction());
                list.add(user);
            }

            return list;
        }
            catch (ClassNotFoundException | SQLException e) {
                e.printStackTrace();
                return null;
            }
        finally {

                try {
                    if (pstmt != null)
                        pstmt.close();
                    if (connection != null)
                        connection.close();
                } catch (SQLException e) {
                    e.printStackTrace();
                    return null;
                }
            }

    }
    public static List<UserBean> getUserActionsListByName(String userName)
    {
        List<UserBean> list =new ArrayList<>();
        try {
            String sql = "SELECT * from Actions where Id='"+userName+"' order by Time desc limit 0,50";
            Class.forName("com.mysql.jdbc.Driver");// 加载具体驱动类
            connection = DriverManager.getConnection(URL, USERNAME, PWD);// 建立连接
            pstmt = connection.prepareStatement(sql);
            res = pstmt.executeQuery(sql);
            while (res.next()){
                UserBean user=new UserBean();
                user.setBeginTime(res.getString("Time"));
                user.setUserType(res.getString("Type"));
                user.setAction(res.getString("Action"));
                user.setIp(res.getString("Ip"));
                user.setUserName(res.getString("Id"));
                list.add(user);
            }
            return list;
        }
        catch (ClassNotFoundException | SQLException e) {
            e.printStackTrace();
            return list;
        }
        finally {
            try {
                if (pstmt != null)
                    pstmt.close();
                if (connection != null)
                    connection.close();
            } catch (SQLException e) {
                e.printStackTrace();
                return null;
            }
        }

    }
    public  static List<UserBean> getUserLoginLogByType(String Type)
    {
        List<UserBean> list =new ArrayList<>();
        try {

            String sql ;
            if("manager".equals(Type)) {
                sql="select Managerid as ID,LoginIn,LoginOut,IP from ManagerLoginLog ORDER BY LoginIn Desc Limit 0,50";
            } else if("saler".equals(Type)) {
                sql="select Salerid as ID,LoginIn,LoginOut,IP " +
                        "from SalerLoginLog " +
                        "ORDER BY LoginIn Desc Limit 0,50;";
            } else {
                sql="select Userid as ID,LoginIn,LoginOut,IP " +
                        "from UserLoginLog " +
                        "ORDER BY LoginIn Desc Limit 0,50";
            }
            Class.forName("com.mysql.jdbc.Driver");// 加载具体驱动类
            connection = DriverManager.getConnection(URL, USERNAME, PWD);// 建立连接
            pstmt = connection.prepareStatement(sql);
            res = pstmt.executeQuery(sql);
            while (res.next())
            {
                UserBean user =new UserBean();
                user.setUserName(res.getString("ID"));
                user.setLoginIn(res.getString("LoginIn"));
                user.setLoginOut(res.getString("LoginOut"));
                user.setIp(res.getString("IP"));
                list.add(user);
            }

            return  list;
        }
        catch (ClassNotFoundException | SQLException e) {
            e.printStackTrace();
            return null;
        }
        finally {

            try {
                if (pstmt != null)
                    pstmt.close();
                if (connection != null)
                    connection.close();
            } catch (SQLException e) {
                e.printStackTrace();
                return null;
            }
        }
    }

    public static List<UserBean> getUserBrowseList(String ItemClass)
    {
        List<UserBean> list =new ArrayList<>();
        try
        {
            String sql = "select * from UserBrowseLog where URL='"+ItemClass+"' order by EndTime Desc limit 0,100";
            Class.forName("com.mysql.jdbc.Driver");// 加载具体驱动类
            connection = DriverManager.getConnection(URL, USERNAME, PWD);// 建立连接
            pstmt = connection.prepareStatement(sql);
            res = pstmt.executeQuery(sql);
            while(res.next())
            {
                UserBean userBean=new UserBean();
                userBean.setUserName(res.getString("Userid"));
                userBean.setItemClass(res.getString("URL"));
                userBean.setBeginTime(res.getString("BeginTime"));
                userBean.setEndTime(res.getString("EndTime"));
                userBean.setBrowseTime(res.getString("Length"));
                list.add(userBean);
            }

            return list;
        } catch (ClassNotFoundException | SQLException e) {
            e.printStackTrace();
            return null;
        }
        finally {

            try {
                if (pstmt != null)
                    pstmt.close();
                if (connection != null)
                    connection.close();
            } catch (SQLException e) {
                e.printStackTrace();
                return null;
            }
        }
    }

    public List<ItemBean> getListAll() {
        List<ItemBean> list = new ArrayList<>();
        try {
            String sql = "select * from Items ";
            Class.forName("com.mysql.jdbc.Driver");// 加载具体驱动类
            connection = DriverManager.getConnection(URL, USERNAME, PWD);// 建立连接
            pstmt = connection.prepareStatement(sql);
            res = pstmt.executeQuery(sql);

            while (res.next()) {
                ItemBean item = new ItemBean();
                item.setItemId(res.getString("itemid"));
                item.setItemName(res.getString("itemname"));
                item.setItemPrice(res.getDouble("itemprice"));
                item.setItemStock(res.getInt("itemstock"));
                item.setFilename(res.getString("filename"));
                item.setItemClass(res.getString("Itemclass"));
                item.setItemIfo(res.getString("ItemIfo"));
                list.add(item);

            }
            return list;

        } catch (ClassNotFoundException | SQLException e) {
            e.printStackTrace();
            return null;

        }  finally {

            try {
                if (pstmt != null)
                    pstmt.close();
                if (connection != null)
                    connection.close();
            } catch (SQLException e) {
                e.printStackTrace();
                return null;
            }
        }

    }

    public static List<UserBean> getSalerSituation() {
        List<UserBean> list = new ArrayList<>();
        Map<String, String> map = new HashMap<String, String>();
        try {
            String sql = "select salerid ,Saler.Itemclass ,sum(itemprice*salenumber) as sum from Saletable,Items,Saler where Items.itemid=Saletable.itemid and Saler.Itemclass=Items.Itemclass GROUP BY Salerid;";
            Class.forName("com.mysql.jdbc.Driver");// 加载具体驱动类
            connection = DriverManager.getConnection(URL, USERNAME, PWD);// 建立连接
            pstmt = connection.prepareStatement(sql);
            res = pstmt.executeQuery(sql);
            while (res.next()) {
                UserBean userBean = new UserBean();
                userBean.setItemClass(res.getString("Saler.Itemclass"));
                userBean.setSalemoney(res.getString("sum"));
                userBean.setUserName(res.getString("salerid"));
                list.add(userBean);
            }

            return list;
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
            return null;

        } catch (SQLException e) {
            e.printStackTrace();
            return null;
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        } finally {

            try {
                if (pstmt != null)
                    pstmt.close();
                if (connection != null)
                    connection.close();
            } catch (SQLException e) {
                e.printStackTrace();
                return null;
            }
        }

    }


    public List<ItemBean> getSaleListAll() {
        List<ItemBean> list = new ArrayList<>();
        try {
            String sql = "select Items.itemid,Items.itemname,Items.itemprice,sum(Saletable.salenumber) from Saletable left join Items on Saletable.itemid=Items.itemid group by itemid ";
            Class.forName("com.mysql.jdbc.Driver");// 加载具体驱动类
            connection = DriverManager.getConnection(URL, USERNAME, PWD);// 建立连接
            pstmt = connection.prepareStatement(sql);
            res = pstmt.executeQuery(sql);

            while (res.next()) {
                ItemBean item = new ItemBean();
                item.setItemId(res.getString("Items.itemid"));
                item.setItemName(res.getString("Items.itemname"));
                item.setItemPrice(res.getDouble("Items.itemprice"));
                item.setItemSale(res.getInt("sum(Saletable.salenumber)"));
                double total = (double) Math.round(res.getDouble("Items.itemprice") * res.getInt("sum(Saletable.salenumber)") * 100) / 100;
                item.setTotalprice(total);
                list.add(item);

            }
            return list;

        } catch (ClassNotFoundException e) {
            e.printStackTrace();
            return null;

        } catch (SQLException e) {
            e.printStackTrace();
            return null;
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        } finally {

            try {
                if (pstmt != null)
                    pstmt.close();
                if (connection != null)
                    connection.close();
            } catch (SQLException e) {
                e.printStackTrace();
                return null;
            }
        }

    }

    public List<ItemBean> getSaleListAll2() {
        List<ItemBean> list = new ArrayList<>();
        try {
            String sql = "select Items.itemid,Items.itemname,Items.itemprice,userid,buydate, buynumber from Buytable left join Items on Buytable.itemid=Items.itemid order by buydate desc";
            Class.forName("com.mysql.jdbc.Driver");// 加载具体驱动类
            connection = DriverManager.getConnection(URL, USERNAME, PWD);// 建立连接
            pstmt = connection.prepareStatement(sql);
            res = pstmt.executeQuery(sql);

            while (res.next()) {
                ItemBean item = new ItemBean();
                item.setItemId(res.getString("Items.itemid"));
                item.setItemName(res.getString("Items.itemname"));
                item.setItemPrice(res.getDouble("Items.itemprice"));
                item.setBuyer(res.getString("userid"));
                item.setBuydate(res.getString("buydate"));
                item.setItemSale(res.getInt("buynumber"));
                list.add(item);

            }
            return list;

        } catch (ClassNotFoundException e) {
            e.printStackTrace();
            return null;

        } catch (SQLException e) {
            e.printStackTrace();
            return null;
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        } finally {

            try {
                if (pstmt != null)
                    pstmt.close();
                if (connection != null)
                    connection.close();
            } catch (SQLException e) {
                e.printStackTrace();
                return null;
            }
        }

    }

    public List<ItemBean> getSaleListByClass(String ItemClass) {
        List<ItemBean> list = new ArrayList<>();
        try {
            String sql = "select Items.itemid,Items.itemname,Items.itemprice,userid,buydate, buynumber from Buytable left join Items on Buytable.itemid=Items.itemid where Items.ItemClass='" + ItemClass + "' order by buydate desc";
            Class.forName("com.mysql.jdbc.Driver");// 加载具体驱动类
            connection = DriverManager.getConnection(URL, USERNAME, PWD);// 建立连接
            pstmt = connection.prepareStatement(sql);
            res = pstmt.executeQuery(sql);

            while (res.next()) {
                ItemBean item = new ItemBean();
                item.setItemId(res.getString("Items.itemid"));
                item.setItemName(res.getString("Items.itemname"));
                item.setItemPrice(res.getDouble("Items.itemprice"));
                item.setBuyer(res.getString("userid"));
                item.setBuydate(res.getString("buydate"));
                item.setItemSale(res.getInt("buynumber"));
                list.add(item);

            }
            return list;

        } catch (ClassNotFoundException e) {
            e.printStackTrace();
            return null;

        } catch (SQLException e) {
            e.printStackTrace();
            return null;
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        } finally {

            try {
                if (pstmt != null)
                    pstmt.close();
                if (connection != null)
                    connection.close();
            } catch (SQLException e) {
                e.printStackTrace();
                return null;
            }
        }

    }

    public void update(ItemBean item) {
        try {
            String sql = "update  Items  set itemname='" + item.getItemName() + "' ,itemprice='" + item.getItemPrice() + "',itemstock ='" + item.getItemStock() + "' where itemid='" + item.getItemId() + "'";
            Class.forName("com.mysql.jdbc.Driver");// 加载具体驱动类
            connection = DriverManager.getConnection(URL, USERNAME, PWD);// 建立连接
            pstmt = connection.prepareStatement(sql);


            pstmt.executeUpdate();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
            return;

        } catch (SQLException e) {
            e.printStackTrace();
            return;
        } catch (Exception e) {
            e.printStackTrace();
            return;
        } finally {

            try {
                if (pstmt != null)
                    pstmt.close();
                if (connection != null)
                    connection.close();
            } catch (SQLException e) {
                e.printStackTrace();
                return;
            }
        }

    }

    public static int check(String itemId) {
        try {


            String sql = "select count(*) from Items where itemid ='" + itemId + "'";
            Class.forName("com.mysql.jdbc.Driver");// 加载具体驱动类
            connection = DriverManager.getConnection(URL, USERNAME, PWD);// 建立连接
            pstmt = connection.prepareStatement(sql);

            res = pstmt.executeQuery();
            int count = 1;
            while (res.next()) {
                count = res.getInt("count(*)");
                return count;
            }
            return count;
        } catch (SQLException e) {
            e.printStackTrace();
            return -1;
        } catch (Exception e) {
            e.printStackTrace();
            return -1;
        } finally {

            try {
                if (pstmt != null)
                    pstmt.close();
                if (connection != null)
                    connection.close();
            } catch (SQLException e) {
                e.printStackTrace();
                return -1;
            }
        }

    }


}
