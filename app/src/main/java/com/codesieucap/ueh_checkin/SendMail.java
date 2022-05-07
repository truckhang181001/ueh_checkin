package com.codesieucap.ueh_checkin;

import android.content.Context;
import android.widget.Toast;

import com.codesieucap.ueh_checkin.models.JoinerModel;

import java.util.Properties;

import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;

public class SendMail {
    private Context context;
    private JoinerModel joinerData;
    private String eventName, eventTime, eventDate;
    private final String USERNAME = "truckhang181001@gmail.com";
    private final String PASSWORD = "cpqoecauytbondlt";

    public SendMail(Context context,String eventName, String eventDate, String eventTime, JoinerModel joinerData) {
        this.context = context;
        this.eventDate = eventDate;
        this.eventName = eventName;
        this.eventTime = eventTime;
        this.joinerData = joinerData;
    }

    public void sendEmail(){
        String emailMesseage ="UEH Checkin xin chào " + joinerData.getJoinerName()+",\n"+
                "\nCám ơn bạn đã đăng ký tham gia chương trình "+ eventName+".\n"+
                "\nThời gian diễn ra: "+eventTime+" "+eventDate+".\n"+
                "\nVui lòng tải mã QR bên dưới để có thể Checkin tham dự sự kiện:"+
                "\n"+joinerData.getTicketCodeLink()+".\n"+
                "\nTrân trọng.";
        String emailReceiver = joinerData.getEmail();
        String emailSubject = "[UEH CHECKIN][VÉ THAM DỰ "+eventName+"]";

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
            message.setText(emailMesseage);
            Transport.send(message);
            Toast.makeText(context, "Gửi email thành công!", Toast.LENGTH_LONG).show();

        } catch (MessagingException e) {
            Toast.makeText(context, "Gửi email thất bại :(((", Toast.LENGTH_LONG).show();
            throw new RuntimeException(e);
        }
    }
}
