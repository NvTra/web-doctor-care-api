package com.tranv.webdoctorcareapi.service;

import com.tranv.webdoctorcareapi.entity.ResetPassword;
import com.tranv.webdoctorcareapi.entity.Users;
import com.tranv.webdoctorcareapi.repository.ResetPasswordResponsitory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;

@Service
public class ResetPasswordServiceImpl implements ResetPasswordService {
    @Autowired
    private UsersService usersService;
    @Autowired
    private ResetPasswordResponsitory resetPasswordResponsitory;

    @Autowired
    public ResetPasswordServiceImpl(ResetPasswordResponsitory resetPasswordResponsitory) {
        this.resetPasswordResponsitory = resetPasswordResponsitory;
    }
    // Save the confirmation code
    @Override
    public void saveConfirmationCode(ResetPassword resetPassword) {
        resetPasswordResponsitory.save(resetPassword);
    }

    // Validate the confirmation code
    @Override
    public boolean validateConfirmationCode(String confirmationCode) {
        ResetPassword resetPassword = resetPasswordResponsitory.findByConfirmationCode(confirmationCode);
        return resetPassword.getConfirmationCode() != null && LocalDateTime.now().isBefore(resetPassword.getExpirationTime());
    }

    // Reset the user's password
    @Override
    public void resetPassword(String email, String newPassword) {
        Users user = usersService.findUserByEmail(email);
        user.setPassword(newPassword);
        usersService.saveUser(user);
    }

    // Delete the confirmation code
    @Override
    @Transactional
    public void deleteByConfirmationCode(String code) {
        resetPasswordResponsitory.deleteByConfirmationCode(code);
    }
}
