package com.yangfan.untils;

import com.yangfan.dao.UserCodeMapper;
import com.yangfan.dao.UserCodeMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Component;

import java.util.Random;

@Component
public class EmailUtils {
    @Value("${spring.mail.username}")
    private String MAIL_SENDER;
    @Autowired
    private JavaMailSender javaMailSender;
    @Autowired
    private UserCodeMapper userCodeMapper;

    public String sendEmail(String qqEmail){
        SimpleMailMessage simpleMailMessage = new SimpleMailMessage();
        String code=randomCode();
        System.out.println();
        simpleMailMessage.setFrom(MAIL_SENDER);       //发件人
        simpleMailMessage.setTo(qqEmail);             //收件人
        simpleMailMessage.setSubject("注册验证码");     //主题
        simpleMailMessage.setText(code);       //验证码

        try {
            javaMailSender.send(simpleMailMessage);
            return code;
        }catch (Exception e){
            return "";
        }
    }

    public static String randomCode(){
        Random random=new Random();
        StringBuffer sb=new StringBuffer();
        for(int i=0;i<6;i++){
            int c=random.nextInt(9);
            sb.append(c);
        }
        return sb.toString();
    }
}
