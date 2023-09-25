package com.tranv.webdoctorcareapi.controller;

import com.tranv.webdoctorcareapi.entity.ForgotPasswordRequest;
import com.tranv.webdoctorcareapi.entity.ResetPassword;
import com.tranv.webdoctorcareapi.service.MailService;
import com.tranv.webdoctorcareapi.service.ResetPasswordService;
import com.tranv.webdoctorcareapi.service.UsersService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.time.LocalDateTime;
import java.time.temporal.ChronoUnit;
import java.util.Random;

@RestController
public class ForgotpasswordController {
    @Autowired
    private UsersService usersService;

    @Autowired
    private MailService mailService;

    @Autowired
    private ResetPasswordService resetPasswordService;

    public ForgotpasswordController(MailService mailService, ResetPasswordService resetPasswordService) {
        this.mailService = mailService;
        this.resetPasswordService = resetPasswordService;
    }


    // add mapping for POST /forgotPassword
    @PostMapping("/api/forgotPassword")
    public ResponseEntity<String> forgotPassword(@RequestBody ForgotPasswordRequest request) {
        // Check if the email exists in the user repository
        if (!usersService.isEmailAlreadyExists(request.getEmail())) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Email không tồn tại");
        }
        try {
            // Generate a random confirmation token
            String confirmationToken = generateConfirmationToken();
            // Create a ResetPassword object with the email, confirmation code, and expiration time
            ResetPassword resetPassword = new ResetPassword();
            resetPassword.setEmail(request.getEmail());
            resetPassword.setConfirmationCode(confirmationToken);
            resetPassword.setExpirationTime(LocalDateTime.now().plus(15, ChronoUnit.MINUTES));
            // Save the confirmation code using resetPasswordService
            resetPasswordService.saveConfirmationCode(resetPassword);
            // Compose the email body and subject
            String emailBody = "Mã xác nhận khôi phục mật khẩu: " + confirmationToken + " , hết hạn sau 15 phút";
            String subject = "Mã xác minh khôi phục mật khẩu của bạn";
            // Send the confirmation email using mailService
            mailService.sendEmail(request.getEmail(), subject, emailBody);
            return ResponseEntity.ok("Gửi Email xác nhận thành công");
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Lỗi khi gửi email xác nhận");
        }

    }

    // Method to generate a random confirmation token
    private String generateConfirmationToken() {
        Random random = new Random();
        StringBuilder numberBuilder = new StringBuilder();
        // Generate a 6-digit random token
        for (int i = 0; i < 6; i++) {
            int digit = random.nextInt(10);
            numberBuilder.append(digit);
        }
        return numberBuilder.toString();
    }

    @PostMapping("/api/resetPassword")
    public ResponseEntity<String> resetPassWord(@RequestBody ResetPassword resetPassword) {
        try {
            // Validate the confirmation code
            if (!resetPasswordService.validateConfirmationCode(resetPassword.getConfirmationCode())) {
                return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Mã xác nhận đã hết hạn");
            }
            if (resetPassword.getConfirmationPassword().equals(resetPassword.getNewPassword())) {
                // Reset the password using resetPasswordService
                resetPasswordService.resetPassword(resetPassword.getEmail(), resetPassword.getNewPassword());
                // Delete the confirmation code from the database
                resetPasswordService.deleteByConfirmationCode(resetPassword.getConfirmationCode());
                // Return an OK response
                return ResponseEntity.ok("Thay đổi mật khẩu thành công");
            } else {
                return ResponseEntity.ok("Xác nhận mật khẩu không hợp lệ");
            }
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Mã xác nhận không hợp lệ");
        }
    }
}
