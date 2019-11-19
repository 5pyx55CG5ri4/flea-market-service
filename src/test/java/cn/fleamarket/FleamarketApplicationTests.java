package cn.fleamarket;

import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.core.io.ClassPathResource;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSenderImpl;
import org.springframework.test.context.junit4.SpringRunner;

import javax.annotation.Resource;
import java.io.File;

@RunWith(SpringRunner.class)
@SpringBootTest
class FleamarketApplicationTests {

   JavaMailSenderImpl  javaMailSender = new JavaMailSenderImpl();

    @Test
    void contextLoads() {
    }

//    @Test
//    void email(){
//        javaMailSender.setHost("smtp.163.com");
//        javaMailSender.setUsername("zly_lyp82nlf@163.com");
//        javaMailSender.setPassword("Lyp82nlf");
//        javaMailSender.setPort(25);
//        SimpleMailMessage simpleMailMessage = new SimpleMailMessage();
//        simpleMailMessage.setTo("1559422960@qq.com");
//        simpleMailMessage.setFrom("zly_lyp82nlf@163.com");
//        simpleMailMessage.setSubject("测试");
//        simpleMailMessage.setText("测试");
//        javaMailSender.send(simpleMailMessage);
//        System.out.println("发送成功");
//    }
    @Test
    void getPath(){
        ClassPathResource resource = new ClassPathResource("");
        System.out.println(resource.getClassLoader().getResource(""));
    }
}
