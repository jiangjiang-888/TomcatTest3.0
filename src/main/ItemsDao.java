package main;

import main.ItemBean;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;


public class ItemsDao {

	static String URL = "jdbc:mysql://101.37.13.59:3306/websites?useUnicode=true&characterEncoding=utf8&zeroDateTimeBehavior=convertToNull";
	static String USERNAME = "root";
	static String PWD = "root123!";
	static Connection connection = null;
	static PreparedStatement pstmt = null;
	static ResultSet res = null;

	public static int insert(ItemBean itemBean) {

		try {
			String sql = "insert into items values(?,?,?,?,?)";
			Class.forName("com.mysql.jdbc.Driver");// 加载具体驱动类
			connection = DriverManager.getConnection(URL, USERNAME, PWD);// 建立连接
			pstmt = connection.prepareStatement(sql);
			pstmt.setString(1, itemBean.getItemId());
			pstmt.setString(2, itemBean.getItemName());
			pstmt.setDouble(3, itemBean.getItemPrice());
			pstmt.setInt(4, itemBean.getItemStock());
			pstmt.setString(5, itemBean.getFilename());
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
		}

		finally {

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
			String sql = "delete from items where itemid='"+itemId+"'";
			Class.forName("com.mysql.jdbc.Driver");// 加载具体驱动类
			connection = DriverManager.getConnection(URL, USERNAME, PWD);// 建立连接
			pstmt = connection.prepareStatement(sql);
			pstmt.executeUpdate();
			sql = "delete from cart where itemid='"+itemId+"'";
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

	public ItemBean getById(String itemId) {
		try {
			String sql = "select from items where itemid='?'";
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
				return null	;
			}
		}

	}
	
	public List<ItemBean> getListByName(String itemName)
	{
		List<ItemBean>list=new ArrayList<>();
		try
		{
			String sql="select * from items where itemname like '%"+itemName+"%'";
			Class.forName("com.mysql.jdbc.Driver");// 加载具体驱动类
			connection = DriverManager.getConnection(URL, USERNAME, PWD);// 建立连接
			pstmt = connection.prepareStatement(sql);
		
			res=pstmt.executeQuery(sql);
			
			while (res.next())
			{
				ItemBean item =new ItemBean();
				item.setItemId(res.getString("itemid"));
				item.setItemName(res.getString("itemname"));
				item.setItemPrice(res.getDouble("itemprice"));
				item.setItemStock(res.getInt("itemstock"));
				item.setFilename(res.getString("filename"));
				System.out.println(res.getString("itemid")+res.getString("itemname") +res.getDouble("itemprice")+res.getInt("itemstock"));
				
				list.add(item);
				
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
				return null	;
			}
		}

	}
	
	public List<ItemBean> getSaleListByName(String itemName)
	{
		List<ItemBean>list=new ArrayList<>();
		try
		{
			String sql="select items.itemid,items.itemname,items.itemprice,sum(saletable.salenumber)from saletable left join items on saletable.itemid=items.itemid where items.itemname like '%"+itemName+"%' group by itemid;";
			Class.forName("com.mysql.jdbc.Driver");// 加载具体驱动类
			connection = DriverManager.getConnection(URL, USERNAME, PWD);// 建立连接
			pstmt = connection.prepareStatement(sql);
		
			res=pstmt.executeQuery(sql);
			
			while (res.next())
			{
				ItemBean item =new ItemBean();
				item.setItemId(res.getString("items.itemid"));
				item.setItemName(res.getString("items.itemname"));
				item.setItemPrice(res.getDouble("items.itemprice"));
				item.setItemSale(res.getInt("sum(saletable.salenumber)"));
				
				//System.out.println(res.getString("itemid")+res.getString("itemname") +res.getDouble("itemprice")+res.getInt("itemstock"));
				
				list.add(item);
				
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
				return null	;
			}
		}

	}

	
	
	public List<ItemBean> getListAll()
	{
		List<ItemBean>list=new ArrayList<>();
		try
		{
			String sql="select * from items ";
			Class.forName("com.mysql.jdbc.Driver");// 加载具体驱动类
			connection = DriverManager.getConnection(URL, USERNAME, PWD);// 建立连接
			pstmt = connection.prepareStatement(sql);
			res=pstmt.executeQuery(sql);
			
			while (res.next())
			{
				ItemBean item =new ItemBean();
				item.setItemId(res.getString("itemid"));
				item.setItemName(res.getString("itemname"));
				item.setItemPrice(res.getDouble("itemprice"));
				item.setItemStock(res.getInt("itemstock"));
				item.setFilename(res.getString("filename"));
				list.add(item);
				
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
				return null	;
			}
		}

	}
	
	
	public List<ItemBean> getSaleListAll()
	{
		List<ItemBean>list=new ArrayList<>();
		try
		{
			String sql="select items.itemid,items.itemname,items.itemprice,sum(saletable.salenumber) from saletable left join items on saletable.itemid=items.itemid group by itemid ";
			Class.forName("com.mysql.jdbc.Driver");// 加载具体驱动类
			connection = DriverManager.getConnection(URL, USERNAME, PWD);// 建立连接
			pstmt = connection.prepareStatement(sql);
			res=pstmt.executeQuery(sql);
			
			while (res.next())
			{
				ItemBean item =new ItemBean();
				item.setItemId(res.getString("items.itemid"));
				item.setItemName(res.getString("items.itemname"));
				item.setItemPrice(res.getDouble("items.itemprice"));
				item.setItemSale(res.getInt("sum(saletable.salenumber)"));
				double total=(double) Math.round(res.getDouble("items.itemprice")*res.getInt("sum(saletable.salenumber)")* 100) / 100;
				item.setTotalprice(total);
				list.add(item);
				
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
				return null	;
			}
		}

	}
	
	public List<ItemBean> getSaleListAll2()
	{
		List<ItemBean>list=new ArrayList<>();
		try
		{
			String sql="select items.itemid,items.itemname,items.itemprice,userid,buydate, buynumber from buytable left join items on buytable.itemid=items.itemid order by buydate desc";
			Class.forName("com.mysql.jdbc.Driver");// 加载具体驱动类 
			connection = DriverManager.getConnection(URL, USERNAME, PWD);// 建立连接
			pstmt = connection.prepareStatement(sql);
			res=pstmt.executeQuery(sql);
			
			while (res.next())
			{
				ItemBean item =new ItemBean();
				item.setItemId(res.getString("items.itemid"));
				item.setItemName(res.getString("items.itemname"));
				item.setItemPrice(res.getDouble("items.itemprice"));
				item.setBuyer(res.getString("userid"));
				item.setBuydate(res.getString("buydate"));
				item.setItemSale(res.getInt("buynumber"));
				list.add(item);
				
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
				return null	;
			}
		}

	}
	
	public void update(ItemBean item)
	{
		try
		{
			String sql="update  items  set itemname='"+item.getItemName()+"' ,itemprice='"+item.getItemPrice()+"',itemstock ='"+item.getItemStock()+"' where itemid='"+item.getItemId()+"'";
			Class.forName("com.mysql.jdbc.Driver");// 加载具体驱动类
			connection = DriverManager.getConnection(URL, USERNAME, PWD);// 建立连接
			pstmt = connection.prepareStatement(sql);
			
	
			pstmt.executeUpdate();
		}catch (ClassNotFoundException e) {
			e.printStackTrace();
			return ;

		} catch (SQLException e) {
			e.printStackTrace();
			return ;
		} catch (Exception e) {
			e.printStackTrace();
			return 	;
		}

		finally {

			try {
				if (pstmt != null)
					pstmt.close();
				if (connection != null)
					connection.close();
			} catch (SQLException e) {
				e.printStackTrace();
				return ;
			}
		}

	}
	
	public static int check(String itemId)
	{
		try
		{

			
			String sql="select count(*) from items where itemid ='"+itemId+"'";
			Class.forName("com.mysql.jdbc.Driver");// 加载具体驱动类
			connection = DriverManager.getConnection(URL, USERNAME, PWD);// 建立连接
			pstmt = connection.prepareStatement(sql);
			
			res=pstmt.executeQuery();
			int count=1;
			while(res.next())
			{
				
				count=res.getInt("count(*)");
				System.out.println("查询结果："+count);
				return count;
			}

				return count;
		}
		catch (SQLException e) {
			e.printStackTrace();
			return -1;
		} catch (Exception e) {
			e.printStackTrace();
			return 	-1;
		}

		finally {

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
