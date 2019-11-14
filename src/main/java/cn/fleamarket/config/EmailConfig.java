package cn.fleamarket.config;


import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.mail.javamail.JavaMailSenderImpl;

/**
 * 邮箱配置
 */
@Configuration
public class EmailConfig {

    @Bean
    public JavaMailSenderImpl javaMailSender(){
        JavaMailSenderImpl javaMailSender = new JavaMailSenderImpl();
        javaMailSender.setHost("smtp.163.com");
        javaMailSender.setUsername("zly_lyp82nlf@163.com");
        javaMailSender.setPassword("Lyp82nlf");
        javaMailSender.setDefaultEncoding("UTF-8");
        return javaMailSender;
    }
}
