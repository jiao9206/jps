package com.stu.jps.mail;

import java.io.FileOutputStream;
import java.util.Properties;

import javax.activation.DataHandler;
import javax.activation.FileDataSource;
import javax.mail.Address;
import javax.mail.Message;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeBodyPart;
import javax.mail.internet.MimeMessage;
import javax.mail.internet.MimeMultipart;

/**
 * 测试发送邮件的类
 * 需要POM里面引入的jar包
 * @author JiaoPeng
 *
 */
public class TestMail {

	public static void main(String[] args) {
		try {
			Properties props=new Properties();
			props.setProperty("mail.host", "smtp.qq.com");
			props.setProperty("mail.transport.protocol", "smtp");
			props.setProperty("mail.smtp.auth", "true");
			props.setProperty("mail.password", "wixkfqeagwgobdjc");
			//使用javaMail发送邮件
			//1、创建Session
			Session session=Session.getInstance(props);
			session.setDebug(true);
			//2、通过Session获取transport对象
			Transport ts=session.getTransport();
			//3、设置用户名和密码（授权码）
			ts.connect("smtp.qq.com", "931642903@qq.com", "wixkfqeagwgobdjc");
			//4、创建邮件
			Message message=createAttachMail(session);
			//5、发送邮件
			ts.sendMessage(message,message.getAllRecipients());
			ts.close();
		}catch(Exception e) {
			e.printStackTrace();
		}
	}
	
	/**
	 * 创建纯文本简单邮件
	 * @param session
	 * @return
	 * @throws Exception
	 */
	public static MimeMessage createSimpleMail(Session session) throws Exception {
		//1、创建邮件对象
		MimeMessage message = new MimeMessage(session);
		//2、指明发件人
		message.setFrom(new InternetAddress("931642903@qq.com"));
		//3、指明收件人
		message.setRecipient(Message.RecipientType.TO, new InternetAddress("931642903@qq.com"));
		//4、邮件标题
		message.setSubject("只包含文本的简单邮件");
		//5、邮件内容
		message.setContent("你好啊！", "text/html;charset=UTF-8");
		return message;
	}
	
	/**
	 * 创建带有图片信息的邮件
	 * @param session
	 * @return
	 * @throws Exception
	 */
	public static MimeMessage createImageMail(Session session) throws Exception {
		MimeMessage message = new MimeMessage(session);
		//1、基本信息
		message.setFrom(new InternetAddress("931642903@qq.com"));
		message.setRecipient(Message.RecipientType.TO, new InternetAddress("931642903@qq.com"));
		message.setSubject("含有图片的邮件");
		//2、邮件正文
		MimeBodyPart text=new MimeBodyPart();
		text.setContent("这是一封邮件正文带图片<img src='cid:mail.jpg'>的邮件,还有一张图片<img src='cid:mail1.jpg'>","text/html;charset=UTF-8");
		//3、图片数据
		//mail.jpg
		MimeBodyPart image=new MimeBodyPart();
		DataHandler dh = new DataHandler(new FileDataSource("D:\\mail.jpg"));
		image.setDataHandler(dh);
		image.setContentID("mail.jpg");
		//mail1.jpg
		MimeBodyPart image1=new MimeBodyPart();
		DataHandler dh1 = new DataHandler(new FileDataSource("D:\\mail1.jpg"));
		image1.setDataHandler(dh1);
		image1.setContentID("mail1.jpg");
		//4、描述数据关系
		MimeMultipart mm = new MimeMultipart();
		mm.addBodyPart(text);
		mm.addBodyPart(image);
		mm.addBodyPart(image1);
		mm.setSubType("related");
		
		message.setContent(mm);
		message.saveChanges();
		//5、将邮件保存在本地
		message.writeTo(new FileOutputStream("D:\\ImageMail.eml"));
		return message;
	}
	
	/**
	 * 创建带有附件的邮件
	 * @param session
	 * @return
	 * @throws Exception
	 */
	public static MimeMessage createAttachMail(Session session) throws Exception{
		MimeMessage message = new MimeMessage(session);
		//1、基本信息
		message.setFrom(new InternetAddress("931642903@qq.com"));
//		message.setRecipient(Message.RecipientType.TO, new InternetAddress("931642903@qq.com"));
		message.setRecipients(Message.RecipientType.TO, new Address[] {new InternetAddress("931642903@qq.com"),new InternetAddress("17605413833@163.com")});
		message.setSubject("含有附件的邮件");
		//2、正文
		MimeBodyPart text = new MimeBodyPart();
		text.setContent("使用JavaMail创建的带附件的邮件", "text/html;charset=UTF-8");
		
		//3、附件
		MimeBodyPart attach = new MimeBodyPart();
		DataHandler dh = new DataHandler(new FileDataSource("D:\\1.xlsx"));
		attach.setDataHandler(dh);
		attach.setFileName(dh.getName());
		
		//4、描述数据关系
		MimeMultipart mp = new MimeMultipart();
		mp.addBodyPart(text);
		mp.addBodyPart(attach);
		mp.setSubType("mixed");
		message.setContent(mp);
		message.saveChanges();
		
		//5、保存本地
		message.writeTo(new FileOutputStream("D:\\attachMail.eml"));
		return message;
	}
}
