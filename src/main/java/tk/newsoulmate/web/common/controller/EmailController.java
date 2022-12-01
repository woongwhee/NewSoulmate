package tk.newsoulmate.web.common.controller;

import tk.newsoulmate.domain.vo.Member;
import tk.newsoulmate.web.common.APIKeys;

import javax.mail.*;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import java.io.UnsupportedEncodingException;
import java.util.Date;
import java.util.Properties;
import java.util.Random;

public class EmailController {

    public int sendConfirmMail(String confirmCode,String email) {
        String subject="환승주인 인증번호 안내 입니다.";
        String content= "<h1>인증번호는 " + confirmCode + " 입니다.</h1>";
        int result = sendMail(subject,content,email);
        return result;
    }

    public int sendPasswordMail(String newPwd,String Email) {
        String subject="환승주인 비밀번호 안내 입니다.";
        String content="<h1>비밀번호는 " + newPwd + " 입니다.</h1>";
        int result = sendMail(subject,content,Email);
        return result;
    }

    public static String rannum() {
        Random rcode = new Random();
        StringBuilder rannum = new StringBuilder();
        for (int i = 0; i < 8; i++) {
            int rcd = rcode.nextInt(3);
            if (rcd == 0) {
                int randomNum = rcode.nextInt(10);
                rannum.append(randomNum);
            } else if (rcd == 1) {
                char randomChar = (char) (rcode.nextInt(26) + 65);
                rannum.append(randomChar);
            } else if (rcd == 2) {
                char randomChar = (char) (rcode.nextInt(26) + 97);
                rannum.append(randomChar);
            }
        }
        return rannum.toString();
    }

    /**
     * 전달받은 문자열에 따라 이메일을 전송하는 메소드
     * @param subject 제목
     * @param content 내용
     * @param email 이메일주소
     * @return
     */
    private int sendMail(String subject,String content,String email) {
        Properties prop = System.getProperties();
        prop.put("mail.smtp.host", "smtp.gmail.com");
        prop.put("mail.smtp.starttls.enable", true);
        prop.put("mail.smtp.auth", "true");
        prop.put("mail.smtp.port", 25); // 앱코드 비밀번호를 사용하기 때문에 port번호를 모바일로 해야 작동함
        prop.put("mail.smtp.ssl.protocols", "TLSv1.2");
        Session session = Session.getDefaultInstance(prop, new Authenticator() {
            @Override
            protected PasswordAuthentication getPasswordAuthentication() { return new PasswordAuthentication(APIKeys.emailId, APIKeys.emailPwd);}//구글계정}
        });

        MimeMessage msg = new MimeMessage(session);

        try {
            msg.setSentDate(new Date());
            msg.setFrom(new InternetAddress(APIKeys.emailId+"@gmail.com", APIKeys.emailId));
            InternetAddress to = new InternetAddress(email,"");
            /*InternetAddress to = new InternetAddress(email,userName);*/
            msg.setRecipient(Message.RecipientType.TO, to);
            msg.setSubject(subject,"UTF-8");
            msg.setContent(content,"text/html;charset=utf-8");
            Transport.send(msg);
            return 1;
        } catch (MessagingException e) {
            e.printStackTrace();
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }
        return 0;
    }
}
