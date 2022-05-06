package com.codesieucap.ueh_checkin;

import java.util.Properties;

import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;

public class SendMail {
    private String emailReceiver,emailSubject,emailMessage;
    private final String USERNAME = "truckhang181001@gmail.com";
    private final String PASSWORD = "cpqoecauytbondlt";

    public SendMail(String emailReceiver, String emailSubject, String emailMessage) {
        this.emailReceiver = emailReceiver;
        this.emailSubject = emailSubject;
        this.emailMessage = emailMessage;
    }

    public void sendEmail(){
        Properties pros= new Properties();
        pros.put("mail.smtp.auth","true");
        pros.put("mail.smtp.starttls.enable","true");
        pros.put("mail.smtp.host","smtp.gmail.com");
        pros.put("mail.smtp.port","587");

        Session session = Session.getInstance(pros, new javax.mail.Authenticator(){
            @Override
            protected PasswordAuthentication getPasswordAuthentication() {
                return new PasswordAuthentication(USERNAME,PASSWORD);
            }
        });

        try {
            Message message = new MimeMessage(session);
            message.setFrom(new InternetAddress(USERNAME));
            message.setRecipient(Message.RecipientType.TO, new InternetAddress(emailReceiver));
            message.setSubject(emailSubject);
            message.setText("TESTING UEH CHECKIN");
            Transport.send(message);

        } catch (MessagingException e) {
            throw new RuntimeException(e);
        }
    }
}
