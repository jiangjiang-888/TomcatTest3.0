package main;

import java.util.Map;
import java.util.HashMap;

public class RegisterFormBean {
	private String userName;
	private String userPwd;
	private String userPwd2;
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
	public String getUserPwd2() {
		return userPwd2;
	}
	public void setUserPwd2(String userPwd2) {
		this.userPwd2 = userPwd2;
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
	public void setErrors(Map<String, String> errors) {
		this.errors = errors;
	}
	
	public boolean validateForModify()
	{
		boolean flag=true;
		if(userEmail==null||userEmail.trim().equals(""))
		{
			errors.put("userEmail","请输入邮箱.");
			System.out.print("请输入邮箱.");
			flag=false;
		}else if(!userEmail.matches("[a-zA-Z0-9_-]+@[a-zA-Z0-9_-]+(\\.[a-zA-Z0-9_-]+)+")){
			errors.put("userEmail","邮箱格式错误.");
			System.out.print("邮箱格式错误..");
			flag=false;
		}
		if(userPhone==null||userPhone.trim().equals(""))
		{
			errors.put("userPhone","请输入手机号.");
			System.out.print(userPhone+"请输入手机号.");
			flag=false;
		}else if(userPhone.length()!=11)
		{
			errors.put("userPhone","请输入11位手机号.");
			System.out.print("请输入11位手机号");
			flag=false;
		}
		
		if(userAddr==null||userAddr.trim().equals(""))
		{
			errors.put("userAddr","请输入邮寄地址.");
			System.out.print("请输入邮寄地址");
			flag=false;
		}
		return flag;
	}
	public boolean validate()
	{
		boolean flag=true;
		if(userName==null||userName.trim().equals(""))
		{
			errors.put("userName","请输入用户名.");
			System.out.print("请输入用户名.");
			flag=false;
		}
		if(userPwd==null||userPwd.trim().equals(""))
		{
			errors.put("userPwd","请输入密码.");
			System.out.print(userPwd+"请输入密码.");
			flag=false;
		}else if(userPwd.length()<6||userPwd.length()>12)
		{
			errors.put("userPwd","请输入6-12位字符.");
			System.out.print("请输入6-12位字符.");
			flag=false;
		}
		
		if(userPwd!=null&&!userPwd.equals(userPwd2))
		{
			errors.put("UserPwd2", "两次输入密码不匹配");
			System.out.print("两次输入密码不匹配.");
			flag=false;
		}
		if(userEmail==null||userEmail.trim().equals(""))
		{
			errors.put("userEmail","请输入邮箱.");
			System.out.print("请输入邮箱.");
			flag=false;
		}else if(!userEmail.matches("[a-zA-Z0-9_-]+@[a-zA-Z0-9_-]+(\\.[a-zA-Z0-9_-]+)+")){
			errors.put("userEmail","邮箱格式错误.");
			System.out.print("邮箱格式错误..");
			flag=false;
		}
		if(userPhone==null||userPhone.trim().equals(""))
		{
			errors.put("userPhone","请输入手机号.");
			System.out.print(userPhone+"请输入手机号.");
			flag=false;
		}else if(userPhone.length()!=11)
		{
			errors.put("userPhone","请输入11位手机号.");
			System.out.print("请输入11位手机号");
			flag=false;
		}
		
		if(userAddr==null||userAddr.trim().equals(""))
		{
			errors.put("userAddr","请输入邮寄地址.");
			System.out.print("请输入邮寄地址");
			flag=false;
		}
		if(userSex==null)
		{
			errors.put("userSex","请输入性别.");
			System.out.print("请输入性别.");
			flag=false;
		}
		
		return flag;
	}
	
	public void setErrorMag(String err,String errMeg)
	{
		
		if((err!=null)&&(errMeg!=null))
		{
			errors.put(err,errMeg);
		}
	}
	
	public Map<String ,String >getErrors(){
		return errors;
	}

}
