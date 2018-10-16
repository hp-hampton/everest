package com.hup.everest.notification.service.impl;

import com.hup.everest.notification.model.Mail;
import com.hup.everest.notification.service.MailService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Service;
import org.springframework.util.StopWatch;

import javax.mail.MessagingException;
import javax.mail.internet.MimeMessage;

/**
 * @author hupeng on 2018/10/16
 * @version 1.0
 * @Description
 */
@Service
public class MailServiceImpl implements MailService {

    @Autowired
    private JavaMailSender mailSender;

    public void sendMimeMail(Mail mail) throws MessagingException {
        StopWatch stopWatch = new StopWatch();
        stopWatch.start();
        MimeMessage mimeMessage = mailSender.createMimeMessage();
        MimeMessageHelper mimeMessageHelper = new MimeMessageHelper(mimeMessage, true);
        //mimeMessageHelper.setFrom(deliver);
        mimeMessageHelper.setTo(mail.getReceivers());
        mimeMessageHelper.setCc(mail.getCarbonCopys());
        //从数据库获取
        String subject = "";
        String text = "";
        mimeMessageHelper.setSubject(subject);
        mimeMessageHelper.setText(text, mail.getIsHtml());
    }
}
