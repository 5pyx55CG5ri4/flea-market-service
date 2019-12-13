package cn.fleamarket.utils;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSenderImpl;
import org.springframework.stereotype.Component;

import java.text.ParseException;
import java.text.SimpleDateFormat;

/**
 * 邮箱工具
 */
@Component
public class EmailUtil {
    @Autowired
    JavaMailSenderImpl javaMailSender;

    public boolean getCode(String toEmail, String code, int sta) {
        SimpleMailMessage simpleMailMessage = new SimpleMailMessage();
        simpleMailMessage.setTo(toEmail);
        simpleMailMessage.setFrom("fleamarket@aliyun.com");
        if(sta==0) {
            simpleMailMessage.setSubject("欢迎注册FleaMarket官网,验证码如下");
        } else if(sta==1){
            simpleMailMessage.setSubject("您正在FleaMarket上找回密码,验证码如下");
        }
        simpleMailMessage.setText("验证码为" + code + "\nFleaMarket:" + code + "是您本次身份验证码,30分种内有效\nFleaMarket工作人员绝不会向您索取此验证码，切勿告知他人。【FleaMarket】");
        boolean ret = false;
        try {
            javaMailSender.send(simpleMailMessage);
            System.out.println("发送成功");
            ret = true;
        } catch (Exception e) {
            ret = false;
            e.printStackTrace();
        }
        return ret;
    }

    /**
     * 邮箱验证码时间差
     *
     * @param newData
     * @param codeData
     * @return
     * @throws ParseException
     */
    public boolean getTimeCJ(String newData, String codeData) throws ParseException {
        SimpleDateFormat simpleFormat = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss");
        long from = simpleFormat.parse(codeData).getTime();
        long to = simpleFormat.parse(newData).getTime();
        int minutes = (int) ((to - from) / (1000 * 60));
        System.out.println(minutes);
        return minutes < 1;
    }

}
