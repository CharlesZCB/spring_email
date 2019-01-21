package com.cn;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.mail.javamail.JavaMailSenderImpl;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.test.context.junit4.SpringRunner;

import javax.mail.MessagingException;
import javax.mail.internet.MimeMessage;
import java.io.File;
import java.util.Date;

@RunWith(SpringRunner.class)
@SpringBootTest
public class EmailTestApplicationTests {


    @Autowired
    JavaMailSenderImpl javaMailSender;

    @Test
    public void contextLoads() throws MessagingException {
        MimeMessage mimeMessage = javaMailSender.createMimeMessage();//创建复杂邮件
        MimeMessageHelper helper=new MimeMessageHelper(mimeMessage,true);//可以携带多文件
        helper.setText("<b style='color:red'>这是测试邮件，请勿回复..</b>",true);//文件正文
        helper.setSubject("测试邮件");//文件标题
        //携带的文件
        helper.addAttachment("file1",new File("C:\\Users\\ThinkPad\\Pictures\\Saved Pictures\\baby0.jpg"));
        helper.addAttachment("file2",new File("C:\\Users\\ThinkPad\\Pictures\\Saved Pictures\\baby1.jpg"));
        helper.addAttachment("file3",new File("C:\\Users\\ThinkPad\\Pictures\\Saved Pictures\\baby.jpg"));

        //此处的邮箱需要与配置文件中的username一致！！
        helper.setFrom("2285094526@qq.com");
        //setTo  可以接受数组或者单个邮箱地址
        String[] strings={"2365956483@qq.com"};
        helper.setTo(strings);

        helper.setSentDate(new Date());

        //将复杂邮件放在mailsend中发送
        javaMailSender.send(mimeMessage);
    }

}

