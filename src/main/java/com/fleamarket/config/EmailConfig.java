package com.fleamarket.config;


import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.mail.javamail.JavaMailSenderImpl;

import java.util.Properties;

/**
 * 邮箱配置
 */
@Configuration
@ConfigurationProperties(prefix = "email-config")
@Data
public class EmailConfig {

    private String host;

    private String username;

    private String password;

    private static final Properties PROPERTIES = new Properties();

    static {
        PROPERTIES.setProperty("mail.smtp.socketFactory.class", "javax.net.ssl.SSLSocketFactory");
        PROPERTIES.setProperty("mail.smtp.socketFactory.port", "465");
        PROPERTIES.setProperty("mail.smtp.port", "465");
    }

    @Bean
    public JavaMailSenderImpl javaMailSender() {
        JavaMailSenderImpl javaMailSender = new JavaMailSenderImpl();
        javaMailSender.setHost(host);
        javaMailSender.setUsername(username);
        javaMailSender.setPassword(password);
        javaMailSender.setJavaMailProperties(PROPERTIES);
        javaMailSender.setDefaultEncoding("UTF-8");
        return javaMailSender;
    }
}
