package main;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import java.util.Date;
import java.util.Properties;

public class SendEmail  {
	
    public static String myEmailAccount = "1254587226@qq.com";
    public static String myEmailPassword ="xtmkkahmkubyijjg";
    public static String myEmailSMTPHost = "smtp.qq.com";
    static Transport transport=null;
  //  public static String receiveMailAccount = "2334344529@qq.com";
    public static void sendEmails(String userName,String receiveMailAccount) {

    	try {
        Properties props = new Properties();                    // 参数配置
        props.setProperty("mail.transport.protocol", "smtp");   // 使用的协议（JavaMail规范要求）
        props.setProperty("mail.smtp.host", myEmailSMTPHost);   // 发件人的邮箱的 SMTP 服务器地址
        props.setProperty("mail.smtp.auth", "true");            // 需要请求认证
        props.put("mail.smtp.socketFactory.class", "javax.net.ssl.SSLSocketFactory");
        props.setProperty("mail.smtp.port", "465");
        Session session = Session.getDefaultInstance(props);
       // session.setDebug(true);   
        MimeMessage message = createMimeMessage(session, userName,myEmailAccount, receiveMailAccount);
        transport = session.getTransport();
        transport.connect(myEmailAccount, myEmailPassword);
        transport.sendMessage(message, message.getAllRecipients());
        }catch (ClassNotFoundException e) {
			e.printStackTrace();
			return ;

		}  catch (Exception e) {
			e.printStackTrace();
			return ;
		}
        finally {
        try
        {
        	if(transport!=null)
        		transport.close();
        	}catch (Exception e) {
			e.printStackTrace();
			return ;
        	}
        }


    }
    public static MimeMessage createMimeMessage(Session session, String userName,String sendMail, String receiveMail) throws Exception {
        MimeMessage message = new MimeMessage(session);
        message.setFrom(new InternetAddress(sendMail, "购物网站", "UTF-8"));
        message.setRecipient(MimeMessage.RecipientType.TO, new InternetAddress(receiveMail, "尊敬的客户", "UTF-8"));
        message.setSubject("已发货", "UTF-8");
        message.setContent("尊敬的"+userName+"用户你好, 您的订单已准备发货，<a href='http://101.37.13.59:8080/TomcatTest/CartBeanListServlet?act=orderForm'>点击查看</a>", "text/html;charset=UTF-8");
        message.setSentDate(new Date());
        message.saveChanges();
        return message;
    }

}