package com.tranv.webdoctorcareapi.service;

import javax.mail.MessagingException;
import java.io.File;

public interface MailService {
    void sendEmail(String to, String subject, String body);

    void sendPdfEmail(String fileName, String to, String subject, String body, byte[] pdfFile) throws MessagingException;
}
