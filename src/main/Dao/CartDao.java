package main.Dao;
import main.Bean.CartBean;
import main.Bean.UserBean;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class CartDao {
	static String URL = "jdbc:mysql://101.37.13.59:3306/websites?useUnicode=true&characterEncoding=utf8&zeroDateTimeBehavior=convertToNull";
	static String USERNAME = "root";
	static String PWD = "root123!";
	static Connection connection = null;
	static PreparedStatement pstmt = null;
	static ResultSet res = null;
	
	public static void insertToCart(CartBean cartBean,int Stock)
	{
		try
		{
			String sql="select count(*),sum(buynumber) from Cart where userid='"+cartBean.getUserId()+"' and itemid='"+cartBean.getItemId()+"'";
			Class.forName("com.mysql.jdbc.Driver");
			connection=DriverManager.getConnection(URL,USERNAME,PWD);
			pstmt=connection.prepareStatement(sql);
			ResultSet result=pstmt.executeQuery();
			int count =0;
			int sum=0;
			while(result.next())
			{
				count=result.getInt("count(*)");
				sum=result.getInt("sum(buynumber)");
			}
			if(sum+cartBean.getBuyNumber()>Stock)cartBean.setBuyNumber(Stock-sum);
			if(count>0)
			{
				sql="update Cart set buynumber=buynumber+"+cartBean.getBuyNumber()+"  where userid='"+cartBean.getUserId()+"' and itemid='"+cartBean.getItemId()+"'";
				pstmt=connection.prepareStatement(sql);
				pstmt.executeUpdate();
			}
			else if(count==0)
			{
				sql="insert into Cart values(?,?,?)";
				pstmt=connection.prepareStatement(sql);
				pstmt.setString(1, cartBean.getItemId());
				pstmt.setString(2, cartBean.getUserId());
				pstmt.setInt(3,cartBean.getBuyNumber());
				pstmt.executeUpdate();
			}
		}catch (ClassNotFoundException | SQLException e) {
			e.printStackTrace();
			return ;
		}
		finally {
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
	
	public static void insertToBuyTable(UserBean userBean)
	{
		try
		{
	        SimpleDateFormat dateFormat = new SimpleDateFormat(" yyyy-MM-dd HH:mm:ss");
	        String currentDate =   dateFormat.format(new Date());
			String sql="insert into Buytable select * ,'"+currentDate+"' from  Cart where userid='"+userBean.getUserName()+"'";
			Class.forName("com.mysql.jdbc.Driver");
			connection=DriverManager.getConnection(URL,USERNAME,PWD);
			pstmt=connection.prepareStatement(sql);
			int a=pstmt.executeUpdate();
			if(a>0)
			{
				sql=" update Items,(select Items.itemid as itemid ,buynumber from Buytable left join Items on Buytable.itemid=Items.itemid order by Items.itemid asc)as temp set Items.itemstock=Items.itemstock-temp.buynumber where Items.itemid=temp.itemid";
				Class.forName("com.mysql.jdbc.Driver");
				connection=DriverManager.getConnection(URL,USERNAME,PWD);
				pstmt=connection.prepareStatement(sql);
				int b=pstmt.executeUpdate();
				if(b>0)
				{
					sql="insert into Saletable select itemid,buynumber ,'"+currentDate+"' from Cart  where userid='"+userBean.getUserName()+"'";
					pstmt=connection.prepareStatement(sql);
					int c=pstmt.executeUpdate();
					if(c>0)
					{
						sql="delete from Cart where userid='"+userBean.getUserName()+"'";
						pstmt=connection.prepareStatement(sql);
						pstmt.executeUpdate();
					}
				}
			}

		}catch (ClassNotFoundException |SQLException e) {
			e.printStackTrace();
		}
		finally {
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


	public  static List<CartBean> getListAll(UserBean user)
	{
		if(user==null)return null;
		List<CartBean>list =new ArrayList<>();
		try
		{
			String sql;
			sql = "select * from Cart left join Items on Cart.itemid=Items.itemid where userid='"+user.getUserName()+"'";

			Class.forName("com.mysql.jdbc.Driver");// 加载具体驱动类
			connection = DriverManager.getConnection(URL, USERNAME, PWD);// 建立连接
			pstmt = connection.prepareStatement(sql);
			res=pstmt.executeQuery(sql);
			
			while (res.next())
			{
				CartBean cart =new CartBean();
				cart.setItemId(res.getString("itemid"));
				cart.setUserId(res.getString("userid"));
				cart.setBuyNumber(res.getInt("buynumber"));
				cart.setItemName(res.getString("itemname"));
				cart.setItemPrice(res.getDouble("itemPrice"));
				cart.setFilename(res.getString("filename"));
				cart.setTotalPrice((double)res.getDouble("itemPrice")*res.getInt("buynumber"));
				list.add(cart);
			}
			return list;
			
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
			return null	;

		} catch (SQLException e) {
			e.printStackTrace();
			return null	;
		} catch (Exception e) {
			e.printStackTrace();
			return null	;
		}

		finally {

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
	public  static List<CartBean> getListBuyOrder(UserBean user)
	{
		List<CartBean>list =new ArrayList<>();
		try
		{
			String sql="select * from Buytable  left join Items on Buytable.itemid=Items.itemid where userid='"+user.getUserName()+"' order by buydate desc";
			Class.forName("com.mysql.jdbc.Driver");// 加载具体驱动类
			connection = DriverManager.getConnection(URL, USERNAME, PWD);// 建立连接
			pstmt = connection.prepareStatement(sql);
			res=pstmt.executeQuery(sql);
			while (res.next())
			{
				CartBean cart =new CartBean();
				cart.setItemId(res.getString("itemid"));
				cart.setUserId(res.getString("userid"));
				cart.setBuyNumber(res.getInt("buynumber"));
				cart.setItemName(res.getString("itemname"));
				cart.setItemPrice(res.getDouble("itemPrice"));
				cart.setDate(res.getString("buydate"));
				cart.setTotalPrice((double)res.getDouble("itemPrice")*res.getInt("buynumber"));
				list.add(cart);
			}
			return list;
		} catch (ClassNotFoundException | SQLException e) {
			e.printStackTrace();
			return null	;
		}

		finally {
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
	public  static double getListTotalPrice(UserBean user)  {
		double totalPrice=0;
		try
		{
			String sql="select * from Cart  left join Items on Cart.itemid=Items.itemid where userid='"+user.getUserName()+"'";
			Class.forName("com.mysql.jdbc.Driver");// 加载具体驱动类
			connection = DriverManager.getConnection(URL, USERNAME, PWD);// 建立连接
			pstmt = connection.prepareStatement(sql);
			res=pstmt.executeQuery(sql);
			
			while (res.next())
			{

				totalPrice+=(double)res.getDouble("itemPrice")*res.getInt("buynumber");

			}
			totalPrice=(double) Math.round(totalPrice * 100) / 100;
			return totalPrice;
			
		} catch (SQLException | ClassNotFoundException e) {
			e.printStackTrace();
			return 0;
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
	
	public static void delete(String itemId,String userid) {
		try {
			String sql = "delete from Cart where itemid='"+itemId+"' and userid='"+userid+"'";
			Class.forName("com.mysql.jdbc.Driver");// 加载具体驱动类
			connection = DriverManager.getConnection(URL, USERNAME, PWD);// 建立连接
			pstmt = connection.prepareStatement(sql);
			pstmt.executeUpdate();

		} catch (ClassNotFoundException | SQLException e) {
			e.printStackTrace();
		}
		finally {
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
	
	public static void reduce(String itemId,String userid)
	{
		try {
			String sql = "update Cart set buynumber =buynumber-1 where itemid='"+itemId+"' and userid='"+userid+"' and buynumber>1 ";
			Class.forName("com.mysql.jdbc.Driver");// 加载具体驱动类
			connection = DriverManager.getConnection(URL, USERNAME, PWD);// 建立连接
			pstmt = connection.prepareStatement(sql);
			pstmt.executeUpdate();
		} catch (ClassNotFoundException | SQLException e) {
			e.printStackTrace();
		}
		finally {
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
	public static void add(String itemId,String userid)
	{
		try {
			String sql = "update Cart set buynumber =buynumber+1 where itemid='"+itemId+"' and userid='"+userid+"'";
			Class.forName("com.mysql.jdbc.Driver");// 加载具体驱动类
			connection = DriverManager.getConnection(URL, USERNAME, PWD);// 建立连接
			pstmt = connection.prepareStatement(sql);
			pstmt.executeUpdate();

		} catch (ClassNotFoundException | SQLException e) {
			e.printStackTrace();
		}
		finally {
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


}
