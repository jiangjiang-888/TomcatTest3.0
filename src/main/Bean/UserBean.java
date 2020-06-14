package main.Bean;

import java.util.HashMap;
import java.util.Map;

public class UserBean {
    private String userName;            //账号
    private String userPwd;             //密码
    private String userEmail;           //邮箱
    private String userPhone;           //电话
    private String userAddr;            //地址
    private String userSex;             //性别
    private String ItemClass;           //销售员销售类别
    private String salemoney;           //销售业绩
    private String ip;                  //登录ip
    private String loginIn;             //登陆时间
    private String loginOut;            //登出时间
    private String userType;            //使用者类别
    private String beginTime;
    private String endTime;
    private String browseTime;           //浏览时间
    private String action;
    public String getAction() {
        return action;
    }

    public void setAction(String action) {
        this.action = action;
    }


    public String getBeginTime() {
        return beginTime;
    }

    public void setBeginTime(String beginTime) {
        this.beginTime = beginTime;
    }

    public String getEndTime() {
        return endTime;
    }

    public void setEndTime(String endTime) {
        this.endTime = endTime;
    }


    public String getBrowseTime() {
        return browseTime;
    }

    public void setBrowseTime(String browseTime) {
        this.browseTime = browseTime;
    }


    public String getUserType() {
        return userType;
    }

    public void setUserType(String userType) {
        this.userType = userType;
    }

    public String getSalemoney() {
        return salemoney;
    }

    public void setSalemoney(String salemoney) {
        this.salemoney = salemoney;
    }

    public String getIp() {
        return ip;
    }

    public void setIp(String ip) {
        this.ip = ip;
    }



    public String getLoginIn() {
        return loginIn;
    }

    public void setLoginIn(String loginIn) {
        this.loginIn = loginIn;
    }

    public String getLoginOut() {
        return loginOut;
    }

    public void setLoginOut(String loginOut) {
        this.loginOut = loginOut;
    }

    private Map<String, String> errors = new HashMap<String, String>();

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

    public void setItemClass(String itemClass){this.ItemClass=itemClass;}

    public String getItemClass(){return  this.ItemClass;}

    public boolean validate() {
        boolean flag = true;
        if (userName == null || userName.trim().equals("")) {
            errors.put("userName", "请输入用户名.");
//            System.out.println("UserBean:请输入用户名.");
            flag = false;
        }

        if (userPwd == null || userPwd.trim().equals("")) {
            errors.put("userPwd", "请输入密码.");
//            System.out.print("UserBean:" + userPwd + "请输入密码.");
            flag = false;
        }
//        System.out.println("UserBean.validate:" + flag);
        return flag;

    }


}
