package main;
import java.io.*;
import java.sql.*;
import main.UserBean;
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
			String sql="insert into user values('"+userBean.getUserName()+"','"+userBean.getUserPwd()+"','"+userBean.getUserEmail()+"','"+userBean.getUserSex()+"','"+userBean.getUserPhone()+"','"+userBean.getUserAddr()+"')";
			int result = stmt.executeUpdate(sql); 
			int count;
			System.out.print("result"+result);
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
	
	public static int Login(UserBean userBean)  //1登录成功  0登录失败   -1程序异常
	{
		
		try {
			Class.forName("com.mysql.jdbc.Driver");// 加载具体驱动类
			connection = DriverManager.getConnection(URL, USERNAME, PWD);// 建立连接
			stmt = connection.createStatement();
			System.out.println("LoginDao:"+userBean.getUserName()+userBean.getUserPwd());
			String sql="select count(*)  from user where userid='"+userBean.getUserName()+"' and userPwd ='"+userBean.getUserPwd()+"'";
			System.out.println(sql);
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
	
	public static int ManagerLogin(UserBean userBean)  //1登录成功  0登录失败   -1程序异常
	{
		
		try {
			Class.forName("com.mysql.jdbc.Driver");// 加载具体驱动类
			connection = DriverManager.getConnection(URL, USERNAME, PWD);// 建立连接
			stmt = connection.createStatement();
		
			String sql="select count(*)  from manager where managerid='"+userBean.getUserName()+"' and managerpwd ='"+userBean.getUserPwd()+"'";
			System.out.println(sql);
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
			System.out.println("LoginDao:"+userBean.getUserName()+userBean.getUserPwd());
			String sql="select *,count(*) from user where userid='"+userBean.getUserName()+"' and userPwd ='"+userBean.getUserPwd()+"'";
			System.out.println(sql);
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
				System.out.println(userBean.getUserName()+userBean.getUserAddr()+userBean.getUserPhone()+userBean.getUserSex());
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
			String sql="select *,count(*) from manager where managerid='"+userBean.getUserName()+"' ";
			System.out.println(sql);
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
			String sql="update user set userphone='"+userBean.getUserPhone()+"' , userEmail='"+userBean.getUserEmail()+"' ,userhome='"+userBean.getUserAddr()+"' where userid='"+userBean.getUserName()+"'";
			int result = stmt.executeUpdate(sql); 
			int count;
			System.out.print("result"+result);
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
}
