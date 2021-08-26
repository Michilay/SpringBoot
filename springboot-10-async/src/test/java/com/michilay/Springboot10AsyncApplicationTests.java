package com.michilay;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSenderImpl;
import org.springframework.mail.javamail.MimeMessageHelper;

import javax.mail.MessagingException;
import javax.mail.internet.MimeMessage;
import java.io.File;

@SpringBootTest
class Springboot10AsyncApplicationTests {
    @Autowired
    JavaMailSenderImpl mailSender;
    @Test
    void contextLoads() {
        SimpleMailMessage mailMessage = new SimpleMailMessage();
        mailMessage.setSubject("测试邮件主题名");
        mailMessage.setText("测试邮件文本内容");
        mailMessage.setTo("1912400157@qq.com");
        mailMessage.setFrom("1912400157@qq.com");
        mailSender.send(mailMessage);
    }
    @Test
    void contextLoads2() throws MessagingException {
        MimeMessage mimeMessage = mailSender.createMimeMessage();
        MimeMessageHelper helper = new MimeMessageHelper(mimeMessage,true);
        helper.setSubject("测试邮件主题2");
        helper.setText("<p style='color:red'>测试邮件文本内容</p>",true);
        helper.addAttachment("1.jpg",new File("C:\\Users\\Michilay\\Desktop\\1.jpg"));
        helper.addAttachment("2.jpg",new File("C:\\Users\\Michilay\\Desktop\\2.jpg"));
        helper.setTo("1912400157@qq.com");
        helper.setFrom("1912400157@qq.com");
        mailSender.send(mimeMessage);
    }

}
