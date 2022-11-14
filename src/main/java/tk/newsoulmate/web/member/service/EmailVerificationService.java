package tk.newsoulmate.web.member.service;

import javax.mail.*;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import java.io.UnsupportedEncodingException;
import java.util.Date;
import java.util.Properties;
import java.util.Random;

public class EmailVerificationService {

    public static String sendMail(String email) {
        System.out.println(email);
        boolean result = false;

        Random rcode = new Random();
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < 6; i++) {

            int rcd = rcode.nextInt(3);
            System.out.println(rcd);
            if (rcd == 0) {
                int randomNum = rcode.nextInt(10);
                sb.append(randomNum);

            } else if (rcd == 1) {
                char randomChar = (char) (rcode.nextInt(26) + 65);
                sb.append(randomChar);
            } else if (rcd == 2) {
                char randomChar = (char) (rcode.nextInt(26) + 97);
                sb.append(randomChar);
            }
        }

        Properties prop = System.getProperties();

        prop.put("mail.smtp.host", "smtp.gmail.com");
        prop.put("mail.smtp.starttls.enable", true);
        prop.put("mail.smtp.auth", "true");
        prop.put("mail.smtp.port", 25);
        prop.put("mail.smtp.ssl.protocols", "TLSv1.2");



        Session session = Session.getDefaultInstance(prop, new Authenticator() {
            protected PasswordAuthentication getPasswordAuthentication() {
                PasswordAuthentication pw = new PasswordAuthentication("newsoulmatemaster", "ejcptolhwgpmvytc");
                // 구글 계정 : newsoulmatemaster@gmail.com 비밀번호 : newsoulmatemaster123 앱코드 비밀번호 : ejcptolhwgpmvytc
                return pw;
            }
        });

        MimeMessage msg = new MimeMessage(session);

        try {

            msg.setSentDate(new Date());
            msg.setFrom(new InternetAddress("newsoulmatemaster@gmail.com", "newsoulmatemaster"));
            System.out.print(email);
            InternetAddress to = new InternetAddress(email,"");
            /*InternetAddress to = new InternetAddress(email,userName);*/
            msg.setRecipient(Message.RecipientType.TO, to);

            msg.setSubject("환승주인 회원가입 인증번호 안내 입니다.","UTF-8");

            msg.setContent(
                    "<h1>인증번호는 " + sb.toString() + " 입니다.</h1>","text/html;charset=utf-8");

            Transport.send(msg);
            result = true;

        } catch (MessagingException e) {
            e.printStackTrace();
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }

        if (result) {
            return sb.toString();
        } else {
            return null;
        }

    }

}
