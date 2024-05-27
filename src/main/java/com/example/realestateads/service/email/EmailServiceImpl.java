package com.example.realestateads.service.email;

import com.example.realestateads.service.dataServices.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;

import java.sql.Timestamp;

@Service
public class EmailServiceImpl implements EmailService{

    @Autowired
    private JavaMailSender emailSender;

    @Autowired
    private PasswordResetUtil passwordResetUtil;

    @Autowired
    private UserService userService;


    @Value("${mail.sender}")
    private String senderEmail;

    @Override
    public void sendSimpleMessage(String to, String subject, String text) {

        SimpleMailMessage message = new SimpleMailMessage();
        message.setFrom(senderEmail);
        message.setTo("tokarenko2003sasha@gmail.com");
        message.setSubject(subject);
        message.setText(text);
        emailSender.send(message);
    }

    private void sendActivationCodeToUser(String userEmail, String subject, String text) {

        String activationCode = passwordResetUtil.generateResetCode();
        userService.updateActivationCode(userEmail,activationCode, new Timestamp(System.currentTimeMillis()));
        sendSimpleMessage(userEmail,subject, text + activationCode);
    }

    public void sendActivationCodeForRestorePassword(String userEmail) {

        sendActivationCodeToUser(userEmail, "Відновлення паролю","Код відновлення паролю: ");
    }

    public void sendActivationCodeForVerifyEmail(String userEmail) {

        sendActivationCodeToUser(userEmail, "Підтвердження електронної пошти","Код підтвердження пошти: ");
    }
}
