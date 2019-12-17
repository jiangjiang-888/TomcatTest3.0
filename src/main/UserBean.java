package main;

import java.util.HashMap;
import java.util.Map;

public class UserBean {
	private String userName;
	private String userPwd;
	private String userEmail;
	private String userPhone;
	private String userAddr;
	private String userSex;

	private Map<String ,String > errors=new HashMap<String,String>();

	public String getUserName() {
		return userName;
	}
	public void setUserName(String userName) {
		this.userName = userName;
	}
	public String getUserPwd() {
		return userPwd;
	}
	public void setUserPwd(String userPwd) {
		this.userPwd = userPwd;
	}
	public String getUserEmail() {
		return userEmail;
	}
	public void setUserEmail(String userEmail) {
		this.userEmail = userEmail;
	}
	public String getUserPhone() {
		return userPhone;
	}
	public void setUserPhone(String userPhone) {
		this.userPhone = userPhone;
	}
	public String getUserAddr() {
		return userAddr;
	}
	public void setUserAddr(String userAddr) {
		this.userAddr = userAddr;
	}
	public String getUserSex() {
		return userSex;
	}
	public void setUserSex(String userSex) {
		this.userSex = userSex;
	}
	public void setuserAddr(String userAddr)
	{
		this.userAddr=userAddr;
	}
	
	public boolean validate()
	{
		boolean flag=true;
		if(userName==null||userName.trim().equals(""))
		{
			errors.put("userName","请输入用户名.");
			System.out.println("UserBean:请输入用户名.");
			flag=false;
		}
		
		if(userPwd==null||userPwd.trim().equals(""))
		{
			errors.put("userPwd","请输入密码.");
			System.out.print("UserBean:"+userPwd+"请输入密码.");
			flag=false;
		}
		System.out.println("UserBean.validate:"+flag);
		return flag;
		
	}


}
