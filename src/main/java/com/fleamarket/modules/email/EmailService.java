package com.fleamarket.modules.email;


import cn.hutool.core.date.DateUnit;
import cn.hutool.core.util.RandomUtil;
import com.fleamarket.common.cache.FleaMarketCache;
import com.fleamarket.common.constants.Constants;
import com.fleamarket.common.utils.ThreadTask;
import com.fleamarket.config.EmailConfig;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSenderImpl;
import org.springframework.stereotype.Service;

/**
 * 电子邮件服务
 *
 * @author zhuliyou
 * @date 2023/04/28
 */
@Service
@Slf4j
public class EmailService {

    private static final String TEXT_TEMPLATE = "验证码为%s\nFleaMarket:%s是您本次身份验证码,5分钟内有效\nFleaMarket工作人员绝不会向您索取此验证码，切勿告知他人。【FleaMarket】";

    private static final String SUBJECT_TEMPLATE = "欢迎使用FleaMarket,您正在进行邮箱身份验证";

    private static final SimpleMailMessage MAIL_MESSAGE = new SimpleMailMessage();


    @Autowired
    private JavaMailSenderImpl javaMailSender;

    @Autowired
    private EmailConfig emailConfig;


    public void asyncSendEmailCode(String email) {
        String code = RandomUtil.randomNumbers(5);
        log.info("send to email [{}] code is [{}]", email, code);
        ThreadTask.getInstance().addTask(() -> {
            build(email, code);
            javaMailSender.send(MAIL_MESSAGE);
            FleaMarketCache.setCache(String.format(Constants.EMAIL, email), code, DateUnit.MINUTE.getMillis() * 5);
        });
    }

    private void build(String to, String code) {
        MAIL_MESSAGE.setFrom(emailConfig.getUsername());
        MAIL_MESSAGE.setTo(to);
        MAIL_MESSAGE.setSubject(SUBJECT_TEMPLATE);
        MAIL_MESSAGE.setText(String.format(TEXT_TEMPLATE, code, code));
    }

}
