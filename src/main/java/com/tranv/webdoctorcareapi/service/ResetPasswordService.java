package com.tranv.webdoctorcareapi.service;

import com.tranv.webdoctorcareapi.entity.ResetPassword;

public interface ResetPasswordService {
    void saveConfirmationCode(ResetPassword resetPassword);

    boolean validateConfirmationCode(String confirmationCode);

    void resetPassword(String email, String password);
    void deleteByConfirmationCode(String code);
}
