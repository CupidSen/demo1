package com.example.demo.service;

import jakarta.mail.MessagingException;
import jakarta.mail.internet.MimeMessage;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Service;

import java.io.UnsupportedEncodingException;

/**
 * 邮箱发送类
 */
@Service
public class EmailService {
    @Autowired
    private JavaMailSender mailSender;

    public void sendEmail(String to, String subject, String text) {
        MimeMessage message = mailSender.createMimeMessage();
        //邮箱名不为qq号
        MimeMessageHelper helper = new MimeMessageHelper(message);
        try {
            helper.setFrom("2780736975@qq.com","社团管理");
            helper.setTo(to);
            helper.setCc("2780736975@qq.com");
            helper.setSubject(subject);
            helper.setText(text);
            mailSender.send(message);
        }catch (MessagingException | UnsupportedEncodingException e){
            e.printStackTrace();
        }
    }
}
