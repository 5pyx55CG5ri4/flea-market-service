package cn.fleamarket.config;


import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.mail.javamail.JavaMailSenderImpl;

import java.util.Properties;

/**
 * 邮箱配置
 */
@Configuration
public class EmailConfig {

    @Bean
    public JavaMailSenderImpl javaMailSender(){
        JavaMailSenderImpl javaMailSender = new JavaMailSenderImpl();
        javaMailSender.setHost("smtp.aliyun.com");
        javaMailSender.setUsername("fleamarket@aliyun.com");
        javaMailSender.setPassword("lyp82nlf");
        Properties properties =  new Properties();
        properties.setProperty("mail.smtp.socketFactory.class","javax.net.ssl.SSLSocketFactory");
        properties.setProperty("mail.smtp.socketFactory.port","465");
        properties.setProperty("mail.smtp.port","465");
        javaMailSender.setJavaMailProperties(properties);
        javaMailSender.setDefaultEncoding("UTF-8");
        return javaMailSender;
    }
}
