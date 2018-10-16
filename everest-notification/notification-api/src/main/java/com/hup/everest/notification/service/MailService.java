package com.hup.everest.notification.service;

import com.hup.everest.notification.model.Mail;

import javax.mail.MessagingException;

/**
 * @author hupeng on 2018/10/16
 * @version 1.0
 * @Description
 */
public interface MailService {

    void sendMimeMail(Mail mail) throws MessagingException;
}
