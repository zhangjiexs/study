package com.uplookina.bigdata;

import javax.mail.*;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import java.util.Properties;

public class SendEmail {

    public static void main(String[] args) throws Exception{

     Properties properties=new Properties();
        properties.put("mail.smtp.host", "smtp.163.com");
        properties.put("mail.smtp.auth", "true");
        //建立会话封装自己的用户名和密码
        Session session=Session.getDefaultInstance(properties, new Authenticator() {
            @Override
            protected PasswordAuthentication getPasswordAuthentication() {
                return new PasswordAuthentication("zjyahj1314@163.com", "Ahj@521521");
            }
        });
        //开启Debug模式,就可以显示邮件发送的详情
        session.setDebug(true);
        //创建消息对象
        MimeMessage msg=new MimeMessage(session);
        //给消息对象设置详细信息
        msg.setFrom(new InternetAddress("zjyahj1314@163.com"));
        //接收对象详细信息
        msg.addRecipient(Message.RecipientType.TO, new InternetAddress("534081444@qq.com"));
        //
        msg.setSubject("HelloWorld JavaMail");
        msg.setText("Welcome to JavaMail World!");
        //发送消息
        Transport.send(msg);
    }

}


