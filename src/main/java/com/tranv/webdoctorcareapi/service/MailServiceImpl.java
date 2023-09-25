package com.tranv.webdoctorcareapi.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.ByteArrayResource;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Service;

import javax.mail.MessagingException;
import javax.mail.internet.MimeMessage;
import java.io.File;

@Service
public class MailServiceImpl implements MailService {
    @Autowired
    private JavaMailSender javaMailSender;

    // Send an email
    @Override
    public void sendEmail(String to, String subject, String body) {
        SimpleMailMessage mailMessage = new SimpleMailMessage();
        mailMessage.setTo(to);
        mailMessage.setSubject(subject);
        mailMessage.setText(body);
        javaMailSender.send(mailMessage);
    }

    // Send an email with an attached PDF file
    @Override
    public void sendPdfEmail(String fileName, String to, String subject, String body, byte[] pdfFile) throws MessagingException {
        MimeMessage mimeMessage = javaMailSender.createMimeMessage();
        MimeMessageHelper helper = new MimeMessageHelper(mimeMessage, true);
        //set email detail
        helper.setTo(to);
        helper.setSubject(subject);
        helper.setText(body);
        //attach the PDF File
        helper.addAttachment(fileName, new ByteArrayResource(pdfFile));
        //send email
        javaMailSender.send(mimeMessage);

    }
}
