package main.Dao;
import java.sql.*;
import java.util.LinkedHashMap;
import java.util.Map;

import com.mysql.jdbc.ExceptionInterceptor;
import com.mysql.jdbc.exceptions.MySQLIntegrityConstraintViolationException;
import main.Bean.UserBean;
import sun.awt.image.ImageWatched;
//连接数据库，判断账号密码是否对应

public class RegisterDao {
	
	static String URL = "jdbc:mysql://101.37.13.59:3306/websites?useUnicode=true&characterEncoding=utf8&zeroDateTimeBehavior=convertToNull";
	static String USERNAME = "root";
	static String PWD = "root123!";
	static Connection connection = null;
	static Statement stmt = null;
	
	public static int Register(UserBean userBean)  //1注册成功 0注册失败   -1程序异常
	{
		
		try {
			Class.forName("com.mysql.jdbc.Driver");// 加载具体驱动类
			connection = DriverManager.getConnection(URL, USERNAME, PWD);// 建立连接
			stmt = connection.createStatement();
			String sql="insert into User values('"+userBean.getUserName()+"','"+userBean.getUserPwd()+"','"+userBean.getUserEmail()+"','"+userBean.getUserSex()+"','"+userBean.getUserPhone()+"','"+userBean.getUserAddr()+"')";
			int result = stmt.executeUpdate(sql); 
			int count;
			count=result;
			return count;
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
			return -1;

		} catch (SQLException e) {
			e.printStackTrace();
			return -1;
		} catch (Exception e) {
			e.printStackTrace();
			return -1;
		}

		finally {

			try {
				if (stmt != null)
					stmt.close();
				if (connection != null)
					connection.close();
			} catch (SQLException e) {
				e.printStackTrace();
				return -1;
			}
		}

	}

	public static  int AddSaler(UserBean userBean)
	{
		try {
			Class.forName("com.mysql.jdbc.Driver");// 加载具体驱动类
			connection = DriverManager.getConnection(URL, USERNAME, PWD);// 建立连接
			stmt = connection.createStatement();
			String sql="insert into Saler(Salerid,Salerpwd,Itemclass,Saleremail) values('"+userBean.getUserName()+"','"+userBean.getUserPwd()+"','"+userBean.getItemClass()+"','"+userBean.getUserEmail()+"')";
			int result = stmt.executeUpdate(sql);
			int count;
			count=result;
			return count;
		}
		catch(MySQLIntegrityConstraintViolationException e)
		{
			e.printStackTrace();
			return -1;
		}
		catch (ClassNotFoundException e) {
			e.printStackTrace();
			return -1;

		} catch (SQLException e) {
			e.printStackTrace();
			return -1;
		} catch (Exception e) {
			e.printStackTrace();
			return -1;
		}

		finally {

			try {
				if (stmt != null)
					stmt.close();
				if (connection != null)
					connection.close();
			} catch (SQLException e) {
				e.printStackTrace();
				return -1;
			}
		}
	}

	public static  int ModifySaler(UserBean userBean)
	{
		try {
			Class.forName("com.mysql.jdbc.Driver");// 加载具体驱动类
			connection = DriverManager.getConnection(URL, USERNAME, PWD);// 建立连接
			stmt = connection.createStatement();
			String sql="Update Saler set Salerpwd= '"+userBean.getUserPwd()+"',Itemclass='"+userBean.getItemClass()+"',Saleremail='"+userBean.getUserEmail()+"' where Salerid='"+userBean.getUserName()+"'";
			int result = stmt.executeUpdate(sql);
			int count;
			count=result;
			return count;
		}catch (SQLIntegrityConstraintViolationException e)
		{
			e.printStackTrace();
			return -1;
		}
		catch (ClassNotFoundException e) {
			e.printStackTrace();
			return -1;

		} catch (SQLException e) {
			e.printStackTrace();
			return -1;
		} catch (Exception e) {
			e.printStackTrace();
			return -1;
		}

		finally {

			try {
				if (stmt != null)
					stmt.close();
				if (connection != null)
					connection.close();
			} catch (SQLException e) {
				e.printStackTrace();
				return -1;
			}
		}
	}

	public static  void DeleteSaler(UserBean userBean)
	{
		try {
			Class.forName("com.mysql.jdbc.Driver");// 加载具体驱动类
			connection = DriverManager.getConnection(URL, USERNAME, PWD);// 建立连接
			stmt = connection.createStatement();
			String sql="delete from  Saler where Itemclass='"+userBean.getItemClass()+"'";
			stmt.executeUpdate(sql);

		} catch (ClassNotFoundException e) {
			e.printStackTrace();


		} catch (SQLException e) {
			e.printStackTrace();

		} catch (Exception e) {
			e.printStackTrace();

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

	public static UserBean SalerLogin(UserBean userBean) //返回完整的Saler
	{
		try {
			Class.forName("com.mysql.jdbc.Driver");// 加载具体驱动类
			connection = DriverManager.getConnection(URL, USERNAME, PWD);// 建立连接
			stmt = connection.createStatement();
			String sql="select *  from Saler where Salerid='"+userBean.getUserName()+"' and Salerpwd ='"+userBean.getUserPwd()+"'";
			ResultSet result = stmt.executeQuery(sql);
			if(result!=null&&result.next())
			{
				userBean.setItemClass(result.getString("Itemclass"));
				userBean.setUserEmail(result.getString("Saleremail"));
				return userBean;
			}
			return  userBean;

		}
		catch (ClassNotFoundException e) {
			e.printStackTrace();
			return null;

		} catch (SQLException e) {
			e.printStackTrace();
			return null;
		} catch (Exception e) {
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
				return null;
			}
		}
	}

	public static int Login(UserBean userBean)  //1登录成功  0登录失败   -1程序异常
	{
		
		try {
			Class.forName("com.mysql.jdbc.Driver");// 加载具体驱动类
			connection = DriverManager.getConnection(URL, USERNAME, PWD);// 建立连接
			stmt = connection.createStatement();
//			System.out.println("LoginDao:"+userBean.getUserName()+userBean.getUserPwd());
			String sql="select count(*)  from User where userid='"+userBean.getUserName()+"' and userPwd ='"+userBean.getUserPwd()+"'";
			ResultSet result = stmt.executeQuery(sql); 
			int count = -1;
			while(result.next())
			{
				count=result.getInt("count(*)");
			}
			return count;
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
			return -1;

		} catch (SQLException e) {
			e.printStackTrace();
			return -1;
		} catch (Exception e) {
			e.printStackTrace();
			return -1;
		}

		finally {

			try {
				if (stmt != null)
					stmt.close();
				if (connection != null)
					connection.close();
			} catch (SQLException e) {
				e.printStackTrace();
				return -1;
			}
		}

	}

	public static void BrowseLog(String userid,String url,String BeginTime,String EndTime,String Length)
	{
		try{
			Class.forName("com.mysql.jdbc.Driver");// 加载具体驱动类
			connection = DriverManager.getConnection(URL, USERNAME, PWD);// 建立连接
			stmt = connection.createStatement();
			String sql="insert into UserBrowseLog values('"+userid+"','"+url+"','"+BeginTime+"','"+EndTime+"','"+Length+"')";
			stmt.execute(sql);

		}catch (ClassNotFoundException e) {
			e.printStackTrace();
		} catch (SQLException e) {
			e.printStackTrace();
		} catch (Exception e) {
			e.printStackTrace();
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

	public static int ManagerLogin(UserBean userBean)  //1登录成功  0登录失败   -1程序异常
	{
		
		try {
			Class.forName("com.mysql.jdbc.Driver");// 加载具体驱动类
			connection = DriverManager.getConnection(URL, USERNAME, PWD);// 建立连接
			stmt = connection.createStatement();
		
			String sql="select count(*)  from Manager where managerid='"+userBean.getUserName()+"' and managerpwd ='"+userBean.getUserPwd()+"'";
			ResultSet result = stmt.executeQuery(sql); 
			int count = -1;
			while(result.next())
			{
				count=result.getInt("count(*)");
			}
			return count;
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
			return -1;

		} catch (SQLException e) {
			e.printStackTrace();
			return -1;
		} catch (Exception e) {
			e.printStackTrace();
			return -1;
		}

		finally {

			try {
				if (stmt != null)
					stmt.close();
				if (connection != null)
					connection.close();
			} catch (SQLException e) {
				e.printStackTrace();
				return -1;
			}
		}

	}
	
	public static UserBean GetUser(UserBean userBean)
	{
		try {
			Class.forName("com.mysql.jdbc.Driver");// 加载具体驱动类
			connection = DriverManager.getConnection(URL, USERNAME, PWD);// 建立连接
			stmt = connection.createStatement();
//			System.out.println("LoginDao:"+userBean.getUserName()+userBean.getUserPwd());
			String sql="select *,count(*) from User where userid='"+userBean.getUserName()+"' and userPwd ='"+userBean.getUserPwd()+"'";
			ResultSet result = stmt.executeQuery(sql); 
			int count = -1;
			if(result.next())
			{
				count=result.getInt("count(*)");
			}
			if(count==1)
			{
				userBean.setUserSex(result.getString("usersex"));
				userBean.setUserAddr(result.getString("userhome"));
				userBean.setUserEmail(result.getString("useremail"));
				userBean.setUserPhone(result.getString("userphone"));
			}
			return userBean;
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
			return null;

		} catch (SQLException e) {
			e.printStackTrace();
			return null	;
		} catch (Exception e) {
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
				return null;
			}
		}

	}

	public static UserBean GetManager(UserBean userBean)
	{
		try {
			Class.forName("com.mysql.jdbc.Driver");// 加载具体驱动类
			connection = DriverManager.getConnection(URL, USERNAME, PWD);// 建立连接
			stmt = connection.createStatement();
			String sql="select *,count(*) from Manager where managerid='"+userBean.getUserName()+"' ";
			ResultSet result = stmt.executeQuery(sql); 
			int count = -1;
			if(result.next())
			{
				count=result.getInt("count(*)");
			}
			if(count==1)
			{
				userBean.setUserEmail(result.getString("manageremail"));
			}
			return userBean;
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
			return null;

		} catch (SQLException e) {
			e.printStackTrace();
			return null	;
		} catch (Exception e) {
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
				return null;
			}
		}

	}
	public static int ModifyUser(UserBean userBean)
	{
		try {
			Class.forName("com.mysql.jdbc.Driver");// 加载具体驱动类
			connection = DriverManager.getConnection(URL, USERNAME, PWD);// 建立连接
			stmt = connection.createStatement();
			String sql="update User set userphone='"+userBean.getUserPhone()+"' , userEmail='"+userBean.getUserEmail()+"' ,userhome='"+userBean.getUserAddr()+"' where userid='"+userBean.getUserName()+"'";
			int result = stmt.executeUpdate(sql); 
			int count;
			count=result;
			return count;
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
			return -1;

		} catch (SQLException e) {
			e.printStackTrace();
			return -1;
		} catch (Exception e) {
			e.printStackTrace();
			return -1;
		}

		finally {

			try {
				if (stmt != null)
					stmt.close();
				if (connection != null)
					connection.close();
			} catch (SQLException e) {
				e.printStackTrace();
				return -1;
			}
		}
		
	}

	public static void UserIp(UserBean userBean)  //用户退出时登记
	{

		try {
			Class.forName("com.mysql.jdbc.Driver");// 加载具体驱动类
			connection = DriverManager.getConnection(URL, USERNAME, PWD);// 建立连接
			stmt = connection.createStatement();
			String sql="insert into UserLoginLog values('"+userBean.getUserName()+"','"+userBean.getLoginIn()+"','"+userBean.getLoginOut()
					+"','"+userBean.getIp()+"')";
			stmt.executeUpdate(sql);
		} catch (ClassNotFoundException e) {
			e.printStackTrace();


		} catch (SQLException e) {
			e.printStackTrace();

		} catch (Exception e) {
			e.printStackTrace();

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
	public static void ManagerIp(UserBean userBean)  //用户退出时登记
	{

		try {
			Class.forName("com.mysql.jdbc.Driver");// 加载具体驱动类
			connection = DriverManager.getConnection(URL, USERNAME, PWD);// 建立连接
			stmt = connection.createStatement();
			String sql="insert into ManagerLoginLog values('"+userBean.getUserName()+"','"+userBean.getLoginIn()+"','"+userBean.getLoginOut()
					+"','"+userBean.getIp()+"')";
			stmt.executeUpdate(sql);
		} catch (ClassNotFoundException |SQLException e) {
			e.printStackTrace();
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

	public static void SalerIp(UserBean userBean)  //用户退出时登记
	{

		try {
			Class.forName("com.mysql.jdbc.Driver");// 加载具体驱动类
			connection = DriverManager.getConnection(URL, USERNAME, PWD);// 建立连接
			stmt = connection.createStatement();
			String sql="insert into SalerLoginLog values('"+userBean.getUserName()+"','"+userBean.getLoginIn()+"','"+userBean.getLoginOut()
					+"','"+userBean.getIp()+"')";
			stmt.executeUpdate(sql);
		} catch (ClassNotFoundException e) {
			e.printStackTrace();


		} catch (SQLException e) {
			e.printStackTrace();

		} catch (Exception e) {
			e.printStackTrace();

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



	public static Map UserPersona(String userName)
	{
		Map<String,String> map=new LinkedHashMap<>();

		try
		{
			Class.forName("com.mysql.jdbc.Driver");// 加载具体驱动类
			connection = DriverManager.getConnection(URL, USERNAME, PWD);// 建立连接
			stmt = connection.createStatement();
			String sql="select count(*) as a from UserLoginLog where Userid='"+userName+"' " +
					"and str_to_date(LoginIn,'%Y-%m-%d %H:%i:%s') between DATE_FORMAT(DATE_SUB(NOW(), INTERVAL 7 DAY),'%Y-%m-%d %H:%i:%s') and NOW() ";
			ResultSet res=stmt.executeQuery(sql);
			if(res.next())
			{
				map.put("近七天登录次数",String.valueOf(res.getInt("a")+1));
			}
			sql="select IP FROM UserLoginLog where Userid='"+userName+"' order by LoginOut DESC  limit 0,1";
			res=stmt.executeQuery(sql);
			if(res.next())
			{
				map.put("上一次登录IP",res.getString("IP"));
			}
			sql="SELECT a.URL ,sum(a.Length) from UserBrowseLog  as a INNER JOIN " +
					"(SELECT * from UserBrowseLog  where Userid='"+userName+"'  ORDER BY BeginTime Desc  Limit 0, 30 )as b on a.BeginTime=b.BeginTime  " +
					"GROUP BY a.URL ORDER BY sum(a.Length) Desc Limit 0 ,1";
			res=stmt.executeQuery(sql);
			if(res.next())
			{
				map.put("近期最爱浏览",res.getString("URL"));
			}
			sql="select Itemclass,sum(itemprice*buynumber) FROM Buytable LEFT JOIN Items on Items.itemid=Buytable.itemid where" +
					" userid='"+userName+"' GROUP BY Itemclass ORDER BY sum(itemprice*buynumber) DESC limit 0,1";
			res=stmt.executeQuery(sql);
			if(res.next())
			{
				map.put("最爱购买的商品",res.getString("Itemclass"));
			}
			return  map;

		}catch (ClassNotFoundException | SQLException e) {
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
	public void UserBrowse(UserBean userBean,String itemClass)  //用户退出时登记
	{

		try {
			Class.forName("com.mysql.jdbc.Driver");// 加载具体驱动类
			connection = DriverManager.getConnection(URL, USERNAME, PWD);// 建立连接
			stmt = connection.createStatement();
			String sql="insert into UserBrowseLog values('"+userBean.getUserName()+"','"+userBean.getLoginIn()+"','"+userBean.getLoginOut()
					+"','"+userBean.getIp()+"')";
			stmt.executeUpdate(sql);
		} catch (ClassNotFoundException | SQLException e) {
			e.printStackTrace();
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


	public static void Insert(String sql)  //用户退出时登记
	{

		try {
			Class.forName("com.mysql.jdbc.Driver");// 加载具体驱动类
			connection = DriverManager.getConnection(URL, USERNAME, PWD);// 建立连接
			stmt = connection.createStatement();
			stmt.executeUpdate(sql);
		} catch (ClassNotFoundException |SQLException e) {
			e.printStackTrace();
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


}
